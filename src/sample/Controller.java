package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
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

    public static void main(String[] args) {
        launch(args);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
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


    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(1000);
        this.stage = stage;
        Map.renderMap();
        LoadImages.loadImageBomber();
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
        },0, 400);

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
                        img_bomber[1] = LoadImages.showImage((int) bomber.realX, (int) bomber.realY,
                                LoadImages.img_bomb);
                        Map.isFilled[(int) bomber.realX][(int) bomber.realY] = true;
                        int placeX = (int) bomber.realX;
                        int placeY = (int)bomber.realY;
                        root.getChildren().add(img_bomber[1]);
                        root.getChildren().add(img_bomber[0]);
                        hasBomb[0]++;
                        Timer timer1 = new Timer();
                        timer1.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                img_bomber[1].setImage(LoadImages.img_bombexploded);
                                Timer timer2 = new Timer();
                                timer2.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        img_bomber[1].setImage(LoadImages.grass);
                                        hasBomb[0] --;
                                        Map.isFilled[placeX][placeY]  = false;
                                    }
                                }, 1000);
                            }
                        }, 3000);
                    }
                }
            }
        });

        root.getChildren().add(img_bomber[0]);
        stage.setScene(scene);
        stage.show();
    }
}
