package sample.Icons;

import sample.Enities;
import sample.LoadRes.LoadResources;

public class PowerUpBombs extends Enities {
    public PowerUpBombs(double x, double y) {
        super(x, y);
        this.image = LoadResources.powerup_bombs;
    }
}
