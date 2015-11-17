package com.myGame.renderEngine;

import com.myGame.entities.Entity;
import com.myGame.models.RawModel;
import com.myGame.models.TexturedModel;
import com.myGame.shaders.TerrainShader;
import com.myGame.terrains.Terrain;
import com.myGame.textures.ModelTexture;
import com.myGame.toolbox.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

/**
 * Created by user on 15.11.2015.
 */
public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader,Matrix4f projectionMatrix){
        this.shader=shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains){
        for(Terrain terrain:terrains){
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES,terrain.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT,0);
            unbindTextureModel();
        }
    }

    private void prepareTerrain(Terrain terrain){
        RawModel rawModel = terrain.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture =terrain.getTexture();
        shader.loadShineVariables(texture.getShineDamper(),texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
    }

    private void unbindTextureModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void loadModelMatrix (Terrain terrain){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(
                new Vector3f(terrain.getX(),0,terrain.getZ()), 0, 0, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);
    }
}