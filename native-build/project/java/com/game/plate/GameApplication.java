package com.game.plate;

import com.game.plate.appstates.CameraAppState;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.Joystick;
import com.jme3.input.JoystickAxis;
import com.jme3.input.controls.JoyAxisTrigger;
import com.jme3.input.controls.JoyButtonTrigger;
import com.jme3.material.TechniqueDef;
import com.jme3.renderer.Camera;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.ss.editor.extension.loader.SceneLoader;
import controlers.AvatarControl;

/**
 * The game application class.
 */
public class GameApplication extends SimpleApplication {

    /**
     * The post filter processor.
     */
    protected FilterPostProcessor postProcessor;

    private Spatial avatar = null;

    private AvatarControl avatarControl;

    @Override
    public void simpleInitApp() {
        renderManager.setPreferredLightMode(TechniqueDef.LightMode.SinglePass);
        renderManager.setSinglePassLightBatchSize(5);

        postProcessor = new FilterPostProcessor(assetManager);
        postProcessor.initialize(renderManager, viewPort);

        // register post effects filter
        viewPort.addProcessor(postProcessor);

        // register loader of j3s files
        SceneLoader.install(this, postProcessor);

        //final Camera camera = getCamera();
        //camera.setLocation(new Vector3f(0, 19.356062F, 44.070957F));
       // camera.setRotation(new Quaternion(-0.042982846F, 0.90933293F, -0.09716145F, -0.40227568F));

        // avatar
        SceneGraphVisitor visitor = new SceneGraphVisitor() {
            @Override
            public void visit(Spatial spat) {
                CharacterControl control = spat.getControl(CharacterControl.class);
                if(control != null){
                    avatar = spat;
                    // reception du controller d'animation
                    AnimControl animControl = avatar.getControl(AnimControl.class);
                    if(animControl != null){
                        AnimChannel animChannel = animControl.createChannel();
                        animChannel.setAnim("IDLE_BASE");
                    }
                    // creation de l'avatarcontrol
                    avatarControl = new AvatarControl();
                    avatar.addControl(avatarControl);



                }

            }

        };



       // getFlyByCamera().setMoveSpeed(2f);
        getFlyByCamera().setEnabled(false);

        rootNode.attachChild(assetManager.loadModel("Scenes/level01Scene_1.j3s"));

        getRootNode().depthFirstTraversal(visitor);

        // chargement des appstate
        Level01AppState level01AppState = new Level01AppState();
        this.getStateManager().attach(level01AppState);
        CameraAppState cameraAppState = new CameraAppState(avatar);
        this.getStateManager().attach(cameraAppState);

        // initialisation du gamepad
        //inputManager.addRawInputListener( JoystickEventListener.getInstance() );

        inputManager.addListener(JoystickAnalog.getInstance(),"button");
        inputManager.addMapping("button",new JoyButtonTrigger(0,0));

        inputManager.addRawInputListener(JoystickEventListener.getInstance());
       /* inputManager.addListener(JoystickAnalog.getInstance(),"axisYPositive");
        inputManager.addMapping("axisYPositive",new JoyAxisTrigger(0,1,false));
        inputManager.addListener(JoystickAnalog.getInstance(),"axisYNegative");
        inputManager.addMapping("axisYNegative",new JoyAxisTrigger(0,1,true));

        inputManager.addListener(JoystickAnalog.getInstance(),"axisXPositive");
        inputManager.addMapping("axisXPositive",new JoyAxisTrigger(0,0,false));
        inputManager.addListener(JoystickAnalog.getInstance(),"axisXNegative");
        inputManager.addMapping("axisXNegative",new JoyAxisTrigger(0,0,true));

        inputManager.addListener(JoystickAnalog.getInstance(),"viewXPositive");
        inputManager.addMapping("viewXPositive",new JoyAxisTrigger(0,2,false));
        inputManager.addListener(JoystickAnalog.getInstance(),"viewXNegative");
        inputManager.addMapping("viewXNegative",new JoyAxisTrigger(0,2,true));


        inputManager.addListener(JoystickAnalog.getInstance(),"viewYPositive");
        inputManager.addMapping("viewYPositive",new JoyAxisTrigger(0,3,false));
        inputManager.addListener(JoystickAnalog.getInstance(),"viewYNegative");
        inputManager.addMapping("viewYNegative",new JoyAxisTrigger(0,3,true));*/

        inputManager.setAxisDeadZone(0.1f);




        //this.getFlyByCamera().setEnabled(true);
        //this.getFlyByCamera().setDragToRotate(true);

    }

    @Override
    public void update() {
        super.update();

        if(avatar != null){
            float x = JoystickEventListener.getInstance().getValueAxeX();
            float y = JoystickEventListener.getInstance().getValueAxeY();
            boolean jump = JoystickAnalog.getInstance().isButton();
            Vector3f dir = Vector3f.ZERO.clone();
            Vector3f dirY = getCamera().getDirection().clone();
            Vector3f dirX = getCamera().getLeft().clone();
            dirX.normalizeLocal();
            dirY.normalizeLocal();
            dirY.setY(0f);
            dirX.setY(0f);
            dirX.multLocal(x);
            dirY.multLocal(y);
            dir.subtractLocal(dirX);
            dir.subtractLocal(dirY);



            if(avatarControl != null){
                avatarControl.setDirVelocity(dir);
                avatarControl.setJump(jump);
            }

        }
    }
}