package sample.Animations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Enities;
import sample.Map;

import java.util.Random;

public class Animations extends Enities {
    public double speed;
    protected int typeUp;
    protected int typeDown;
    protected int typeLeft;
    protected int typeRight;
    public int placeX;
    public int placeY;
    public boolean isLife;
    public Animations(double x, double y) {
        super(x, y);
    }

    public Animations(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
        this.typeUp = 0;
        this.typeDown = 0;
        this.typeLeft = 0;
        this.typeRight = 0;
        this.isLife = true;
    }
    public void place(int x, int y) {
        placeX = (int) realX;
        placeY = (int) realY;
        if (placeX < x && realX > placeX)
            placeX ++;
        if (placeY < y && realY > placeY)
            placeY ++;
    }
    public boolean canGoUp() {
        int nextX = (int) realX;
        int nextY = (int) (realY + 1 - speed);
        if (realX - nextX == 0) {
            return !Map.isFilled[nextX][nextY - 1];
        } else {
            return !Map.isFilled[nextX][nextY - 1] && !Map.isFilled[nextX + 1][nextY - 1];
        }
    }

    public boolean canDownWard() {
        int nextX = (int) realX;
        int nextY = (int) realY;
        if (realX - nextX == 0) {
            return !Map.isFilled[nextX][nextY + 1];
        } else {
            return !Map.isFilled[nextX][nextY + 1] && !Map.isFilled[nextX + 1][nextY + 1];
        }
    }
    public boolean canTurnLeft() {
        int nextX = (int) (realX + 1 - speed);
        int nextY = (int) realY;
        if (realY - nextY == 0) {
            return !Map.isFilled[nextX - 1][nextY];
        } else {
            return !Map.isFilled[nextX - 1][nextY] && !Map.isFilled[nextX - 1][nextY + 1];
        }
    }
    public boolean canTurnRight()     {
        int nextX = (int) realX;
        int nextY = (int) realY;
        if (realY - nextY == 0) {
            return !Map.isFilled[nextX + 1][nextY];
        } else {
            return !Map.isFilled[nextX + 1][nextY] && !Map.isFilled[nextX + 1][nextY + 1];
        }
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }

}
