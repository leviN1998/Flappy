package flappy.engine.entities;

import flappy.engine.Loader;
import flappy.engine.models.RawModel;
import flappy.toolbox.Data;
import flappy.toolbox.Texture;
import flappy.toolbox.TextureUtils;
import org.lwjgl.util.vector.Vector2f;


/**
 * Created by levin on 12.06.2017.
 */
public class BasicTextureObject {

    private RawModel model;
    private Vector2f position;
    private Vector2f scale;
    private float rotation;
    private Texture texture;

    private float width;
    private float height;


    public BasicTextureObject(Vector2f position, Vector2f scale, String texturePath, Loader loader){
        this.position = position;
        this.scale = scale;
        this.texture = new Texture(texturePath);
        this.model = loader.loadToVAO(Data.textureCoords, Data.indices, this.texture);
        Vector2f dim = TextureUtils.getDimension(this.texture, this.scale);
        this.width = dim.getX();
        this.height = dim.getY();
        rotate(180);
    }

    public void move(float dx, float dy){
        this.position.x += dx;
        this.position.y += dy;
    }

    public void rotate(float angle){
        this.rotation += angle;
    }

    public RawModel getModel() {
        return model;
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

    public Texture getTexture(){
        return texture;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setPosition(Vector2f pos){
        this.position = pos;
    }
}
