package com.myGame.shaders;

/**
 * Created by user on 11.11.2015.
 */
public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE="src/com/myGame/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE="src/com/myGame/shaders/fragmentShader.txt";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
    }
}
