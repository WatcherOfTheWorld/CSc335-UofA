package model;

import javafx.scene.paint.Color;

/**
 * @author Shwan Jin
 */
public class Connect4MoveMessage {

    private int row, col;
    private Color color;
    public Connect4MoveMessage(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Color getColor() {
        return this.color;
    }
}
