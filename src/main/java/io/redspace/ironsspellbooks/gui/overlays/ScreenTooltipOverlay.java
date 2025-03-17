package io.redspace.ironsspellbooks.gui.overlays;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;


public class ScreenTooltipOverlay implements LayeredDraw.Layer {
    public static final ScreenTooltipOverlay instance = new ScreenTooltipOverlay();

    private record RenderInfo(List<Component> tooltip, ClientTooltipPositioner positioner) {
    }

    RenderInfo toRender = null;

    public void render(GuiGraphics guiHelper, DeltaTracker deltaTracker) {
        if (Minecraft.getInstance().options.hideGui || toRender == null) {
            return;
        }
        var screenWidth = guiHelper.guiWidth();
        var screenHeight = guiHelper.guiHeight();

        guiHelper.renderTooltip(Minecraft.getInstance().font, toRender.tooltip.stream().map(component -> FormattedCharSequence.forward(component.getString(), component.getStyle())).toList(), toRender.positioner, 0, 0);
        toRender = null;

    }

    public static void renderTooltip(List<Component> tooltip, ClientTooltipPositioner positioner) {
        instance.toRender = new RenderInfo(tooltip, positioner);
    }
}
