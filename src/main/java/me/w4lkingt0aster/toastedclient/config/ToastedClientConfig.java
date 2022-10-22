package me.w4lkingt0aster.toastedclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.w4lkingt0aster.toastedclient.ToastedClient;
import me.w4lkingt0aster.toastedclient.screen.widgets.DoubleFieldWidget;
import me.w4lkingt0aster.toastedclient.utils.BoolConfigValue;
import me.w4lkingt0aster.toastedclient.utils.ConfigValue;
import me.w4lkingt0aster.toastedclient.utils.DoubleConfigValue;
import me.w4lkingt0aster.toastedclient.utils.IntConfigValue;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToastedClientConfig {
    private static String configPath;
    private static Path path;
    private static Map<String, ConfigValue> configValues = new HashMap<>();

    public static void registerConfigs() {
        //Create config values
        configValues.put("AUTO_FISH_ENABLE", new BoolConfigValue("AUTO_FISH_ENABLE", "Auto Fish", ConfigValue.Category.Utilities, false));
        configValues.put("AUTO_FISH_RECAST_DELAY", new IntConfigValue("AUTO_FISH_RECAST_DELAY", "Auto Fish Recast Delay (Ticks)", ConfigValue.Category.Utilities, 20));
        configValues.put("FLY_HACK_ENABLE", new BoolConfigValue("FLY_HACK_ENABLE", "Fly", ConfigValue.Category.Movement, false));
        configValues.put("FLY_HACK_SPEED_MULTIPLIER", new DoubleConfigValue("FLY_HACK_SPEED_MULTIPLIER", "Fly Speed Multiplier", ConfigValue.Category.Movement, 1));

        //Path variables for reading/writing config file
        configPath = FabricLoader.getInstance().getConfigDir() + "\\" + ToastedClient.MOD_ID + ".json";
        path = Paths.get(configPath);
        File configFile = new File(configPath);
        try {
            if (configFile.createNewFile()) {
                writeConfigFile();
                ToastedClient.LOGGER.info("Created config file.");

            }
            else {
                ToastedClient.LOGGER.info("Found config file.");
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        if (readConfigFile()) {
            ToastedClient.LOGGER.info("Loaded config file.");
        }

    }

    public static boolean readConfigFile() {
        boolean ret = false;
        try {
            Reader reader = Files.newBufferedReader(path);
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

            for (ConfigValue value : configValues.values()) {
                if (value instanceof BoolConfigValue) {
                    value.setValue(parser.get(value.getConfigKey()).getAsBoolean());
                    ToastedClient.LOGGER.info(value.getConfigKey() + ": " + value.getValue());
                }
                else if (value instanceof IntConfigValue) {
                    value.setValue(parser.get(value.getConfigKey()).getAsInt());
                    ToastedClient.LOGGER.info(value.getConfigKey() + ": " + value.getValue());
                }
                else if (value instanceof DoubleConfigValue) {
                    value.setValue(parser.get(value.getConfigKey()).getAsDouble());
                    ToastedClient.LOGGER.info(value.getConfigKey() + ": " + value.getValue());
                }
            }
            reader.close();
            ret = true;
        }
        catch (Exception e) {
            ToastedClient.LOGGER.error("Error reading from config file.");
            e.printStackTrace();
            ToastedClient.LOGGER.info("Try deleting the config file and restarting your game.");
        }
        return ret;
    }

    public static void writeConfigFile() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);

            Map<String, Object> config = new HashMap<>();

            for (ConfigValue value : configValues.values()) {
                config.put(value.getConfigKey(), value.getValue());
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(config));
            writer.close();
        }
        catch (Exception e) {
            ToastedClient.LOGGER.error("Error writing to config file.");
            e.printStackTrace();
        }

    }

    public static ConfigValue get(String key) {
        return configValues.get(key);
    }

    public static Collection<ConfigValue> getValues() {
        return configValues.values();
    }
}
