package org.lilachshop.panels;

import org.lilachshop.controller.Controller;

public class OperationsPanelFactory {
    private OperationsPanelFactory() {
    }

    public static Panel createPanel(int panel_num, Controller controller) {    // for now int, later going to be enum!
        switch (panel_num) {
            case 1 -> {
                return new ExamplePanel("localhost", 3000, controller);
            }
            default -> {
                System.out.println("No panel found.");
                return null;
            }
        }
    }
}
