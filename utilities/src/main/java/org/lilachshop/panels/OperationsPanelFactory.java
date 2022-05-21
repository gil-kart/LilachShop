package org.lilachshop.panels;

public class OperationsPanelFactory {
    private OperationsPanelFactory() {
    }

    public static Panel createPanel(int panel_num, Object controller) {    // for now int, later going to be enum!
        switch (panel_num) {
            case 1 -> {
                return new ExamplePanel("localhost", 3000, controller);
            }
            case 2 ->{
                return new CustomerAnonymousPanel("localhost", 3000, controller);
            }
            default -> {
                System.out.println("No panel found.");
                return null;
            }
        }
    }
}
