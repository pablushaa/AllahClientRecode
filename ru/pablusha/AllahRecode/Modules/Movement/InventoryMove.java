package ru.pablusha.AllahRecode.Modules.Movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class InventoryMove extends Module { // module by @strokegmd (https://github.com/strokegmd)
	public InventoryMove() {
		super("InventoryMove", 0x00, Type.Movement);
	}
	
	public void onUpdate() {
		KeyBinding[] binds = {mc.gameSettings.keyBindForward,
							  mc.gameSettings.keyBindBack,
							  mc.gameSettings.keyBindLeft,
							  mc.gameSettings.keyBindRight,
							  mc.gameSettings.keyBindSprint,
							  mc.gameSettings.keyBindJump,
							  mc.gameSettings.keyBindSneak};
		
		for(KeyBinding bind : binds) {
			if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)) {
				bind.setKeyBindState(bind.getKeyCode(), Keyboard.isKeyDown(bind.getKeyCode()));
			}
		}
	}

}
