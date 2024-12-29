package ru.pablusha.AllahRecode.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class TriggerBot extends Module {

	public TriggerBot() {
		super("TriggerBot", 0x00, Type.Combat);
	}
	
	public void onUpdate() {
		if (mc.player.getCooledAttackStrength(0) == 1) {
			try {
				RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
				if (objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && objectMouseOver != null) {
					if(Criticals.mod.enabled) {
						Criticals.mod.doCrit();
					}
					mc.clickMouse();
				}
			} catch(Exception ignored) {}
		}
	}
}