package net.slqmy.block_muncher;

import net.slqmy.block_muncher.managers.ArenaManager;
import net.slqmy.block_muncher.utility.ConfigurationUtility;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockMuncher extends JavaPlugin {
		private ArenaManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic.
        ConfigurationUtility.setUpConfig(this);

				manager = new ArenaManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic.
    }

		public ArenaManager getManager() {
			return  manager;
		}
}
