package com.myGame.renderEngine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

/**
 * Created by user on 16.09.15.
 */
public class DisplayMenager {


    // We need to strongly reference callback instances.
    private static GLFWErrorCallback errorCallback;
    private static GLFWKeyCallback keyCallback;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    // The window handle
    private static long window;

    public static void  createDisplay(){
//
//        String libopencv_java = "@lib/natives/";
//        System.load(libopencv_java);
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

//        // Initialize GLFW. Most GLFW functions will not work before doing this.
//        if ( glfwInit() != GL11.GL_TRUE )
//            throw new IllegalStateException("Unable to initialize GLFW");
//
//        // Configure our window
//        glfwDefaultWindowHints(); // optional, the current window hints are already the default
//        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
//        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
//
//        int WIDTH = 300;
//        int HEIGHT = 300;
//
//        // Create the window
//        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
//        if ( window == NULL )
//            throw new RuntimeException("Failed to create the GLFW window");
//
//        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
//            @Override
//            public void invoke(long window, int key, int scancode, int action, int mods) {
//                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
//            }
//        });
//
//        // Get the resolution of the primary monitor
//        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//        // Center our window
//        glfwSetWindowPos(
//                window,
//                (GLFWvidmode.width(vidmode) - WIDTH) / 2,
//                (GLFWvidmode.height(vidmode) - HEIGHT) / 2
//        );
//
//        // Make the OpenGL context current
//        glfwMakeContextCurrent(window);
//        // Enable v-sync
//        glfwSwapInterval(1);
//
//        // Make the window visible
//        glfwShowWindow(window);

    }

    public static void  updateDisplay(){}

    public static void  closeDisplay(){}
}
