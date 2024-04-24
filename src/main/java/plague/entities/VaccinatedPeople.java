package plague.entities;

import framework.interfaces.Elements;
import plague.simulatorcontrol.PlagueSimulator;
import plague.simulatorcontrol.PlagueGrid;
import position.Position;

import java.util.ArrayList;
import java.util.List;

/** Classe représentant les personnes vaccinées dans le proto-simulateur. */
public class VaccinatedPeople implements Elements {
    private final List<Position> vpPositions = new ArrayList<>();
    PlagueGrid grid;
    PlagueSimulator model;
    public VaccinatedPeople(PlagueGrid grid, PlagueSimulator model) {
        this.grid = grid;
        this.model = model;
    }
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++)
            vpPositions.add(model.randomPosition());

    }

    @Override
    public void activation() {
        for(Position people : vpPositions) grid.painter.paintVaccinated(people.row(),people.col());

    }
    public List<Position> getPositions() {
        return vpPositions;
    }
}

