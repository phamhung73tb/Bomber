package sample;

public class Balloom extends Enities {
    protected double speed;
    public Balloom(double x, double y) {
        super(x, y);
        this.image = LoadImages.img_balloomleft;
        this.speed = 0.5;
    }
    public void move() {
        int nextX = (int) (realX + 0.5) - 1;
        if (!Map.isFilled[nextX][(int) realY]) {
            realX = realX -speed;
            this.image = LoadImages.img_balloomleft;
        }
        else {
            realX = realX + speed;
            this.image = LoadImages.img_balloomright;
        }
    }
}
