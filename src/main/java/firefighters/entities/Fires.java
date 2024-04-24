package firefighters.entities;

import firefighters.simulatorcontrol.FireFighterSimulator;
import firefighters.simulatorcontrol.FireFighterGrid;
import framework.interfaces.Elements;
import position.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Classe représentant les feux.*/
public class Fires implements Elements {
    private final FireFighterGrid grid;
    private final FireFighterSimulator model;
    private final Set<Position> firesSet = new HashSet<>();
    private int step = 0;

    public Fires(FireFighterGrid grid, FireFighterSimulator model) {
        this.grid = grid;
        this.model = model;
    }

    /**
     * Initialise les feux sur la grille en les positionnant aléatoirement.
     * @param number Le nombre de feux à initialiser.
     */
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++) {
            firesSet.add(model.randomPosition());
        }
    }

    /**
     * Active la propagation des feux tous les deux tours.
     * Sur les rochers, le feu met quatre tours à se propager.
     */
    @Override
    public void activation() {
        if (step % 2 == 0) {
            propagateFires();
        }
        step++;
    }

    /** Propage les feux en fonction des règles spécifiques au terrain. */
    private void propagateFires() {
        List<Position> newFires = calculateNewFirePositions();
        firesSet.addAll(newFires);
        newFires.forEach(fire -> grid.painter.paintFire(fire.row(), fire.col()));
    }

    /**
     * Calcule les nouvelles positions des feux.
     * @return Liste des nouvelles positions de feu.
     */
    private List<Position> calculateNewFirePositions() {
        List<Position> newFires = new ArrayList<>();
        int tour = 1;

        for (Position fire : firesSet) {
            newFires.addAll(model.skipMountainAndRoad(fire));
        }

        List<Position> rockPositions = new ArrayList<>();
        for (Position fire : newFires) {
            if (tour < 4 && model.rock.getPositions().contains(fire)) {
                rockPositions.add(fire);
                tour++;
            }
            if (tour == 4) {
                model.rock.getPositions().remove(fire);
                tour = 0;
            }
        }

        newFires.removeAll(rockPositions);
        return newFires;
    }

    public Set<Position> getFiresPositions() {
        return firesSet;
    }
}
