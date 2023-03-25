package com.example.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicItem{
    private Point2D moveVector = new Point2D(1, -1).normalize();
    public double velocity = 300;
    public double previousX, previousY;

    public Ball(){
        width = height = canvasWidth*0.02;
        x = y = 0;
    }

    public void updatePosition(double delta){
        x += moveVector.getX()*velocity*delta;
        y += moveVector.getY()*velocity*delta;
    }

    public void setPosition(Point2D position){
        x = position.getX() - width*0.5;
        y = position.getY() - height;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void bounceHorizontally(){
        moveVector = new Point2D(-moveVector.getX(), moveVector.getY()).normalize();
        x = previousX;
        y = previousY;
    }

    public void bounceVertically(){
        moveVector = new Point2D(moveVector.getX(), -moveVector.getY()).normalize();
        x = previousX;
        y = previousY;
    }

    public Point2D upperLeft(){
        return new Point2D(getX(), getY());
    }

    public Point2D upperRight(){
        return new Point2D(getX()+width, getY());
    }

    public Point2D lowerLeft(){
        return new Point2D(getX(), getY()+height);
    }

    public Point2D lowerRight(){
        return new Point2D(getX()+width, getY()+height);
    }
}
