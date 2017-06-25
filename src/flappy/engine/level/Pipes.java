package flappy.engine.level;

import flappy.engine.Loader;
import flappy.engine.entities.BasicTextureObject;
import flappy.engine.entities.Pipe;
import flappy.engine.graphics.rendering.Renderer;
import flappy.engine.level.collision.Collision;
import flappy.engine.level.collision.Hitbox;
import flappy.engine.models.RawModel;
import flappy.toolbox.Data;
import flappy.toolbox.MatrixMaths;
import flappy.toolbox.Texture;
import flappy.toolbox.TextureUtils;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 12.06.2017.
 */
public class Pipes {

    private RawModel model;
    private Texture texture;
    private String path = "res/images/pipe.png";
    private Vector2f scale = new Vector2f(10,10);

    private List<Pipe> pipes;

    private float width;
    private float height;

    public static final int miny = -10;
    public static final int maxy = -4;

    public static final int offset = 14;

    public static final float pipeDisatance = 6f;

    public static float outsideX;
    public static float outsideRight;



    public Pipes(Loader loader){
        this.texture = new Texture(path);
        this.model = loader.loadToVAO(Data.textureCoords, Data.indices, texture);
        pipes = new ArrayList<>();

        Vector2f dim = TextureUtils.getDimension(texture,scale);
        this.width = dim.x;
        this.height = dim.y;
        outsideX = -10 - width;
        outsideRight = 10;


        //Testing
        //spawnPipe(new Vector2f(0,-9), false);
        //spawnPipe(new Vector2f(0,-9+offset), true);
        spawnPipes(new Vector2f(0,-5));
        spawnPipes(new Vector2f(0+pipeDisatance, -6));
    }


    public void update(float speed, float delta){
        checkSpawn();
        for(int i = 0;i<pipes.size();i++){
            updatePipe(pipes.get(i), speed, i, delta);
        }
    }

    private void updatePipe(Pipe pipe, float speed, int index, float delta){
        pipe.move(-speed * delta,0);
        if(pipe.getPosition().x <= outsideX && !pipe.isFlipped()){
            deletePipe(index);
        }
        if (pipe.getPosition().x <= outsideX+width && pipe.isFlipped()){
            deletePipe(index);
        }
    }

    public boolean collide(BasicTextureObject object){
        Hitbox bird = new Hitbox(object.getPosition(), object.getWidth(), object.getHeight());
        for (int i = 0; i<pipes.size();i++){
            if (Collision.collide(bird, new Hitbox(pipes.get(i).getPosition(), width, height))){
                return true;
            }
        }
        return false;
    }




    private void checkSpawn(){
        getYSpawn();
        if(pipes.get(pipes.size()-2).getPosition().x <= outsideRight-(pipeDisatance/2)){
            spawnPipes(new Vector2f(pipes.get(pipes.size()-2).getPosition().x + pipeDisatance, getYSpawn()));
        }
    }

    private float getYSpawn(){
        float random = (float) (Math.random()*(maxy-miny)) + miny;
        if(random < miny || random > maxy){
            System.out.println("Error");
        }
        return random;
    }

    public void process(Renderer renderer){
        renderer.processPipes(pipes, texture, model);
    }

    private void spawnPipes(Vector2f position){
        pipes.add(new Pipe(position,scale));
        Pipe flip = new Pipe(new Vector2f(position.x, position.y + offset), scale);
        flip.rotate(180);
        flip.setFlipped(true);
        pipes.add(flip);
    }

    @Deprecated
    private void spawnPipe(Vector2f position, boolean flipped){
        Pipe p = new Pipe(position, scale);
        p.setFlipped(flipped);
        if(flipped) p.rotate(180);
        pipes.add(p);
    }

    private void deletePipe(int index){
        pipes.remove(index);
    }




}
