package fr.amacz13.skmusic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class Events implements Listener {

	@EventHandler
	private void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if (SkMusic.songPlayers.containsKey(p)){
			SkMusic.songPlayers.remove(p);
		}
	}
}
