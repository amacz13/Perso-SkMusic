package fr.amacz13.skmusic.Expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import fr.amacz13.skmusic.SkMusic;

public class GetFadeDuration extends SimpleExpression<Integer>{

	private Expression<Player> player;
	
	@Override
    public boolean isSingle() {
        return true;
    }
 
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
    	player = (Expression<Player>) expr[0];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
    	return "[skmusic|nbs] fade duration of [player] %player%";
    }
 
    @Override
    @Nullable
    protected Integer[] get(Event e) {
    	Player p = player.getSingle(e);
    	int duration = 0;
    	if (SkMusic.songPlayers.containsKey(p)){
    		duration = SkMusic.songPlayers.get(p).getFadeDuration();
    	}
        return new Integer[]{ duration };
    }
}
