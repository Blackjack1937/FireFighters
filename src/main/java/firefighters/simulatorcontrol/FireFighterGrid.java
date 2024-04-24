package firefighters.simulatorcontrol;

import framework.abstracts.Grid;
import javafx.scene.input.MouseEvent;
import graphics.FireFighterPainter;
import java.net.MalformedURLException;

/** Classe représentant la grille de jeu pour le simulateur de lutte contre les incendies. */
public class FireFighterGrid extends Grid {
    private final int width, height;
    public FireFighterSimulator model;
    public FireFighterPainter painter;
    public final int colCount;
    public final int rowCount;

    /**
     * Constructeur de la grille de jeu.
     *
     * @param width Largeur de la grille.
     * @param height Hauteur de la grille.
     * @param colCount Nombre de colonnes.
     * @param rowCount Nombre de rangées.
     * @throws MalformedURLException En cas d'erreur de création du peintre (FireFighterPainter).
     */
    public FireFighterGrid(int width, int height, int colCount, int rowCount) throws MalformedURLException {
        super(width, height, colCount, rowCount);
        this.width = width;
        this.height = height;
        this.colCount = colCount;
        this.rowCount = rowCount;

        painter = new FireFighterPainter(this);
        setFocusTraversable(true);
        setOnMousePressed(this::mousePressed);

        model = new FireFighterSimulator(this);
        model.initialisation();
    }

    /**
     * Redémarre le jeu en réinitialisant le modèle et le peintre.
     *
     * @param mouseEvent L'événement de souris déclenchant le redémarrage.
     */
    @Override
    public void restart(MouseEvent mouseEvent) {
        model = new FireFighterSimulator(this);
        model.initialisation();
        try {
            painter = new FireFighterPainter(this);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        getGraphicsContext2D().clearRect(0, 0, width, height);
        painter.repaint();
    }

    /**
     * Gère les clics de souris pour activer le jeu et redessiner la grille.
     *
     * @param mouseEvent L'événement de souris déclenchant l'activation.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        model.activation();
        painter.repaint();
    }
}
