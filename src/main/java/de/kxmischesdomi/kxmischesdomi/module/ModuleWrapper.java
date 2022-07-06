package de.kxmischesdomi.kxmischesdomi.module;

import com.mojang.blaze3d.platform.InputConstants;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

import java.util.*;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ModuleWrapper {

	private final Map<Class<? extends IModule>, IModule> byClass;
	private final Map<ModuleTab, Collection<IModule>> modules;
	private final Map<IModule, KeyMapping> keyMappings;

	public ModuleWrapper() {
		modules = new LinkedHashMap<>();
		byClass = new HashMap<>();
		keyMappings = new LinkedHashMap<>();
	}

	public void registerModule(ModuleTab tab, IModule module) {

		KeyMapping keyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"module." + module.getName(),
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.getValue(),
				"category.modules"
		));

		keyMappings.put(module, keyMapping);

		Collection<IModule> modules = getModulesByTab().computeIfAbsent(tab, tab1 -> new LinkedList<>());
		modules.add(module);
		byClass.put(module.getClass(), module);
	}

	public Map<IModule, KeyMapping> getKeyMappings() {
		return keyMappings;
	}

	public IModule getModuleByClass(Class<? extends IModule> clazz) {
		return byClass.get(clazz);
	}

	public boolean isModuleEnabled(Class<? extends IModule> clazz) {
		IModule module = byClass.get(clazz);
		return module != null && module.isEnabled();
	}

	public Map<ModuleTab, Collection<IModule>> getModulesByTab() {
		return modules;
	}

	public Collection<IModule> getModules() {
		Collection<IModule> modules = new LinkedList<>();
		for (Collection<IModule> value : getModulesByTab().values()) {
			modules.addAll(value);
		}
		return modules;
	}

}
