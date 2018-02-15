package com.game.plate;

import com.jme3.input.JoystickAxis;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;

public class JoystickAnalog implements AnalogListener,ActionListener {

    private static JoystickAnalog instance = null;

    private boolean button = false;

    private float valueAxeX;
    private float valueAxeY;
    private float valueViewX;
    private float valueViewY;

    public boolean isButton() {
        return button;
    }

    private JoystickAnalog(){

    }

    public static JoystickAnalog getInstance(){
        if(instance == null)
            instance = new JoystickAnalog();
        return instance;
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
    public void onAction(String name, boolean isPressed, float value) {

       /* if(name.equals("axisXPositive"))
            valueAxeX = value ;

        if(name.equals("axisXNegative"))
            valueAxeX = -value ;

        if(name.equals("axisYPositive"))
            valueAxeY = value ;

        if(name.equals("axisYNegative"))
            valueAxeY = -value ;

        if(name.equals("viewXPositive"))
            valueViewX = value;

        if(name.equals("viewXNegative"))
            valueViewX = -value;

        if(name.equals("viewYPositive"))
            valueViewY = value ;

        if(name.equals("viewYNegative"))
            valueViewY = -value ;*/



        if(isPressed){
            if(name.equals("button")){
                button = true;
            }

        }
        else
            button = false;

    }



    @Override
    public void onAnalog(String name, float value, float tpf) {


        value = value * 1000;

        value = Math.max(0, Math.min(1, value));

       if(name.equals("axisXPositive"))
            valueAxeX = value ;

        if(name.equals("axisXNegative"))
            valueAxeX = -value ;

        if(name.equals("axisYPositive"))
            valueAxeY = value ;

        if(name.equals("axisYNegative"))
            valueAxeY = -value ;

        if(name.equals("viewXPositive"))
            valueViewX = value;

        if(name.equals("viewXNegative"))
            valueViewX = -value;

        if(name.equals("viewYPositive"))
            valueViewY = value ;

        if(name.equals("viewYNegative"))
            valueViewY = -value ;


    }
}
