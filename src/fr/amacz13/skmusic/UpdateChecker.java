package fr.amacz13.skmusic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

	public static void checkforUpdate(){
		try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream()
                    .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + SkMusic.resource_nb)
                            .getBytes("UTF-8"));
            String version = new BufferedReader(new InputStreamReader(
                    con.getInputStream())).readLine();
            if (version.length() <= 7) {
            	if (version.equals(SkMusic.ver)){
            		SkMusic.getConsole().sendMessage("[§6SkMusic§r] §2You're up to date !");
            	} else {
            		SkMusic.getConsole().sendMessage("[§6SkMusic§r] §eA new version of SkMusic is available : "+version);
            		SkMusic.getConsole().sendMessage("[§6SkMusic§r] §eDownload it at : §3https://www.spigotmc.org/resources/skmusic.34625/");
            	}
            }
        } catch (Exception ex) {
        	SkMusic.getConsole().sendMessage("[§6SkMusic§r] §cAn error occured while checking for updates. Maybe SpigotMc is offline...");
        }
	}
	
}
