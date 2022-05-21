package org.lilachshop.employeeclient;

import javafx.fxml.FXML;
import org.lilachshop.entities.ExampleEntity;
import org.lilachshop.entities.ExampleEnum;
import org.lilachshop.entities.Item;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");

    }
}
