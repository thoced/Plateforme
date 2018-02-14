package com.game.plate;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import controlers.AvatarControl;

public class Level01AppState extends AbstractAppState {

    private SimpleApplication simpleApplication;


    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        simpleApplication = (SimpleApplication) app;




    }



    @Override
    public void update(float tpf) {
        super.update(tpf);



    }
}


