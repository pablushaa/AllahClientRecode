package ru.pablusha.AllahRecode.Modules.Render;

import java.awt.Graphics2D;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import ru.pablusha.AllahRecode.ClickGUI;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class ClickGui extends Module {
	private ClickGUI cgui;
	public ClickGui() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, Type.Render);
	}
	
	public void onEnable() {
		if(cgui == null) {
			this.cgui = new ClickGUI();
		}
		
		if (mc.currentScreen == null) {
			mc.displayGuiScreen(cgui);
		}
		this.enabled = false;
	}
}
