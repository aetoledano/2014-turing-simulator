/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ACTION.TM;

import CONTROLLER.GC;
import CONTROLLER.TM.tmController;
import LOGIC.TM.State;
import LOGIC.TM.tmNetWork;
import SUPPORT.config;
import java.awt.Point;

/**
 *
 * @author docencia
 */
public class AddNodeAction extends ACTION.Action {

    Point p;
    int number = -1, kind = -1;

    public AddNodeAction(Point loc, int number) {
        super("Add Node Number " + number);
        p = loc;
        this.number = number;
    }

    public AddNodeAction(int kind, Point loc, int number) {
        super("Add Node Type " + kind + " Number " + number);
        p = loc;
        this.number = number;
        this.kind = kind;
    }

    @Override
    public void undo(GC gc) {
        ((tmController) gc).getNet().delState(number);
        gc.getVisualSystem().DelNode(number);
        gc.fireAnalyzerChange();
    }

    @Override
    public void redo(GC gc) {
        if (kind == -1) {
            tmNetWork net = ((tmController) gc).getNet();
            State ist;
            if (net.states.isEmpty()) {
                ist = new State(0, 0);
            } else {
                ist = new State(net.states.size(), 1);
            }
            if (net.addState(ist)) {
                gc.getVisualSystem().AddNode(p);
            } else {
                gc.getNc().quickReport(config.getText("msg.node.not.added"));
            }
        } else {
            tmNetWork net = ((tmController) gc).getNet();
            if (net.states.isEmpty()) {
                State ist = new State(net.states.size(), kind);
                if (kind == 0 || kind == 3) {
                    if (net.addState(ist)) {
                        gc.getVisualSystem().AddNode(kind, p);
                    } else {
                        gc.getNc().quickReport(config.getText("msg.node.not.added"));
                    }
                }
            } else {
                State ist = new State(net.states.size(), kind);
                if (kind == 1 || kind == 2) {
                    if (net.addState(ist)) {
                        gc.getVisualSystem().AddNode(kind, p);
                    } else {
                        gc.getNc().quickReport(config.getText("msg.node.not.added"));
                    }
                }
            }
        }
        gc.fireAnalyzerChange();
    }

}
