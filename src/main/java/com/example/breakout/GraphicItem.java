package com.example.breakout;

import javafx.scene.canvas.GraphicsContext;

public abstract class GraphicItem {
    protected static double canvasWidth, canvasHeight;
    protected double x, y, width, height;

    public static void setCanvasSize(double width, double height){
        canvasWidth = width;
        canvasHeight = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public abstract void draw(GraphicsContext graphicsContext);

}
