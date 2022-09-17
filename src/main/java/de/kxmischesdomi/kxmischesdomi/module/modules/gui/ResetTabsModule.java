package de.kxmischesdomi.kxmischesdomi.module.modules.gui;

import de.kxmischesdomi.kxmischesdomi.module.ModuleTab;
import de.kxmischesdomi.kxmischesdomi.module.types.OneTimeModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ResetTabsModule extends OneTimeModule {

	@Override
	public String getName() {
		return "reset_tabs";
	}

	@Override
	public void onActivate() {
		ModuleTab.initPositions();
	}

}
