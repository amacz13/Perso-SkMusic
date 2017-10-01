package fr.amacz13.skmusic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import fr.amacz13.skmusic.Conditions.SongExist;
import fr.amacz13.skmusic.Effects.BroadcastNbs;
import fr.amacz13.skmusic.Effects.PlayNbs;
import fr.amacz13.skmusic.Effects.PlayNbsInRadius;
import fr.amacz13.skmusic.Effects.SetFadeDuration;
import fr.amacz13.skmusic.Effects.SetVolume;
import fr.amacz13.skmusic.Effects.StopNbs;
import fr.amacz13.skmusic.Effects.StopNbsBroadcast;
import fr.amacz13.skmusic.Expressions.GetFadeDuration;
import fr.amacz13.skmusic.Expressions.GetSongAuthor;
import fr.amacz13.skmusic.Expressions.GetSongLength;
import fr.amacz13.skmusic.Expressions.GetSongSpeed;
import fr.amacz13.skmusic.Expressions.GetSongTitle;
import fr.amacz13.skmusic.Expressions.GetVolume;

public class SkMusic extends JavaPlugin {
	// Console de bukkit
	private static ConsoleCommandSender console = Bukkit.getConsoleSender();
	public static SkMusic plugin;
	public static Map<Player,SongPlayer> songPlayers = new HashMap<>();
	public static int resource_nb = 34625;
	public static String ver = "2.0.0";

	// Méthode d'activation
	@Override
	public void onEnable() {
		
		console.sendMessage("[§6SkMusic§r] §aEnabling SkMusic version "+ver);
		UpdateChecker.checkforUpdate();
		//Events
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		//Effects
		Skript.registerEffect(PlayNbs.class, "[skmusic] [nbs] play [song][music] %string% to %player%");
		Skript.registerEffect(StopNbs.class, "[skmusic] [nbs] stop play[ing] [song][music] to %player%");
		Skript.registerEffect(BroadcastNbs.class, "[skmusic] [nbs] broadcast [song][music] %string%");
		Skript.registerEffect(StopNbsBroadcast.class, "[skmusic] [nbs] stop broadcast[ing] [song][music]");
		Skript.registerEffect(PlayNbsInRadius.class, "[skmusic] [nbs] play [song][music] %string% to [all] players in radius %integer% arround [location] %location%");
		Skript.registerEffect(SetVolume.class, "[skmusic] [nbs] set volume of [player] %player% to %integer%");
		Skript.registerEffect(SetFadeDuration.class, "[skmusic] [nbs] set fade duration of [player] %player% to %integer%");
		
		//Conditions
		Skript.registerCondition(SongExist.class, "[skmusic] [nbs] (song|music) %string% exist");
		
		//Expressions
		Skript.registerExpression(GetVolume.class, Integer.class, ExpressionType.PROPERTY, "[skmusic] [nbs] volume of [player] %player%", "%player%['s] [music] volume");
		Skript.registerExpression(GetFadeDuration.class, Integer.class, ExpressionType.PROPERTY, "[skmusic] [nbs] fade duration of [player] %player%");
		Skript.registerExpression(GetSongTitle.class, String.class, ExpressionType.PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) (name|title)");
		Skript.registerExpression(GetSongAuthor.class, String.class, ExpressionType.PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) author");
		Skript.registerExpression(GetSongSpeed.class, Float.class, ExpressionType.PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) speed");
		Skript.registerExpression(GetSongLength.class, Integer.class, ExpressionType.PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) (length|duration)");
		
		//Addon activation
		Skript.registerAddon(this);
		SkMusic.plugin = this;
		File dir = this.getDataFolder();
		ConsoleCommandSender console = getServer().getConsoleSender();
		if (!dir.exists()) {
			console.sendMessage("[§6SkMusic§f] Creating SkMusic Directory...");
			dir.mkdirs();
		}
	}

	// Méthode de désactivation
	@Override
	public void onDisable() {
		// Message en rouge
		console.sendMessage("[§6SkMusic§r] §cDisabling SkMusic...");
	}

	public static ConsoleCommandSender getConsole() {
		return console;
	}
}
