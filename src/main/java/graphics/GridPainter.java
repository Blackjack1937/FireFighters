package graphics;

import framework.abstracts.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class GridPainter implements Painter{
    double width;
    double height;
    int colCount;
    int rowCount;
    Grid grid;
    GraphicsContext context;
    public GridPainter(Grid grid) {
        this.grid = grid;
        context = grid.getGraphicsContext2D();
        this.width = grid.getWidth();
        this.height = grid.getHeight();
        this.colCount = grid.colCount;
        this.rowCount = grid.rowCount;
    }
    @Override
    public void repaint() {
        for(int col=0; col<colCount; col++)
            context.strokeLine(0, col*width/colCount, height, col*width/colCount);
        for(int row=0; row<rowCount;row++)
            context.strokeLine(row*height/rowCount,0,row*height/rowCount, width);
    }

    @Override
    public void paint(int row, int col) {
        context.setFill(Color.WHITE);
        context.fillRect(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }
}
