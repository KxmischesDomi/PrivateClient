package de.kxmischesdomi.kxmischesdomi.module.modules.client;

import com.google.gson.JsonObject;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import de.kxmischesdomi.kxmischesdomi.module.types.OneTimeModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ResetModulesModule extends OneTimeModule {

	@Override
	public String getName() {
		return "reset_modules";
	}

	@Override
	public void onActivate() {
		for (IModule module : KxmischesDomiMod.getInstance().getModuleWrapper().getModules()) {
			if (!(module instanceof OneTimeModule)) {
				module.setEnabled(module.getDefaultValue());
			}
		}
	}

}
