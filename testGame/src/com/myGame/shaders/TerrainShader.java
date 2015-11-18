package com.myGame.shaders;

import com.myGame.entities.Camera;
import com.myGame.entities.Light;
import com.myGame.toolbox.Maths;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by user on 15.11.2015.
 */
public class TerrainShader extends ShaderProgram {
    private static final String VERTEX_FILE="src/com/myGame/shaders/terrainVertexShader.txt";
    private static final String FRAGMENT_FILE="src/com/myGame/shaders/terrainFragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int locatiom_viewMatrix;
    private int locatiom_lightPosition;
    private int locatiom_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_skyColour;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoordinate");
        super.bindAttribute(2,"normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix=super.getUniformLocation("transformationMatrix");
        location_projectionMatrix=super.getUniformLocation("projectionMatrix");
        locatiom_viewMatrix=super.getUniformLocation("viewMatrix");
        locatiom_lightColour=super.getUniformLocation("lightColour");
        locatiom_lightPosition=super.getUniformLocation("lightPosition");
        location_shineDamper=super.getUniformLocation("shineDamper");
        location_reflectivity=super.getUniformLocation("reflectivity");
        location_skyColour=super.getUniformLocation("skyColour");
    }

    public void loadSkyColour(float r, float g, float b){
        super.loadVector(location_skyColour, new Vector3f(r,g,b));
    }

    public void loadShineVariables(float damper, float reflectivity){
        super.loadFloat(location_shineDamper,damper);
        super.loadFloat(location_reflectivity,reflectivity);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix,matrix);
    }

    public void loadLight(Light light){
        super.loadVector(locatiom_lightPosition,light.getPosition());
        super.loadVector(locatiom_lightColour,light.getColour());
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(locatiom_viewMatrix,viewMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix,projection);
    }
}
