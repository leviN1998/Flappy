package flappy.engine.level.collision;



/**
 * Created by levin on 18.06.2017.
 */
public class Collision {

    public static boolean collide(Hitbox a, Hitbox b){
        float aX = a.getA().x;
        float hX = b.getD().x;
        float eX = b.getA().x;
        float dX = a.getD().x;

        float eY = b.getA().y;
        float bY = a.getB().y;
        float fY = b.getB().y;
        float aY = a.getA().y;

        if(hX > aX && eX < dX){
            if(eY > bY && fY < aY){
                return true;
            }
        }
        return false;
    }


}
