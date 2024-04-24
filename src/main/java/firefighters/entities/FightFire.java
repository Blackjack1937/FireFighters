package firefighters.entities;

import position.Position;

/** Interface pour les entités capables de combattre le feu.*/
public interface FightFire {

    /** Méthode pour combattre le feu.*/

    Position fightFire(Position position);
}
