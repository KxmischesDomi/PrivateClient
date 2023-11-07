package de.kxmischesdomi.kxmischesdomi.module;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.fun.Deadmau5EarsModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.fun.DinnerboneEverythingModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.fun.JebEverythingModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.player.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.settings.ResetModulesModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.settings.ResetTabsModule;
import de.kxmischesdomi.kxmischesdomi.module.modules.special.*;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModuleLoader {

	public void init() {
		addModule(ModuleTab.SETTINGS, new ResetModulesModule());
		addModule(ModuleTab.SETTINGS, new ResetTabsModule());

		addModule(ModuleTab.CLIENT, new FullBrightModule());
		addModule(ModuleTab.CLIENT, new NoNameTagsModule());
		addModule(ModuleTab.CLIENT, new InfiniteNameTagsModule());
		addModule(ModuleTab.CLIENT, new NoArmorModule());
		addModule(ModuleTab.CLIENT, new NoEffectParticlesModule());
		addModule(ModuleTab.CLIENT, new TNTTimerModule());
		addModule(ModuleTab.CLIENT, new CreeperTimerModule());
		addModule(ModuleTab.CLIENT, new AutoReconnectModule());

		addModule(ModuleTab.GUI, new OverlayModule());
		addModule(ModuleTab.GUI, new CoordinateOverlayModule());
		addModule(ModuleTab.GUI, new CompassOverlayModule());
		addModule(ModuleTab.GUI, new FireOverlayModule());
		addModule(ModuleTab.GUI, new ReconnectButtonModule());
		addModule(ModuleTab.GUI, new HardcoreHeartsModule());
		addModule(ModuleTab.GUI, new NoScoreboardModule());
		addModule(ModuleTab.GUI, new NoEffectOverlayModule());

//		loadModule(ModuleTab.PLAYER, new VanillaFlyModule()); // Not legit in any way. Only enable in single player
		addModule(ModuleTab.PLAYER, new AutoRunModule());
		addModule(ModuleTab.PLAYER, new AutoJumpModule());
		addModule(ModuleTab.PLAYER, new DamageLeaveModule());
		addModule(ModuleTab.PLAYER, new TotemRestockModule());

//		addModule(ModuleTab.PLAYER, new NukerModule()); // Not legit in any way. Only enable in single player


		addModule(ModuleTab.FUN, new Deadmau5EarsModule());
		addModule(ModuleTab.FUN, new JebEverythingModule());
		addModule(ModuleTab.FUN, new DinnerboneEverythingModule());

		addModule(ModuleTab.SPECIAL, new RaidClockModule());
	}

	private void addModule(ModuleTab tab, IModule module) {
		KxmischesDomiMod.getInstance().getModuleWrapper().registerModule(tab, module);
	}

}
