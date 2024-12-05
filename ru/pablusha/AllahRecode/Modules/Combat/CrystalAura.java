package ru.pablusha.AllahRecode.Modules.Combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumHand;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class CrystalAura extends Module {

	public CrystalAura() {
		super("CrystalAura", 0x00, Type.Combat);
	}
	
    public void onUpdate() {
        for(Entity ent: mc.world.loadedEntityList) {
            if(ent instanceof EntityEnderCrystal) {
                if (mc.player.getDistanceToEntity(ent) <= 5) {
                    mc.playerController.attackEntity(mc.player, ent);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
}