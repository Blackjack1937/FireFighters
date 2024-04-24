package firefighters.obstacles;

import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import framework.interfaces.Elements;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/** Classe représentant les routes.*/
public class Road implements Elements, Obstacles {

    private final FireFighterGrid grid;
    private final FireFighterSimulator model;
    private final List<Position> roadPositions = new ArrayList<>();

    public Road(FireFighterGrid grid, FireFighterSimulator model) {
        this.grid = grid;
        this.model = model;
    }

    /**
     * Initialise un certain nombre de routes sur la grille du proto-simulateur.
     * @param number Le nombre de routes à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            roadPositions.add(setPosition());
        }
    }

    /**
     * Active l'affichage des routes sur la grille.
     */
    @Override
    public void activation() {
        for (Position road : roadPositions) {
            grid.painter.paintRoad(road.row(), road.col());
        }
    }

    /**
     * Définit une position aléatoire pour une route, en évitant les montagnes et les rochers.
     * @return La position aléatoire générée pour la route.
     */
    @Override
    public Position setPosition() {
        Position randomPosition;
        do {
            randomPosition = model.randomPosition();
        } while (model.mountains.getPositions().contains(randomPosition) ||
                model.rock.getPositions().contains(randomPosition));

        return randomPosition;
    }

    /**
     * Récupère les positions des routes.
     * @return La liste des positions des routes.
     */
    @Override
    public List<Position> getPositions() {
        return roadPositions;
    }
}
