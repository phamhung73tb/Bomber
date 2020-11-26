package sample;

import javafx.scene.image.ImageView;

public class Bomb extends Enities {
    protected int sizeOfFire;
    public Bomb(double x, double y) {
        super(x, y);
        this.image = LoadImages.img_bomb;
    }

    @Override
    public ImageView imageView() {
        return super.imageView();
    }

}
