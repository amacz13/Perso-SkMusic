package fr.amacz13.skmusic.Effects;


import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class StopNbs extends Effect {

		private Expression<Player> player;

	    @SuppressWarnings("unchecked")
		@Override
	    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
	        player = (Expression<Player>) expr[0];
	    	return true;
	    }

	    @Override
	    public String toString(@Nullable Event e, boolean b) {
	    	return "[skmusic|nbs] stop play[ing] (song|music) to %player%";
	    }

	    @Override
	    protected void execute(Event e) {
	    	Player p = player.getSingle(e);
	    	if (SkMusic.songPlayers.containsKey(p)){
	    		SkMusic.songPlayers.get(p).setPlaying(false);
	    	}
	    }
}
