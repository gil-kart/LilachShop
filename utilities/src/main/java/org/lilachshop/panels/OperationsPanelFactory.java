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
            case 3 ->{
                return new StoreCustomerPanel("localhost", 3000, controller);
            }
            case 4 ->{
                return new ChainCustomerPanel("localhost", 3000, controller);
            }
            case 5 ->{
                return new AnnualCustomerPanel("localhost", 3000, controller);
            }
            case 6 ->{
                return new EmployeeAnonymousPanel("localhost", 3000, controller);
            }
            case 7 ->{
                return new GeneralEmployeePanel("localhost", 3000, controller);
            }
            case 8 ->{
                return new StoreManagerPanel("localhost", 3000, controller);
            }
            case 9 ->{
                return new ChainManagerPanel("localhost", 3000, controller);
            }
            case 10 ->{
                return new CustomerServicePanel("localhost", 3000, controller);
            }
            case 11 ->{
                return new ChainSystemPanel("localhost", 3000, controller);
            }
            default -> {
                System.out.println("No panel found.");
                return null;
            }
        }
    }
}
