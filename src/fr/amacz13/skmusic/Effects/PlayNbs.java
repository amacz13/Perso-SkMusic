package fr.amacz13.skmusic.Effects;

import java.io.File;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class PlayNbs extends Effect {

	private Expression<String> song;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			ParseResult paramParseResult) {
		song = (Expression<String>) expr[0];
		player = (Expression<Player>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean b) {
		return "[skmusic|nbs] play [song|music] %string% to %player%";
	}

	@Override
	public void execute(Event e) {
		Player p = player.getSingle(e);
		String fileName = song.getSingle(e);
    	if (!fileName.contains(".nbs")){
    		fileName = fileName + ".nbs";
    	}
		File music = new File(SkMusic.plugin.getDataFolder(), fileName);
		if (!music.exists()) {
			Bukkit.getConsoleSender().sendMessage("[§6SkMusic§f] §cError while loading §f" + song.getSingle(e)
					+ "§c. Maybe the song does not exist?");
		} else {
			Song s = NBSDecoder.parse(music);
			SongPlayer sp = new RadioSongPlayer(s);
			if (SkMusic.songPlayers.containsKey(p)){
				SkMusic.songPlayers.get(p).destroy();
				SkMusic.songPlayers.replace(p, sp);
			} else {
				SkMusic.songPlayers.put(p, sp);
			}
			sp.setAutoDestroy(true);
			sp.addPlayer(p);
			sp.setPlaying(true);
		}
	}

}