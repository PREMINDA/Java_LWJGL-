package org.example.jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

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

    public static void mousePosCallBack(long window, double xPos, double yPos){
        get().lastX = get().xPos;
        get().lastY = get().yPos;

        get().xPos = xPos;
        get().yPos = yPos;

        get().isDragging = get().mouseButtonPress[0] || get().mouseButtonPress[1] || get().mouseButtonPress[2];


    }

    public static void mouseButtonCallback(long window,int button, int action, int mods) {
        if(action == GLFW_PRESS){
            if (button < get().mouseButtonPress.length){
                get().mouseButtonPress[button] = true;
            }
            get().mouseButtonPress[button] = true;
        }else if(action == GLFW_RELEASE){
            if (button < get().mouseButtonPress.length) {
                get().mouseButtonPress[button] = false;
                get().isDragging = false;
            }
        }

    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX(){
        return (float) get().xPos;
    }

    public static float getY(){
        return (float) get().yPos;
    }

    public static float getDx(){
        return (float) (get().lastX-get().xPos);
    }

    public static float getDy(){
        return (float) (get().lastY-get().yPos);
    }

    public static float getScrollX(){
        return (float) get().scrollX;
    }

    public static float getScrollY(){
        return (float) get().scrollY;
    }

    public static boolean isDragging(){
        return get().isDragging;
    }

    public static boolean isButtonDown(int button){
        if(button>get().mouseButtonPress.length)return false;
        return get().mouseButtonPress[button];
    }
}
