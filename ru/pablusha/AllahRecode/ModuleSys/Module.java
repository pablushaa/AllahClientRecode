package ru.pablusha.AllahRecode.ModuleSys;

import net.minecraft.client.Minecraft;
import ru.pablusha.AllahRecode.Utils.Font.FontUtils;

public class Module {
	public int bind;
	public Type type;
	public String name;
	public Boolean enabled = false;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Module(String name, int bind, Type type) {
		this.name = name;
		this.bind = bind;
		this.type = type;
		this.enabled = false;
	}
	
	public void toggle() {
		this.enabled = !this.enabled;
		if (this.enabled) { this.onEnable(); }
		else { this.onDisable(); }
	}
	
	public int getWidth() {
		return (int) FontUtils.normal.getStringWidth(this.name);
	}
	
	public void onEnable() {}
	public void onDisable() {}
	
	public void onUpdate() {}
	public void onRender2D() {}
	public void onRender3D() {}
}
