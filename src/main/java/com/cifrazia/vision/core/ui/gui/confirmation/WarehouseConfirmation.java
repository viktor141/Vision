package com.cifrazia.vision.core.ui.gui.confirmation;

import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItemHolder;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import net.minecraft.client.Minecraft;

public class WarehouseConfirmation extends Screen {

    private final Gui parent;
    private final WarehouseItemHolder holder;

    public WarehouseConfirmation(Minecraft mc, Gui parent, WarehouseItemHolder holder) {
        this.mc = mc;
        this.parent = parent;
        this.holder = holder;
    }

    @Override
    public void setResolution(int width, int height) {

    }
}
