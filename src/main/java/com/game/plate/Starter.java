package com.game.plate;

import com.jme3.system.AppSettings;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The starter class.
 */
public class Starter extends Application {

    public static void main(final String[] args) {


        final AppSettings settings = new AppSettings(true);
        settings.setResolution(800, 600);
        settings.setFullscreen(false);
        settings.setGammaCorrection(true);
        settings.setRenderer(AppSettings.LWJGL_OPENGL33);
        settings.setUseJoysticks(true);



        final GameApplication application = new GameApplication();
        application.setSettings(settings);
        application.setShowSettings(false);
        application.start();
    }

    @Override
    public void start(final Stage primaryStage) {
    }
}
