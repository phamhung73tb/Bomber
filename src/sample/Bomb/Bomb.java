package sample.Bomb;

import javafx.scene.image.ImageView;
import sample.Enities;
import sample.LoadRes.LoadResources;

public class Bomb extends Enities {
    protected int sizeOfFire;
    public Bomb(double x, double y) {
        super(x, y);
        this.image = LoadResources.img_bomb;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }

}
