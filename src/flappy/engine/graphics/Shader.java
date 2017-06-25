package flappy.engine.graphics;


import flappy.toolbox.Matrix;
import flappy.toolbox.MatrixMaths;
import flappy.toolbox.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


/**
 * Created by levin on 12.06.2017.
 */
public class Shader extends ShaderProgram{

    private static final String VERTEX_FILE = "res/shaders/shader.vert";
    private static final String FRAGMENT_FILE = "res/shaders/shader.frag";


    private int location_prMatrix;
    private int location_scale;
    private int location_translation;
    private int location_rotation;

    private int location_transMatrix;

    public Shader(){
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadPrMatrix(Matrix4f matrix){
        super.loadMatrix(location_prMatrix,matrix);
    }

    public void loadScale(Vector2f scale){
        super.load2DVector(location_scale,scale);
    }

    public void loadTranslation(Vector2f translation){
        super.load2DVector(location_translation,translation);
    }

    public void loadRotation(float rotation){
        super.loadFloat(location_rotation,rotation);
    }

    public void loadTransformation(Matrix4f matrix){
        super.loadMatrix(location_transMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations() {
        location_prMatrix = super.getUniformLocation("pr_matrix");
        location_scale = super.getUniformLocation("scale");
        location_translation = super.getUniformLocation("translation");
        location_rotation = super.getUniformLocation("rotation");
        location_transMatrix = super.getUniformLocation("trans_matrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }
}
