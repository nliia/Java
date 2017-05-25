package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
public class EditCottageController {
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
    TextField placeAmount;
    @FXML
    TextField cost;
    @FXML
    Label cottageNumber;

    private Cottage cottage;
    private int intPlaces;
    private int intCost;

    @FXML
    private void initialize() {
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
        cottageNumber.setText(cottage.getId().toString());
        if (cottage.getParking())
            parking.setSelected(true);

        if (cottage.getPlayground())
            playground.setSelected(true);

        if (cottage.getPavilion())
            pavilion.setSelected(true);

        if (cottage.getAnimalsPermission())
            animalsPermission.setSelected(true);

        placeAmount.setText(String.valueOf(cottage.getPlaceAmount()));
        cost.setText(String.valueOf(cottage.getCost()));
    }

    @FXML
    public void update() {
        if (isValid()) {
            cottage.setId(cottage.getId());
            cottage.setCost(intCost);
            cottage.setPlaceAmount(intPlaces);
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

            cottageService.update(cottage);
            MainController mainController = (MainController) cottagesView.getController();
            mainController.showCottageDetails(cottage);
            mainController.refresh();

            Stage stage = (Stage) cost.getScene().getWindow();
            stage.getScene().setRoot(new Pane());
            setCottage(cottage);
            stage.close();
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cost.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }

    private boolean isValid() {
        String errorMessage;

        if (placeAmount.getText().length() == 0
                || cost.getText().length() == 0) {
            errorMessage = "Fields cannot be empty";
            showAlert(errorMessage);
            return false;
        }
        try {
            intPlaces = Integer.valueOf(placeAmount.getText());
            intCost = Integer.valueOf(cost.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessage = "You can use only numbers!";
            showAlert(errorMessage);
            return false;
        }

        return true;
    }
}

