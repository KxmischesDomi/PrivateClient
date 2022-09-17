package de.kxmischesdomi.kxmischesdomi.module.types;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public abstract class AbstractModule implements IModule {

	final boolean defaultValue;
	boolean enabled = false;

	public AbstractModule() {
		defaultValue = false;
	}

	public AbstractModule(boolean enabled) {
		this.enabled = enabled;
		this.defaultValue = enabled;
	}

	public void onEnable() { }
	public void onDisable() { }

	@Override
	public Component getTitle() {
		return Component.translatable("module." + getName());
	}

	@Override
	public Component getOverlayTitle() {
		return getTitle();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public int getBackgroundColor() {
		return isEnabled() ? 0xFF0798FC : 0xFF364760;
	}

	@Override
	public boolean inOverlay() {
		return true;
	}

	@Override
	public boolean getDefaultValue() {
		return defaultValue;
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) return;
		if (!enabled) onDisable();
		else onEnable();
		this.enabled = enabled;
	}

	@Override
	public void loadCustomData(JsonObject object) {
		setEnabled(object.get("enabled") != null && object.get("enabled").getAsBoolean());
	}

	@Override
	public void writeCustomData(JsonObject object) {
		object.addProperty("enabled", enabled);
	}

}
