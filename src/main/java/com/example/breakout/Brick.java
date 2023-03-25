package com.example.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicItem{
    private static int gridRows, gridCols;
    private final Color color;
    public enum CrushType{NoCrush, HorizontalCrush, VerticalCrush}


    public static void setGridSize(int rows, int cols){
        gridRows = rows;
        gridCols = cols;
    }

    public Brick(int x, int y, Color color){
        width = canvasWidth/gridCols;
        height = canvasHeight/gridRows;
        this.x = 0+width*y;
        this.y = 0+height*x;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color.darker());
        graphicsContext.fillRect(x, y, width, height);
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y+height*0.15, width*0.9, height*0.85);
    }

    public CrushType shouldBeCrushed(Point2D ul, Point2D ur, Point2D ll, Point2D lr, double speed){
        if((((ul.getX() >= getX()+speed && ul.getX() <= getX()+width) && (ul.getY() >= getY() && ul.getY()<=getY()+height)) &&
                ((ll.getX() >= getX()+speed && ll.getX() <= getX()+width) && (ll.getY() >= getY() && ll.getY() <= getY()+height))) ||
                (((ur.getX() >= getX() && ur.getX() <= getX()+speed) && (ur.getY() >= getY() && ur.getY() <= getY()+height)) &&
                        ((lr.getX() >= getX() && lr.getX() <= getX()+speed) && (lr.getY() >= getY() && lr.getY() <= getY()+height)))){
            return CrushType.HorizontalCrush;
        }else if((((ul.getX() >= getX() && ul.getX() <= getX()+width) && (ul.getY() <= getY()+height && ul.getY() >= getY()+speed)) &&
                (ur.getX() >= getX() && ur.getX() <= getX()+width) && (ur.getY() <= getY()+height && ur.getY() >= getY()+speed)) ||
        ((ll.getX() >= getX() && ll.getX() <= getX()+width) && (ll.getY() >= getY() && ll.getY() <= getY()+speed)) &&
                ((lr.getX() >= getX() && lr.getX() <= getX()+width) && (lr.getY() >= getY() && lr.getY() <= getY()+speed))){
            return CrushType.VerticalCrush;
        }else{
            return CrushType.NoCrush;
        }
    }
}