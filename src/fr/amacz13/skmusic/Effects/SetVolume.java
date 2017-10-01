package fr.amacz13.skmusic.Effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class SetVolume extends Effect {

	private Expression<Integer> vol;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
		ParseResult paramParseResult) {
		player = (Expression<Player>) expr[0];
		vol = (Expression<Integer>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean b) {
		return "[skmusic|nbs] set volume of [player] %player% to %integer%";
	}

	@Override
	public void execute(Event e) {
		Player p = player.getSingle(e);
		Byte volume = vol.getSingle(e).byteValue();
		if (SkMusic.songPlayers.containsKey(p)){
			SkMusic.songPlayers.get(p).setVolume(volume);
		}
	}

}