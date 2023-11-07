package de.kxmischesdomi.kxmischesdomi.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.CoordinateOverlayModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.OverlayModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CoordinateOverlayRenderer {

    public static void renderCoordinateOverlay(Gui gui, PoseStack poseStack, int screenWidth) {

        if (!KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(CoordinateOverlayModule.class)) return;

        poseStack.pushPose();


        Entity entity = Minecraft.getInstance().getCameraEntity();
        if (entity != null) {
            MutableComponent component = Component.literal(String.format("X: %.2f Y: %.2f Z: %.2f", entity.getX(), entity.getY(), entity.getZ()));
            int textWidth = gui.getFont().width(component);

            int widthPadding = 5;
            int width = textWidth + (widthPadding * 2);
            int height = 10;

            poseStack.translate(screenWidth - width - 5, 5, 0);

            Gui.fill(poseStack, 0, 0, width, height, 0x762A394F);
            Gui.drawCenteredString(poseStack, gui.getFont(), component, width / 2, height - gui.getFont().lineHeight, 0xFFFFFFFF);
        }

        poseStack.popPose();


    }

}
