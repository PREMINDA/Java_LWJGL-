package org.example.jade;

public class Window {
    private int height,width;
    private String title;

    private static Window window = null;
    private Window(){
        this.height = 1080;
        this.width = 1920;
        this.title = "Mario";
    }

    public static Window get(){
        if (window == null){
            window = new Window();
        }
        return window;
    }

    public void run(){
        System.out.println("Run LWJGL");
    }
}
