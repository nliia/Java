package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ConfigurationControllers;
import ru.kpfu.itis.model.Cottage;
import ru.kpfu.itis.service.CottageService;

import static ru.kpfu.itis.util.AlertShower.showAlert;

/**
 * @author Liia
 */
public class AddCottageController {
    @Autowired
    CottageService cottageService;
    @Qualifier("cottagesView")
    @Autowired
    private ConfigurationControllers.View cottagesView;
    @FXML
    RadioButton parking;
    @FXML
    RadioButton pavilion;
    @FXML
    RadioButton playground;
    @FXML
    RadioButton animalsPermission;
    @FXML
    TextField cottageNumber;
    @FXML
    TextField placeAmount;
    @FXML
    TextField cost;

    private int intPlaces;
    private long intCottageNum;
    private int intCost;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cost.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }

    @FXML
    private void addCottage() {
        if (isValid()) {
            Cottage cottage = new Cottage();
            cottage.setId(intCottageNum);
            cottage.setPlaceAmount(intPlaces);
            cottage.setCost(intCost);

            if (pavilion.isSelected())
                cottage.setPavilion(true);
            else
                cottage.setPavilion(false);

            if (parking.isSelected())
                cottage.setParking(true);
            else
                cottage.setParking(false);

            if (playground.isSelected())
                cottage.setPlayground(true);
            else
                cottage.setPlayground(false);

            if (animalsPermission.isSelected())
                cottage.setAnimalsPermission(true);
            else
                cottage.setAnimalsPermission(false);

            cottageService.add(cottage);

            MainController mainController = (MainController) cottagesView.getController();
            mainController.refresh();
            Stage stage = (Stage) cost.getScene().getWindow();
            stage.getScene().setRoot(new Pane());
            stage.close();
            animalsPermission.setSelected(false);
            playground.setSelected(false);
            parking.setSelected(false);
            pavilion.setSelected(false);
            placeAmount.setText("");
            cost.setText("");
            cottageNumber.setText("");
        }
    }

    private boolean isValid() {
        String errorMessage;
        if (placeAmount.getText().length() == 0
                || cottageNumber.getText().length() == 0
                || cost.getText().length() == 0) {
            errorMessage = "Fields cannot be empty";
            showAlert(errorMessage);
            return false;
        }
        try {
            intPlaces = Integer.valueOf(placeAmount.getText());
            intCottageNum = Long.valueOf(cottageNumber.getText());
            intCost = Integer.valueOf(cost.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessage = "You can use only numbers in fields!";
            showAlert(errorMessage);
            return false;
        }

        if (cottageService.exists(Long.valueOf(cottageNumber.getText()))){
            showAlert("Cottage with this number already exists");
            return false;
        }
            return true;
    }


}
