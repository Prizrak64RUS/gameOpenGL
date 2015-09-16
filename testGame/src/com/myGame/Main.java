package com.myGame;

/**
 * Created by user on 16.09.15.
 */
import com.myGame.renderEngine.DisplayMenager;

import static org.lwjgl.opengl.GL45.*;

public class Main {
    public static void main(String[] args) {
        SharedLibraryLoader.load();
        new   DisplayMenager().createDisplay();
    }
}
