package firefighters.entities;

import firefighters.simulatorcontrol.FireExtinguisher;
import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import position.Position;
import java.util.*;

/** Classe représentant les pompiers dans le proto-simulateur.*/
public class FireFighters extends FireExtinguisher implements FightFire {
    private List<Position> firefighterPositions = new ArrayList<>();

    /**
     * Constructeur pour les pompiers.
     * @param grid  La grille où les pompiers seront placés.
     * @param model Le modèle contenant la logique et les données.
     */
    public FireFighters(FireFighterGrid grid, FireFighterSimulator model) {
        super(grid, model);
    }

    /**
     * Initialise les pompiers en les plaçant aléatoirement sur la grille.
     * @param number Le nombre de pompiers à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int i = 0; i < number; i++) {
            firefighterPositions.add(model.randomPosition());
        }
    }

    /** Active les pompiers pour qu'ils combattent les incendies. */
    @Override
    public void activation() {
        List<Position> newPositions = new ArrayList<>();
        for (Position firefighter : firefighterPositions) {
            Position newPosition = fightFire(firefighter);
            grid.painter.paint(firefighter.row(), firefighter.col());
            grid.painter.paintFireFighter(newPosition.row(), newPosition.col());
            newPositions.add(newPosition);
        }
        firefighterPositions = newPositions;
    }

    /**
     * Détermine le mouvement d'un pompier et éteint les feux dans la nouvelle position.
     *
     * @param position La position actuelle du pompier.
     * @return La nouvelle position après avoir combattu le feu.
     */
    @Override
    public Position fightFire(Position position) {
        Position targetPosition = aStepTowardFire(position);
        List<Position> adjacentFires = grid.model.next(targetPosition).stream()
                .filter(model.fires.getFiresPositions()::contains)
                .toList();
        extinguish(targetPosition);
        adjacentFires.forEach(this::extinguish);
        return targetPosition;
    }
}
