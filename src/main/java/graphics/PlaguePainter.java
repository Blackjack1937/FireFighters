package graphics;

import plague.simulatorcontrol.PlagueGrid;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

public class PlaguePainter extends GridPainter {
    // Déclaration des images
    private Image doctorIcon, virusIcon, virologistIcon, infectedIcon, vaccinatedIcon;

    public PlaguePainter(PlagueGrid grid) throws MalformedURLException {
        super(grid);
        loadImages(); // Charger les images
    }

    private void loadImages() throws MalformedURLException {
        // Chargement des images
        doctorIcon = loadImage("../firefighters/src/main/java/graphics/icons/plague/doctor.png");
        virusIcon = loadImage("../firefighters/src/main/java/graphics/icons/plague/virus.png");
        virologistIcon = loadImage("../firefighters/src/main/java/graphics/icons/plague/virologist.png");
        infectedIcon = loadImage("../firefighters/src/main/java/graphics/icons/plague/infected.png");
        vaccinatedIcon = loadImage("../firefighters/src/main/java/graphics/icons/plague/vaccinated.png");

    }

    private Image loadImage(String path) throws MalformedURLException {
        return new Image(new File(path).toURI().toURL().toString());
    }

    // Méthode à revoir pour intégrer dans "paintElement"
    public void paintVirus(int row, int col) {

        context.drawImage(virusIcon,row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    public void paintDoctor(int row, int col) {
        paintElement(doctorIcon, row, col);
    }
    public void paintVaccinated(int row, int col) {
        paintElement(vaccinatedIcon, row, col);
    }
    public void paintInfected(int row, int col) {
        paintElement(infectedIcon, row, col);
    }
    public void paintVirologist(int row, int col) {
        paintElement(virologistIcon, row, col);
    }

    private void paintElement(Image image, int row, int col) {
        context.drawImage(image, row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }
}
