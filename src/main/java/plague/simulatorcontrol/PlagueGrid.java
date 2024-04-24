package plague.simulatorcontrol;

import framework.abstracts.Grid;
import javafx.scene.input.MouseEvent;
import graphics.PlaguePainter;
import java.net.MalformedURLException;

/** Classe représentant la grille graphique pour le proto-simulateur de pandémie.*/
public class PlagueGrid extends Grid {
    private final int width, height;
    public PlagueSimulator model;
    public PlaguePainter painter;


    /**
     * Constructeur de PlagueGrid.
     * @throws MalformedURLException Si les chemins des images pour le rendu sont incorrects.
     */
    public PlagueGrid(int width, int height, int colCount, int rowCount) throws MalformedURLException {
        super(width, height, colCount, rowCount);
        this.width = width;
        this.height = height;
        painter = new PlaguePainter(this);
        setFocusTraversable(true); // Permet à la grille de recevoir des événements de focus
        setOnMousePressed(this::mousePressed); // Configure l'écouteur d'événements de souris
        model = new PlagueSimulator(this); // Crée le modèle de simulation
        model.initialisation(); // Initialise le modèle
    }

    /**
     * Réinitialise le simulateur en réponse à un événement de souris.
     *
     * @param mouseEvent L'événement de souris qui déclenche la réinitialisation.
     */
    @Override
    public void restart(MouseEvent mouseEvent) {
        model = new PlagueSimulator(this); // Crée un nouveau modèle de simulation
        model.initialisation(); // Initialise le nouveau modèle
        try {
            painter = new PlaguePainter(this); // Crée un nouveau rendu pour la grille
        } catch (MalformedURLException e) {
            throw new RuntimeException(e); // Gestion des exceptions liées au chargement des images
        }
        getGraphicsContext2D().clearRect(0, 0, width, height); // Efface la grille avant le nouveau rendu
        painter.repaint(); // Redessine la grille
    }

    /**
     * Gère les pressions de souris sur la grille.
     *
     * @param mouseEvent L'événement de souris.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        model.activation(); // Active le modèle de simulation
        painter.repaint(); // Met à jour le rendu graphique
    }
}
