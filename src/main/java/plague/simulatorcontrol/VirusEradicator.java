package plague.simulatorcontrol;

import framework.interfaces.Elements;
import position.Position;
import java.util.*;

/**
 * Classe abstraite représentant un éliminateur de virus dans le proto-simulateur de pandémie.
 */
public abstract class VirusEradicator implements Elements {
    PlagueSimulator model;
    PlagueGrid grid;

    /**
     * Constructeur de VirusEradicator.
     *
     * @param grid  La grille du simulateur.
     * @param model Le modèle du simulateur.
     */
    public VirusEradicator(PlagueGrid grid, PlagueSimulator model){
        this.grid = grid;
        this.model = model;
    }

    /**
     * Supprime un virus de sa position actuelle et met à jour le rendu.
     *
     * @param position La position du virus à éliminer.
     */
    protected void eliminate(Position position) {
        model.virus.getVirusPositions().remove(position);
        grid.painter.paint(position.row(), position.col());
    }

    /**
     * Trouve le chemin vers le virus le plus proche pour l'éliminer.
     *
     * @param position La position de départ pour chercher le virus le plus proche.
     * @return La position du premier mouvement vers le virus le plus proche.
     */
    public Position aStepTowardVirus(Position position) {
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>(model.next(position));
        for (Position initialMove : toVisit)
            firstMove.put(initialMove, initialMove);
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (model.virus.getVirusPositions().contains(current))
                return firstMove.get(current);
            for (Position adjacent : model.next(current)) {
                if (seen.contains(adjacent)) continue;
                toVisit.add(adjacent);
                seen.add(adjacent);
                firstMove.put(adjacent, firstMove.get(current));
            }
        }
        return position;
    }

    // Les méthodes initialisation et activation sont vides, car cette classe est abstraite.
    // Les classes concrètes qui étendent VirusEradicator devront implémenter ces méthodes.
    @Override
    public void initialisation(int number) {
        // Implémentation spécifique dans les sous-classes.
    }
    @Override
    public void activation() {
        // Implémentation spécifique dans les sous-classes.
    }
}
