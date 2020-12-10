package sample.Icons;

import sample.Enities;
import sample.LoadRes.LoadResources;

public class Portal extends Enities {
    public Portal(double x, double y) {
        super(x, y);
        this.image = LoadResources.portal;
    }

}
