package sample.Graphics;

import javafx.scene.image.ImageView;
import sample.Enities;
import sample.LoadImages;

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
