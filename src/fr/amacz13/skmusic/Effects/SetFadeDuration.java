package fr.amacz13.skmusic.Effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.FadeType;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class SetFadeDuration extends Effect {

	private Expression<Integer> dur;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
		ParseResult paramParseResult) {
		player = (Expression<Player>) expr[0];
		dur = (Expression<Integer>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean b) {
		return "[skmusic|nbs] set fade duration of [player] %player% to %integer%";
	}

	@Override
	public void execute(Event e) {
		Player p = player.getSingle(e);
		int duration = dur.getSingle(e);
		if (SkMusic.songPlayers.containsKey(p)){
			SkMusic.songPlayers.get(p).setFadeType(FadeType.FADE_LINEAR);
			SkMusic.songPlayers.get(p).setFadeDuration(duration);
		}
	}

}