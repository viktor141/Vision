package com.cifrazia.vision.core.ui.buttons.base;

import com.cifrazia.vision.core.abstracts.Gui;

import java.util.Collection;

public abstract class ActiveButton extends Button {

    protected boolean pressed;
    protected Collection<? extends ActiveButton> buttons;

    public ActiveButton(Gui parentGUI, Collection<? extends ActiveButton> buttons, int x, int y, int widthIn, int heightIn, String buttonText, int color) {
        super(parentGUI, x, y, widthIn, heightIn, buttonText, color);

        this.buttons = buttons;
        pressed = false;
    }

    @Override
    public void onClick() {
        pressed = true;

        for (ActiveButton button: buttons){
            if(button.id !=id){
                button.turnOff();
            }
        }
        
        super.onClick();
    }

    public void turnOff() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }
}
