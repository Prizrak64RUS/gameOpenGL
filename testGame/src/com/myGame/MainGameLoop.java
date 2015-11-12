package com.myGame;

import com.myGame.renderEngine.DisplayMenager;
import com.myGame.renderEngine.Loader;
import com.myGame.renderEngine.RawModel;
import com.myGame.renderEngine.Renderer;
import com.myGame.shaders.StaticShader;
import org.lwjgl.opengl.Display;

/**
 * Created by user on 10.11.2015.
 */
public class MainGameLoop {
    public void game(){
        DisplayMenager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };
        int[] indices = {
              0,1,3,
              3,1,2
        };

        RawModel model = loader.loadToVAO(vertices,indices);

        while(!Display.isCloseRequested()){
            //game logic
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            DisplayMenager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayMenager.closeDisplay();
    }
}
