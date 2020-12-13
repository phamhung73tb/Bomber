package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Animations.Bomber.Bomber;
import sample.Animations.Enemy.Balloom;
import sample.Animations.Enemy.Oneal;
import sample.Bomb.Bomb;
import sample.Graphics.Brick;
import sample.Graphics.Grass;
import sample.Icons.PowerUpFlames;
import sample.Icons.PowerUpSpeed;
import sample.LoadRes.LoadResources;


import java.util.List;
import java.util.Random;
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
    boolean checkWingame = false;
    int hasBomb = 0;
    int limitBomb = 1;
    long time = 0;

    MediaPlayer sound_Background;
    MediaPlayer sound_enemyDie;
    MediaPlayer sound_bomberDie;
    MediaPlayer sound_pickItem;
    MediaPlayer sound_putBomb;
    MediaPlayer sound_bombbang;
    MediaPlayer sound_wingame;
    MediaPlayer sound_beep;

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
        for (int i = 0; i < Map.listOneal.size(); i++) {
            imageViews[indexoflistImage] = Map.listOneal.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listOneal.get(i).index = indexoflistImage;
            indexoflistImage ++;
        }
        for (int i = 0; i < Map.listballoom.size(); i ++) {
            imageViews[indexoflistImage] = Map.listballoom.get(i).imageView();
            root.getChildren().add(imageViews[indexoflistImage]);
            Map.listballoom.get(i).index = indexoflistImage;
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
                imageViews[index].setImage(LoadResources.img_explosionhorizontal);
                hasFire[x + i][y] = true;
            } else {
                if (Map.isBrick[x + i][y]) {
                    int index = showIndex(x + i, y);
                    hasFire[x + i][y] = true;
                    imageViews[index].setImage(LoadResources.brick_ex);
                    Map.isBrick[x + i][y] = false;
                    Map.isFilled[x + i][y] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x - i][y]) {
                int index = showIndex(x - i, y);
                imageViews[index].setImage(LoadResources.img_explosionhorizontal);
                hasFire[x - i][y] = true;
            } else {
                if (Map.isBrick[x - i][y]) {
                    int index = showIndex(x - i, y);
                    hasFire[x - i][y] = true;
                    imageViews[index].setImage(LoadResources.brick_ex);
                    Map.isBrick[x - i][y] = false;
                    Map.isFilled[x - i][y] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x][y - i]) {
                int index = showIndex(x, y - i);
                imageViews[index].setImage(LoadResources.img_explosionvertical);
                hasFire[x][y - i] = true;
            } else {
                if (Map.isBrick[x][y - i]) {
                    int index = showIndex(x, y - i);
                    hasFire[x][y - i] = true;
                    imageViews[index].setImage(LoadResources.brick_ex);
                    Map.isBrick[x][y - i] = false;
                    Map.isFilled[x][y - i] = false;
                }
                break;
            }
        }
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x][y + i]) {
                int index = showIndex(x, y + i);
                imageViews[index].setImage(LoadResources.img_explosionvertical);
                hasFire[x][y + i] = true;
            } else {
                if (Map.isBrick[x][y + i]) {
                    int index = showIndex(x, y + i);
                    hasFire[x][y + i] = true;
                    imageViews[index].setImage(LoadResources.brick_ex);
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
                imageViews[balloom.index].setImage(LoadResources.img_balloomddead);
                sound_enemyDie = new MediaPlayer(LoadResources.sound_enemyDie);
                sound_enemyDie.play();
                Map.listballoom.get(i).isLife = false;
            }
        }
        for (int i = 0; i < Map.listOneal.size(); i ++) {
            Oneal oneal = Map.listOneal.get(i);
            oneal.place(x, y);
            if (hasFire[oneal.placeX][oneal.placeY]) {
                imageViews[oneal.index].setImage(LoadResources.img_onealdead);
                sound_enemyDie = new MediaPlayer(LoadResources.sound_enemyDie);
                sound_enemyDie.play();
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
                    imageViews[showIndex(i, j)].setImage(LoadResources.grass);
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
            imageViews[showIndex(i, j)].setImage(LoadResources.grass);
            Map.powerUpBombs.realX = 0;
            Map.powerUpBombs.realY = 0;
            sound_pickItem = new MediaPlayer(LoadResources.sound_pickItem);
            sound_pickItem.play();
        }
        if (Map.powerUpFlames.realX == x && Map.powerUpFlames.realY == y) {
            sizeOfFire ++;
            imageViews[showIndex(i, j)].setImage(LoadResources.grass);
            Map.powerUpFlames = new PowerUpFlames(0, 0);
            sound_pickItem = new MediaPlayer(LoadResources.sound_pickItem);
            sound_pickItem.play();
        }
        if (Map.powerUpSpeed.realX == x && Map.powerUpSpeed.realY == y) {
            bomber.speed *= 2;
            imageViews[showIndex(i, j)].setImage(LoadResources.grass);
            Map.powerUpSpeed = new PowerUpSpeed(0, 0);
            sound_pickItem = new MediaPlayer(LoadResources.sound_pickItem);
            sound_pickItem.play();
        }
    }


    StackPane anchorPane = new StackPane();
    Button button = new Button("OK");

    public void checkWinGame(Bomber bomber) {
        double x = bomber.realX;
        double y = bomber.realY;
        if (balloomList.size() == 0 && Map.listOneal.size() == 0) {
            if (Map.portal.realX == x && Map.portal.realY == y) {
                checkGameOver = false;
                checkWingame = true;
            }
        }
    }
    public void checkDeadwithBalloom(Balloom balloom, Bomber bomber) {
        if (balloom.realX == bomber.realX) {
            if (Math.abs(balloom.realY - bomber.realY) < 1)
                bomber.isLife = false;
        }
        if (balloom.realY == bomber.realY) {
            if (Math.abs(balloom.realX - bomber.realX) < 1)
                bomber.isLife = false;
        }
    }
    public void checkDeadwithOneal(Oneal oneal, Bomber bomber) {
        if ((int) oneal.realX == (int) bomber.realX) {
            if (Math.abs(oneal.realY - bomber.realY) < 1)
                bomber.isLife = false;
        }
        if ((int) oneal.realY == (int) bomber.realY) {
            if (Math.abs(oneal.realX - bomber.realX) < 1)
                bomber.isLife = false;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(1000);
        this.stage = stage;
        LoadResources.loadSound();
        Map.renderMap();
        addImage();
        Bomber bomber = new Bomber(Map.bomber.realX, Map.bomber.realY, Map.bomber.speed);
        ImageView imageViewBomber = bomber.imageView();
        AnimationTimer animationGame = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (time % 20 == 0) {
                    for (int i = 0; i < balloomList.size(); i ++) {
                        Balloom balloom = balloomList.get(i);
                        if (!balloom.isLife) {
                            changImageView(imageViews[balloomList.get(i).index], 0, 1,
                                    LoadResources.grass);
                            balloomList.remove(i);
                        }
                        else {
                            balloom.move();
                            changImageView(imageViews[balloomList.get(i).index],
                                    balloom.realX, balloom.realY, balloom.image);
                            checkDeadwithBalloom(balloom, bomber);
                            if (!bomber.isLife) {
                                imageViewBomber.setImage(LoadResources.img_playerdead);
                                checkGameOver = true;
                            }

                        }

                    }
                    for (int i = 0; i < Map.listOneal.size(); i ++) {
                        Oneal oneal = Map.listOneal.get(i);
                        if (!oneal.isLife) {
                            changImageView(imageViews[oneal.index], 0, 1, LoadResources.grass);
                            Map.listOneal.remove(i);
                        } else {
                            oneal.move();
                            int rand = 1;
                            long timenow = 0;
                            if (time % 200 == 0) {
                                rand = new Random().nextInt(2);
                                timenow = time;
                            }
                            if (rand == 0) {
                                if (time < (timenow + 200))
                                    oneal.image = LoadResources.grass;
                            }
                            changImageView(imageViews[oneal.index], oneal.realX, oneal.realY, oneal.image);
                            checkDeadwithOneal(oneal, bomber);
                            if (!bomber.isLife) {
                                imageViewBomber.setImage(LoadResources.img_playerdead);
                                checkGameOver = true;
                            }

                        }
                    }
                }
                if(checkGameOver && !checkWingame){
                    checkWingame = true;
                    anchorPane.setMaxSize(200,200);
                    anchorPane.setMinSize(200,200);
                    anchorPane.setLayoutX(400);
                    anchorPane.setLayoutY(100);
                    anchorPane.setStyle("-fx-background-color:white");
                    Label label = new Label("Defeat!");
                    label.setTranslateY(-20);
                    label.setFont(new Font(30));
                    button.setTranslateY(30);
                    button.setFont(new Font(15));
                    anchorPane.getChildren().addAll(label,button);
                    root.getChildren().addAll(anchorPane);
                    sound_Background.stop();
                    sound_bomberDie = new MediaPlayer(LoadResources.sound_bomberDie);
                    sound_bomberDie.play();
                    stop();
                }
                if(checkWingame && !checkGameOver){
                    checkGameOver = true;
                    anchorPane.setMaxSize(200,200);
                    anchorPane.setMinSize(200,200);
                    anchorPane.setLayoutX(400);
                    anchorPane.setLayoutY(100);
                    anchorPane.setStyle("-fx-background-color:white");
                    Label label = new Label("Victory!");
                    label.setTranslateY(-20);
                    label.setFont(new Font(30));
                    button.setTranslateY(30);
                    button.setFont(new Font(15));
                    anchorPane.getChildren().addAll(label,button);
                    root.getChildren().addAll(anchorPane);
                    sound_Background.stop();
                    sound_wingame = new MediaPlayer(LoadResources.sound_wingame);
                    sound_wingame.play();
                }
                time ++;
            }

        };
        animationGame.start();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
                animationGame.stop();
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                for (int i = 0; i < Map.listOneal.size(); i ++) {
                    Oneal oneal = Map.listOneal.get(i);
                    if (bomber.realX == oneal.realX && bomber.realY == oneal.realY) {
                        bomber.isLife = false;
                        checkGameOver = true;
                        imageViewBomber.setImage(LoadResources.img_playerdead);
                        sound_bomberDie = new MediaPlayer(LoadResources.sound_bomberDie);
                        sound_bomberDie.play();

                    }
                }
                for (int i = 0; i < balloomList.size(); i ++) {
                    Balloom balloom = Map.listballoom.get(i);
                    if (bomber.realX == balloom.realX && bomber.realY == balloom.realY) {
                        bomber.isLife = false;
                        checkGameOver = true;
                        imageViewBomber.setImage(LoadResources.img_playerdead);
                        sound_bomberDie = new MediaPlayer(LoadResources.sound_bomberDie);
                        sound_bomberDie.play();
                    }
                }
                if (!checkGameOver) {
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
                            sound_putBomb = new MediaPlayer(LoadResources.sound_putBomb);
                            sound_putBomb.play();
                            sound_beep = new MediaPlayer(LoadResources.sound_beep);
                            sound_beep.play();
                            hasBomb++;

                            Timer timer1 = new Timer();
                            timer1.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    imageViewBomb.setImage(LoadResources.img_bombexploded);
                                    sound_bombbang = new MediaPlayer(LoadResources.sound_bombbang);
                                    sound_bombbang.play();
                                    showFire(placeX, placeY, bomber);
                                    if (!bomber.isLife && !checkGameOver) {
                                        imageViewBomber.setImage(LoadResources.img_playerdead);
                                        checkGameOver = true;
                                    }
                                    Map.isFilled[placeX][placeY]  = false;
                                    hasFire[placeX][placeY] = true;
                                    Timer timer2 = new Timer();
                                    timer2.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            offFire();
                                            changImageView(imageViewBomb, 0, 1, LoadResources.wall);
                                            hasBomb --;
                                        }
                                    }, 200);
                                }
                            }, 2200);
                        }
                    }
                }

            }
        });
        sound_Background = new MediaPlayer(LoadResources.sound_backgound);
        //sound_Background.play();
        root.getChildren().add(imageViewBomber);
        stage.setScene(scene);
        stage.show();
    }
}
