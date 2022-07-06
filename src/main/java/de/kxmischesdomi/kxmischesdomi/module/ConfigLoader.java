package de.kxmischesdomi.kxmischesdomi.module;


import com.google.gson.*;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.types.IModule;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ConfigLoader {

	public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

	private static File configFile;

	public void loadConfigFile() {
		if (configFile == null) {
			configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), KxmischesDomiMod.MOD_ID + ".json");
		}
	}

	public void load() {
		loadConfigFile();

		try {
			if (!configFile.exists()) {
				save();
			}
			if (configFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(configFile));
				JsonObject json = new JsonParser().parse(br).getAsJsonObject();

				for (ModuleTab tab : ModuleTab.values()) {
					try {
						JsonObject object = (JsonObject) json.get(String.format("tab.%s", tab.name().toLowerCase()));
						tab.x = object.get("x").getAsInt();
						tab.y = object.get("y").getAsInt();
						tab.focusIndex = object.get("focus").getAsInt();
					} catch (Exception exception) {
						exception.printStackTrace();
						System.err.println("Error while loading config for tab " + tab.name().toLowerCase());
					}
				}

				for (IModule module : KxmischesDomiMod.getInstance().getModuleWrapper().getModules()) {
					try {
						JsonObject object = (JsonObject) json.get(String.format("module.%s", module.getName()));
						module.loadCustomData(object);

					} catch (Exception exception) {
						System.err.println("Error while loading config for module " + module.getName());
					}

				}

			}
		} catch (Exception exception) {
			System.err.println("Couldn't load configuration file");
			exception.printStackTrace();
		}
	}

	public void save() {
		System.out.println("Saving KxmischesDomi client config");
		loadConfigFile();

		JsonObject json = new JsonObject();

		try {
			for (ModuleTab tab : ModuleTab.values()) {
				JsonObject object = new JsonObject();
				object.addProperty("x", tab.x);
				object.addProperty("y", tab.y);
				object.addProperty("focus", tab.focusIndex);
				json.add(String.format("tab.%s", tab.name().toLowerCase()), object);
			}

			for (IModule module : KxmischesDomiMod.getInstance().getModuleWrapper().getModules()) {
				JsonObject object = new JsonObject();
				module.writeCustomData(object);
				json.add(String.format("module.%s", module.getName()), object);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		String jsonString = GSON.toJson(json);

		try (FileWriter fileWriter = new FileWriter(configFile)) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			System.err.println("Couldn't save configuration file");
			e.printStackTrace();
		}
	}

}
