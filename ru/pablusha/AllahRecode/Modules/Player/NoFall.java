package ru.pablusha.AllahRecode.Modules.Player;

import net.minecraft.network.play.client.CPacketPlayer;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class NoFall extends Module {
	public NoFall() {
		super("NoFall", 0x00, Type.Player);
	}
	
	public void onUpdate() {
		if(mc.player.fallDistance > 1.0f) {
			mc.player.connection.sendPacket(new CPacketPlayer(true));
		}
	}
}