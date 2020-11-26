package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


public class Controller extends Application {

    Stage stage;
    Group root = new Group();
    Scene scene = new Scene(root);
    ImageView[] imageViews = new ImageView[2000];
    int indexoflistImage = 0;
    boolean[][] hasFire = new boolean[100][100];
    int sizeOfFire = 1;
    boolean checkGameOver = false;
    int hasBomb = 0;
    int limitBomb = 1;



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
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x +i][y]) {
                int index = showIndex(x + i, y);
                imageViews[index].setImage(LoadImages.img_explosionhorizontal);
                hasFire[x + i][y] = true;
            } else {
                if (Map.isBrick[x + i][y]) {
                    int index = showIndex(x + i, y);
                    hasFire[x + i][y] = true;
                    imageViews[index].setImage(LoadImages.brick_ex);
                    Map.isBrick[x + i][y] = false;
                    Map.isFilled[x +i][y] = false;
                }
            }

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
            }
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
            }
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
        bomber.place(x, y);
        if (hasFire[bomber.placeX][bomber.placeY])
            bomber.isLife = false;
    }

    public void offFire() {
        for (int i = 0; i < 50; i ++)
            for (int j = 0; j < 30; j++) {
                if (hasFire[i][j]) {
                    if (Map.isBrick[i][j]) {
                        Map.isFilled[i][j] = false;
                        Map.isBrick[i][j] = false;
                    }
                    imageViews[showIndex(i, j)].setImage(LoadImages.grass);
                    hasFire[i][j] = false;
                }
            }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(1000);
        this.stage = stage;
        Map.renderMap();
        LoadImages.loadImageBomber();
        LoadImages.loadImageBomb();
        addImage();
        Bomber bomber = new Bomber(Map.bomber.realX, Map.bomber.realY, Map.bomber.speed);
        ImageView imageViewBomber = bomber.imageView();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < Map.listballoom.size(); i ++) {
                    Balloom balloom = Map.listballoom.get(i);
                    if (!balloom.isLife) {
                        changImageView(imageViews[Map.listballoom.get(i).index], 0, 1,
                                LoadImages.grass);
                        Map.listballoom.remove(i);

                    }
                    else {
                        balloom.move();
                        changImageView(imageViews[Map.listballoom.get(i).index],
                                balloom.realX, balloom.realY, balloom.image);
                    }

                }
            }
        },0, 700);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    bomber.turnLeft();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);

                }
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    bomber.turnRight();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                }
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    bomber.goUp();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                }
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
                    bomber.downWard();
                    changImageView(imageViewBomber, bomber.realX, bomber.realY, bomber.image);
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
                        }, 2000);
                    }
                }
            }
        });
        root.getChildren().add(imageViewBomber);
        stage.setScene(scene);
        stage.show();
    }
}
