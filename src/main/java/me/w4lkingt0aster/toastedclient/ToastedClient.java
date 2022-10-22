package me.w4lkingt0aster.toastedclient;

import me.w4lkingt0aster.toastedclient.config.ToastedClientConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToastedClient implements ModInitializer {
	public static final String MOD_ID = "toastedclient";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ToastedClientConfig.registerConfigs();
	}
}
