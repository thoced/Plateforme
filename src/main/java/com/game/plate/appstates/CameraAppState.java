package com.game.plate.appstates;

import com.game.plate.JoystickAnalog;
import com.game.plate.JoystickEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.ss.editor.extension.scene.app.state.impl.bullet.EditableBulletSceneAppState;
import sun.java2d.pipe.SpanShapeRenderer;

public class CameraAppState extends AbstractAppState {

    private Camera camera;

    private SimpleApplication simpleApplication;

    private Spatial avatarSpatial;

    private Vector3f offsetCamera = new Vector3f(0,5,-15);

    private double minPitchAngle = 0f;

    private double maxPitchAngle = 70f;

    private CameraNode cameraNode;

    private Node nodeTarget;

    public CameraAppState(Spatial avatarSpatial) {
        this.avatarSpatial = avatarSpatial;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        simpleApplication = (SimpleApplication) app;
        camera = simpleApplication.getCamera();
        cameraNode = new CameraNode("camera",camera);
        nodeTarget = new Node();
        if(avatarSpatial != null){
            nodeTarget.setLocalTranslation(avatarSpatial.getWorldTranslation());
            nodeTarget.attachChild(cameraNode);
            simpleApplication.getRootNode().attachChild(nodeTarget);
            cameraNode.setLocalTranslation(offsetCamera);
            cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);

        }

    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

        float rotationCameraX = JoystickEventListener.getInstance().getValueViewX();
        float rotationCameraY = JoystickEventListener.getInstance().getValueViewY();

        Quaternion quaternionCamera = nodeTarget.getWorldRotation();
        float[] angles = new float[3];
        quaternionCamera.toAngles(angles);
        angles[0] += rotationCameraX * tpf;
        angles[1] += rotationCameraY * tpf;

        if(angles[0] < Math.toRadians(minPitchAngle))
            angles[0] = (float)minPitchAngle;
        if(angles[0] > Math.toRadians(maxPitchAngle))
            angles[0] = (float)Math.toRadians(maxPitchAngle);

        quaternionCamera.fromAngles(angles);
        nodeTarget.setLocalRotation(quaternionCamera);

        if(avatarSpatial != null) {
            nodeTarget.setLocalTranslation(avatarSpatial.getWorldTranslation());
            cameraNode.lookAt(nodeTarget.getWorldTranslation(), Vector3f.UNIT_Y);
        }


    }
}
