package ru.pablusha.AllahRecode.Modules.Render;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class AntiOverlay extends Module {
	public static Module mod;
	public AntiOverlay() {
		super("AntiOverlay", 0x00, Type.Render);
		mod = this;
	}
	
	// edits in: GuiBossOverlay, GuiIngame, EntityRenderer, ItemRenderer
}