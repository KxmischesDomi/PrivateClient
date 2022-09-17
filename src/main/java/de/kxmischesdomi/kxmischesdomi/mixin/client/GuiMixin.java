package de.kxmischesdomi.kxmischesdomi.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.HardcoreHeartsModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.NoEffectOverlayModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.NoScoreboardModule;
import de.kxmischesdomi.kxmischesdomi.rendering.GuiOverlayRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.scores.Objective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(Gui.class)
public class GuiMixin {

	@Inject(method = "render", at = @At("TAIL"))
	public void renderMixin(PoseStack poseStack, float f, CallbackInfo ci) {
		GuiOverlayRenderer.renderGuiOverlay((Gui) (Object) this, poseStack, f);
	}

	@Inject(method = "displayScoreboardSidebar", at = @At("HEAD"), cancellable = true)
	public void displayScoreboardSidebarMixin(PoseStack poseStack, Objective objective, CallbackInfo ci) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(NoScoreboardModule.class)) {
			ci.cancel();
		}
	}

	@Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
	public void renderEffectsMixin(PoseStack poseStack, CallbackInfo ci) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(NoEffectOverlayModule.class)) {
			ci.cancel();
		}
	}

	@Redirect(method = "renderHearts", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/LevelData;isHardcore()Z"))
	public boolean isHardcoreRedirect(LevelData instance) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(HardcoreHeartsModule.class)) {
			return true;
		}
		return instance.isHardcore();
	}


}
