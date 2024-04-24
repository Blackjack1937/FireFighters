import plague.simulatorcontrol.PlagueGrid;
import graphics.PlaguePainter;

import firefighters.simulatorcontrol.FireFighterGrid;
import graphics.FireFighterPainter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Classe principale de l'application JavaFX pour le proto-simulateur de pandémie.
 * Permet également de basculer vers d'autres simulateurs comme celui des pompiers.
 */
public class App extends Application {

    boolean isInPause = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        Group root = new Group();
        Button restart = new Button("Restart");
        Button switchPause = new Button("Pause");
        VBox buttons = new VBox();
        HBox total = new HBox();

        /* Paramétrer le simulateur à exécuter : */

        // Code pour configurer le simulateur "FireFighters"
        // FireFighterGrid grid = new FireFighterGrid(800, 800, 20, 20);
        // FireFighterPainter painter = new FireFighterPainter(grid);

        // Code pour configurer le simulateur "Plague"
        PlagueGrid grid = new PlagueGrid(800, 800, 20, 20);
        PlaguePainter painter = new PlaguePainter(grid);

        // Assemblage de la structure de l'interface utilisateur
        root.getChildren().add(total);
        total.getChildren().add(buttons);
        total.getChildren().add(grid);
        buttons.getChildren().add(restart);
        buttons.getChildren().add(switchPause);

        // Configuration des actions des boutons
        restart.setOnMouseClicked(grid::restart);
        switchPause.setOnMouseClicked((value) -> isInPause = !isInPause);

        // Affichage de la scène
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        painter.repaint();

        // Configuration du thread pour la mise à jour périodique du simulateur
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        threadPoolExecutor.scheduleAtFixedRate(() -> {
            if (!isInPause) {
                grid.model.activation();
                painter.repaint();
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }
}
