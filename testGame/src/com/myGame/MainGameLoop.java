package com.myGame;

import com.myGame.entities.Camera;
import com.myGame.entities.Entity;
import com.myGame.entities.Light;
import com.myGame.models.TexturedModel;
import com.myGame.objConverter.ModelData;
import com.myGame.objConverter.OBJFileLoader;
import com.myGame.renderEngine.*;
import com.myGame.models.RawModel;
import com.myGame.shaders.StaticShader;
import com.myGame.terrains.Terrain;
import com.myGame.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 10.11.2015.
 */
public class MainGameLoop {
    public void game(){
        DisplayMenager.createDisplay();
        Loader loader = new Loader();

        ModelData data = OBJFileLoader.loadOBJ("tree");

        RawModel model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),
                data.getNormals(), data.getIndices());

        TexturedModel staticModel = getTexturedModel("tree","tree",loader);
        TexturedModel grass = getTexturedModel("grassModel","grassTexture",loader);
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);
        TexturedModel fern = getTexturedModel("fern","fern",loader);
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLighting(true);

        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for(int i=0;i<500;i++){
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800-400,0,
                    random.nextFloat()*-600),0,0,0,3));
            entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800-400,0,
                    random.nextFloat()*-600),0,0,0,1));
            entities.add(new Entity(fern, new Vector3f(random.nextFloat()*800-400,0,
                    random.nextFloat()*-600),0,0,0,0.6f));
        }

        //Entity entity = new Entity(staticModel, new Vector3f(0,0,-22),0,0,0,1);

        Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));

        Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));


        Camera camera = new Camera();
        MasterRender renderer = new MasterRender();
        while(!Display.isCloseRequested()){
            camera.move();
            //game logic
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for(Entity entity:entities) {
                renderer.processEntity(entity);
            }
            renderer.render(light,camera);
            DisplayMenager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayMenager.closeDisplay();
    }


    private TexturedModel getTexturedModel(String modelName, String textureName, Loader loader ){

        ModelData data = OBJFileLoader.loadOBJ(modelName);

        RawModel model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),
                data.getNormals(), data.getIndices());

        return  new TexturedModel(model,
                new ModelTexture(loader.loadTexture(textureName)));

    }
}
