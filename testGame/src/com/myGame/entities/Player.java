package com.myGame.entities;

import com.myGame.models.TexturedModel;
import com.myGame.renderEngine.DisplayMenager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by user on 19.11.2015.
 */
public class Player extends Entity{

    private static final float RUN_SPEED=20;
    private static final float TURN_SPEED=160;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;

    private static final float TERRAIN_HEIGHT = 0;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;

    private boolean isInAir = false;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move(){
        checkInputs();
        super.increaseRotation(0,currentTurnSpeed* DisplayMenager.getFrameTimeSeconds(),0);
        float distance = currentSpeed * DisplayMenager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
        upwardsSpeed += GRAVITY * DisplayMenager.getFrameTimeSeconds();
        super.increasePosition(0,upwardsSpeed *DisplayMenager.getFrameTimeSeconds(),0);
        if(super.getPosition().y<TERRAIN_HEIGHT){
            isInAir=false;
            upwardsSpeed=0;
            super.getPosition().y=TERRAIN_HEIGHT;
        }
    }

    private void jump(){
        isInAir=true;
        this.upwardsSpeed = JUMP_POWER;
    }

    private void checkInputs(){
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
            this.currentSpeed = RUN_SPEED;
        } else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed=0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.currentTurnSpeed = TURN_SPEED;
        } else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            this.currentTurnSpeed = -TURN_SPEED;
        } else {
            this.currentTurnSpeed=0;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)&&!isInAir){
            jump();
        }

    }
}
