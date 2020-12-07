package sample.Graphics;

import javafx.scene.image.ImageView;
import sample.Enities;
import sample.LoadImages;

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
