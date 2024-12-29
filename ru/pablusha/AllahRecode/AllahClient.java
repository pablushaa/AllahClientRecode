package ru.pablusha.AllahRecode;

import de.florianmichael.viamcp.ViaMCP;
import net.minecraft.client.Minecraft;
import ru.pablusha.AllahRecode.ModuleSys.Loader;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.Modules.Render.HUD;
import ru.pablusha.AllahRecode.Utils.FileManager;
import ru.pablusha.AllahRecode.Utils.Font.FontUtils;

public class AllahClient {
	public static String clientName = "AllahClient Recode v1.0";
	public static String version = "Recode 1.0";
	public static FileManager fileMgr;
	public static Loader loader;
	public static Minecraft mc;
	
	public static int color = -1;
	public static int acolor = -1;
	public static int corner = 20;
	public static int rainbowDelay = 50;

	public void start(Minecraft minecraft) {
		loader = new Loader();
		loader.loadModules();
		fileMgr = new FileManager();
		mc = minecraft;
		System.out.println("[" + clientName + "] Modules loaded!!!");
		ViaMCP.create();
		ViaMCP.INSTANCE.initAsyncSlider();
	}
	
	// edits in Minecraft
	
	public static void onKeyPress(int bind) {
		if (mc.currentScreen == null) {
			for (Module m : loader.modules) {
				if (m.bind == bind) {
					m.toggle();
				}
			}
		}
	}
}
