package firefighters.obstacles;

import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import framework.interfaces.Elements;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant les montagnes dans le proto-simulateur.
 * Les montagnes sont des obstacles qui ne peuvent être franchis ni par le feu ni par les pompiers.
 */
public class Mountains implements Elements, Obstacles {
    private final List<Position> mountainsList = new ArrayList<>();
    private final FireFighterGrid grid;
    private final FireFighterSimulator model;

    public Mountains(FireFighterGrid grid, FireFighterSimulator model) {
        this.grid = grid;
        this.model = model;
    }

    /**
     * Initialise un certain nombre de montagnes sur la grille de jeu.
     * @param number Le nombre de montagnes à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            mountainsList.add(setPosition());
        }
    }

    /**
     * Active l'affichage des montagnes sur la grille.
     */
    @Override
    public void activation() {
        for (Position mountain : mountainsList) {
            grid.painter.paintMountain(mountain.row(), mountain.col());
        }
    }

    /**
     * Récupère les positions des montagnes.
     * @return La liste des positions des montagnes.
     */
    @Override
    public List<Position> getPositions() {
        return mountainsList;
    }

    /**
     * Définit une position aléatoire pour une montagne, en évitant les routes et les rochers.
     * @return La position aléatoire générée pour la montagne.
     */
    @Override
    public Position setPosition() {
        Position randomPosition;
        do {
            randomPosition = model.randomPosition();
        } while (model.road.getPositions().contains(randomPosition) || model.rock.getPositions().contains(randomPosition));

        return randomPosition;
    }
}
