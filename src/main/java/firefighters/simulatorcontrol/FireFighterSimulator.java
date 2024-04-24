package firefighters.simulatorcontrol;

import firefighters.entities.Clouds;
import firefighters.entities.FireFighters;
import firefighters.entities.FireTrucks;
import firefighters.entities.Fires;
import firefighters.obstacles.Mountains;
import firefighters.obstacles.Road;
import firefighters.obstacles.Rocks;
import framework.interfaces.Framework;
import framework.abstracts.Movement;
import position.Position;

import java.util.List;

/** Classe principale du jeu de lutte contre les incendies. */
public class FireFighterSimulator extends Movement implements Framework {
    private final FireFighters fireFighters;
    private final FireTrucks fireTrucks;
    private final Clouds clouds;
    public final Rocks rock;
    public Fires fires;
    public Mountains mountains;
    public Road road;

    /**
     * Constructeur pour initialiser le jeu avec tous ses composants.
     *
     * @param grid La grille de jeu sur laquelle les éléments seront placés.
     */
    public FireFighterSimulator(FireFighterGrid grid) {
        super(grid.rowCount, grid.colCount);
        fireFighters = new FireFighters(grid, this);
        fires = new Fires(grid, this);
        clouds = new Clouds(grid, this);
        fireTrucks = new FireTrucks(grid, this);
        mountains = new Mountains(grid, this);
        road = new Road(grid, this);
        rock = new Rocks(grid, this);
    }

    /**Initialise tous les éléments du jeu. */
    @Override
    public void initialisation() {
        road.initialisation(13);
        rock.initialisation(5);
        fireFighters.initialisation(5);
        clouds.initialisation(5);
        fireTrucks.initialisation(5);
        mountains.initialisation(7);
        fires.initialisation(15);
    }

    /** Active tous les éléments du jeu pour un tour de jeu. */
    @Override
    public void activation() {
        clouds.activation();
        road.activation();
        rock.activation();
        fireFighters.activation();
        fireTrucks.activation();
        mountains.activation();
        fires.activation();
    }

    /**
     * Calcule les positions adjacentes à une position donnée, en excluant les montagnes.
     *
     * @param position La position de départ.
     * @return Une liste de positions adjacentes, excluant les montagnes.
     */
    public List<Position> skipMountain(Position position) {
        List<Position> list = next(position);
        list.removeAll(mountains.getPositions());
        return list;
    }

    /**
     * Calcule les positions adjacentes à une position donnée, en excluant les montagnes et les routes.
     *
     * @param position La position de départ.
     * @return Une liste de positions adjacentes, excluant les montagnes et les routes.
     */
    public List<Position> skipMountainAndRoad(Position position) {
        List<Position> list = skipMountain(position);
        list.removeAll(road.getPositions());
        return list;
    }
}
