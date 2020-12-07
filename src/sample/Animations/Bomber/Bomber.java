package sample.Animations.Bomber;

import javafx.scene.image.ImageView;
import sample.Animations.Animations;
import sample.LoadImages;
import sample.Map;

public class Bomber extends Animations {

    public Bomber(double x, double y) {
        super(x, y);
        this.image = LoadImages.img_playerdown;
    }

    public Bomber(double x, double y, double speed) {
        super(x, y, speed);
        this.image = LoadImages.img_playerdown;
    }

    public void goUp() {
        typeUp += 1;
        typeDown = 0;
        typeRight = 0;
        typeLeft = 0;
        int nextX = (int) realX;
        int nextY = (int) (realY + 1 - speed);
        if (typeUp % 3 == 0) image = LoadImages.img_playerup;
        if (typeUp % 3 == 1) image = LoadImages.img_playerup1;
        if (typeUp % 3 == 2) image = LoadImages.img_playerup2;
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
        typeDown += 1;
        typeUp = typeRight = typeLeft = 0;
        if (typeDown % 3== 0) image = LoadImages.img_playerdown;
        if (typeDown % 3== 1) image = LoadImages.img_playerdown1;
        if (typeDown %3 == 2) image = LoadImages.img_playerdown2;
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
        int nextX = (int) (realX + 1 - speed);
        int nextY = (int) realY;
        typeLeft += 1;
        typeUp = typeRight = typeDown = 0;
        if (typeLeft %3 == 0) image = LoadImages.img_playerleft;
        if (typeLeft %3 == 1) image = LoadImages.img_playerleft1;
        if (typeLeft %3 == 2) image = LoadImages.img_playerleft2;
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
        typeRight += 1;
        typeLeft = typeDown = typeUp = 0;
        if (typeRight %3 == 0) image = LoadImages.img_playerright;
        if (typeRight %3 == 1) image = LoadImages.img_playerright1;
        if (typeRight %3 == 2) image = LoadImages.img_playerright2;
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
    public void place(int x, int y) {
        super.place(x, y);
    }

    public void placeBomb() {
        this.placeX = (int) realX;
        this.placeY = (int) realY;
        if (typeLeft > 0 && placeX != realX)
            placeX ++;
        if (typeUp > 0 && realY != placeY)
            placeY ++;
    }


    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
