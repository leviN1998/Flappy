package flappy.engine;


import flappy.engine.graphics.Shader;
import flappy.engine.graphics.rendering.Renderer;
import flappy.engine.level.Level;
import flappy.toolbox.DisplayManager;
import org.lwjgl.opengl.Display;

import java.io.IOException;


/**
 * Created by levin on 12.06.2017.
 */
public class Main implements Runnable{

    private Loader loader;
    private Renderer renderer;
    private Shader shader;
    private Level level;

    private long time;


    @Override
    public void run() {
        /*try {
            Process p = Runtime.getRuntime().exec("/C:/WINDOWS/system32/notepad.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        DisplayManager.createDisplay();
        init();
        while (!Display.isCloseRequested()){
            float delta = System.nanoTime() - time;
            time = System.nanoTime();
            update(delta);
            render();
            DisplayManager.updateDisplay();
        }
        cleanUp();
        DisplayManager.closeDisplay();
    }

    public void init(){
        loader = new Loader();
        shader = new Shader();
        renderer = new Renderer(shader);
        level = new Level(loader);
        time = System.nanoTime();
    }

    public void update(float delta){
        delta = delta / 7200000;
        level.update(delta);
    }

    public void render(){
        //Pre-Rendering
        level.process(renderer);

        //Prepare
        renderer.prepare();

        //rendering
        renderer.render();
    }

    public void cleanUp(){
        loader.cleanUp();
        shader.cleanUp();
        renderer.cleanUp();
        level.cleanUp();
    }


    public static void main(String[] args){
        new Main().run();
    }
}
