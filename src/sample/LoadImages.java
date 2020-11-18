package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadImages {
    public static Image img_playerup;
    public static Image img_playerdown;
    public static Image img_playerleft;
    public static Image img_playerright;
    public static Image img_bomb;
    public static Image wall;
    public static Image brick;
    public static Image grass;
    public static Image img_balloomleft;
    public static Image img_balloomright;
    public static Image img_bombexploded;
    public static Image img_explosionhorizontal;
    public static Image img_explosionvertical;


    public static void loadImageBomber() throws FileNotFoundException {
        LoadImages.img_playerup = new Image(new FileInputStream(Resources.player_up));
        LoadImages.img_playerdown = new Image(new FileInputStream(Resources.player_down));
        LoadImages.img_playerleft = new Image(new FileInputStream(Resources.player_left));
        LoadImages.img_playerright = new Image(new FileInputStream(Resources.player_right));
        LoadImages.img_bomb = new Image(new FileInputStream(Resources.bomb));
        LoadImages.img_bombexploded = new Image(new FileInputStream(Resources.bomb_exploded));
    }

    public static void loadImageGraphics() throws FileNotFoundException {
        LoadImages.wall = new Image(new FileInputStream(Resources.wall));
        LoadImages.brick = new Image(new FileInputStream(Resources.brick));
        LoadImages.grass = new Image(new FileInputStream(Resources.grass));
    }

    public static void loadBalloom() throws FileNotFoundException {
        LoadImages.img_balloomleft = new Image(new FileInputStream(Resources.balloom_left));
        LoadImages.img_balloomright = new Image(new FileInputStream(Resources.balloom_right));
    }

    public static ImageView showImage(double x, double y,Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setX(x * 30);
        imageView.setY(y * 30);
        return imageView;
    }
}
