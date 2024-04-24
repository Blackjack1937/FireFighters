package firefighters.entities;

import firefighters.simulatorcontrol.FireExtinguisher;
import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant les nuages dans le proto-simulateur de pompiers.
 */
public class Clouds extends FireExtinguisher implements FightFire {
    // Liste pour garder une trace des positions des nuages.
    private List<Position> cloudsList = new ArrayList<>();

    public Clouds(FireFighterGrid grid, FireFighterSimulator model) {
        super(grid, model);
    }

    /**
     * Initialise les nuages avec un nombre spécifié de nuages.
     * @param number le nombre de nuages à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            cloudsList.add(model.randomPosition());
        }
    }

    /**
     * Active les nuages pour qu'ils se déplacent et éteignent les feux.
     */
    @Override
    public void activation() {
        List<Position> cloudsNewPositions = new ArrayList<>();
        for (Position cloud : cloudsList) {
            Position newPosition = fightFire(cloud);
            grid.painter.paint(cloud.row(), cloud.col());
            grid.painter.paintCloud(newPosition.row(), newPosition.col());
            cloudsNewPositions.add(newPosition);
        }
        cloudsList = cloudsNewPositions;
    }

    /**
     * Détermine un déplacement aléatoire pour un nuage.
     * @param position la position actuelle du nuage.
     */
    public Position randomStep(Position position){
        List<Position> positions = model.next(position);
        Random random = new Random();
        int randomPosition = random.nextInt(positions.size());
        if(!model.road.getPositions().contains(positions.get(randomPosition))){
            return positions.get(randomPosition);
        }
        return randomStep(position);
    }

    /**
     * Éteint les feux autour de la position du nuage et déplace le nuage.
     * @param position la position actuelle du nuage.
     */
    @Override
    public Position fightFire(Position position) {
        Position randomPosition = randomStep(position);
        List<Position> nextFires = model.next(randomPosition).stream().filter(model.fires.getFiresPositions()::contains).toList();
        extinguish(randomPosition);
        for (Position fire : nextFires) {
            extinguish(fire);
        }
        return randomPosition;
    }
}
