package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sample.Animations.Bomber.Bomber;
import sample.Animations.Enemy.Balloom;
import sample.Animations.Enemy.Oneal;
import sample.Bomb.Bomb;
import sample.Graphics.Brick;
import sample.Graphics.Grass;
import sample.Icons.PowerUpFlames;
import sample.Icons.PowerUpSpeed;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Controller extends Application {

    Stage stage;
    Group root = new Group();
    Scene scene = new Scene(root);
    ImageView[] imageViews = new ImageView[2000];
    List<Balloom> balloomList = Map.listballoom;
    int indexoflistImage = 0;
    boolean[][] hasFire = new boolean[100][100];
    int sizeOfFire = 1;
    boolean checkGameOver = false;
    int hasBomb = 0;
    int limitBomb = 1;
    MediaPlayer sound_Background;



    public static void main(String[] args) {
        launch(args);
    }


    public void addImage() {
        for (int i = 0; i < Map.listgrass.size(); i ++) {
            imageViews[indexoflistImage] = Map.listgrass.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listgrass.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
        for (int i = 0; i < Map.listbirck.size(); i ++) {
            imageViews[indexoflistImage] = Map.listbirck.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listbirck.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
        for (int i = 0; i < Map.listwall.size(); i ++) {
            imageViews[indexoflistImage] = Map.listwall.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listwall.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
        for (int i = 0; i < Map.listballoom.size(); i ++) {
            imageViews[indexoflistImage] = Map.listballoom.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listballoom.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
        for (int i = 0; i < Map.listOneal.size(); i++) {
            imageViews[indexoflistImage] = Map.listOneal.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listOneal.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
    }

    public void changImageView(ImageView imageView, double x, double y, Image image) {
        imageView.setImage(image);
        imageView.setX(x * 30);
        imageView.setY(y * 30);
    }

    public int showIndex(int x, int y) {
        for (int i = 0; i < Map.listbirck.size(); i ++) {
            Brick brick = Map.listbirck.get(i);
            if (x == brick.realX && y == brick.realY)
                return brick.index;
        }
        for (int i = 0; i < Map.listgrass.size(); i ++) {
            Grass grass = Map.listgrass.get(i);
            if (x == grass.realX && y == grass.realY)
                return grass.index;
        }
        return 0;
    }
    public void showFire(int x, int y, Bomber bomber) {
        hasFire[x][y] = true;
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x + i][y]) {
                int index = showIndex(x + i, y);
                imageViews[index].setImage(LoadImages.img_explosionhorizontal);
                hasFire[x + i][y] = true;
            } else {
                if (Map.isBrick[x + i][y]) {
                    int index = showIndex(x + i, y);
                    hasFire[x + i][y] = true;
                    imageViews[index].setImage(LoadImages.brick_ex);
                    Map.isBrick[x + i][y] = false;
                    Map.isFilled[x + i][y] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x - i][y]) {
                int index = showIndex(x - i, y);
                imageViews[index].setImage(LoadImages.img_explosionhorizontal);
                hasFire[x - i][y] = true;
            } else {
                if (Map.isBrick[x - i][y]) {
                    int index = showIndex(x - i, y);
                    hasFire[x - i][y] = true;
                    imageViews[index].setImage(LoadImages.brick_ex);
                    Map.isBrick[x - i][y] = false;
                    Map.isFilled[x - i][y] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x][y - i]) {
                int index = showIndex(x, y - i);
                imageViews[index].setImage(LoadImages.img_explosionvertical);
                hasFire[x][y - i] = true;
            } else {
                if (Map.isBrick[x][y - i]) {
                    int index = showIndex(x, y - i);
                    hasFire[x][y - i] = true;
                    imageViews[index].setImage(LoadImages.brick_ex);
                    Map.isBrick[x][y - i] = false;
                    Map.isFilled[x][y - i] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x][y + i]) {
                int index = showIndex(x, y + i);
                imageViews[index].setImage(LoadImages.img_explosionvertical);
                hasFire[x][y + i] = true;
            } else {
                if (Map.isBrick[x][y + i]) {
                    int index = showIndex(x, y + i);
                    hasFire[x][y + i] = true;
                    imageViews[index].setImage(LoadImages.brick_ex);
                    Map.isBrick[x][y + i] = false;
                    Map.isFilled[x][y + i] = false;
                }
                break;
            }
        }
        for (int i = 0; i < Map.listballoom.size(); i ++) {
            Balloom balloom = Map.listballoom.get(i);
            balloom.place(x, y);
            if (hasFire[balloom.placeX][balloom.placeY]) {
                imageViews[balloom.index].setImage(LoadImages.img_balloomddead);
                Map.listballoom.get(i).isLife = false;
            }
        }
        for (int i = 0; i < Map.listOneal.size(); i ++) {
            Oneal oneal = Map.listOneal.get(i);
            oneal.place(x, y);
            if (hasFire[oneal.placeX][oneal.placeY]) {
                imageViews[oneal.index].setImage(LoadImages.img_onealdead);
                Map.listOneal.get(i).isLife = false;
            }
        }
        bomber.place(x, y);
        if (hasFire[bomber.placeX][bomber.placeY])
            bomber.isLife = false;
    }

    public void offFire() {
        for (int i = 0; i < 50; i ++)
            for (int j = 0; j < 30; j++) {
                if (hasFire[i][j]) {
                    imageViews[showIndex(i, j)].setImage(LoadImages.grass);
                    if (Map.portal.realX == i && Map.portal.realY == j) {
                        imageViews[showIndex(i, j)].setImage(Map.portal.image);
                    }
                    if (Map.powerUpBombs.realX == i && Map.powerUpBombs.realY == j) {
                        imageViews[showIndex(i, j)].setImage(Map.powerUpBombs.image);
                    }
                    if (Map.powerUpFlames.realX == i && Map.powerUpFlames.realY == j) {
                        imageViews[showIndex(i, j)].setImage(Map.powerUpFlames.image);
                    }
                    if (Map.powerUpSpeed.realX == i && Map.powerUpSpeed.realY == j) {
                        imageViews[showIndex(i, j)].setImage(Map.powerUpSpeed.image);
                    }
                    hasFire[i][j] = false;
                }
            }
    }
    public void buffIcons(Bomber bomber) {
        double x = bomber.realX;
        double y =bomber.realY;
        int i = (int) x;
        int j = (int) y;
        if (Map.powerUpBombs.realX == x && Map.powerUpBombs.realY == y) {
            limitBomb ++;
            imageViews[showIndex(i, j)].setImage(LoadImages.grass);
            Map.powerUpBombs.realX = 0;
            Map.powerUpBombs.realY = 0;
        }
        if (Map.powerUpFlames.realX == x && Map.powerUpFlames.realY == y) {
            sizeOfFire ++;
            imageViews[showIndex(i, j)].setImage(LoadImages.grass);
            Map.powerUpFlames = new PowerUpFlames(0, 0);
        }
        if (Map.powerUpSpeed.realX == x && Map.powerUpSpeed.realY == y) {
            bomber.speed *= 2;
            imageViews[showIndex(i, j)].setImage(LoadImages.grass);
            Map.powerUpSpeed = new PowerUpSpeed(0, 0);
        }
    }

    public void checkWinGame(Bomber bomber) {
        double x = bomber.realX;
        double y =bomber.realY;
        if (balloomList.size() == 0) {
            if (Map.portal.realX == x && Map.portal.realY == y) {

                System.out.println("Win game!");
            }
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(1000);
        this.stage = stage;
        LoadImages.loadSound();
        Map.renderMap();
        addImage();
        Bomber bomber = new Bomber(Map.bomber.realX, Map.bomber.realY, Map.bomber.speed);
        ImageView imageViewBomber = bomber.imageView();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < balloomList.size(); i ++) {
                    Balloom balloom = balloomList.get(i);
                    if (!balloom.isLife) {
                        changImageView(imageViews[balloomList.get(i).index], 0, 1,
                                LoadImages.grass);
                        balloomList.remove(i);
                    }
                    else {
                        balloom.move();
                        changImageView(imageViews[balloomList.get(i).index],
                                balloom.realX, balloom.realY, balloom.image);
                        if (bomber.realX == balloom.realX && bomber.realY == balloom.realY) {
                            bomber.isLife = false;
                            checkGameOver = true;
                            imageViewBomber.setImage(LoadImages.img_playerdead);
                        }
                    }

                }
                for (int i = 0; i < Map.listOneal.size(); i ++) {
                    Oneal oneal = Map.listOneal.get(i);
                    if (!oneal.isLife) {
                        changImageView(imageViews[oneal.index], 0, 1, LoadImages.grass);
                        Map.listOneal.remove(i);
                    } else {
                        changImageView(imageViews[oneal.index], oneal.realX, oneal.realY, oneal.image);
                        oneal.move();
                    }
                }
            }
        },0, 700);

        Timer timerOneal = new Timer();
        timerOneal.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        },0, 500);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (checkGameOver) {
                    imageViewBomber.setImage(LoadImages.img_playerdead);
                }
                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    bomber.turnLeft();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                    buffIcons(bomber);
                    checkWinGame(bomber);
                }
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    bomber.turnRight();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                    buffIcons(bomber);
                    checkWinGame(bomber);
                }
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    bomber.goUp();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                    buffIcons(bomber);
                    checkWinGame(bomber);
                }
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                    buffIcons(bomber);
                    checkWinGame(bomber);
                    bomber.downWard();
                }
                if (event.getCode() == KeyCode.SPACE) {
                    if (hasBomb < limitBomb) {
                        bomber.placeBomb();
                        int placeX = bomber.placeX;
                        int placeY = bomber.placeY;
                        Bomb bomb = new Bomb(placeX, placeY);
                        ImageView imageViewBomb = bomb.imageView();
                        root.getChildren().add(imageViewBomb);
                        hasBomb++;
                        Timer timer1 = new Timer();
                        timer1.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                imageViewBomb.setImage(LoadImages.img_bombexploded);
                                showFire(placeX, placeY, bomber);
                                if (!bomber.isLife) {
                                    imageViewBomber.setImage(LoadImages.img_playerdead);
                                    checkGameOver = true;

                                        bomber.realX = Map.bomber.realX;
                                        bomb.realY = Map.bomber.realY;
                                        changImageView(imageViewBomber, bomber.realX,
                                                bomber.realY, LoadImages.img_playerdown);
                                    balloomList = Map.listballoom;
                                        System.out.println(balloomList.size());
                                        System.out.println(Map.listballoom.size());
                                }
                                Map.isFilled[placeX][placeY]  = false;
                                hasFire[placeX][placeY] = true;
                                Timer timer2 = new Timer();
                                timer2.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        offFire();
                                        changImageView(imageViewBomb, 0, 1, LoadImages.wall);
                                        hasBomb --;
                                    }
                                }, 700);
                            }
                        }, 2100);
                    }
                }
            }
        });
        sound_Background = new MediaPlayer(LoadImages.sound_backgound);
        sound_Background.play();
        root.getChildren().add(imageViewBomber);
        stage.setScene(scene);
        stage.show();
    }
}
