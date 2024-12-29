package ru.pablusha.AllahRecode.Modules.Movement;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class AirJump extends Module {
	public AirJump() {
		super("AirJump", 0x00, Type.Movement);
	}
	
	public void onUpdate() {
        if(mc.gameSettings.keyBindJump.isPressed()) {
        	mc.player.jump();
        }
	}
}