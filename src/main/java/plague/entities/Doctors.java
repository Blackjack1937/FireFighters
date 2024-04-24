package plague.entities;


import plague.simulatorcontrol.PlagueSimulator;
import plague.simulatorcontrol.PlagueGrid;
import plague.simulatorcontrol.VirusEradicator;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/** Classe représentant les médecins dans le proto-simulateur. */
public class Doctors extends VirusEradicator implements EradicateVirus {
    private List<Position> doctorsList = new ArrayList<>();
    PlagueGrid grid;
    PlagueSimulator model;
    public Doctors(PlagueGrid grid, PlagueSimulator model) {
        super(grid,model);
        this.grid = grid;
        this.model = model;
    }

    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++)
            doctorsList.add(model.randomPosition());
    }

    @Override
    public void activation() {
        List<Position> doctorsNewPositions = new ArrayList<>();
        for (Position doctor : doctorsList) {
            Position newPosition = this.eliminateVirus(doctor);
            grid.painter.paint(doctor.row(), doctor.col());
            grid.painter.paintDoctor(newPosition.row(), newPosition.col());
            doctorsNewPositions.add(newPosition);
        }
        doctorsList = doctorsNewPositions;
    }


    @Override
    public Position eliminateVirus(Position position) {
        Position randomPosition = aStepTowardVirus (position);
        List<Position> nextVirus = grid.model.next(randomPosition).stream().filter(model.virus.getVirusPositions()::contains).toList();
        eliminate(randomPosition);
        for (Position virus : nextVirus)
            eliminate(virus);
        return randomPosition;
    }

    public List<Position> getDoctorsPositions() {
        return doctorsList;
    }
}
