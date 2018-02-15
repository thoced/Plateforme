package controlers;

import com.jme3.export.*;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

import java.io.IOException;

public class AssenceurControl extends AbstractControl implements Savable,Cloneable {

    private Vector3f startPosition = null;

    private Vector3f endPosition = null;

    private Vector3f velocity = Vector3f.ZERO;

    private float speed = 1f;

    private Node destination;

    public AssenceurControl() {
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    @Override
    protected void controlUpdate(float tpf) {

        if(destination != null) {
            // sauvegarde de la position de départ


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

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if(spatial != null){
            destination = (Node) ((Node)spatial).getChild("destination");

            if (startPosition == null)
                startPosition = this.getSpatial().getWorldTranslation().clone();
            if (endPosition == null) {
                endPosition = startPosition.clone();
                endPosition.addLocal(destination.getLocalTranslation());
            }
        }
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);

        float[] sF = new float[3];
        float[] eF = new float[3];


        OutputCapsule out = ex.getCapsule(this);
        if(startPosition != null) {
            startPosition.toArray(sF);
            out.write(sF, "startPosition", null);
        }
        if(endPosition != null) {
            endPosition.toArray(eF);
            out.write(eF, "endPosition", null);

        }
        out.write(speed, "speed", 1f);

        out.write(destination,"destination",null);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);

        InputCapsule in = im.getCapsule(this);
        float[] sP = in.readFloatArray("startPosition",null);
        if(sP != null) {
            startPosition = new Vector3f(sP[0], sP[1], sP[2]);
        }
        float[] eP = in.readFloatArray("endPosition",null);
        if(eP != null)
            endPosition = new Vector3f(eP[0],eP[1],eP[2]);
        speed = in.readFloat("speed",1f);

        destination = (Node) in.readSavable("destination",null);


       // this.setSpatial(this.getSpatial());
    }
}
