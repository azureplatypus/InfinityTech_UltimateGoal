package org.firstinspires.ftc.teamcode.ringdetect;
import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test class to train the ring neural network.
 */
public class RingNeuralNetworkTrainer {

    RingDataProcessor mDataProcessor = new RingDataProcessor();

    /**
     * Reads data into the pre-processor for both training and testing.
     */
    public void readData(File trainingDataFile,double testFraction,boolean normalize){
        mDataProcessor.processData(trainingDataFile,testFraction,normalize);
    }
    /**
     * Called to train the network
     */
    public void
    trainNetwork(File trainingDataFile,File networkFile,File logFile){
        readData(trainingDataFile,0.10,true);
        // Now create and pass training data to neural network
        JavaNeuralNetwork ringnn = new JavaNeuralNetwork(new int[]{13,15,3});

        final StringBuffer logBuffer = new StringBuffer();
        logBuffer.append("Epoch#,Normal Error\n");

        ringnn.addTrainingStatusListener(new JavaNeuralNetwork.ITrainingStatusListener() {
            @Override
            public void trainingStatus(int epochNumber, double normalError) {
                logBuffer.append(epochNumber+","+ normalError +"\n");
            }
        });
        ringnn.train(mDataProcessor.getXTrainingData(), mDataProcessor.getYTrainingData(),
                mDataProcessor.getScaleFactors(), 10,0.1d,5000,false);
         // Write out the network
        try {
            if (networkFile.exists()){
                networkFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(networkFile);
            ringnn.serializeNetwork(fos);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // Save the training log
        try{
            if (logFile.exists()){
                logFile.delete();
            }
            FileWriter fw = new FileWriter(logFile);
            fw.write(logBuffer.toString());
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void testNetwork(File trainingDataFile,File neuralNetworkFile,File logFile,double testFraction){
       // Read the network
        RingDetectorNeuralNetwork ringnn=null;
        try {
            FileInputStream fis = new FileInputStream(neuralNetworkFile);
            ringnn = new RingDetectorNeuralNetwork(fis);
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }

        // Init the log buffer
        final StringBuffer logBuffer = new StringBuffer();
        logBuffer.append("Data#,Truth,yTruth,Detection,yDetection\n");

        // Read the test data
        readData(trainingDataFile,testFraction,true);
        SimpleMatrix x = mDataProcessor.getXTestData();
        SimpleMatrix y = mDataProcessor.getYTestData();

        // And loop through the test data
        int pass = 0;
        int fail = 0;
        for(int column=0;column < x.numCols();column++){
            SimpleMatrix input = x.extractVector(false,column);
            SimpleMatrix ytruth = y.extractVector(false,column);

            SimpleMatrix output = ringnn.doTestInference(input);
            int truth = ringnn.decodeOutput(ytruth);
            int inference = ringnn.decodeOutput(output);

            if (truth != inference){
                fail++;
                logBuffer.append("Fail:");
            }
            else {
                pass++;
                logBuffer.append("Pass:");

            }

            String ytruths = MatrixUtils.printColumn(ytruth,0);
            String truths = ringnn.convertToString(truth);

            String yinference = MatrixUtils.printColumn(output,0);
            String inferences = ringnn.convertToString(inference);
            logBuffer.append(","+truths+","+ytruths+","+inferences+","+yinference+"\n");
        }
        double percent = 100d * (double)pass/(double)x.numCols();
        logBuffer.append("Summary\n");
        logBuffer.append(String.format("%2.1f",percent)+".  Pass="+pass+".  Fail="+fail+". of "+x.numCols()+" test samples.\n");
        // Save the test log
        try{
            if (logFile.exists()){
                logFile.delete();
            }
            FileWriter fw = new FileWriter(logFile);
            fw.write(logBuffer.toString());
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void main() {
        RingNeuralNetworkTrainer trainer = new RingNeuralNetworkTrainer();

        Path currentRelativePath = Paths.get("");
        String trainingDataPath = currentRelativePath.toAbsolutePath().toString() + "/src/test/java/org/firstinspires/ftc/teamcode/ringdetect";
        File trainingFile = new File(trainingDataPath, "06DEC20 initial training data.csv");
        String runtimeNetworkPath = currentRelativePath.toAbsolutePath().toString() + "/src/main/assets";
        File networkFile = new File(runtimeNetworkPath, "all_sensors_ring_neuralnetwork.bin");

        boolean train = true;
        if (train) {
            // Now save the network to the runtime assets directory
            File logFile = new File(trainingDataPath + "/training_log.csv");
            trainer.trainNetwork(trainingFile,networkFile,logFile);
        }
        boolean test = true;
        if (test){
            File logFile = new File(trainingDataPath + "/testing_log.csv");
            testNetwork(trainingFile,networkFile,logFile,0.10);
        }

    }
}