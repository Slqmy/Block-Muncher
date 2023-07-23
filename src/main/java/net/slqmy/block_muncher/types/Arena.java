package net.slqmy.block_muncher.types;

import net.slqmy.block_muncher.enums.GameState;
import net.slqmy.block_muncher.utility.ConfigurationUtility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Arena {
	private final int id;
	private final Location spawn;

	private GameState state = GameState.WAITING;
	private final List<UUID> players = new ArrayList<>();

	public Arena(final int id, @NotNull final Location spawn) {
		this.id = id;
		this.spawn = spawn;
	}

	public void addPlayer(@NotNull final Player player) {
		players.add(player.getUniqueId());

		player.teleport(spawn);
	}

	public void removePlayer(@NotNull final Player player) {
		players.remove(player.getUniqueId());

		player.teleport(ConfigurationUtility.getLobbySpawn());
	}

	public int getID() {
		return id;
	}

	public GameState getState() {
		return state;
	}

	public List<UUID> getPlayers() {
		return players;
	}
}
