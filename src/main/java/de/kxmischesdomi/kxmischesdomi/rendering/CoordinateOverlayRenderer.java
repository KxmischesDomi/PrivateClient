package de.kxmischesdomi.kxmischesdomi.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.sun.jna.platform.unix.X11;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.CompassOverlayModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.CoordinateOverlayModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.OverlayModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.world.entity.Entity;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CoordinateOverlayRenderer {

    public static void renderCoordinateOverlay(Gui gui, PoseStack poseStack, int screenWidth) {
        poseStack.pushPose();

        poseStack.translate(screenWidth - 5, 5, 0);

        Entity entity = Minecraft.getInstance().getCameraEntity();
        if (entity != null) {

            if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(CoordinateOverlayModule.class)) {
                poseStack.pushPose();

                MutableComponent component = Component.literal(String.format("X: %.2f Y: %.2f Z: %.2f", entity.getX(), entity.getY(), entity.getZ()));
                int textWidth = gui.getFont().width(component);

                int widthPadding = 5;
                int width = textWidth + (widthPadding * 2);
                int height = 10;

                poseStack.translate(-width , 0, 0);

                Gui.fill(poseStack, 0, 0, width, height, 0x762A394F);
                Gui.drawCenteredString(poseStack, gui.getFont(), component, width / 2, height - gui.getFont().lineHeight, 0xFFFFFFFF);

                poseStack.popPose();

                poseStack.translate(0, height + 5, 0);
            }

            if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(CompassOverlayModule.class)) {

                int radius = 50;

                // EAST
                int rightMargin = gui.getFont().width("E");
                poseStack.translate(-rightMargin, 0, 0);

                // NORTH
                poseStack.pushPose();
                poseStack.translate(-radius / 2f, 0, 0);
                renderCompassDirection(poseStack, gui, "N");
                poseStack.popPose();

                poseStack.translate(0, gui.getFont().lineHeight, 0);

                // SOUTH
                poseStack.pushPose();
                poseStack.translate(-radius / 2f, radius + 3, 0);
                renderCompassDirection(poseStack, gui, "S");
                poseStack.popPose();

                // WEST
                poseStack.pushPose();
                poseStack.translate(-radius - gui.getFont().width("W"), radius / 2f - gui.getFont().lineHeight / 2f, 0);
                renderCompassDirection(poseStack, gui, "W");
                poseStack.popPose();

                // EAST
                poseStack.pushPose();
                poseStack.translate(rightMargin, radius / 2f - gui.getFont().lineHeight / 2f, 0);
                renderCompassDirection(poseStack, gui, "E");
                poseStack.popPose();

                // COMPASS AND NEEDLE

                // COMPASS
                poseStack.pushPose();
                poseStack.translate(-radius, 0, 0);
                Gui.fill(poseStack, 0, 0, radius, radius, 0x762A394F);
                poseStack.pushPose();

                // NEEDLE
                int width = 5;
                int height = radius / 2;
                poseStack.translate(radius / 2d, radius / 2d, 0);
                poseStack.mulPose(Vector3f.ZN.rotationDegrees(entity.getYRot() % 360));
                Gui.fill(poseStack, -width / 2, -height / 2, width, height, 0xFFFFFFFF);
                poseStack.popPose();
                poseStack.popPose();

            }

        }

        poseStack.popPose();

    }

    private static void renderCompassDirection(PoseStack poseStack, Gui gui, String direction) {
        poseStack.scale(0.8f, 0.8f, 1);
        Gui.drawCenteredString(poseStack, gui.getFont(), Component.literal(direction), 0, 0, 0xFFFFFFFF);
    }

}
