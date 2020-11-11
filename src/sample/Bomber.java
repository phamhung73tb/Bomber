package sample;

import javafx.scene.image.ImageView;

public class Bomber extends Enities {

    protected double speed;

    public Bomber(double x, double y) {
        super(x, y);
        this.image = LoadImages.img_playerdown;
    }

    public Bomber(double x, double y, double speed) {
        super(x, y);
        this.image = LoadImages.img_playerdown;
        this.speed = speed;
    }

    public void goUp() {
        int nextX = (int) realX;
        int nextY = (int) (realY + 0.5);
        image = LoadImages.img_playerup;
        if (realX - nextX == 0) {
            if (!Map.isFilled[nextX][nextY -1]) {
                realY = realY - speed;
            }
        }
        else {
            if (!Map.isFilled[nextX][nextY -1] && !Map.isFilled[nextX + 1][nextY -1]) {
                realY = realY - speed;
            }
        }

    }
    public void downWard() {
        int nextX = (int) realX;
        int nextY = (int) realY;
        image = LoadImages.img_playerdown;
        if (realX - nextX == 0) {
            if (!Map.isFilled[nextX][nextY + 1]) {
                realY = realY + speed;
            }
        }
        else {
            if (!Map.isFilled[nextX][nextY +1] && !Map.isFilled[nextX + 1][nextY +1]) {
                realY = realY + speed;
            }
        }
    }

    public void turnLeft() {
        int nextX = (int) (realX + 0.5);
        int nextY = (int) realY;
        image = LoadImages.img_playerleft;
        if (realY - nextY == 0) {
            if (!Map.isFilled[nextX - 1][nextY]) {
                realX = realX - speed;
            }
        }
        else {
            if (!Map.isFilled[nextX - 1][nextY] && !Map.isFilled[nextX - 1][nextY + 1]) {
                realX = realX - speed;
            }
        }
    }

    public void turnRight() {
        int nextX = (int) realX;
        int nextY = (int) realY;
        image = LoadImages.img_playerright;
        if (realY - nextY == 0) {
            if (!Map.isFilled[nextX + 1][nextY]) {
                realX = realX + speed;
            }
        }
        else {
            if (!Map.isFilled[nextX + 1][nextY] && !Map.isFilled[nextX + 1][nextY + 1]) {
                realX = realX + speed;
            }
        }

    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
