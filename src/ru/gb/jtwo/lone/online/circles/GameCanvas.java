package ru.gb.jtwo.lone.online.circles;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    MainCircles gameController;
    long lastFrameTime;
    double time = 0d;

    GameCanvas(MainCircles gameController) {
        this.gameController = gameController;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //60fps
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        gameController.onDrawFrame(this, g, deltaTime);
        lastFrameTime = currentTime;
        time += deltaTime; // счетчик времени для изменения фона
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (time >= 10d){ // условие для изменения фона и процедуры изменения
            Color colorbackground = new Color(
                    (int)(Math.random() * 255),
                    (int)(Math.random() * 255),
                    (int)(Math.random() * 255)
            );
            setBackground(colorbackground);
            time =0;
        }
        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
