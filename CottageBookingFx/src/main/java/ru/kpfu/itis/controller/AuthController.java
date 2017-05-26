package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.ConfigurationControllers;
import ru.kpfu.itis.ViewLoader;
import ru.kpfu.itis.config.AuthProviderImpl;

/**
 * @author Liia
 */

public class AuthController {
    @Autowired
    private AuthProviderImpl authProvider;
    @Autowired
    ConfigurationControllers configurationControllers;
    @Autowired
    ViewLoader viewLoader;
    @FXML
    Label header;
    @FXML
    TextField login;
    @FXML
    PasswordField password;

    @FXML
    public void login() {
        Authentication authToken = new UsernamePasswordAuthenticationToken(login.getText(), password.getText());
        try {
            authToken = authProvider.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (AuthenticationException e) {
            header.setText("User not found");
            return;
        }

        viewLoader.showCottages();
    }

    @FXML
    public void showSignUp() {
        viewLoader.showReg();
    }

}
