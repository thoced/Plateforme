package controlers;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

import java.io.IOException;

public class AssenceurControl extends AbstractControl {

    private Vector3f startPosition = null;

    private Vector3f endPosition = null;

    private Vector3f velocity = Vector3f.ZERO;

    private float speed = 1f;

    private Node destination;

    public Vector3f getVelocity() {
        return velocity;
    }

    @Override
    protected void controlUpdate(float tpf) {

        // récupération du node de destination
        Node destination = (Node) ((Node)this.getSpatial()).getChild("destination");
        if(destination != null) {
            // sauvegarde de la position de départ
            if (startPosition == null)
                startPosition = this.getSpatial().getWorldTranslation().clone();
            if (endPosition == null) {
                endPosition = startPosition.clone();
                endPosition.addLocal(destination.getLocalTranslation());
            }

            Vector3f t = this.getSpatial().getWorldTranslation();
            velocity = (endPosition.subtract(startPosition)).normalize().mult(speed * tpf);
            t.addLocal(velocity);
            //   t.interpolateLocal(endPosition,speed * tpf);
            this.getSpatial().setLocalTranslation(t);

            if (this.getSpatial().getWorldTranslation().distance(endPosition) < 0.1f) {
                Vector3f d = endPosition.clone();
                endPosition = startPosition.clone();
                startPosition = d.clone();
            }
        }



    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {

    }


}
