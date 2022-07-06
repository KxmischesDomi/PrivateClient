package de.kxmischesdomi.kxmischesdomi.mixin;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

	@Inject(method = "handleLogin", at = @At("TAIL"))
	public void handleLoginMixin(ClientboundLoginPacket clientboundLoginPacket, CallbackInfo ci) {
		for (Collection<IModule> collection : KxmischesDomiMod.getInstance().getModuleWrapper().getModulesByTab().values()) {
			for (IModule module : collection) {
				if (module.isEnabled()) {
					module.onEnable();
				}
			}
		}
	}

}
