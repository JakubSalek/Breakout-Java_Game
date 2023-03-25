package com.example.breakout;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas{
    private final GraphicsContext graphicsContext = getGraphicsContext2D();
    private Paddle paddle;
    private Boolean gameRunning = false;
    private Ball ball;
    private List<Brick> brickList;

    private final AnimationTimer animationTimer = new AnimationTimer() {
        private long last;
        @Override
        public void start() {
            super.start();
            last = System.nanoTime();
        }
        @Override
        public void handle(long l) {
            double delta = (l - last)/1_000_000_000.;

            if(!gameRunning){
                    ball.setPosition(new Point2D(paddle.getX()+paddle.width/2, paddle.getY()));
            }
            else{
                ball.previousX = ball.x;
                ball.previousY = ball.y;
                ball.updatePosition(delta);
                if(shouldBallBounceHorizontally()) ball.bounceHorizontally();
                else if(shouldBallBounceVertically()) ball.bounceVertically();
                else if(shouldBallBounceFromPaddle()) ball.bounceVertically();
                Brick temp = null;
                for(Brick brick : brickList){
                    if(brick.shouldBeCrushed(ball.upperLeft(), ball.upperRight(), ball.lowerLeft(), ball.lowerRight(), ball.velocity*delta) == Brick.CrushType.HorizontalCrush){
                        ball.bounceHorizontally();
                        ball.velocity = ball.velocity*1.02;
                        temp = brick;
                    }else if(brick.shouldBeCrushed(ball.upperLeft(), ball.upperRight(), ball.lowerLeft(), ball.lowerRight(), ball.velocity*delta) == Brick.CrushType.VerticalCrush){
                        ball.bounceVertically();
                        ball.velocity = ball.velocity*1.02;
                        temp = brick;
                    }
                }
                brickList.remove(temp);
                if(brickList.isEmpty()){
                    gameRunning = false;
                }
            }
            draw();
            last = l;
        }
    };

    public GameCanvas(){
        super(600, 800);
        GraphicItem.setCanvasSize(getWidth(), getHeight());
    }

    public void draw(){
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());
        paddle.draw(graphicsContext);
        ball.draw(graphicsContext);
        for(Brick brick : brickList){
            brick.draw(graphicsContext);
        }
        this.setOnMouseMoved(event -> paddle.setPosition(event.getX()));
        this.setOnMouseClicked(event -> gameRunning = true);
                animationTimer.start();
    }

    public void initialize(){
        paddle = new Paddle();
        ball = new Ball();
        brickList = new ArrayList<>();
        loadLevel();
    }


    private Boolean shouldBallBounceHorizontally(){
        return (ball.getX()+ball.getWidth() >= getWidth()) || (ball.getX() <= 0);
    }

    private Boolean shouldBallBounceVertically(){
        return ball.getY() <= 0;
    }

    private Boolean shouldBallBounceFromPaddle(){
        return (ball.getY() + ball.getHeight() >= paddle.getY() &&
                ball.getY() + ball.getHeight() <= paddle.getY()+paddle.getHeight() &&
                ball.getX() >= paddle.getX() &&
                ball.getX() <= paddle.getX()+paddle.getWidth());
    }

    private void loadLevel(){
        Brick.setGridSize(20, 10);
        for(int i=2; i<=7; i++) {
            for (int j = 0; j < 10; j++) {
                switch (i) {
                    case 2 -> brickList.add(new Brick(i, j, Color.GREEN));
                    case 3 -> brickList.add(new Brick(i, j, Color.LIME));
                    case 4 -> brickList.add(new Brick(i, j, Color.CYAN));
                    case 5 -> brickList.add(new Brick(i, j, Color.BLUE));
                    case 6 -> brickList.add(new Brick(i, j, Color.VIOLET));
                    case 7 -> brickList.add(new Brick(i, j, Color.RED));
                }
            }
        }
    }
}