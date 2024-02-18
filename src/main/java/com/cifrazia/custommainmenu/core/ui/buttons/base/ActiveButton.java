package com.cifrazia.custommainmenu.core.ui.buttons.base;

import com.cifrazia.custommainmenu.core.abstracts.Gui;

import java.util.List;

public abstract class ActiveButton extends Button {

    protected boolean pressed;
    protected List<? extends ActiveButton> buttons;

    public ActiveButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, int widthIn, int heightIn, String buttonText, int color) {
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
