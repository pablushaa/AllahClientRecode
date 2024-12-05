package ru.pablusha.AllahRecode.Modules.Combat;

import org.lwjgl.input.Keyboard;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Velocity extends Module {
	public static Velocity mod;

	public Velocity() {
		super("Velocity", Keyboard.KEY_NONE, Type.Combat);
		mod = this;
	}
	
	// edits in SPacketEntityVelocity
}