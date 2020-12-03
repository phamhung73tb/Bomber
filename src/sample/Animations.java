package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animations extends Enities{
    protected double speed;
    protected int typeUp;
    protected int typeDown;
    protected int typeLeft;
    protected int typeRight;
    protected int placeX;
    protected int placeY;
    protected boolean isLife;
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
        if (placeX < x && realX > placeX + 0.25)
            placeX ++;
        if (placeY < y && realY > placeY + 0.25)
            placeY ++;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
