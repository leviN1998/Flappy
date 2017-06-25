package flappy.engine.level.collision;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 18.06.2017.
 */
public class Hitbox {

    private Vector2f a;
    private Vector2f b;
    private Vector2f d;

    public Hitbox(Vector2f middle, float width, float height){
        this.a = new Vector2f(middle.x - (width/2f), middle.y + (height/2f));
        this.b = new Vector2f(middle.x - (width/2f), middle.y - (height/2f));
        this.d = new Vector2f(middle.x + (width/2f), middle.y + (height/2f));
    }


    public Vector2f getA() {
        return a;
    }

    public Vector2f getB() {
        return b;
    }

    public Vector2f getD() {
        return d;
    }
}
