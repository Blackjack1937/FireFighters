package framework.abstracts;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import framework.interfaces.Controller;

/**
 * Classe abstraite représentant une grille dans le proto-simulateur.
 * Étend Canvas pour le rendu graphique et implémente Controller pour la gestion des interactions.
 */
public abstract class Grid extends Canvas implements Controller {
    public int colCount, rowCount;

    /**
     * Constructeur de la grille.
     *
     * @param width    Largeur du canvas.
     * @param height   Hauteur du canvas.
     * @param colCount Nombre de colonnes dans la grille.
     * @param rowCount Nombre de rangées dans la grille.
     */
    public Grid(int width, int height, int colCount, int rowCount) {
        super(width, height);
        this.colCount = colCount;
        this.rowCount = rowCount;
    }

    /**
     * Méthode pour redémarrer l'état de la grille.

     * @param mouseEvent L'événement de souris qui déclenche le redémarrage.
     */
    @Override
    public void restart(MouseEvent mouseEvent) {
    }

    /**
     * Gère les pressions de souris sur la grille.
     *
     * @param mouseEvent L'événement de souris qui déclenche l'action.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }
}
