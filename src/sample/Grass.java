package sample;

import javafx.scene.image.ImageView;

public class Grass extends Enities {
    public Grass(double x, double y) {
        super(x, y);
        this.image = LoadImages.grass;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
