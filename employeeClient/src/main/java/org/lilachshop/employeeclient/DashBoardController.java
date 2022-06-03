package org.lilachshop.employeeclient;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.lilachshop.entities.Employee;
import org.lilachshop.entities.Role;
import org.lilachshop.panels.Panel;

public class DashBoardController {

    public Employee employee;
    private static Panel panel;
    private Stage stage;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private HBox catalogBtn;

    @FXML
    private HBox complaintsBtn;

    @FXML
    private HBox costumerBtn;

    @FXML
    private Pane displayer;

    @FXML
    private HBox employeeBtn;

    @FXML
    private HBox reportBtn;

    @FXML
    private BorderPane mainPane;

    @FXML
    void onClickReportBtn(MouseEvent event) { // WE DONT USE IT

    }

    @FXML
    void InClickOrderReportBtn(ActionEvent event) {
        try {
            this.displayer.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderReport.fxml"));
            AnchorPane anchorPaneOrderReport =  fxmlLoader.load();
            displayer.getChildren().add(anchorPaneOrderReport);
//            OrderReportController controller = fxmlLoader.getController();
//            controller.employee= this.employee;

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onClickComplaintReportBtn(ActionEvent event) {
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ComplaintReport.fxml"));
                AnchorPane anchorPaneComplaintReport =  fxmlLoader.load();
                displayer.getChildren().add(anchorPaneComplaintReport);
//                ComplaintReportController controller = fxmlLoader.getController();
//                controller.employee= this.employee;

            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void onClickIncomeReportBtn(Event event) {
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IncomeReport.fxml"));
                AnchorPane anchorPaneIncomeReport =  fxmlLoader.load();
                displayer.getChildren().add(anchorPaneIncomeReport);
//                IncomeReportController controller = fxmlLoader.getController();
//                controller.employee= this.employee;
//                controller.setData(this.employee);

            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }



    @FXML
    void onClickEmployeeBtn(MouseEvent event) throws IOException { // waiting for the GUI & Controller to be done
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeTable.fxml"));
                AnchorPane anchorPaneEmployeeTable =  fxmlLoader.load();
                displayer.getChildren().add(anchorPaneEmployeeTable);
//                EmployeeTableController controller = fxmlLoader.getController();
//                controller.employee= this.employee;
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void onClickCostumerBtn(MouseEvent event) {     // waiting for the GUI & Controller to be done
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerTable.fxml"));
                AnchorPane anchorPaneCustomerTable =  fxmlLoader.load();
                displayer.getChildren().add(anchorPaneCustomerTable);
//                CustomerTableController controller = fxmlLoader.getController();
//                controller.employee= this.employee;
            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void onClickCatalogBtn(MouseEvent event) {
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CatalogEditTable.fxml"));
                AnchorPane anchorPaneCatalog =  fxmlLoader.load();
                displayer.getChildren().add(anchorPaneCatalog);
//                CatalogEditTableController  controller = fxmlLoader.getController();
//                controller.employee= this.employee;

            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void onClickComplaintsBtn(MouseEvent event) {
        Platform.runLater(()-> {
            try {
                this.displayer.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerServiceView.fxml"));
                AnchorPane anchorPaneComplaints =  fxmlLoader.load();
                displayer.getChildren().add( anchorPaneComplaints);
                CustomerServiceViewController controller = fxmlLoader.getController();
                controller.employee= this.employee;
            }catch (IOException e) {
                e.printStackTrace();
            }
        });


    }





    @FXML
    void initialize() {

        assert catalogBtn != null : "fx:id=\"catalogBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert complaintsBtn != null : "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert costumerBtn != null : "fx:id=\"costumerBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert displayer != null : "fx:id=\"displayer\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert reportBtn != null : "fx:id=\"reportBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        catalogBtn.setVisible(false);   //CHECK IF I CAN DO IT AT ONCE
        complaintsBtn.setVisible(false);//CHECK IF I CAN DO IT AT ONCE
        costumerBtn.setVisible(false);  //CHECK IF I CAN DO IT AT ONCE
        reportBtn.setVisible(false);    //CHECK IF I CAN DO IT AT ONCE
        employeeBtn.setVisible(false);  //CHECK IF I CAN DO IT AT ONCE
        stage= App.getStage();
    }


    public void setData(Employee employee) {

        Role role = employee.getRole();
        switch (role) {

            case STORE_EMPLOYEE: {
                catalogBtn.setVisible(true);
                break;
            }

            case STORE_MANAGER:

            case CHAIN_MANAGER: {
                catalogBtn.setVisible(true);
                reportBtn.setVisible(true);
                break;
            }

            case CUSTOMER_SERVICE: {
                complaintsBtn.setVisible(true);
                break;
            }

            case SYSTEM_MANAGER: {
                employeeBtn.setVisible(true);
                costumerBtn.setVisible(true);
                break;
            }
        }
    }



//       public void openNewScreen(String screen){ WAS thinking to reduce duplicate, but for that we need to change the controllers.
//           Platform.runLater(()-> {
//               try {
//                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screen));
//                   Parent root1 = (Parent) fxmlLoader.load();
//                   ModuleLayer.Controller controller = fxmlLoader.getController();
//                  // controller.employee= this.employee;
//                   Stage stage = App.getStage();
//                   stage.setScene(new Scene(root1));
//                   stage.show();
//               }catch (IOException e) {
//                   e.printStackTrace();
//               }
//           });
//
//       }

}
