package com.myGame.renderEngine;

import com.myGame.entities.Entity;
import com.myGame.models.RawModel;
import com.myGame.models.TexturedModel;
import com.myGame.shaders.StaticShader;
import com.myGame.textures.ModelTexture;
import com.myGame.toolbox.Maths;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 11.11.2015.
 */
public class EntityRenderer {

    private StaticShader shader;

    public EntityRenderer(StaticShader shader,Matrix4f projectionMatrix) {
        this.shader=shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel,List<Entity>> entities){
        for (TexturedModel model:entities.keySet()){
            prepareTextureModel(model);
            List<Entity> batch = entities.get(model);
            for (Entity entity:batch){
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES,model.getRawModel().getVertexCount(),GL11.GL_UNSIGNED_INT,0);
            }
            unbindTextureModel();
        }
    }

    private void prepareTextureModel(TexturedModel model){
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture =model.getTexture();
        if(texture.isHasTransparency()){
            MasterRender.disableCulling();
        }
        shader.loadFakeLighting(texture.isUseFakeLighting());
        shader.loadShineVariables(texture.getShineDamper(),texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,model.getTexture().getTextureID());
    }

    private void unbindTextureModel(){
        MasterRender.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance (Entity entity){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),entity.getRotX(), entity.getRotY(),
                entity.getRotZ(),entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }

}
