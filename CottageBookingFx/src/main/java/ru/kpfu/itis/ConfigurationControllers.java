package ru.kpfu.itis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.controller.*;

import java.io.IOException;
import java.io.InputStream;


@Configuration
public class ConfigurationControllers {

    @Bean(name = "authView")
    public View getAuthView() throws IOException {
        return loadView("fxml/auth.fxml");
    }

    @Bean(name = "regView")
    public View getRegView() throws IOException {
        return loadView("fxml/reg.fxml");
    }

    @Bean(name = "cottagesView")
    public View getCottagesView() throws IOException {
        return loadView("fxml/cottages.fxml");
    }

    @Bean(name = "cottageEdit")
    public View getCottageEdit() throws IOException {
        return loadView("fxml/cottageEditDialog.fxml");
    }


    @Bean(name = "cottageAdd")
    public View getCottageAdd() throws IOException {
        return loadView("fxml/cottageAddDialog.fxml");
    }

    @Bean
    public AuthController getAuthController() throws IOException {
        return (AuthController) getAuthView().getController();
    }

    @Bean
    public RegController getRegController() throws IOException {
        return (RegController) getRegView().getController();
    }

    @Bean
    public MainController getCottageController() throws IOException {
        return (MainController) getCottagesView().getController();
    }

    @Bean
    public EditCottageController getEditCottageController() throws IOException {
        return (EditCottageController) getCottageEdit().getController();
    }

    @Bean
    public AddCottageController getAddCottageController() throws IOException {
        return (AddCottageController) getCottageAdd().getController();
    }


    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}