package de.kxmischesdomi.kxmischesdomi.mixin.client;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.Deadmau5EarsModule;
import net.minecraft.client.renderer.entity.layers.Deadmau5EarsLayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(Deadmau5EarsLayer.class)
public class Deadmau5EarsLayerMixin {

	@Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;getString()Ljava/lang/String;"))
	public String equalsRedirect(Component instance) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(Deadmau5EarsModule.class)) {
			return "deadmau5";
		}
		return instance.getString();
	}

}
