package sample.LoadRes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Random;

public class LoadResources {
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
    public static Media sound_enemyDie;
    public static Media sound_bomberDie;
    public static Media sound_pickItem;
    public static Media sound_putBomb;
    public static Media sound_bombbang;
    public static Media sound_wingame;
    public static Media sound_beep;



    public static void loadImageBomber() throws FileNotFoundException {
        LoadResources.img_playerup = new Image(new FileInputStream(Resources.player_up));
        LoadResources.img_playerup1 = new Image(new FileInputStream(Resources.player_up1));
        LoadResources.img_playerup2 = new Image(new FileInputStream(Resources.player_up2));
        LoadResources.img_playerdown = new Image(new FileInputStream(Resources.player_down));
        LoadResources.img_playerdown1 = new Image(new FileInputStream(Resources.player_down1));
        LoadResources.img_playerdown2 = new Image(new FileInputStream(Resources.player_down2));
        LoadResources.img_playerleft = new Image(new FileInputStream(Resources.player_left));
        LoadResources.img_playerleft1 = new Image(new FileInputStream(Resources.player_left1));
        LoadResources.img_playerleft2 = new Image(new FileInputStream(Resources.player_left2));
        LoadResources.img_playerright = new Image(new FileInputStream(Resources.player_right));
        LoadResources.img_playerright1 = new Image(new FileInputStream(Resources.player_right1));
        LoadResources.img_playerright2 = new Image(new FileInputStream(Resources.player_right2));
        LoadResources.img_playerdead = new Image(new FileInputStream(Resources.player_dead));

    }

    public static void loadImageBomb() throws FileNotFoundException {
        LoadResources.img_bomb = new Image(new FileInputStream(Resources.bomb));
        LoadResources.img_bombexploded = new Image(new FileInputStream(Resources.bomb_exploded));
        LoadResources.img_explosionhorizontal =
                new Image(new FileInputStream(Resources.explosion_horizontal));
        LoadResources.img_explosionvertical =
                new Image(new FileInputStream(Resources.explosion_vertical));
    }

    public static void loadImageGraphics() throws FileNotFoundException {
        LoadResources.wall = new Image(new FileInputStream(Resources.wall));
        LoadResources.brick = new Image(new FileInputStream(Resources.brick));
        LoadResources.brick_ex = new Image(new FileInputStream(Resources.brick_ex));
        LoadResources.grass = new Image(new FileInputStream(Resources.grass));
    }

    public static void loadBalloom() throws FileNotFoundException {
        LoadResources.img_balloomleft = new Image(new FileInputStream(Resources.balloom_left));
        LoadResources.img_balloomright = new Image(new FileInputStream(Resources.balloom_right));
        LoadResources.img_balloomleft1 = new Image(new FileInputStream(Resources.balloom_left1));
        LoadResources.img_balloomright1 = new Image(new FileInputStream(Resources.balloom_right1));
        LoadResources.img_balloomleft2 = new Image(new FileInputStream(Resources.balloom_left2));
        LoadResources.img_balloomright2 = new Image(new FileInputStream(Resources.balloom_right2));
        LoadResources.img_balloomddead = new Image(new FileInputStream(Resources.balloom_dead));
    }

    public static void loadOneal() throws FileNotFoundException {
        LoadResources.img_onealdead = new Image(new FileInputStream(Resources.oneal_dead));
        LoadResources.img_onealleft1 = new Image(new FileInputStream(Resources.oneal_left1));
        LoadResources.img_onealleft2 = new Image(new FileInputStream(Resources.oneal_left2));
        LoadResources.img_onealleft3 = new Image(new FileInputStream(Resources.oneal_left3));
        LoadResources.img_onealright1 = new Image(new FileInputStream(Resources.oneal_right1));
        LoadResources.img_onealright2 = new Image(new FileInputStream(Resources.oneal_right2));
        LoadResources.img_onealright3 = new Image(new FileInputStream(Resources.oneal_right3));
    }

    public static void loadIcons() throws FileNotFoundException {
        LoadResources.portal = new Image(new FileInputStream(Resources.portal));
        LoadResources.powerup_bombs = new Image(new FileInputStream(Resources.powerup_bombs));
        LoadResources.powerup_flames = new Image(new FileInputStream(Resources.powerup_flames));
        LoadResources.powerup_speed = new Image(new FileInputStream(Resources.powerup_speed));
    }

    public static void loadSound() throws FileNotFoundException {
        LoadResources.sound_backgound = new Media(Paths.get(Resources.sound_background).toUri().toString());
        LoadResources.sound_bomberDie =
                new Media(Paths.get(Resources.sound_bomberDie).toUri().toString());
        LoadResources.sound_enemyDie =
                new Media(Paths.get(Resources.sound_enemyDie).toUri().toString());
        LoadResources.sound_pickItem =
                new Media(Paths.get(Resources.sound_pickItem).toUri().toString());
        LoadResources.sound_putBomb =
                new Media(Paths.get(Resources.sound_putBomb).toUri().toString());
        LoadResources.sound_bombbang =
                new Media(Paths.get(Resources.sound_bombbang).toUri().toString());
        LoadResources.sound_wingame =
                new Media(Paths.get(Resources.sound_winGame).toUri().toString());
        LoadResources.sound_beep = new Media(Paths.get(Resources.sound_beep).toUri().toString());
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
