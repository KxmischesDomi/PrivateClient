package de.kxmischesdomi.kxmischesdomi.module.modules;

import com.mojang.realmsclient.RealmsMainScreen;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;


/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class DamageLeaveModule extends AbstractModule {

	private float health = -1;

	public DamageLeaveModule() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (isEnabled()) {
				if (Minecraft.getInstance().player != null) {
					if (health != -1 && health > Minecraft.getInstance().player.getHealth()) {
						quit();
					} else {
						health = Minecraft.getInstance().player.getHealth();
					}
				} else {
					health = -1;
				}
			}
		});
	}

	@Override
	public String getName() {
		return "damage_leave";
	}

	private void focusWindow() {

	}

	private void quit() {
		Minecraft minecraft = Minecraft.getInstance();
		boolean bl = minecraft.isLocalServer();
		boolean bl2 = minecraft.isConnectedToRealms();
		minecraft.level.disconnect();
		if (bl) {
			minecraft.clearLevel(new GenericDirtMessageScreen(Component.translatable("menu.savingLevel")));
		} else {
			minecraft.clearLevel();
		}
		TitleScreen titleScreen = new TitleScreen();
		if (bl) {
			minecraft.setScreen(titleScreen);
		} else if (bl2) {
			minecraft.setScreen(new RealmsMainScreen(titleScreen));
		} else {
			minecraft.setScreen(new JoinMultiplayerScreen(titleScreen));
		}
	}

}
