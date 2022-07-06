package de.kxmischesdomi.kxmischesdomi.module.types;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface IModule {

	String getName();

	Component getTitle();

	Component getOverlayTitle();

	boolean isEnabled();

	void setEnabled(boolean enabled);

	boolean getDefaultValue();

	void onEnable();
	void onDisable();

	void loadCustomData(JsonObject object);
	void writeCustomData(JsonObject object);

}
