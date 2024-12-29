package ru.pablusha.AllahRecode.Modules.Movement;

import net.minecraft.network.play.client.CPacketPlayer;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Fly extends Module {
	public static float sped = 0.2f;
    public Fly() {
        super("Fly", 0x00, Type.Movement);
    }

    public void onEnable() {
        mc.player.capabilities.setFlySpeed(sped);
    }
    
    public void onUpdate() {
        mc.player.capabilities.isFlying = true;
        mc.player.capabilities.setFlySpeed(sped);
        if (mc.player.ticksExisted % 10 == 0 && !mc.player.onGround) {
        	if(mc.player.motionY > 0) {
        		mc.player.motionY = -0.04;
        	}
        	mc.player.motionY = -0.04;
        }
        if (mc.player.ticksExisted % 15 == 0 && !mc.player.onGround) {
        	mc.player.motionY = +0.04;
        }
        if(mc.player.fallDistance > 1.0f) {
			mc.player.connection.sendPacket(new CPacketPlayer(true));
		}
    }

    public void onDisable() {
        mc.player.capabilities.isFlying = false;
    	if(mc.player.isSpectator() || mc.player.isCreative()) {
            mc.player.capabilities.allowFlying = true;
    	}
    }
}