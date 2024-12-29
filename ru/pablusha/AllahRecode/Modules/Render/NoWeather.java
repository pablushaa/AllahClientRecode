package ru.pablusha.AllahRecode.Modules.Render;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class NoWeather extends Module {
	public static Module mod;
	public NoWeather() {
		super("NoWeather", 0x00, Type.Render);
		mod = this;
	}
	
	// edits in World
}