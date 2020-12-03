package sample.Icons;

import javafx.scene.image.Image;
import sample.Enities;
import sample.LoadImages;

public class Portal extends Enities {
    public Portal(double x, double y) {
        super(x, y);
        this.image = LoadImages.portal;
    }

}
