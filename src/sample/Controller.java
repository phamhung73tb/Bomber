package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
    public void showFire(int x, int y) {
        for (int i = 1; i <= sizeOfFire; i++) {
            if (!Map.isFilled[x +i][y]) {
                int index = showIndex(x + i, y);
                imageViews[index].setImage(LoadImages.img_explosionhorizontal);
                hasFire[x + i][y] = true;
            } else {
                if (Map.isBrick[x + i][y])
                    hasFire[x + i][y] = true;
            }
            if (!Map.isFilled[x - i][y]) {
                int index = showIndex(x - i, y);
                imageViews[index].setImage(LoadImages.img_explosionhorizontal);
                hasFire[x - i][y] = true;
            } else {
                if (Map.isBrick[x - i][y])
                    hasFire[x - i][y] = true;
            }
            if (!Map.isFilled[x][y - i]) {
                int index = showIndex(x, y - i);
                imageViews[index].setImage(LoadImages.img_explosionvertical);
                hasFire[x][y - i] = true;
            } else {
                if (Map.isBrick[x][y - i])
                    hasFire[x][y - i] = true;
            }
            if (!Map.isFilled[x][y + i]) {
                int index = showIndex(x, y + i);
                imageViews[index].setImage(LoadImages.img_explosionvertical);
                hasFire[x][y + i] = true;
            } else {
                if (Map.isBrick[x][y + i])
                    hasFire[x][y + i] = true;
            }
        }
    }

    public void boom() {
        for (int i = 0; i < 50; i ++)
            for (int j = 0; j < 30; j++) {
                if (hasFire[i][j]) {
                    if (Map.isBrick[i][j])
                        Map.isFilled[i][j] = false;
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
        ImageView[] img_bomber = new ImageView[2];
        img_bomber[0] = bomber.imageView();
        int limitBomb = 1;
        final int[] hasBomb = {0};
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            final int[] type = new int[10];
            @Override
            public void run() {
                for (int i = 0; i < Map.listballoom.size(); i ++) {
                    Balloom balloom = Map.listballoom.get(i);

                    if (type[i] == 0) {
                        int nextX = (int) (balloom.realX + 0.5) - 1;
                        if (!Map.isFilled[nextX][(int) balloom.realY]) {
                            type[i] = 0;
                            balloom.realX = balloom.realX - balloom.speed;
                            balloom.image = LoadImages.img_balloomleft;
                        } else {
                            type[i] = 1;
                        }
                    } else {
                        if (!Map.isFilled[(int) balloom.realX + 1][(int) balloom.realY]) {
                            balloom.realX = balloom.realX + balloom.speed;
                            balloom.image = LoadImages.img_balloomright;
                        } else type[i] = 0;
                    }
                    imageViews[Map.listballoom.get(i).index].setImage(balloom.image);
                    imageViews[Map.listballoom.get(i).index].setX(balloom.realX * 30);
                    imageViews[Map.listballoom.get(i).index].setY(balloom.realY * 30);
                }
            }
        },0, 600);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    root.getChildren().remove(img_bomber[0]);
                    bomber.turnLeft();
                    img_bomber[0] = bomber.imageView();
                    root.getChildren().add(img_bomber[0]);
                }
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    root.getChildren().remove(img_bomber[0]);
                    bomber.turnRight();
                    img_bomber[0] = bomber.imageView();
                    root.getChildren().add(img_bomber[0]);
                }
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    root.getChildren().remove(img_bomber[0]);
                    bomber.goUp();
                    img_bomber[0] = bomber.imageView();
                    root.getChildren().add(img_bomber[0]);
                }
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    root.getChildren().remove(img_bomber[0]);
                    bomber.downWard();
                    img_bomber[0] = bomber.imageView();
                    root.getChildren().add(img_bomber[0]);
                }
                if (event.getCode() == KeyCode.SPACE) {
                    if (hasBomb[0] < limitBomb) {
                        root.getChildren().remove(img_bomber[0]);
                        int placeX = (int) bomber.realX;
                        int placeY = (int)bomber.realY;
                        Map.isFilled[placeX][placeY] = true;
                        Bomb bomb = new Bomb(placeX, placeY);
                        ImageView imageViewBomb = bomb.imageView();
                        root.getChildren().add(imageViewBomb);
                        root.getChildren().add(img_bomber[0]);
                        hasBomb[0]++;
                        Timer timer1 = new Timer();
                        timer1.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                imageViewBomb.setImage(LoadImages.img_bombexploded);
                                showFire(placeX, placeY);
                                Timer timer2 = new Timer();
                                timer2.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageViewBomb.setImage(LoadImages.grass);
                                        hasBomb[0] --;
                                        Map.isFilled[placeX][placeY]  = false;
                                        boom();
                                    }
                                }, 500);
                            }
                        }, 2000);
                    }
                }
            }
        });

        root.getChildren().add(img_bomber[0]);
        stage.setScene(scene);
        stage.show();
    }
}
