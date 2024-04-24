package plague.entities;

import framework.interfaces.Elements;
import plague.simulatorcontrol.PlagueSimulator;
import plague.simulatorcontrol.PlagueGrid;
import position.Position;

import java.util.*;

/** Classe représentant les virus dans le proto-simulateur. */
public class Virus implements Elements {
    PlagueGrid grid;
    PlagueSimulator model;
    private final Set<Position> virusSet = new HashSet<>();
    int step = 0;
    public Virus(PlagueGrid grid, PlagueSimulator model) {
        this.grid =  grid;
        this.model = model;
    }

    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++){
            virusSet.add(model.randomPosition());
        }
    }

    @Override
    public void activation() {
        if (step % 2 == 0) {
            List<Position> newVirus = new ArrayList<>();
            for (Position virus : virusSet) {
                Position newPosition = this.kill(virus);
                grid.painter.paint(virus.row(), virus.col());
                grid.painter.paintVirus(newPosition.row(), newPosition.col());

                newVirus.addAll(model.next(virus));
            }

            for (Position virus : newVirus )
                grid.painter.paintVirus( virus.row(), virus.col());
            virusSet.addAll(newVirus);
        }
        step++;
    }

    public Position kill(Position position) {
        Position randomPosition = aStepTowardPeople(position);
        List<Position> nextPeople = grid.model.next(randomPosition).stream().filter(model.infectedPeople.getPeoplePositions()::contains).toList();
        contaminate(randomPosition);
        for (Position people : nextPeople){
           contaminate(people);
        }
        return randomPosition;
    }

    protected void contaminate(Position position) {
        model.infectedPeople.getPeoplePositions().remove(position);
        grid.painter.paint(position.row(), position.col());
    }

    public Position aStepTowardPeople(Position position) {
        HashSet<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>(model.next(position));
        for (Position initialMove : toVisit)
            firstMove.put(initialMove, initialMove);
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (model.infectedPeople.getPeoplePositions().contains(current))
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
    public Set<Position> getVirusPositions() {
        return virusSet;
    }
}