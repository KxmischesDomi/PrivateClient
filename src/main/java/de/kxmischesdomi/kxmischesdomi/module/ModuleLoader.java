package de.kxmischesdomi.kxmischesdomi.module;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.gui.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.player.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.smp.*;
import de.kxmischesdomi.kxmischesdomi.module.modules.world.*;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModuleLoader {

	public void init() {

		addModule(ModuleTab.CLIENT, new AutoReconnectModule());
		addModule(ModuleTab.CLIENT, new Deadmau5EarsModule());
		addModule(ModuleTab.CLIENT, new JebEverythingModule());
		addModule(ModuleTab.CLIENT, new DinnerboneEverythingModule());
		addModule(ModuleTab.CLIENT, new NoEffectParticlesModule());
		addModule(ModuleTab.CLIENT, new ResetModulesModule());

		addModule(ModuleTab.GUI, new OverlayModule());
		addModule(ModuleTab.GUI, new ReconnectButtonModule());
		addModule(ModuleTab.GUI, new HardcoreHeartsModule());
		addModule(ModuleTab.GUI, new NoScoreboardModule());
		addModule(ModuleTab.GUI, new NoEffectOverlayModule());
		addModule(ModuleTab.GUI, new ResetTabsModule());

//		loadModule(ModuleTab.PLAYER, new VanillaFlyModule()); // Not legit in any way.
		addModule(ModuleTab.PLAYER, new AutoRunModule());
		addModule(ModuleTab.PLAYER, new AutoJumpModule());
		addModule(ModuleTab.PLAYER, new DamageLeaveModule());
		addModule(ModuleTab.PLAYER, new TotemRestockModule());

		addModule(ModuleTab.WORLD, new NukerModule());
		addModule(ModuleTab.WORLD, new TNTTimerModule());
		addModule(ModuleTab.WORLD, new CreeperTimerModule());

		addModule(ModuleTab.SMP, new RaidClockModule());
	}

	private void addModule(ModuleTab tab, IModule module) {
		KxmischesDomiMod.getInstance().getModuleWrapper().registerModule(tab, module);
	}

}
