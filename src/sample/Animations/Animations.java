package sample.Animations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Enities;

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

    @Override
    public ImageView imageView() {
        return super.imageView();
    }

}
