package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enities {
    protected double realX;
    protected double realY;
    protected Image image;

    public Enities(double x, double y) {
        this.realY = y;
        this.realX = x;
    }

    public Enities(double x, double y, Image image) {
        this.realX = x;
        this.realY = y;
        this.image = image;
    }


    public double getRealX() {
        return realX;
    }

    public void setRealX(double realX) {
        this.realX = realX;
    }

    public double getRealY() {
        return realY;
    }

    public void setRealY(double realY) {
        this.realY = realY;
    }

    public ImageView imageView() {
        return LoadImages.showImage(realX, realY, image);
    }


}
