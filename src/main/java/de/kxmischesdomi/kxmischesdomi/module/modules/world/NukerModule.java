package de.kxmischesdomi.kxmischesdomi.module.modules.world;

import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class NukerModule extends AbstractModule {

	public NukerModule() {
		ClientTickEvents.END_WORLD_TICK.register(world -> {
			if (!isEnabled()) return;
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player == null) return;

			if (minecraft.gameMode.getPlayerMode().isCreative()) {
				for (int x = -2; x < 3; x++) {
					for (int y = -1; y < 4; y++) {
						for (int z = -2; z < 3; z++) {
							BlockPos blockPos = player.blockPosition().offset(x, y, z);
							BlockState state = world.getBlockState(blockPos);
							if (!state.isAir() && !state.is(Blocks.WATER) && !state.is(Blocks.LAVA)) {
								minecraft.gameMode.startDestroyBlock(blockPos, Direction.UP);
							}
						}
					}
				}
			}

		});
	}

	@Override
	public String getName() {
		return "nuker";
	}

}
