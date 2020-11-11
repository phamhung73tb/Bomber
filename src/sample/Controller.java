package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;



public class Controller extends Application {

    Stage stage;
    Group root = new Group();
    Scene scene = new Scene(root);

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(1000);
        this.stage = stage;
        Map.renderMap();
        LoadImages.loadImageBomber();
        Bomber bomber = new Bomber(Map.bomber.realX, Map.bomber.realY, Map.bomber.speed);
        ImageView[] img_bomber = new ImageView[2];
        img_bomber[0] = bomber.imageView();
        int limitBomb = 1;
        final int[] hasBomb = {0};

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
                        img_bomber[1] = LoadImages.showImage(bomber.realX, bomber.realY,
                                LoadImages.img_bomb);
                        Map.isFilled[(int) bomber.realX][(int) bomber.realY] = true;
                        root.getChildren().add(img_bomber[1]);
                        root.getChildren().add(img_bomber[0]);
                        hasBomb[0]++;
                    }

                }
            }
        });
        for (int i = 0; i < Map.listgrass.size(); i ++) {
            root.getChildren().add(Map.listgrass.get(i).imageView());
        }
        for (int i = 0; i < Map.listbirck.size(); i ++) {
            root.getChildren().add(Map.listbirck.get(i).imageView());
        }
        for (int i = 0; i < Map.listwall.size(); i ++) {
            root.getChildren().add(Map.listwall.get(i).imageView());
        }
        for (int i = 0; i < Map.listballoom.size(); i ++) {
            root.getChildren().add(Map.listballoom.get(i).imageView());
        }

        root.getChildren().add(img_bomber[0]);
        stage.setScene(scene);
        stage.show();
    }
}
