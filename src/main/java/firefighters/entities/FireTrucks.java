package firefighters.entities;

import firefighters.simulatorcontrol.FireExtinguisher;
import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import position.Position;

import java.util.*;

/** Classe représentant les camions de pompiers dans le proto-simulateur. */
public class FireTrucks extends FireExtinguisher implements FightFire {
    private List<Position> firetruckPositions = new ArrayList<>();

    /**
     * Constructeur pour les camions de pompiers.
     *
     * @param grid  La grille du jeu où les camions de pompiers seront placés.
     * @param model Le modèle du jeu contenant la logique et les données.
     */
    public FireTrucks(FireFighterGrid grid, FireFighterSimulator model) {
        super(grid, model);
    }

    /**
     * Initialise les camions de pompiers en les plaçant aléatoirement sur la grille.
     *
     * @param number Le nombre de camions de pompiers à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            firetruckPositions.add(model.randomPosition());
        }
    }

    /** Active les camions de pompiers pour qu'ils combattent les incendies. */
    @Override
    public void activation() {
        List<Position> newPositions = new ArrayList<>();
        for (Position ft : firetruckPositions) {
            Position newPosition = fightFire(ft);
            grid.painter.paint(ft.row(), ft.col());
            grid.painter.paintFireTrucks(newPosition.row(), newPosition.col());
            newPositions.add(newPosition);
        }
        firetruckPositions = newPositions;
    }

    /**
     * Permet aux camions de pompiers de faire deux pas en direction du feu le plus proche,
     * et éteint les feux dans la nouvelle position.
     *
     * @param position La position actuelle du camion de pompiers.
     * @return La nouvelle position après avoir combattu le feu.
     */
    @Override
    public Position fightFire(Position position) {
        Position targetPosition = twoStepsTowardFire(position);
        List<Position> adjacentFires = grid.model.next(targetPosition).stream()
                .filter(model.fires.getFiresPositions()::contains)
                .toList();
        extinguish(targetPosition);
        adjacentFires.forEach(this::extinguish);
        return targetPosition;
    }

    /**
     * Calcule deux pas en direction du feu le plus proche à partir de la position actuelle.
     *
     * @param position La position actuelle du camion de pompiers.
     * @return La position après avoir fait deux pas vers le feu.
     */
    private Position twoStepsTowardFire(Position position) {
        Position firstStep = aStepTowardFire(position);
        return aStepTowardFire(firstStep);
    }
}
