package com.myGame.renderEngine;

import org.lwjgl.opengl.GL11;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 * Created by user on 16.09.15.
 */
public class DisplayMenager {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int FPS_CAP = 120;
    // The window handle

    public static void  createDisplay(){

       // System.setProperty("org.lwjgl.librarypath", "lib/natives");

        ContextAttribs attribs = new ContextAttribs(3,2);
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("myDisplay");
        } catch (LWJGLException e){
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    public static void  updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }

    public static void  closeDisplay(){
        Display.destroy();
    }
}