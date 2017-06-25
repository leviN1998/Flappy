package flappy.engine.level;

import flappy.engine.Loader;
import flappy.engine.entities.BasicTextureObject;
import flappy.engine.graphics.rendering.Renderer;
import flappy.engine.models.RawModel;
import flappy.toolbox.Data;
import flappy.toolbox.Texture;
import org.lwjgl.util.vector.Vector2f;

import javax.xml.bind.ValidationEvent;

/**
 * Created by levin on 12.06.2017.
 */
public class BackGround {

    private BasicTextureObject[] objects;
    private String path = "res/images/bg.jpeg";


    private float speed;

    public BackGround(Loader loader){
        objects = new BasicTextureObject[5];
        init(loader);
    }

    private void init(Loader loader){
        for (int i = 0; i<objects.length;i++){
            if(i == 0){
                objects[i] = new BasicTextureObject(new Vector2f(-7,0), new Vector2f(12,12), path, loader);
            }else {
                Vector2f pos = new Vector2f(objects[i-1].getPosition().x + objects[i-1].getWidth() - 0.2f, objects[i-1].getPosition().y);
                Vector2f scale = objects[i-1].getScale();
                objects[i] = new BasicTextureObject(pos,scale, path, loader);
            }
        }

        speed = 0.01f;
    }

    public void update(float delta){
        speed = 0.01f * delta;
        for (int i = 0;i<objects.length;i++) {
            moveObject(objects[i],i);
        }
    }

    public void increaseSpeed(float inc){
        this.speed += inc;
    }

    public void moveObject(BasicTextureObject o, int index){
        o.move(-speed,0);
        if(o.getPosition().x < -7-o.getWidth()){
            Vector2f last = getLastObject().getPosition();
            float width = getLastObject().getWidth();
            o.setPosition(new Vector2f(last.x + width, last.y));
            if(index == 0){
                o.move(-speed,0);
            }
        }
    }

    public BasicTextureObject getLastObject(){
        float[] positions = new float[objects.length];
        for (int i = 0; i<objects.length;i++){
            positions[i] = objects[i].getPosition().x;
        }
        float highest = positions[0];
        int counter = 0;
        for (int i = 0;i<positions.length;i++){
            if(positions[i] >= highest){
                highest = positions[i];
                counter = i;
            }
        }
        return objects[counter];
    }

    public void process(Renderer renderer){
        for (int i = 0; i<objects.length;i++){
            renderer.process(objects[i]);
        }
    }

}
