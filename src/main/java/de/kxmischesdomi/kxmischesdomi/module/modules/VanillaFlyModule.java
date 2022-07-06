package de.kxmischesdomi.kxmischesdomi.module.modules;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.GameType;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class VanillaFlyModule extends AbstractModule {

	public VanillaFlyModule() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (isEnabled()) {
				if (KxmischesDomiMod.isInGame()) {
					Minecraft.getInstance().player.getAbilities().mayfly = true;
				}
			}
		});
	}

	@Override
	public void onEnable() {
		if (KxmischesDomiMod.isInGame()) {
			Minecraft.getInstance().player.getAbilities().mayfly = true;
		}
	}

	@Override
	public void onDisable() {
		if (KxmischesDomiMod.isInGame()) {
			GameType mode = Minecraft.getInstance().gameMode.getPlayerMode();
			if (!mode.isCreative() && mode != GameType.SPECTATOR) {
				Minecraft.getInstance().player.getAbilities().mayfly = false;
				Minecraft.getInstance().player.getAbilities().flying = false;

			}
		}
	}

	@Override
	public String getName() {
		return "vanilla_fly";
	}

}
