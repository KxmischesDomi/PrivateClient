package de.kxmischesdomi.kxmischesdomi.module.modules.gui;

import de.kxmischesdomi.kxmischesdomi.module.types.AbstractModule;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class OverlayModule extends AbstractModule {

	public OverlayModule() {
		super(true);
	}

	@Override
	public String getName() {
		return "overlay";
	}

	@Override
	public boolean inOverlay() {
		return false;
	}

}
