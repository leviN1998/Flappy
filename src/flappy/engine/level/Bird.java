package flappy.engine.level;

import flappy.engine.Loader;
import flappy.engine.entities.BasicTextureObject;
import flappy.engine.graphics.rendering.Renderer;
import flappy.engine.level.collision.Hitbox;
import flappy.engine.models.RawModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 12.06.2017.
 */
public class Bird {

    private BasicTextureObject object;
    private String path = "res/images/bird.png";

    private Vector2f scale = new Vector2f(1,1);
    private float speed = 0.1f;
    private boolean jumping;
    private int jumpTimer;
    private int jumplength = 25;

    private float fallCounter;



    public Bird(Vector2f spawn, Loader loader){
        this.object = new BasicTextureObject(spawn, scale, path, loader);
        jumping = false;
        this.object.rotate(180);
    }


    public void update(float delta){
        clearRotation();
        updateJump(delta);
        updateFall(delta);
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !jumping){
            jump();
        }
    }

    private void updateFall(float delta){
        if(!jumping){
            move(0, -0.001f * fallCounter * delta);
            fallCounter += 1.5f;
            object.rotate(-(fallCounter * 0.5f));
        }
    }

    private void updateJump(float delta){
        if(jumping && jumpTimer <= jumplength){
            move(0, 0.07f * delta);
            updateRotJump(jumpTimer);
            jumpTimer++;
        }
        if(jumpTimer > jumplength) jumping = false;
    }

    private void updateRotJump(int counter){
        this.object.rotate(90 -counter);
    }

    private void clearRotation(){
        this.object.rotate(-object.getRotation() + 180);
    }

    private void jump(){
        jumping = true;
        jumpTimer = 0;
        fallCounter = 0;
    }

    private void move(float dx, float dy){
        this.object.move(dx, dy);
    }

    public void process(Renderer renderer){
        renderer.process(object);
    }

    public BasicTextureObject getObject(){
        return object;
    }


}
