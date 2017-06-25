package flappy.engine.entities;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 12.06.2017.
 */
public class Pipe {

    private Vector2f position;
    private Vector2f scale;
    private float rotation;
    private boolean flipped;

    public Pipe(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
        rotate(180);
    }

    public void move(float dx, float dy){
        this.position.x += dx;
        this.position.y += dy;
    }

    public void rotate(float angle){
        this.rotation += angle;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setFlipped(boolean flipped){
        this.flipped = flipped;
    }

    public boolean isFlipped(){
        return flipped;
    }
}
