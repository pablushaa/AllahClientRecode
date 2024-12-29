package ru.pablusha.AllahRecode.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ibm.icu.impl.ICUService.Key;
import com.ibm.icu.text.UFormat;
import com.viaversion.viaversion.libs.snakeyaml.scanner.Scanner;

import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.Modules.Combat.KillAura;
import ru.pablusha.AllahRecode.Modules.Misc.Panic;
import ru.pablusha.AllahRecode.Modules.Render.TargetHUD;

public class FileManager {

	public static File DIR = new File("alah");
	public static File modules = new File(DIR, "modules.json");
	
	public void init() {
		if(!DIR.exists()) {DIR.mkdirs();}
		if(!modules.exists()) {
			saveMods();
		} else {
			loadMods();
		}
	}

	public void saveMods() {
		try {
			if (!Panic.mod.enabled) {
				JsonObject json = new JsonObject();
				for(Module m : AllahClient.loader.modules) {
					JsonObject jsonMod = new JsonObject();
					jsonMod.addProperty("enabled", m.enabled);
					jsonMod.addProperty("bind", m.bind);
					json.add(m.name, jsonMod);
				}
				
				PrintWriter save = new PrintWriter(new FileWriter(modules));
				save.println(JsonUtil.prettyJson.toJson(json));
				save.close();
				
				PrintWriter writer = new PrintWriter(new FileWriter(new File(DIR, "cfg")));
				
				writer.write("");
				writer.println("color=" + AllahClient.color);
				writer.println("rbdelay=" + AllahClient.rainbowDelay);
				writer.println("corner=" + AllahClient.corner);
				writer.println("k_fov=" + KillAura.fov);
				writer.println("k_range=" + KillAura.range);
				writer.println("k_timer=" + KillAura.timer);
				writer.print("th_range=" + TargetHUD.range);
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }

        folder.delete();
    }
	
	private void loadMods() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("alah/cfg"));
			String line;
			while ((line = br.readLine()) != null) {
				setVar(line);
			}
			br.close();
			
			BufferedReader load = new BufferedReader(new FileReader(modules));
			JsonObject json = (JsonObject)JsonUtil.parser.parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while(itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				Module m = AllahClient.loader.getModByName(entry.getKey());
				if(m != null) {
					JsonObject jsonMod = (JsonObject)entry.getValue();
					boolean enabled = jsonMod.get("enabled").getAsBoolean();
					int bind = jsonMod.get("bind").getAsInt();
					
					m.bind = bind;
					if(enabled) {
						try {
							m.enabled = true;
							m.onEnable();
						} catch (Exception e) {}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setVar(String s) {
		try {
			String[] pair = s.split("=");
			String key = pair[0], value = pair[1];
			switch (key) {
			case "color":
				AllahClient.color = Integer.parseInt(value);
				AllahClient.acolor = (AllahClient.color & 0x00FFFFFF) | 0xFF000000;
				break;
			case "rbdelay":
				AllahClient.rainbowDelay = Integer.parseInt(value);
				break;
			case "corner":
				AllahClient.corner = Integer.parseInt(value);
				break;
			case "k_fov":
				KillAura.fov = Double.parseDouble(value);
				break;
			case "k_range":
				KillAura.range = Float.parseFloat(value);
				break;
			case "k_timer":
				KillAura.timer = Integer.parseInt(value);
				break;
			case "th_range":
				TargetHUD.range = Integer.parseInt(value);
				break;
			default:
				break;
			}
		} catch (Exception e) {}
	}
}