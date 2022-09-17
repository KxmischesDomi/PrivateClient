package de.kxmischesdomi.kxmischesdomi.module.modules.smp;

import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.decoration.ArmorStand;

import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class RaidClockModule extends AbstractModule {

	private long lastClick = System.currentTimeMillis();

	public RaidClockModule() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (!isEnabled()) return;
			if (System.currentTimeMillis() - lastClick >= 1500) {
				if (Minecraft.getInstance().player != null) {
					try {
						LocalPlayer player = Minecraft.getInstance().player;
						List<ArmorStand> stands = player.level.getNearbyEntities(ArmorStand.class, TargetingConditions.forCombat(), player, player.getBoundingBox());
						for (int i = 0; i < stands.size() && i < 1; i++) {
							ArmorStand stand = stands.get(i);
							Minecraft.getInstance().gameMode.attack(player, stand);
							player.swing(InteractionHand.MAIN_HAND);
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
				lastClick = System.currentTimeMillis();
			}

		});
	}

	@Override
	public String getName() {
		return "raid_clock";
	}

}
