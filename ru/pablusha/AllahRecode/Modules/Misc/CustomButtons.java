package ru.pablusha.AllahRecode.Modules.Misc;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class CustomButtons extends Module {
	public static Module mod;
	public CustomButtons() {
		super("CustomButtons", 0x00, Type.Misc);
		mod = this;
	}
	// edits in GuiButton
}
