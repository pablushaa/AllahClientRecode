package ru.pablusha.AllahRecode.Modules.Render;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class NoScoreBoard extends Module {
	public static Module mod;
	public NoScoreBoard() {
		super("NoScoreBoard", 0x00, Type.Render);
		mod = this;
	}
	
	// edits in GuiIngame
}