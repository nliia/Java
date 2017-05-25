package ru.kpfu.itis;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.kpfu.itis.controller.MainController;
import ru.kpfu.itis.controller.EditCottageController;
import ru.kpfu.itis.model.Cottage;


/**
 * @author Liia
 */
@Configuration
@Lazy
public class ViewLoader {

    @Value("CottageBooking")
    private String windowTitle;

    @Qualifier("regView")
    @Autowired
    private ConfigurationControllers.View regView;

    @Qualifier("authView")
    @Autowired
    private ConfigurationControllers.View authView;

    @Qualifier("cottagesView")
    @Autowired
    private ConfigurationControllers.View cottagesView;

    @Qualifier("cottageEdit")
    @Autowired
    private ConfigurationControllers.View cottageEditView;

    @Qualifier("cottageAdd")
    @Autowired
    private ConfigurationControllers.View cottageAddView;

    private Stage stage;
    private Scene scene;

    public void showReg() {
        showView(regView);
    }

    public void showAuth() {
        showView(authView);
    }

    public void showCottages() {
        MainController mainController = (MainController) cottagesView.getController();
        mainController.setVisibleBtns();
        showView(cottagesView);
    }

    public void showCottageAdd() {
        showPopUp(cottageAddView);
    }

    public void showCottageEdit(Cottage cottage) {
        Stage newStage = new Stage();
        EditCottageController controller = (EditCottageController) cottageEditView.getController();
        controller.setCottage(cottage);
        newStage.setScene(new Scene(cottageEditView.getView()));
        newStage.setResizable(true);
        newStage.centerOnScreen();
        newStage.setOnCloseRequest(event -> {
            newStage.getScene().setRoot(new Pane());
            newStage.close();
        });
        newStage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void showPopUp(ConfigurationControllers.View view) {
        Stage stage = new Stage();
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(event -> {
            stage.getScene().setRoot(new Pane());
            stage.close();
        });
        stage.show();
    }

    private void showView(ConfigurationControllers.View view) {
        scene.setRoot(view.getView());
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
}
