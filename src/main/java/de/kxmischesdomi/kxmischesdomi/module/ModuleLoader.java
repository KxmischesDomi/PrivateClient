package de.kxmischesdomi.kxmischesdomi.module;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.*;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModuleLoader {

	public void init() {
		loadModule(ModuleTab.CLIENT, new OverlayModule());
		loadModule(ModuleTab.CLIENT, new ResetTabsModule());
		loadModule(ModuleTab.CLIENT, new ResetModulesModule());
		loadModule(ModuleTab.PLAYER, new VanillaFlyModule());
		loadModule(ModuleTab.PLAYER, new DamageLeaveModule());
		loadModule(ModuleTab.PLAYER, new TotemRestockModule());
		loadModule(ModuleTab.WORLD, new TNTTimerModule());
		loadModule(ModuleTab.WORLD, new CreeperTimerModule());
		loadModule(ModuleTab.SMP, new RaidClockModule());
	}

	private void loadModule(ModuleTab tab, IModule module) {
		KxmischesDomiMod.getInstance().getModuleWrapper().registerModule(tab, module);
	}

}
