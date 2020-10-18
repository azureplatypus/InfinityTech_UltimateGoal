/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : FilterDevStateMachine.sm
 */

package org.firstinspires.ftc.teamcode.autonomous;


public class FilterDevStateMachineContext
    extends statemap.FSMContext
{
//---------------------------------------------------------------
// Member methods.
//

    public FilterDevStateMachineContext(AutonomousController owner)
    {
        this (owner, FilterDevStateMachine.Idle);
    }

    public FilterDevStateMachineContext(AutonomousController owner, AutonomousControllerState initState)
    {
        super (initState);

        _owner = owner;
    }

    @Override
    public void enterStartState()
    {
        getState().entry(this);
        return;
    }

    public void evDriveComplete()
    {
        _transition = "evDriveComplete";
        getState().evDriveComplete(this);
        _transition = "";
        return;
    }

    public void evStartDoSquare()
    {
        _transition = "evStartDoSquare";
        getState().evStartDoSquare(this);
        _transition = "";
        return;
    }

    public AutonomousControllerState getState()
        throws statemap.StateUndefinedException
    {
        if (_state == null)
        {
            throw(
                new statemap.StateUndefinedException());
        }

        return ((AutonomousControllerState) _state);
    }

    protected AutonomousController getOwner()
    {
        return (_owner);
    }

    public void setOwner(AutonomousController owner)
    {
        if (owner == null)
        {
            throw (
                new NullPointerException(
                    "null owner"));
        }
        else
        {
            _owner = owner;
        }

        return;
    }

//---------------------------------------------------------------
// Member data.
//

    transient private AutonomousController _owner;

    //-----------------------------------------------------------
    // Constants.
    //

    private static final long serialVersionUID = 1L;

//---------------------------------------------------------------
// Inner classes.
//

    public static abstract class AutonomousControllerState
        extends statemap.State
    {
    //-----------------------------------------------------------
    // Member methods.
    //

        protected AutonomousControllerState(String name, int id)
        {
            super (name, id);
        }

        protected void entry(FilterDevStateMachineContext context) {}
        protected void exit(FilterDevStateMachineContext context) {}

        protected void evDriveComplete(FilterDevStateMachineContext context)
        {
            Default(context);
        }

        protected void evStartDoSquare(FilterDevStateMachineContext context)
        {
            Default(context);
        }

        protected void Default(FilterDevStateMachineContext context)
        {
            throw (
                new statemap.TransitionUndefinedException(
                    "State: " +
                    context.getState().getName() +
                    ", Transition: " +
                    context.getTransition()));
        }

    //-----------------------------------------------------------
    // Member data.
    //

        //-------------------------------------------------------
    // Constants.
    //

        private static final long serialVersionUID = 1L;
    }

    /* package */ static abstract class FilterDevStateMachine
    {
    //-----------------------------------------------------------
    // Member methods.
    //

    //-----------------------------------------------------------
    // Member data.
    //

        //-------------------------------------------------------
        // Constants.
        //

        public static final FilterDevStateMachine_Idle Idle =
            new FilterDevStateMachine_Idle("FilterDevStateMachine.Idle", 0);
    }

    protected static class FilterDevStateMachine_Default
        extends AutonomousControllerState
    {
    //-----------------------------------------------------------
    // Member methods.
    //

        protected FilterDevStateMachine_Default(String name, int id)
        {
            super (name, id);
        }

    //-----------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    private static final class FilterDevStateMachine_Idle
        extends FilterDevStateMachine_Default
    {
    //-------------------------------------------------------
    // Member methods.
    //

        private FilterDevStateMachine_Idle(String name, int id)
        {
            super (name, id);
        }

        @Override
        protected void evStartDoSquare(FilterDevStateMachineContext context)
        {

            (context.getState()).exit(context);
            context.setState(DoSquare.Start);
            (context.getState()).entry(context);
            return;
        }

    //-------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    /* package */ static abstract class DoSquare
    {
    //-----------------------------------------------------------
    // Member methods.
    //

    //-----------------------------------------------------------
    // Member data.
    //

        //-------------------------------------------------------
        // Constants.
        //

        public static final DoSquare_Start Start =
            new DoSquare_Start("DoSquare.Start", 1);
        public static final DoSquare_FirstTurn FirstTurn =
            new DoSquare_FirstTurn("DoSquare.FirstTurn", 2);
        public static final DoSquare_SecondSideDrive SecondSideDrive =
            new DoSquare_SecondSideDrive("DoSquare.SecondSideDrive", 3);
        public static final DoSquare_Complete Complete =
            new DoSquare_Complete("DoSquare.Complete", 4);
    }

    protected static class DoSquare_Default
        extends AutonomousControllerState
    {
    //-----------------------------------------------------------
    // Member methods.
    //

        protected DoSquare_Default(String name, int id)
        {
            super (name, id);
        }

    //-----------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    private static final class DoSquare_Start
        extends DoSquare_Default
    {
    //-------------------------------------------------------
    // Member methods.
    //

        private DoSquare_Start(String name, int id)
        {
            super (name, id);
        }

        @Override
        protected void evDriveComplete(FilterDevStateMachineContext context)
        {

            (context.getState()).exit(context);
            context.setState(DoSquare.FirstTurn);
            (context.getState()).entry(context);
            return;
        }

    //-------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    private static final class DoSquare_FirstTurn
        extends DoSquare_Default
    {
    //-------------------------------------------------------
    // Member methods.
    //

        private DoSquare_FirstTurn(String name, int id)
        {
            super (name, id);
        }

        @Override
        protected void evDriveComplete(FilterDevStateMachineContext context)
        {

            (context.getState()).exit(context);
            context.setState(DoSquare.SecondSideDrive);
            (context.getState()).entry(context);
            return;
        }

    //-------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    private static final class DoSquare_SecondSideDrive
        extends DoSquare_Default
    {
    //-------------------------------------------------------
    // Member methods.
    //

        private DoSquare_SecondSideDrive(String name, int id)
        {
            super (name, id);
        }

        @Override
        protected void evDriveComplete(FilterDevStateMachineContext context)
        {

            return;
        }

    //-------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }

    private static final class DoSquare_Complete
        extends DoSquare_Default
    {
    //-------------------------------------------------------
    // Member methods.
    //

        private DoSquare_Complete(String name, int id)
        {
            super (name, id);
        }

        @Override
        protected void entry(FilterDevStateMachineContext context)
            {
                AutonomousController ctxt = context.getOwner();

            ctxt.stop();
            return;
        }

    //-------------------------------------------------------
    // Member data.
    //

        //---------------------------------------------------
        // Constants.
        //

        private static final long serialVersionUID = 1L;
    }
}

/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */
