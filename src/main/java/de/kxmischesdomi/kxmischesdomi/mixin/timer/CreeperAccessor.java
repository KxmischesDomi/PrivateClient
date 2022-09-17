package de.kxmischesdomi.kxmischesdomi.mixin.timer;

import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(Creeper.class)
public interface CreeperAccessor {

	@Accessor("swell")
	int getSwell();

	@Accessor("maxSwell")
	int getMaxSwell();


}
