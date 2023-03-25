package com.example.breakout;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicItem{

    public Paddle(){
        height = canvasHeight * 0.02;
        width = canvasWidth * 0.2;
        x = canvasWidth * 0.5 - width * 0.5;
        y = canvasHeight * 0.9;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(x,y,width,height);
    }

    public void setPosition(double x){
        if(x+width*0.5 >= canvasWidth){
            this.x = canvasWidth-width;
        }else if(x-width*0.5 <= 0){
            this.x = 0;
        }else {
            this.x = x - width * 0.5;
        }
    }
}
