package framework.abstracts;

import position.Position;

import java.util.ArrayList;
import java.util.List;

/** Classe abstraite fournissant des fonctionnalités de base pour le mouvement et le positionnement
 * dans le proto-simulateur.*/
public abstract class Movement {

    protected int rowCount, colCount;

    /**
     * Constructeur pour la classe Movement.
     *
     * @param rowCount Nombre de rangées dans la grille.
     * @param colCount Nombre de colonnes dans la grille.
     */
    public Movement(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    /**
     * Génère une position aléatoire dans la grille.
     *
     * @return Une position aléatoire valide dans la grille.
     */
    public Position randomPosition() {
        return new Position((int) (Math.random() * rowCount), (int) (Math.random() * colCount));
    }

    /**
     * Calcule les positions adjacentes à une position donnée.
     *
     * @param position La position pour laquelle calculer les positions adjacentes.
     * @return Une liste des positions adjacentes à la position donnée.
     */
    public List<Position> next(Position position) {
        List<Position> adjacentPositions = new ArrayList<>();
        if (position.row() > 0) adjacentPositions.add(new Position(position.row() - 1, position.col()));
        if (position.col() > 0) adjacentPositions.add(new Position(position.row(), position.col() - 1));
        if (position.row() < rowCount - 1) adjacentPositions.add(new Position(position.row() + 1, position.col()));
        if (position.col() < colCount - 1) adjacentPositions.add(new Position(position.row(), position.col() + 1));

        return adjacentPositions;
    }
}
