package de.kxmischesdomi.kxmischesdomi.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.rendering.GuiOverlayRenderer;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

}
