package framework.interfaces;

import javafx.scene.input.MouseEvent;

/** Interface représentant un contrôleur dans le proto-simulateur.*/
public interface Controller {

    /**
     * Méthode appelée pour redémarrer l'état lié à ce contrôleur.
     *
     * @param mouseEvent L'événement de souris qui déclenche le redémarrage.
     */
    void restart(MouseEvent mouseEvent);

    /**
     * Méthode appelée lorsqu'un événement de pression de souris est détecté.
     *
     * @param mouseEvent L'événement de souris détecté.
     */
    void mousePressed(MouseEvent mouseEvent);
}
