package flappy.engine.models;

/**
 * Created by levin on 12.06.2017.
 */
public class RawModel {

    private int ID;
    private int vertexCount;

    public RawModel(int ID, int vertexCount) {
        this.ID = ID;
        this.vertexCount = vertexCount;
    }

    public int getID() {
        return ID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
