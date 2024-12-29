package ru.pablusha.AllahRecode.Modules.Misc;

import java.io.File;

import org.lwjgl.opengl.Display;

import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;
import ru.pablusha.AllahRecode.Utils.FileManager;

public class Panic extends Module {
	public static Module mod;
	public Panic() {
		super("Panic", 0x00, Type.Misc);
		mod = this;
	}
	
	public void onEnable() {
		for (Module m : AllahClient.loader.modules) {
			m.enabled = false;
			m.onDisable();
		}
		
		Display.setTitle("Minecraft 1.12.2");
		FileManager.deleteFolder(new File("alah"));
		FileManager.deleteFolder(new File("ViaMCP"));
	}
}