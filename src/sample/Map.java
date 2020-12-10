package sample;


import sample.Animations.Bomber.Bomber;
import sample.Animations.Enemy.Balloom;
import sample.Animations.Enemy.Oneal;
import sample.Graphics.Brick;
import sample.Graphics.Grass;
import sample.Graphics.Wall;
import sample.Icons.Portal;
import sample.Icons.PowerUpBombs;
import sample.Icons.PowerUpFlames;
import sample.Icons.PowerUpSpeed;
import sample.LoadRes.LoadResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {

    public static List<Wall> listwall = new ArrayList<>();
    public static List<Brick> listbirck = new ArrayList<>();
    public static List<Grass> listgrass = new ArrayList<>();
    public static List<Balloom> listballoom = new ArrayList<>();
    public static List<Oneal> listOneal = new ArrayList<>();

    public static Portal portal;
    public static PowerUpBombs powerUpBombs;
    public static PowerUpFlames powerUpFlames;
    public static PowerUpSpeed powerUpSpeed;

    public static Bomber bomber;

    public static boolean[][] isFilled = new boolean[100][100];
    public static boolean[][] isBrick = new boolean[100][100];

    public static void renderMap() throws FileNotFoundException {
        LoadResources.loadImageGraphics();
        LoadResources.loadBalloom();
        LoadResources.loadImageBomber();
        LoadResources.loadImageBomb();
        LoadResources.loadIcons();
        LoadResources.loadOneal();
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
                    isBrick[i][currow] = true;
                }
                if (kitu == 'p') {
                    bomber = new Bomber(i, currow, 0.25);
                }
                if (kitu == '1') {
                    Balloom balloom = new Balloom(i, currow, 0.125);
                    listballoom.add(balloom);
                }
                if (kitu == '2') {
                    Oneal oneal = new Oneal(i, currow, 0.25);
                    listOneal.add(oneal);
                }
                if (kitu == 'x') {
                    Brick brick = new Brick(i, currow);
                    listbirck.add(brick);
                    portal = new Portal(i, currow);
                    isBrick[i][currow] = true;
                    isFilled[i][currow] = true;
                }
                if (kitu == 'b') {
                    Brick brick = new Brick(i, currow);
                    listbirck.add(brick);
                    powerUpBombs = new PowerUpBombs(i, currow);
                    isBrick[i][currow] = true;
                    isFilled[i][currow] = true;
                }
                if (kitu == 'f') {
                    Brick brick = new Brick(i, currow);
                    listbirck.add(brick);
                    powerUpFlames = new PowerUpFlames(i, currow);
                    isBrick[i][currow] = true;
                    isFilled[i][currow] = true;
                }
                if (kitu == 's') {
                    Brick brick = new Brick(i, currow);
                    listbirck.add(brick);
                    powerUpSpeed = new PowerUpSpeed(i, currow);
                    isBrick[i][currow] = true;
                    isFilled[i][currow] = true;
                }
            }
            currow ++;
        }
    }

}
