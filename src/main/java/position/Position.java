package position;

import java.util.Objects;

/**
 * Record représentant une position dans une grille.
 */
public record Position(int row, int col) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Position[" +
                "row=" + row + ", " +
                "col=" + col + ']';
    }
}
