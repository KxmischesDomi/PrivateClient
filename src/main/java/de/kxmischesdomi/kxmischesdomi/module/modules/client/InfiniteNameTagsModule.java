package de.kxmischesdomi.kxmischesdomi.module.modules.client;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;
import de.kxmischesdomi.kxmischesdomi.module.types.OneTimeModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class InfiniteNameTagsModule extends AbstractModule {

	@Override
	public String getName() {
		return "infinite_nametags";
	}

	@Override
	public void onEnable() {
		KxmischesDomiMod.getInstance().getModuleWrapper().getModuleByClass(NoNameTagsModule.class).setEnabled(false);
	}
}
