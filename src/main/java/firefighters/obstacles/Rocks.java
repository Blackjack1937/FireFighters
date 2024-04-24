package firefighters.obstacles;

import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import framework.interfaces.Elements;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant les rochers dans le proto-simulateur.
 * Les rochers sont des obstacles qui ne peuvent être franchis ni par le feu ni par les pompiers.
 */
public class Rocks implements Elements, Obstacles {
    private final FireFighterGrid grid;
    private final FireFighterSimulator model;
    private final List<Position> rockPositions = new ArrayList<>();

    public Rocks(FireFighterGrid grid, FireFighterSimulator model) {
        this.grid = grid;
        this.model = model;
    }

    /**
     * Initialise un certain nombre de rochers sur la grille du proto-simulateur.
     * @param number Le nombre de rochers à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            rockPositions.add(setPosition());
        }
    }

    /**
     * Active l'affichage des rochers sur la grille.
     */
    @Override
    public void activation() {
        for (Position rock : rockPositions) {
            grid.painter.paintRock(rock.row(), rock.col());
        }
    }

    /**
     * Définit une position aléatoire pour un rocher, en évitant les routes et les montagnes.
     * @return La position aléatoire générée pour le rocher.
     */
    @Override
    public Position setPosition(){
        Position randomPosition;
        do {
            randomPosition = model.randomPosition();
        } while (model.road.getPositions().contains(randomPosition) || model.mountains.getPositions().contains(randomPosition));

        return randomPosition;
    }

    /**
     * Récupère les positions des rochers.
     * @return La liste des positions des rochers.
     */
    @Override
    public List<Position> getPositions() {
        return rockPositions;
    }
}
