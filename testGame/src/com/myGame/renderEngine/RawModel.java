package com.myGame.renderEngine;

/**
 * Created by user on 11.11.2015.
 */
public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID,int vertexCount){
        this.vertexCount = vertexCount;
        this.vaoID = vaoID;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}