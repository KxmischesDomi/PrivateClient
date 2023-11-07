package de.kxmischesdomi.kxmischesdomi.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.OverlayModule;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class GuiOverlayRenderer {

	public static void renderGuiOverlay(Gui gui, PoseStack poseStack, float f) {

		if (!KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(OverlayModule.class)) return;

		poseStack.pushPose();

		float scale = 0.8f;
		poseStack.scale(scale, scale, 1);
		int o = 5;
		poseStack.translate(o / scale, o / scale, 0);

		MutableComponent component = Component.translatable("client.info");
		int textWidth = gui.getFont().width(component);

		int widthPadding = 5 * 2;
		int width = textWidth + widthPadding;
		int height = 10;
		Gui.fill(poseStack, 0, 0, width, height, 0x762A394F);
		Gui.drawCenteredString(poseStack, gui.getFont(), component, width / 2, height - gui.getFont().lineHeight, 0xFFFFFFFF);

		int yOffset = height + 1;

		int i = 0;
		for (IModule module : KxmischesDomiMod.getInstance().getModuleWrapper().getModules()) {

			if (!module.inOverlay()) continue;

			if (module.isEnabled()) {
				i++;

				Component title = module.getTitle();
				width = gui.getFont().width(title) + widthPadding;

				Gui.fill(poseStack, 0, i * yOffset, width, i * yOffset + height, 0x4B0798FC);
				Gui.drawCenteredString(poseStack, gui.getFont(), title, width / 2, i * yOffset + height - gui.getFont().lineHeight, 0xFFFFFFFF);

			}
		}

		poseStack.popPose();
	}

}
