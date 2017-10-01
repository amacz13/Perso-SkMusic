package fr.amacz13.skmusic.Effects;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class StopNbsBroadcast extends Effect {

    
	    @Override
	    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
	    	return true;
	    }

	    @Override
	    public String toString(@Nullable Event e, boolean b) {
	    	return "[skmusic|nbs] stop broadcast[ing] [song|music]";
	    }

	    @Override
	    protected void execute(Event e) {
	    	for (Player p : Bukkit.getOnlinePlayers()) {
	    		if (SkMusic.songPlayers.containsKey(p)){
	    			SkMusic.songPlayers.get(p).setPlaying(false);
	    		}
	    	}
	    }
}
