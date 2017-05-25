package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class EditBookingController {
    @Autowired
    BookingService bookingService;

    @FXML
    TextField arriveDate;
    @FXML
    TextField departureDate;
    @FXML
    TextField phoneNumber;

    private Date arriveDateSql;
    private Date depDateSql;
    private Booking oldBooking;

    public void setBooking(Booking booking) {
        this.oldBooking = booking;
        arriveDate.setText(booking.getArriveDate().toString());
        departureDate.setText(booking.getDepartureDate().toString());
        phoneNumber.setText(booking.getPhoneNumber().toString());
    }


    @FXML
    private void initialize() {
    }

    @FXML
    private void update() {
        if (isValid()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Booking booking = new Booking();
            booking.setName(user.getLogin());
            booking.setPhoneNumber(phoneNumber.getText());
            booking.setArriveDate(arriveDateSql);
            booking.setDepartureDate(depDateSql);
            booking.setCottage(oldBooking.getCottage());
            bookingService.update(booking);
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) arriveDate.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }


    private boolean isValid() {
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

        return true;
    }

}
