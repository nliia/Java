package ru.kpfu.itis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.ConfigurationControllers;
import ru.kpfu.itis.ViewLoader;
import ru.kpfu.itis.config.AuthProviderImpl;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.Role;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.util.FieldValidator;

/**
 * @author Liia
 */
public class RegController {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    FieldValidator fieldValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ConfigurationControllers configurationControllers;
    @Autowired
    private AuthProviderImpl authProvider;
    @Autowired
    ViewLoader viewLoader;
    @FXML
    Label header;
    @FXML
    TextField login;
    @FXML
    PasswordField password;

    @FXML
    public void signUp() {
        String stringLogin = login.getText();
        String stringPassword = password.getText();
        String validationMessage = fieldValidator.validateUser(stringLogin, stringPassword);
        if (validationMessage.equals("true")) {
            userRepository.save(new User(stringLogin, encoder.encode(stringPassword), Role.USER));
            Authentication authToken = new UsernamePasswordAuthenticationToken(login.getText(), password.getText());
            try {
                authToken = authProvider.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                viewLoader.showCottages();
            } catch (AuthenticationException e) {
            }
        } else {
            header.setText(validationMessage);
        }
    }

    @FXML
    public void showAuth() {
        viewLoader.showAuth();
    }

    public void setVisibleBtns() {
    }
}
