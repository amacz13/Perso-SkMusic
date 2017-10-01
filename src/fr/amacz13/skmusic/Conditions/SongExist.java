package fr.amacz13.skmusic.Conditions;

import java.io.File;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class SongExist extends Condition {
	
		private Expression<String> song;
	
	 	@SuppressWarnings("unchecked")
	    @Override
	    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
	 		song = (Expression<String>) expr[0];
	        return true;
	    }

	    @Override
	    public String toString(@Nullable Event e, boolean b) {
	    	return "(song|music) %string% exist";
	    }

	    @Override
	    public boolean check(Event e) {
	    	String music = song.getSingle(e);
	    	if (!music.contains(".nbs")){
	    		music = music + ".nbs";
	    	}
	    	File f = new File(SkMusic.plugin.getDataFolder(),music);
    		if (f.exists()) {
    	        return true;
    		}
    		return false;
	    }
	
	
}
