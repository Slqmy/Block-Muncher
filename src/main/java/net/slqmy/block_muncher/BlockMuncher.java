package net.slqmy.block_muncher;

import net.slqmy.block_muncher.managers.ConfigurationManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockMuncher extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic.

        ConfigurationManager.setUpConfig(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic.
    }
}
