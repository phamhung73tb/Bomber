package sample.Animations.Enemy;

import javafx.scene.image.ImageView;
import sample.Animations.Animations;
import sample.LoadImages;
import sample.Map;

import java.util.Random;

public class Oneal extends Animations {
    public Oneal(double x, double y, double speed) {
        super(x, y, speed);
        this.typeDown = 1;
        this.image = LoadImages.img_onealleft1;
    }

    public void move() {
        Random random = new Random();

        if (typeLeft > 0 || typeRight > 0) {
            int rand = random.nextInt(3);
            if (rand == 1) {
                if (canGoUp()) {
                    goUp();
                } else {
                    rand = 0;
                }
            }
            if (rand == 2) {
                if (canDownWard()) {
                    downWard();
                } else {
                    rand = 0;
                }
            }
            if (rand == 0) {
                if (typeLeft > 0 && canTurnLeft()) {
                    turnLeft();
                }
                if (typeLeft > 0 && !canTurnLeft()) {
                    turnRight();
                }
                if (typeRight > 0 && canTurnRight()) {
                    turnRight();
                }
                if (typeRight > 0 && !canTurnRight()) {
                    turnLeft();
                }
            }
        }
        if (typeUp > 0|| typeDown > 0 ) {
            int rand = random.nextInt(3);
            if (rand == 1) {
                if (canTurnLeft()) {
                    turnLeft();
                } else {
                    rand = 0;
                }
            }
            if (rand == 2) {
                if (canTurnRight()) {
                    turnRight();
                } else {
                    rand = 0;
                }
            }
            if (rand == 0) {
                if (typeUp > 0 && canGoUp())
                    goUp();
                if (typeUp > 0 && !canGoUp())
                    downWard();
                if (typeDown > 0 && canDownWard())
                    downWard();
                if (typeDown > 0 && !canDownWard())
                    goUp();
            }
        }
    }

    @Override
    public void place(int x, int y) {
        super.place(x, y);
    }

    @Override
    public boolean canGoUp() {
        return super.canGoUp();
    }

    @Override
    public boolean canDownWard() {
        return super.canDownWard();
    }

    @Override
    public boolean canTurnLeft() {
        return super.canTurnLeft();
    }

    @Override
    public boolean canTurnRight() {
        return super.canTurnRight();
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }

    public void goUp() {
        typeUp += 1;
        typeDown = 0;
        typeRight = 0;
        typeLeft = 0;
        realY -= speed;
    }

    public void downWard() {
        typeDown += 1;
        typeUp = typeRight = typeLeft = 0;
        realY = realY + speed;
    }

    public void turnLeft() {
        typeLeft += 1;
        typeUp = typeRight = typeDown = 0;
        if (typeLeft % 3 == 0) image = LoadImages.img_onealleft1;
        if (typeLeft % 3 == 1) image = LoadImages.img_onealleft2;
        if (typeLeft % 3 == 2) image = LoadImages.img_onealleft3;
        realX = realX - speed;

    }

    public void turnRight() {
        typeRight += 1;
        typeLeft = typeDown = typeUp = 0;
        if (typeRight % 3 == 0) image = LoadImages.img_onealright1;
        if (typeRight % 3 == 1) image = LoadImages.img_onealright2;
        if (typeRight % 3 == 2) image = LoadImages.img_onealright3;
        realX = realX + speed;
    }
}
