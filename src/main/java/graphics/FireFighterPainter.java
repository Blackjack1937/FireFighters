package graphics;

import firefighters.simulatorcontrol.FireFighterGrid;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

public class FireFighterPainter extends GridPainter {
    // Déclaration des images
    private Image ffIcon, fireIcon, ftIcon, cloudIcon, mountainIcon, roadIcon, rockIcon;

    public FireFighterPainter(FireFighterGrid grid) throws MalformedURLException {
        super(grid);
        loadImages(); // Charger les images
    }

    private void loadImages() throws MalformedURLException {
        // Chargement des images
        ffIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/firefighter.png");
        fireIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/fire.png");
        ftIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/firetruck.png");
        cloudIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/cloud.png");
        mountainIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/mountain.png");
        roadIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/road.png");
        rockIcon = loadImage("../firefighters/src/main/java/graphics/icons/firefighters/rock.png");
    }

    private Image loadImage(String path) throws MalformedURLException {
        return new Image(new File(path).toURI().toURL().toString());
    }

    // Méthodes pour dessiner chaque élément sur la grille
    public void paintFireFighter(int row, int col) {
        paintElement(ffIcon, row, col);
    }
    public void paintFire(int row, int col) {
        paintElement(fireIcon, row, col);
    }
    public void paintFireTrucks(int row, int col) {
        paintElement(ftIcon, row, col);
    }
    public void paintCloud(int row, int col) {
        paintElement(cloudIcon, row, col);
    }
    public void paintRock(int row, int col) {
        paintElement(rockIcon, row, col);
    }
    public void paintRoad(int row, int col) {
        paintElement(roadIcon, row, col);
    }
    public void paintMountain(int row, int col) {
        paintElement(mountainIcon, row, col);
    }



    private void paintElement(Image image, int row, int col) {
        context.drawImage(image, row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }
}