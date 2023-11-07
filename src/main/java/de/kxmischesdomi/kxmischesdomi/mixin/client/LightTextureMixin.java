package de.kxmischesdomi.kxmischesdomi.mixin.client;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.FullBrightModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Redirect(method = "updateLightTexture", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F", ordinal = 1))
    public float updateLightTextureRedirect(Double instance) {
        return KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(FullBrightModule.class) && !(Minecraft.getInstance().getCameraEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffects.DARKNESS)) ? instance.floatValue() * 3 : instance.floatValue();
    }

}
