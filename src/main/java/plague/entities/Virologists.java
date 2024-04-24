package plague.entities;

import plague.simulatorcontrol.PlagueSimulator;
import plague.simulatorcontrol.PlagueGrid;
import plague.simulatorcontrol.VirusEradicator;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/** Classe représentant les virologues experts dans le proto-simulateur. */
public class Virologists extends VirusEradicator implements EradicateVirus {
    private List<Position> virologistsList = new ArrayList<>();
    PlagueGrid grid;
    PlagueSimulator model;
    public Virologists(PlagueGrid grid, PlagueSimulator model) {
        super(grid,model);
        this.grid = grid;
        this.model = model;
    }

    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++)
            virologistsList.add(model.randomPosition());
    }

    @Override
    public void activation() {
        List<Position> vNewPositions = new ArrayList<>();
        for (Position vir : virologistsList) {
            Position newPosition = this.eliminateVirus(vir);
            grid.painter.paint(vir.row(), vir.col());
            grid.painter.paintVirologist(newPosition.row(), newPosition.col());
            vNewPositions.add(newPosition);
        }
        virologistsList = vNewPositions;
    }
    @Override
    public Position eliminateVirus(Position position) {
        Position randomPosition = twoStepsTowardVirus(position);
        List<Position> nextVirus = grid.model.next(randomPosition).stream().filter(model.virus.getVirusPositions()::contains).toList();
        eliminate(randomPosition);
        for (Position virus : nextVirus)
            eliminate(virus);
        return randomPosition;
    }
    private Position twoStepsTowardVirus(Position position){
        Position firstStep = aStepTowardVirus(position);
        return aStepTowardVirus(firstStep);
    }

    public List<Position> getPosition() {
        return virologistsList;
    }
}
