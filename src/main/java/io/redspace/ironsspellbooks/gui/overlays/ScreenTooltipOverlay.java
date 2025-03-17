package io.redspace.ironsspellbooks.gui.overlays;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

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

        guiHelper.renderTooltip(Minecraft.getInstance().font, Language.getInstance().getVisualOrder(toRender.tooltip.stream().map(ScreenTooltipOverlay::cast).toList()), toRender.positioner, 0, 0);
        toRender = null;
    }

    private static FormattedText cast(Component component){
        return component;
    }

    public static void renderTooltip(List<Component> tooltip, ClientTooltipPositioner positioner) {
        instance.toRender = new RenderInfo(tooltip, positioner);
    }
}
