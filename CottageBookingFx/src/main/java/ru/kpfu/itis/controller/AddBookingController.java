package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.ConfigurationControllers;
import ru.kpfu.itis.model.Booking;
import ru.kpfu.itis.model.Cottage;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.BookingService;
import ru.kpfu.itis.util.DateUtil;

import java.sql.Date;

import static ru.kpfu.itis.util.AlertShower.showAlert;

/**
 * @author Liia
 */
public class AddBookingController {
    @Autowired
    BookingService bookingService;
    @Qualifier("cottagesView")
    @Autowired
    private ConfigurationControllers.View cottagesView;
    @FXML
    TextField arriveDate;
    @FXML
    TextField departureDate;
    @FXML
    TextField phoneNumber;

    private Cottage cottage;
    private Date arriveDateSql;
    private Date depDateSql;

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void addBooking() {
        if (isValid()) {
            Booking booking = new Booking();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            booking.setCottage(cottage);
            booking.setArriveDate(arriveDateSql);
            booking.setDepartureDate(depDateSql);
            booking.setPhoneNumber(phoneNumber.getText());
            booking.setName(user.getLogin());
            bookingService.add(booking);
            MainController mainController = (MainController) cottagesView.getController();
            mainController.refresh();
            Stage stage = (Stage) arriveDate.getScene().getWindow();
            stage.getScene().setRoot(new Pane());
            stage.close();
            arriveDate.setText("");
            departureDate.setText("");
            phoneNumber.setText("");
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) arriveDate.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }

    private boolean isValid() {

        if (arriveDate.getText().length() == 0
                || departureDate.getText().length() == 0
                || phoneNumber.getText().length() == 0) {
            showAlert("Fields cannot be empty");
        }

        try {
            arriveDateSql = Date.valueOf(DateUtil.parse(arriveDate.getText()));
            depDateSql = Date.valueOf(DateUtil.parse(departureDate.getText()));
        } catch (NullPointerException e) {
            showAlert("Invalid date format");
            return false;
        }

        if (arriveDateSql.after(depDateSql)) {
            showAlert("Date of arrival cannot be after departure");
            return false;
        }

        try {
            Integer.parseInt(phoneNumber.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid format of phone number");
            return false;
        }

        if(phoneNumber.getText().length() < 10){
            showAlert("Phone number cannot be less than 10 symbols");

        }

        return true;
    }


}
