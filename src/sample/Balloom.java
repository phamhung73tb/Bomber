package sample;

public class Balloom extends Animations {

    public Balloom(double x, double y, double speed) {
        super(x, y, speed);
        this.image = LoadImages.img_balloomleft;
        this.typeLeft = 1;

    }


    @Override
    public void place(int x, int y) {
        super.place(x, y);
    }

    public void move() {
        if (typeLeft > 0) {
            int nextX = (int) (realX  - speed);
            if (!Map.isFilled[nextX][(int) realY]) {
                if (typeLeft % 3 == 0) {
                    image = LoadImages.img_balloomleft;
                }
                if (typeLeft % 3 == 1) {
                    image = LoadImages.img_balloomleft1;
                }
                if (typeLeft % 3 == 2) {
                    image = LoadImages.img_balloomleft2;
                }
                realX = realX - speed;
                typeLeft ++;
            } else {
                typeLeft = 0;
                typeRight ++;
            }
        } else {
            if (!Map.isFilled[(int) realX + 1][(int) realY]) {
                if (typeRight % 3 == 0)
                    image = LoadImages.img_balloomright;
                if (typeRight % 3 == 1)
                    image = LoadImages.img_balloomright1;
                if (typeRight % 3 == 2)
                    image = LoadImages.img_balloomright2;
                realX = realX + speed;
                typeRight ++;
            }
            else {
                typeRight = 0;
                typeLeft ++;
            }
        }
    }
}
