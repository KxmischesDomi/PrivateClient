package de.kxmischesdomi.kxmischesdomi.module.types;

import com.google.gson.JsonObject;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class OneTimeModule extends AbstractModule {

	public abstract void onActivate();

	@Override
	public final void onEnable() { }
	@Override
	public final void onDisable() { }

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled) {
			onActivate();
		}
	}

	@Override
	public int getBackgroundColor() {
		return 0xFFbf0000;
	}

	@Override
	public void writeCustomData(JsonObject object) {

	}

	@Override
	public void loadCustomData(JsonObject object) {

	}

}
