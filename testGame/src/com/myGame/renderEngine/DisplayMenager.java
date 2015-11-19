package com.myGame.renderEngine;

import org.lwjgl.Sys;
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

    private static long lastFrameTime;
    private static float delta;
    // The window handle

    public static void  createDisplay(){
        ContextAttribs attribs = new ContextAttribs(3,2)
        .withForwardCompatible(true)
        .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("myDisplay");
        } catch (LWJGLException e){
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        lastFrameTime = getCurrentTime();
    }

    public static void  updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime)/1000f;
        lastFrameTime=currentFrameTime;
    }

    public static float getFrameTimeSeconds(){
        return delta;
    }

    public static void  closeDisplay(){
        Display.destroy();
    }

    private static long getCurrentTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}