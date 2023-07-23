package net.slqmy.block_muncher.types;

import net.slqmy.block_muncher.enums.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public final class Game {
	private final Arena arena;
	private final HashMap<UUID, Integer> points = new HashMap<>();

	public Game(@NotNull final Arena arena) {
		this.arena = arena;
	}

	public void start() {
		arena.setState(GameState.PLAYING);
		arena.sendTitle(ChatColor.GREEN + "The game has started!");
		arena.sendMessage(ChatColor.YELLOW + "Your objective is to break " + ChatColor.RED + "20" + ChatColor.YELLOW + " blocks as fast as possible!");

		for (final UUID uuid : arena.getPlayers()) {
			points.put(uuid, 0);
		}
	}

	public void addPoint(@NotNull final Player player) {
		final UUID uuid = player.getUniqueId();
		final int playerPoints = points.get(uuid) + 1;

		if (playerPoints == 20) {
			arena.sendTitle(ChatColor.GOLD + player.getName() + "HAS WON!");
			arena.reset(true);

			return;
		}

		player.sendMessage(ChatColor.GREEN + "+1 Point");
		points.replace(uuid, playerPoints);
	}
}
