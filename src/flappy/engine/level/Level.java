package flappy.engine.level;


import flappy.engine.Loader;
import flappy.engine.graphics.rendering.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;


/**
 * Created by levin on 12.06.2017.
 */
public class Level {

    private BackGround backGround;
    private Pipes pipes;
    private Bird bird;
    private static boolean running;
    private Loader loader;


    public Level(Loader loader){
        this.loader = loader;
        init();
    }

    private void init(){
        backGround = new BackGround(loader);
        pipes = new Pipes(loader);
        bird = new Bird(new Vector2f(-5,1),loader);
        running = true;
    }

    public void update(float delta){
        //delta = 1;
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            //running = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            running = false;
        }
        if(running) {
            backGround.update(delta);
            pipes.update(0.03f, delta);
            bird.update(delta);
            updateCollision();
        }else {
            if(Keyboard.isKeyDown(Keyboard.KEY_T)){
                init();
            }
        }
        if(bird.getObject().getPosition().y < -5.5f){
            running = false;
        }
    }

    private void updateCollision(){
        if(pipes.collide(bird.getObject())) running = false;
    }

    private void updateCollision(String debug){
        //pipes.collide(bird.getObject(), "debug");
    }


    public void process(Renderer renderer){
        backGround.process(renderer);
        pipes.process(renderer);
        bird.process(renderer);
    }

    public void cleanUp(){
        loader.cleanUp();
    }
}
