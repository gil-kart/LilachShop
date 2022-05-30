package org.lilachshop.panels;

public class OperationsPanelFactory {
    private OperationsPanelFactory() {
    }

    public static Panel createPanel(PanelEnum panel_num, Object controller) {    // for now int, later going to be enum!
        switch (panel_num) {
//            case 1 -> {
//                return new ExamplePanel("localhost", 3000, controller);
//            }
            case CUSTOMER_ANONYMOUS : {
                return new CustomerAnonymousPanel("localhost", 3000, controller);
            }
            case STORE_CUSTOMER: {
                return new StoreCustomerPanel("localhost", 3000, controller);
            }
            case CHAIN_CUSTOMER: {
                return new ChainCustomerPanel("localhost", 3000, controller);
            }
            case ANNUAL_CUSTOMER: {
                return new AnnualCustomerPanel("localhost", 3000, controller);
            }
            case EMPLOYEE_ANONYMOUS: {
                return new EmployeeAnonymousPanel("localhost", 3000, controller);
            }
            case GENERAL_EMPLOYEE: {
                return new GeneralEmployeePanel("localhost", 3000, controller);
            }
            case STORE_MANAGER: {
                return new StoreManagerPanel("localhost", 3000, controller);
            }
            case CHAIN_MANAGER: {
                return new ChainManagerPanel("localhost", 3000, controller);
            }
            case CUSTOMER_SERVICE: {
                return new CustomerServicePanel("localhost", 3000, controller);
            }
            case SYSTEM_MANAGER: {
                return new SystemManagerPanel("localhost", 3000, controller);
            }
            default : {
                System.out.println("No panel found.");
                return null;
            }

        }
    }
}
