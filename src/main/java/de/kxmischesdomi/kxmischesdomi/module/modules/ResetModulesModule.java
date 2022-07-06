package de.kxmischesdomi.kxmischesdomi.module.modules;

import com.google.gson.JsonObject;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ResetModulesModule extends AbstractModule {

	@Override
	public String getName() {
		return "reset_modules";
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (IModule module : KxmischesDomiMod.getInstance().getModuleWrapper().getModules()) {
			if (!module.getClass().equals(getClass())) {
				module.setEnabled(module.getDefaultValue());
			}
		}
	}

	@Override
	public void writeCustomData(JsonObject object) {

	}

	@Override
	public void loadCustomData(JsonObject object) {

	}

}
