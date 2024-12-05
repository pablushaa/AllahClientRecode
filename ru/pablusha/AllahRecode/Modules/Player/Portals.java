package ru.pablusha.AllahRecode.Modules.Player;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Portals extends Module {
	public static Module mod;
	public Portals() {
		super("Portals", 0x00, Type.Player);
		mod = this;
	}
	
	// edits in EntityPlayeSP
}