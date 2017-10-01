package fr.amacz13.skmusic.Expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.Song;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class GetSongTitle extends SimpleExpression<String>{

	private Expression<Player> player;
	
	@Override
    public boolean isSingle() {
        return true;
    }
 
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
    	player = (Expression<Player>) expr[0];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
    	return "[skmusic|nbs] %player%['s] (song|music) (name|title)";
    }
 
    @Override
    @Nullable
    protected String[] get(Event e) {
    	Player p = player.getSingle(e);
    	String title = "none";
    	if (SkMusic.songPlayers.containsKey(p)){
	    	Song s = SkMusic.songPlayers.get(p).getSong();
	    	title = s.getTitle();
    	}
        return new String[]{ title };
    }
}