package de.kxmischesdomi.kxmischesdomi.mixin;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.screen.ModScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

	public TitleScreenMixin(Component component) {
		super(component);
	}

	@Override
	public boolean keyPressed(int i, int j, int k) {
		if (KxmischesDomiMod.MENU_MAPPING.matches(i, j)) {
			Minecraft.getInstance().setScreen(new ModScreen());
			return true;
		}
		return super.keyPressed(i, j, k);
	}

}
