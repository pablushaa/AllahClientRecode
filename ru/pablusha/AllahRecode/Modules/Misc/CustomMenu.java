package ru.pablusha.AllahRecode.Modules.Misc;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class CustomMenu extends Module {
	public static Module mod;
	public CustomMenu() {
		super("CustomMenu", 0x00, Type.Misc);
		mod = this;
	}
	// edits in GuiGameOver, GuiIngameMenu, GuiMemoryErrorScreen, NetHandlerPlayClient
}