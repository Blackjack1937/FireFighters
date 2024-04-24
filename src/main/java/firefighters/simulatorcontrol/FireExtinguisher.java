package firefighters.simulatorcontrol;

import framework.interfaces.Elements;
import position.Position;
import java.util.*;

/** Classe abstraite représentant un extincteur de feu dans le jeu.*/
public abstract class FireExtinguisher implements Elements {
    protected FireFighterSimulator model;
    protected FireFighterGrid grid;

    /**
     * Constructeur pour initialiser un extincteur de feu avec un modèle de jeu et une grille.
     *
     * @param grid  La grille sur laquelle l'extincteur opère.
     * @param model Le modèle de jeu contenant la logique et les données.
     */
    public FireExtinguisher(FireFighterGrid grid, FireFighterSimulator model){
        this.grid = grid;
        this.model = model;
    }

    /**
     * Éteint le feu à une position donnée.
     *
     * @param position La position du feu à éteindre.
     */
    protected void extinguish(Position position) {
        model.fires.getFiresPositions().remove(position);
        grid.painter.paint(position.row(), position.col());
    }

    /**
     * Calcule un pas en direction du feu le plus proche.
     *
     * @param position La position actuelle de l'extincteur.
     * @return La position vers laquelle se déplacer pour atteindre le feu.
     */
    protected Position aStepTowardFire(Position position) {
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>(model.skipMountain(position));
        for (Position initialMove : toVisit) {
            firstMove.put(initialMove, initialMove);
        }

        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (model.fires.getFiresPositions().contains(current)) {
                return firstMove.get(current);
            }

            for (Position adjacent : model.skipMountain(current)) {
                if (seen.contains(adjacent)) continue;
                toVisit.add(adjacent);
                seen.add(adjacent);
                firstMove.put(adjacent, firstMove.get(current));
            }
        }
        return position;
    }

    // Méthodes abstraites de l'interface Elements.
    @Override
    public abstract void initialisation(int number);
    @Override
    public abstract void activation();
}
