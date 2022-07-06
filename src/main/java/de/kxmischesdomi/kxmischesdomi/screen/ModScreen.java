package de.kxmischesdomi.kxmischesdomi.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModScreen extends Screen {

	private final List<ModuleTabComponent> tabs = new LinkedList<>();

	private ModuleTabComponent dragged;
	private int offsetX, offsetY;

	public ModScreen() {
		super(Component.empty());
	}

	@Override
	protected void init() {

		if (tabs.isEmpty()) {

			for (ModuleTab tab : ModuleTab.values()) {

				int x = tab.x;
				int y = tab.y;

				ModuleTabComponent component = new ModuleTabComponent(x, y, 60, 10, tab);
				tabs.add(component);
				addWidget(component);
			}
			tabs.sort(Comparator.comparingInt(value -> value.getTab().focusIndex));
		} else {
			for (ModuleTabComponent tab : tabs) {
				addWidget(tab);
			}
		}

	}

	@Override
	public void render(PoseStack poseStack, int i, int j, float f) {

		if (dragged != null) {
			dragged.getTab().x = i + offsetX;
			dragged.getTab().y = j + offsetY;
		}

		for (ModuleTabComponent tab : tabs) {
			tab.x = tab.getTab().x;
			tab.y = tab.getTab().y;

			tab.x = Math.max(0, tab.x);
			tab.y = Math.max(0, tab.y);

			tab.x = Math.min(width - tab.getWidth(), tab.x);
			tab.y = Math.min(height - tab.getHeight(), tab.y);

			tab.getTab().x = tab.x;
			tab.getTab().y = tab.y;
		}

		renderBackground(poseStack);

		for (ModuleTabComponent tab : tabs) {
			tab.render(poseStack, i, j, f);
		}

		super.render(poseStack, i, j, f);
	}

	@Override
	public boolean mouseClicked(double d, double e, int i) {

		if (dragged == null) {
			LinkedList<ModuleTabComponent> components = new LinkedList<>(tabs);
			Collections.reverse(components);
			for (ModuleTabComponent tab : components) {
				if (tab.isMouseOver(d, e)) {
					tabs.remove(tab);
					tabs.add(tab);

					for (int i1 = 0; i1 < tabs.size(); i1++) {
						ModuleTabComponent component = tabs.get(i1);
						component.getTab().focusIndex = i1;
					}

					if (!tab.mouseClicked(d, e, i)) {
						dragged = tab;
						offsetX = (int) (tab.x - d);
						offsetY = (int) (tab.y - e);

					}

					break;
				}
			}
		}

		return false;
	}

	@Override
	public boolean mouseReleased(double d, double e, int i) {

		if (dragged != null) {
			dragged = null;
			offsetX = 0;
			offsetY = 0;
		}

		return super.mouseReleased(d, e, i);
	}

	@Override
	public boolean keyPressed(int i, int j, int k) {

		if (KxmischesDomiMod.MENU_MAPPING.matches(i, j) || Minecraft.getInstance().options.keyInventory.matches(i, j)) {
			this.onClose();
			return true;
		}

		return super.keyPressed(i, j, k);
	}

	@Override
	public void renderBackground(PoseStack poseStack) {
		this.fillGradient(poseStack, 0, 0, this.width, this.height, getBackgroundColor(false), getBackgroundColor(true));
		if (this.minecraft.level != null) {
			return;
		}
		super.renderBackground(poseStack);
	}

	public int getBackgroundColor(boolean second) {
		int a = second ? 75 : 75;
		Color col = Color.BLACK;
		int r = (col.getRGB() >> 16) & 0xFF;
		int b = (col.getRGB() >> 8) & 0xFF;
		int g = col.getRGB() & 0xFF;
		float prog = 1;
		a *= prog;
		r *= prog;
		g *= prog;
		b *= prog;
		return a << 24 | r << 16 | b << 8 | g;
	}

	@Override
	public void removed() {
		KxmischesDomiMod.getInstance().getConfigLoader().save();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
