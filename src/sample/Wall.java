package sample;

import javafx.scene.image.ImageView;

public class Wall extends Enities {


    public Wall(double x, double y) {
        super(x, y);
        this.image = LoadImages.wall;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
