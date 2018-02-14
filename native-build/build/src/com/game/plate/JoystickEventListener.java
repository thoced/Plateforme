package com.game.plate;

import com.jme3.input.JoystickAxis;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;

public class JoystickEventListener implements RawInputListener {

    private static JoystickEventListener instance;

    private float valueViewX;
    private float valueViewY;
    private float valueAxeX;
    private float valueAxeY;

    private  boolean button = false;

    private  JoystickEventListener(){

    }

    public static JoystickEventListener getInstance(){
        if(instance == null)
            instance = new JoystickEventListener();
        return instance;
    }

    public boolean isButton() {
        return button;
    }

    public float getValueAxeX() {
        float r = valueAxeX;
        valueAxeX = 0f;
        return r;
    }

    public float getValueAxeY() {
        float r = valueAxeY;
        valueAxeY = 0f;
        return r;
    }

    public float getValueViewX() {
        float r = valueViewX;
        valueViewX = 0f;
        return r;
    }

    public float getValueViewY() {
        float r = valueViewY;
        valueViewY = 0f;
        return r;
    }

    @Override
    public void beginInput() {

    }

    @Override
    public void endInput() {

    }

    public void onJoyAxisEvent(JoyAxisEvent evt) {


        float value = evt.getValue();
      /*  if(value > -0.05f && value < 0)
            value = 0;
        if(value < 0.05f && value > 0)
            value = 0;*/

        JoystickAxis axis = evt.getAxis();



        if(axis.getLogicalId() == JoystickAxis.POV_X)
            valueAxeX = value ;

        if(axis.getLogicalId() == JoystickAxis.POV_Y)
            valueAxeY = value ;

        if(axis.getLogicalId() == JoystickAxis.Z_ROTATION)
            valueViewX= value ;

        if(axis.getLogicalId() == JoystickAxis.Z_AXIS)
            valueViewY = value ;

    }

    public void onJoyButtonEvent(JoyButtonEvent evt) {


    }

    @Override
    public void onMouseMotionEvent(MouseMotionEvent evt) {

    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent evt) {

    }

    @Override
    public void onKeyEvent(KeyInputEvent evt) {

    }

    @Override
    public void onTouchEvent(TouchEvent evt) {

    }


}