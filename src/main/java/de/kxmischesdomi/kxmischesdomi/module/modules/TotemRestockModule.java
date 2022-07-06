package de.kxmischesdomi.kxmischesdomi.module.modules;

import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class TotemRestockModule extends AbstractModule {

	public TotemRestockModule() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (isEnabled()) {
				LocalPlayer player = client.player;
				if (player != null && client.gameMode != null) {
					if (!client.gameMode.hasInfiniteItems() && player.getOffhandItem().isEmpty()) {

						for (int i = 0; i < player.containerMenu.slots.size(); i++) {
							ItemStack item = player.containerMenu.getSlot(i).getItem();
							if (item.is(Items.TOTEM_OF_UNDYING)) {

								client.gameMode.handleInventoryMouseClick(0, i, 0, ClickType.PICKUP, player);
								client.gameMode.handleInventoryMouseClick(0, 45, 0, ClickType.PICKUP, player);
								client.gameMode.handleInventoryMouseClick(0, i, 0, ClickType.PICKUP, player);

								break;
							}
						}

					}
				}
			}
		});
	}

	@Override
	public String getName() {
		return "totem_restock";
	}

}
