package org.example.jade;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int height,width;
    private long glfwWindow;
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

    public void run() {

        try {
            inti();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void inti() throws IllegalAccessException {
        //setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //init GLFW
        if(!glfwInit()){
            throw new IllegalAccessException("Unable to init GLFW");
        }

        //Config GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE );
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE );
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE );

        //Create the window
         glfwWindow = glfwCreateWindow(this.width,this.height,this.title,NULL,NULL);

         if(glfwWindow == NULL){
             throw new IllegalStateException("Failed to create the window");
         }

         glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallBack);
         glfwSetMouseButtonCallback(glfwWindow,MouseListener::mouseButtonCallback);
         glfwSetScrollCallback(glfwWindow,MouseListener::mouseScrollCallback);
         glfwSetKeyCallback(glfwWindow,KeyListener::keyCallback);
        //Make the OpenGL context
         glfwMakeContextCurrent(glfwWindow);

         //Enable v-sync
        glfwSwapInterval(1);

        //make The window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }
    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){
            //Poll events
            glfwPollEvents();

            glClearColor(1.0f,0.0f,0.0f,1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            if(KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
                System.out.println("Space is Pressed");
            }
            glfwSwapBuffers(glfwWindow);
        }
    }
}
