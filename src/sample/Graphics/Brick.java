package sample.Graphics;

import javafx.scene.image.ImageView;
import sample.LoadRes.LoadResources;

public class Brick extends Wall {


    public Brick(double x, double y) {
        super(x, y);
        this.image = LoadResources.brick;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }
}
