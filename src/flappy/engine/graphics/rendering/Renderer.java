package flappy.engine.graphics.rendering;

import flappy.engine.entities.BasicTextureObject;
import flappy.engine.entities.Pipe;
import flappy.engine.graphics.Shader;
import flappy.engine.models.RawModel;
import flappy.toolbox.MatrixMaths;
import flappy.toolbox.Texture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 12.06.2017.
 */
public class Renderer {

    private Shader shader;
    List<BasicTextureObject> toRender;
    List<Pipe> renderPipes;
    RawModel pipeModel;
    Texture pipeTexture;
    Matrix4f pr_Matrix;

    public Renderer(Shader s){
        this.shader = s;
        toRender = new ArrayList<>();
        renderPipes = new ArrayList<>();
        pr_Matrix = MatrixMaths.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
    }

    public void process(BasicTextureObject o){
        toRender.add(o);
    }

    public void processPipes(List<Pipe> pipes, Texture texture, RawModel model){
        this.renderPipes = pipes;
        pipeModel = model;
        pipeTexture = texture;
    }

    public void prepare(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);
    }

    public void render(){
        shader.start();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        for(BasicTextureObject object : toRender){
            GL30.glBindVertexArray(object.getModel().getID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            object.getTexture().bind();


            shader.loadPrMatrix(pr_Matrix);
            /*shader.loadTranslation(object.getPosition());
            shader.loadScale(object.getScale());
            shader.loadRotation(object.getRotation());*/
            shader.loadTransformation(MatrixMaths.createTransformationMatrix(object.getPosition(), object.getScale(), object.getRotation()));

            GL11.glDrawElements(GL11.GL_TRIANGLES, object.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        }
        pipePart();


        toRender = new ArrayList<>();

    }

    private void pipePart(){
        GL30.glBindVertexArray(pipeModel.getID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        pipeTexture.bind();

        for (Pipe pipe : renderPipes){
            shader.loadPrMatrix(pr_Matrix);
            shader.loadTransformation(MatrixMaths.createTransformationMatrix(pipe.getPosition(), pipe.getScale(), pipe.getRotation()));

            GL11.glDrawElements(GL11.GL_TRIANGLES, pipeModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        }
    }




    public void cleanUp(){
        shader.cleanUp();
    }
}
