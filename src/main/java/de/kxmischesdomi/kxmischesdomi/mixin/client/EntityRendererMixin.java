package de.kxmischesdomi.kxmischesdomi.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.InfiniteNameTagsModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.NoNameTagsModule;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {

    @Inject(method = "renderNameTag", at = @At("HEAD"), cancellable = true)
    public void shouldShowName(T entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(NoNameTagsModule.class)) {
            ci.cancel();
        }

    }

    @Redirect(method = "renderNameTag", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;distanceToSqr(Lnet/minecraft/world/entity/Entity;)D"))
    public double renderNameTagRedirect(EntityRenderDispatcher instance, Entity entity) {
        if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(InfiniteNameTagsModule.class)) {
            return 0;
        }
        return instance.distanceToSqr(entity);
    }



}
