package firefighters.obstacles;

import position.Position;
import java.util.List;

/** Interface pour les obstacles dans le proto-simulateur.*/
public interface Obstacles {

    /**@return La position définie pour l'obstacle.*/
    Position setPosition();

    /**@return La liste des positions des obstacles.*/
    List<Position> getPositions();
}
