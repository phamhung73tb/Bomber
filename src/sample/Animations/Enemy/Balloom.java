package sample.Animations.Enemy;

import sample.Animations.Animations;
import sample.LoadRes.LoadResources;
import sample.Map;

public class Balloom extends Animations {

    public Balloom(double x, double y, double speed) {
        super(x, y, speed);
        this.image = LoadResources.img_balloomleft;
        this.typeLeft = 1;

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

    public void move() {
        if (typeLeft > 0) {
            int nextX = (int) (realX  - speed);
            if (!Map.isFilled[nextX][(int) realY]) {
                if (typeLeft % 3 == 0) {
                    image = LoadResources.img_balloomleft;
                }
                if (typeLeft % 3 == 1) {
                    image = LoadResources.img_balloomleft1;
                }
                if (typeLeft % 3 == 2) {
                    image = LoadResources.img_balloomleft2;
                }
                realX = realX - speed;
                typeLeft ++;
            } else {
                typeLeft = 0;
                typeRight ++;
            }
        } else {
            if (!Map.isFilled[(int) realX + 1][(int) realY]) {
                if (typeRight % 3 == 0)
                    image = LoadResources.img_balloomright;
                if (typeRight % 3 == 1)
                    image = LoadResources.img_balloomright1;
                if (typeRight % 3 == 2)
                    image = LoadResources.img_balloomright2;
                realX = realX + speed;
                typeRight ++;
            }
            else {
                typeRight = 0;
                typeLeft ++;
            }
        }
    }
}
