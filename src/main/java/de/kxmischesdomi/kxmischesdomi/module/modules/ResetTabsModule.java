package de.kxmischesdomi.kxmischesdomi.module.modules;

import com.google.gson.JsonObject;
import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ResetTabsModule extends AbstractModule {

	@Override
	public String getName() {
		return "reset_tabs";
	}

	@Override
	public void setEnabled(boolean enabled) {
		ModuleTab.initPositions();
	}

	@Override
	public void writeCustomData(JsonObject object) {

	}

	@Override
	public void loadCustomData(JsonObject object) {

	}

}
