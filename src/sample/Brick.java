package sample;

import javafx.scene.image.ImageView;

public class Brick extends Wall {


    public Brick(double x, double y) {
        super(x, y);
        this.image = LoadImages.brick;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
