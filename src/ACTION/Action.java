package ACTION;

import CONTROLLER.GC;

/**
 *
 * @author aetoledano
 */
public abstract class Action {

    protected String action_name;

    public Action(String action_name) {
        this.action_name = action_name;
    }

    public abstract void undo(GC gc);

    public abstract void redo(GC gc);

    @Override
    public String toString() {
        return action_name;
    }

}
