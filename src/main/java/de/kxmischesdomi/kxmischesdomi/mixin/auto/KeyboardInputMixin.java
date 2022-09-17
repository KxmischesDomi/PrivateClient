package de.kxmischesdomi.kxmischesdomi.mixin.auto;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.player.AutoJumpModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.player.AutoRunModule;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z"))
	public boolean isDown(KeyMapping instance) {
		if (instance == Minecraft.getInstance().options.keyUp) {
			if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(AutoRunModule.class)) {
				return true;
			}
		} else if (instance == Minecraft.getInstance().options.keyJump) {
			if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(AutoJumpModule.class)) {
				return true;
			}
		}
		return instance.isDown();
	}

}
