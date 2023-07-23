package net.slqmy.block_muncher.managers;

import net.slqmy.block_muncher.BlockMuncher;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public final class ConfigurationManager {
	private static FileConfiguration config;

	public static void setUpConfig(@NotNull final BlockMuncher plugin) {
		config = plugin.getConfig();
		plugin.saveDefaultConfig();
	}

	public static int getMinPlayers() {
		return config.getInt("min-players");
	}

	public static int getCountdownSeconds() {
		return config.getInt("countdown-seconds");
	}

	public static Location getLobbySpawn() {
		return config.getObject("lobby-spawn", Location.class);
	}
}
