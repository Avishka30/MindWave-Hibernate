package lk.ijse.mindwave;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.mindwave.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/icon-inspira.png")));
        stage.setTitle("MindWave");

        stage.show();

    }
    private void initializeDatabase() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
    public static void main(String[] args) {
        new AppInitializer().initializeDatabase();
        launch(args);

    }
}
