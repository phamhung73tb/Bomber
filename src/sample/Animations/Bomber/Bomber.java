package sample.Animations.Bomber;

import javafx.scene.image.ImageView;
import sample.Animations.Animations;
import sample.LoadRes.LoadResources;

public class Bomber extends Animations {

    public Bomber(double x, double y) {
        super(x, y);
        this.image = LoadResources.img_playerdown;
    }

    public Bomber(double x, double y, double speed) {
        super(x, y, speed);
        this.image = LoadResources.img_playerdown;
    }

    public void goUp() {
        typeUp += 1;
        typeDown = 0;
        typeRight = 0;
        typeLeft = 0;
        if (typeUp % 3 == 0) image = LoadResources.img_playerup;
        if (typeUp % 3 == 1) image = LoadResources.img_playerup1;
        if (typeUp % 3 == 2) image = LoadResources.img_playerup2;
        if (canGoUp()) {
            realY = realY - speed;
        }

    }
    public void downWard() {
        typeDown += 1;
        typeUp = typeRight = typeLeft = 0;
        if (typeDown % 3== 0) image = LoadResources.img_playerdown;
        if (typeDown % 3== 1) image = LoadResources.img_playerdown1;
        if (typeDown %3 == 2) image = LoadResources.img_playerdown2;
        if (canDownWard()) {
            realY = realY + speed;
        }
    }

    public void turnLeft() {
        typeLeft += 1;
        typeUp = typeRight = typeDown = 0;
        if (typeLeft %3 == 0) image = LoadResources.img_playerleft;
        if (typeLeft %3 == 1) image = LoadResources.img_playerleft1;
        if (typeLeft %3 == 2) image = LoadResources.img_playerleft2;
        if (canTurnLeft()) {
            realX = realX - speed;
        }
    }

    public void turnRight() {
        typeRight += 1;
        typeLeft = typeDown = typeUp = 0;
        if (typeRight %3 == 0) image = LoadResources.img_playerright;
        if (typeRight %3 == 1) image = LoadResources.img_playerright1;
        if (typeRight %3 == 2) image = LoadResources.img_playerright2;
        if (canTurnRight()) {
            realX = realX + speed;
        }
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
