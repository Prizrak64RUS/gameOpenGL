package com.myGame;

/**
 * Created by user on 16.09.15.
 */
import com.myGame.renderEngine.DisplayMenager;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL45.*;

public class Main {
    public static void main(String[] args) {

        DisplayMenager.createDisplay();

        while(!Display.isCloseRequested()){
            //game logic
            DisplayMenager.updateDisplay();
        }

        DisplayMenager.closeDisplay();
    }
}
