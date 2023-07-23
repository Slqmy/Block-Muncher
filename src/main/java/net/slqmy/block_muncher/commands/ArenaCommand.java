package net.slqmy.block_muncher.commands;

import net.slqmy.block_muncher.BlockMuncher;
import net.slqmy.block_muncher.enums.GameState;
import net.slqmy.block_muncher.types.Arena;
import net.slqmy.block_muncher.types.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArenaCommand extends Command {
	private BlockMuncher plugin;

	public ArenaCommand(@NotNull final BlockMuncher plugin) {
		super(
						"arena",
						"Join or leave a minigame arena and see all the active arenas.",
						"/arena <join | leave | list> (arena)",
						new Integer[] { 1, 2 },
						new String[] { "game" },
						"block_muncher.arena",
						true
		);

		this.plugin = plugin;
	}

	@Override
	public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
		final Player player = (Player) sender;

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("list")) {
				for (final Arena arena : plugin.getArenaManager().getArenas()) {
					player.sendMessage(ChatColor.GREEN + "- " + "(" + arena.getID() + arena.getState().name() + ")");
				}
			} else if (args[0].equalsIgnoreCase("leave")) {
				final Arena arena = plugin.getArenaManager().getArena(player.getUniqueId());

				if (arena == null) {
					player.sendMessage(ChatColor.RED + "You are not currently in an arena!");
				} else {
					player.sendMessage(ChatColor.GREEN + "You have left the arena.");

					arena.removePlayer(player);
				}
			} else {
				return false;
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("join")) {
				if (plugin.getArenaManager().getArena(player.getUniqueId()) == null) {
					final int id;

					try {
						id = Integer.parseInt(args[1]);
					} catch (final NumberFormatException exception) {
						player.sendMessage(ChatColor.RED + "Invalid arena ID!");
						return false;
					}

					if (id > 0 && id <= plugin.getArenaManager().getArenas().size()) {
						final Arena arena = plugin.getArenaManager().getArena(id);
						assert arena != null;

						if (arena.getState() != GameState.PLAYING) {
							arena.addPlayer(player);
							player.sendMessage(ChatColor.GREEN + "You have successfully been added to the arena!");
						} else {
							player.sendMessage(ChatColor.RED + "There is currently an active game going on in that arena!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arena ID!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You are already in an arena!");
				}
			} else {
				return false;
			}
		}

		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull final CommandSender sender, @NotNull final String[] args) {
		return null;
	}
}
