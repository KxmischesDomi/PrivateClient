package de.kxmischesdomi.kxmischesdomi.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModuleTabComponent extends AbstractWidget {

	private final int moduleHeight;

	private final ModuleTab tab;
	private List<IModule> modules;

	public ModuleTabComponent(int x, int y, int width, int height, ModuleTab tab) {
		super(x, y, width, height, Component.translatable("tab." + tab.name().toLowerCase()));
		this.moduleHeight = height;
		this.tab = tab;

		Collection<IModule> modules = KxmischesDomiMod.getInstance().getModuleWrapper().getModulesByTab().get(tab);
		if (modules != null) {
			this.modules = ImmutableList.copyOf(modules);
		} else {
			this.modules = Collections.emptyList();
		}

		this.height = this.modules.size() * moduleHeight + moduleHeight;

	}

	@Override
	public void render(PoseStack poseStack, int x, int y, float f) {

		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		fill(poseStack, this.x, this.y, this.x + width, this.y + moduleHeight, 0xFF2A394F);

		drawCenteredString(poseStack, font, getMessage(), this.x + width / 2, this.y + moduleHeight - font.lineHeight, 0xFFFFFFFF);


		int index = 0;
		for (IModule module : modules) {
			index++;
			int offsetY = index * moduleHeight;

			fill(poseStack, this.x, this.y + offsetY, this.x + this.width, this.y + offsetY + moduleHeight, module.getBackgroundColor());

			poseStack.pushPose();

			int textWidth = font.width(module.getTitle());

			float s = (float) (this.width - 5) / textWidth;
			float scale = Math.min(0.65f, s);
			poseStack.scale(scale, scale, 1);
			poseStack.translate((this.x + this.width / 2f) / scale, (this.y + offsetY + moduleHeight / 2f - font.lineHeight / 4f) / scale, 0);
			drawCenteredString(poseStack, font, module.getTitle(), 0, 0, 0xFFFFFFFF);

			poseStack.popPose();
		}

	}

	@Override
	public boolean mouseClicked(double x, double y, int i) {

		double relativeY = y - this.y;

		int module = (int) Math.floor(relativeY / this.moduleHeight) - 1;

		if (modules.size() > module && module != -1) {
			IModule iModule = modules.get(module);
			iModule.setEnabled(!iModule.isEnabled());
			return true;
		}

		return false;
	}

	@Override
	public void updateNarration(NarrationElementOutput narrationElementOutput) { }

	public ModuleTab getTab() {
		return tab;
	}

}
