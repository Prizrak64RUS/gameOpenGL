package com.myGame;

/**
 * Created by user on 16.09.15.
 */
import com.myGame.renderEngine.DisplayMenager;

public class Main {
    public static void main(String[] args) {
        SharedLibraryLoader.load();
        DisplayMenager.createDisplay();
    }
}
