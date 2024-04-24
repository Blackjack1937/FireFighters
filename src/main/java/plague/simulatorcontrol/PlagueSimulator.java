package plague.simulatorcontrol;

import framework.interfaces.Framework;
import framework.abstracts.Movement;
import plague.entities.*;

/** Classe principale du proto-simulateur de pandémie.*/
public class PlagueSimulator extends Movement implements Framework {

    // Déclaration des différents acteurs du simulateur
    public final Doctors doctors;
    public final Virus virus;
    public final InfectedPeople infectedPeople;
    public final Virologists virologists;
    public final VaccinatedPeople vaccinatedPeople;

    /**
     * Constructeur de PlagueSimulator.
     *
     * @param grid La grille sur laquelle le simulateur est basé.
     */
    public PlagueSimulator(PlagueGrid grid) {
        super(grid.rowCount, grid.colCount);
        doctors = new Doctors(grid, this);
        virus = new Virus(grid, this);
        infectedPeople = new InfectedPeople(grid, this);
        vaccinatedPeople = new VaccinatedPeople(grid, this);
        virologists = new Virologists(grid, this);
    }

    /**
     * Initialise les entités du simulateur avec un nombre spécifié d'instances.
     */
    @Override
    public void initialisation() {
        doctors.initialisation(5);
        virus.initialisation(5);
        infectedPeople.initialisation(10);
        vaccinatedPeople.initialisation(8);
        virologists.initialisation(5);
    }

    /** Active chaque entité du simulateur, simulant un cycle de la pandémie.*/
    @Override
    public void activation() {
        virus.activation();
        doctors.activation();
        infectedPeople.activation();
        vaccinatedPeople.activation();
        virologists.activation();
    }
}
