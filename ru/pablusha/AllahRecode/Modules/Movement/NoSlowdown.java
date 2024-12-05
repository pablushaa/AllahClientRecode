package ru.pablusha.AllahRecode.Modules.Movement;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class NoSlowdown extends Module {
	public static Module mod;
	public NoSlowdown() {
		super("NoSlowdown", 0x00, Type.Movement);
		mod = this;
	}
} // edits in EntityPlayerSP