package sample;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Map {

    public static List<Wall> listwall = new ArrayList<>();
    public static List<Brick> listbirck = new ArrayList<>();
    public static List<Grass> listgrass = new ArrayList<>();
    public static List<Balloom> listballoom = new ArrayList<>();


    public static Bomber bomber;

    public static boolean[][] isFilled = new boolean[100][100];

    public static void renderMap() throws FileNotFoundException {
        LoadImages.loadImageGraphics();
        LoadImages.loadBalloom();
        File map = new File("D:\\Java\\Bomber\\src\\sample\\map.txt");
        Scanner read = new Scanner(map);
        String first = read.nextLine();
        String[] mapSize = first.split(" ", 3);
        int width = Integer.parseInt(mapSize[2]);
        int currow = 1;
        while (read.hasNextLine()) {
            String oneline = read.nextLine();
            for (int i = 0; i < width; i ++) {
                Grass grass = new Grass(i, currow);
                listgrass.add(grass);
                char kitu = oneline.charAt(i);
                if (kitu == '#') {
                    Wall wall = new Wall(i, currow);
                    listwall.add(wall);
                    isFilled[i][currow] = true;
                }
                if (kitu == '*') {
                    Brick brick = new Brick(i, currow);
                    listbirck.add(brick);
                    isFilled[i][currow] = true;
                }
                if (kitu == 'p') {
                    bomber = new Bomber(i, currow, 0.25);
                }
                if (kitu == '1') {
                    Balloom balloom = new Balloom(i, currow);
                    listballoom.add(balloom);
                }
            }
            currow ++;
        }
    }

}
