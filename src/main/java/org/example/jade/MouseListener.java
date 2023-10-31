package org.example.jade;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos,yPos, lastX, lastY;

    private boolean mouseButtonPress[] = new boolean[3];
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get(){
        if(instance == null){
            instance = new MouseListener();
        }
        return instance;
    }
}
