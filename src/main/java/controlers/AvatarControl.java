package controlers;

import com.jme3.bullet.collision.PhysicsRayTestResult;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import javafx.scene.control.Control;

import java.util.List;

public class AvatarControl extends AbstractControl {

    private Vector3f dir = Vector3f.UNIT_X;

    private boolean jump = false;

    private Vector3f directionSave = Vector3f.UNIT_X;

    private float speed = 4f;

    public void setDirVelocity(Vector3f dir){
        this.dir = dir;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    @Override
    protected void controlUpdate(float tpf) {
        BetterCharacterControl control  = this.getSpatial().getControl(BetterCharacterControl.class);
        if(control != null) {
                Vector3f direction = Vector3f.ZERO.clone();
                System.out.println(dir);

                direction.addLocal(dir.multLocal(speed));
                control.setWalkDirection(direction);
                control.setViewDirection(direction);




            // jump
            if (this.jump){
                if(control.isOnGround())
                    control.jump();
                jump = false;
                }
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {

    }
}
