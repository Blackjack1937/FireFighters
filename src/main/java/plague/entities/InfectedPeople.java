package plague.entities;

import framework.interfaces.Elements;
import plague.simulatorcontrol.PlagueSimulator;
import plague.simulatorcontrol.PlagueGrid;
import position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Classe représentant les personnes infectées dans le proto-simulateur. */
public class InfectedPeople implements Elements  {
    private List<Position> peoplePositions = new ArrayList<>();
    PlagueGrid grid;
    PlagueSimulator model;
    public InfectedPeople(PlagueGrid grid, PlagueSimulator model) {

        this.grid = grid;
        this.model = model;
    }
    @Override
    public void initialisation(int number) {
        for (int index = 0; index < number; index++)
            peoplePositions.add(model.randomPosition());

    }

    @Override
    public void activation() {
        List<Position> peopleNewPositions = new ArrayList<>();
        for (Position people : peoplePositions) {
            Position newPosition = contaminateVaccinated(people);
            grid.painter.paint(people.row(), people.col());
            grid.painter.paintInfected(newPosition.row(), newPosition.col());
            peopleNewPositions.add(newPosition);
        }
        peoplePositions = peopleNewPositions;
    }

    public Position randomStep(Position position){
        List<Position> positions = model.next(position);
        Random random = new Random();
        int randomPosition = random.nextInt(positions.size());
        if(!model.doctors.getDoctorsPositions().contains(positions.get(randomPosition)) || model.virologists.getPosition().contains(positions.get(randomPosition)) ){
            return  positions.get(randomPosition);
        }
        return randomStep(position);
    }
    public Position contaminateVaccinated(Position position) {
        Position randomPosition = randomStep(position);
        List<Position> nextPeople = grid.model.next(randomPosition).stream().filter(model.vaccinatedPeople.getPositions()::contains).toList();
        contaminate(randomPosition);
        for (Position people : nextPeople){
            contaminate(people);
        }
        return randomPosition;
    }

    private void contaminate(Position position) {
        model.vaccinatedPeople.getPositions().remove(position);
        grid.painter.paint(position.row(), position.col());
        grid.painter.paintInfected(position.row(), position.col());
    }

    public List<Position> getPeoplePositions() {
        return peoplePositions;
    }
}
