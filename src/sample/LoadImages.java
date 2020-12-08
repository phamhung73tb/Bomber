package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import sample.Res.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

public class LoadImages {
    public static Image img_playerup;
    public static Image img_playerup1;
    public static Image img_playerup2;
    public static Image img_playerdown;
    public static Image img_playerdown1;
    public static Image img_playerdown2;
    public static Image img_playerleft;
    public static Image img_playerleft1;
    public static Image img_playerleft2;
    public static Image img_playerright;
    public static Image img_playerright1;
    public static Image img_playerright2;
    public static Image img_playerdead;


    public static Image wall;
    public static Image brick;
    public static Image brick_ex;
    public static Image grass;

    public static Image img_balloomleft;
    public static Image img_balloomright;
    public static Image img_balloomleft1;
    public static Image img_balloomright1;
    public static Image img_balloomleft2;
    public static Image img_balloomright2;
    public static Image img_balloomddead;

    public static Image img_onealdead;
    public static Image img_onealleft1;
    public static Image img_onealleft2;
    public static Image img_onealleft3;
    public static Image img_onealright1;
    public static Image img_onealright2;
    public static Image img_onealright3;

    public static Image img_bomb;
    public static Image img_bombexploded;
    public static Image img_explosionhorizontal;
    public static Image img_explosionvertical;

    public static Image portal;
    public static Image powerup_bombs;
    public static Image powerup_flames;
    public static Image powerup_speed;

    public static Media sound_backgound;


    public static void loadImageBomber() throws FileNotFoundException {
        LoadImages.img_playerup = new Image(new FileInputStream(Resources.player_up));
        LoadImages.img_playerup1 = new Image(new FileInputStream(Resources.player_up1));
        LoadImages.img_playerup2 = new Image(new FileInputStream(Resources.player_up2));
        LoadImages.img_playerdown = new Image(new FileInputStream(Resources.player_down));
        LoadImages.img_playerdown1 = new Image(new FileInputStream(Resources.player_down1));
        LoadImages.img_playerdown2 = new Image(new FileInputStream(Resources.player_down2));
        LoadImages.img_playerleft = new Image(new FileInputStream(Resources.player_left));
        LoadImages.img_playerleft1 = new Image(new FileInputStream(Resources.player_left1));
        LoadImages.img_playerleft2 = new Image(new FileInputStream(Resources.player_left2));
        LoadImages.img_playerright = new Image(new FileInputStream(Resources.player_right));
        LoadImages.img_playerright1 = new Image(new FileInputStream(Resources.player_right1));
        LoadImages.img_playerright2 = new Image(new FileInputStream(Resources.player_right2));
        LoadImages.img_playerdead = new Image(new FileInputStream(Resources.player_dead));

    }

    public static void loadImageBomb() throws FileNotFoundException {
        LoadImages.img_bomb = new Image(new FileInputStream(Resources.bomb));
        LoadImages.img_bombexploded = new Image(new FileInputStream(Resources.bomb_exploded));
        LoadImages.img_explosionhorizontal =
                new Image(new FileInputStream(Resources.explosion_horizontal));
        LoadImages.img_explosionvertical =
                new Image(new FileInputStream(Resources.explosion_vertical));
    }

    public static void loadImageGraphics() throws FileNotFoundException {
        LoadImages.wall = new Image(new FileInputStream(Resources.wall));
        LoadImages.brick = new Image(new FileInputStream(Resources.brick));
        LoadImages.brick_ex = new Image(new FileInputStream(Resources.brick_ex));
        LoadImages.grass = new Image(new FileInputStream(Resources.grass));
    }

    public static void loadBalloom() throws FileNotFoundException {
        LoadImages.img_balloomleft = new Image(new FileInputStream(Resources.balloom_left));
        LoadImages.img_balloomright = new Image(new FileInputStream(Resources.balloom_right));
        LoadImages.img_balloomleft1 = new Image(new FileInputStream(Resources.balloom_left1));
        LoadImages.img_balloomright1 = new Image(new FileInputStream(Resources.balloom_right1));
        LoadImages.img_balloomleft2 = new Image(new FileInputStream(Resources.balloom_left2));
        LoadImages.img_balloomright2 = new Image(new FileInputStream(Resources.balloom_right2));
        LoadImages.img_balloomddead = new Image(new FileInputStream(Resources.balloom_dead));
    }

    public static void loadOneal() throws FileNotFoundException {
        LoadImages.img_onealdead = new Image(new FileInputStream(Resources.oneal_dead));
        LoadImages.img_onealleft1 = new Image(new FileInputStream(Resources.oneal_left1));
        LoadImages.img_onealleft2 = new Image(new FileInputStream(Resources.oneal_left2));
        LoadImages.img_onealleft3 = new Image(new FileInputStream(Resources.oneal_left3));
        LoadImages.img_onealright1 = new Image(new FileInputStream(Resources.oneal_right1));
        LoadImages.img_onealright2 = new Image(new FileInputStream(Resources.oneal_right2));
        LoadImages.img_onealright3 = new Image(new FileInputStream(Resources.oneal_right3));
    }

    public static void loadIcons() throws FileNotFoundException {
        LoadImages.portal = new Image(new FileInputStream(Resources.portal));
        LoadImages.powerup_bombs = new Image(new FileInputStream(Resources.powerup_bombs));
        LoadImages.powerup_flames = new Image(new FileInputStream(Resources.powerup_flames));
        LoadImages.powerup_speed = new Image(new FileInputStream(Resources.powerup_speed));
    }

    public static void loadSound() throws FileNotFoundException {
        LoadImages.sound_backgound = new Media(Paths.get(Resources.sound_background).toUri().toString());
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
