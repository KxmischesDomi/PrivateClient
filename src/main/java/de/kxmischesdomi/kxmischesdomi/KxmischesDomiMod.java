package de.kxmischesdomi.kxmischesdomi;

import com.mojang.blaze3d.platform.InputConstants;
import de.kxmischesdomi.kxmischesdomi.module.ConfigLoader;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import de.kxmischesdomi.kxmischesdomi.module.ModuleLoader;
import de.kxmischesdomi.kxmischesdomi.module.ModuleWrapper;
import de.kxmischesdomi.kxmischesdomi.screen.ModScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.checkerframework.checker.units.qual.C;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KxmischesDomiMod implements ClientModInitializer {

	public static KeyMapping MENU_MAPPING;
	public static final String MOD_ID = "kxmischesdomi";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static KxmischesDomiMod instance;

	private ModuleWrapper moduleWrapper;
	private ConfigLoader configLoader;

	@Override
	public void onInitializeClient() {
		instance = this;

		MENU_MAPPING = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.kxmischesdomi.main_menu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"category.kxmischesdomi"
		));

		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			while (MENU_MAPPING.consumeClick()) {
				Minecraft.getInstance().setScreen(new ModScreen());
			}
			for (Map.Entry<IModule, KeyMapping> entry : moduleWrapper.getKeyMappings().entrySet()) {
				if (entry.getValue().consumeClick()) {
					entry.getKey().setEnabled(!entry.getKey().isEnabled());
				}
			}
		});

		moduleWrapper = new ModuleWrapper();
		new ModuleLoader().init();
		configLoader = new ConfigLoader();
		configLoader.load();
	}

	public ModuleWrapper getModuleWrapper() {
		return moduleWrapper;
	}

	public ConfigLoader getConfigLoader() {
		return configLoader;
	}

	public static boolean isInGame() {
		return Minecraft.getInstance().player != null;
	}

	public static KxmischesDomiMod getInstance() {
		return instance;
	}

}
