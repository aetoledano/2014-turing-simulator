/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ACTION;

import CONTROLLER.GC;
import java.awt.Point;

/**
 *
 * @author docencia
 */
public class MoveAction extends Action {

    int node;
    Point initial, last;

    public MoveAction(int node, Point ipos, Point npos) {
        super("Node changed position");
        this.node = node;
        initial = ipos;
        last = npos;
    }

    @Override
    public void undo(GC gc) {
        gc.getVisualSystem().getNodes()[node].setLocation(initial);
    }

    @Override
    public void redo(GC gc) {
        gc.getVisualSystem().getNodes()[node].setLocation(last);
    }

}
