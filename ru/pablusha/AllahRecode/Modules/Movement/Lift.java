package ru.pablusha.AllahRecode.Modules.Movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Lift extends Module {
	public Lift() {
		super("Lift", 0x00, Type.Movement);
	}
	
	public void onUpdate() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.player.jump();
	}
}
