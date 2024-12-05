package ru.pablusha.AllahRecode.Modules.Combat;

import java.util.Comparator;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class KillAura extends Module {
    public static float range = 4.8F;
    public static float rand_x = 0, rand_y = 0, rand_z = 0;
    
    public static double fov = 60.0;
    public static int timer = 0;
    
	public KillAura() {
		super("KillAura", 0x00, Type.Combat);
	}
	
	public void onUpdate() {
        EntityPlayer target  = mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.player).min(Comparator.comparing(entityPlayer ->
                entityPlayer.getDistanceToEntity(mc.player))).filter(entityPlayer -> entityPlayer.getDistanceToEntity(mc.player) <= range).orElse(null);

        if (target != null && !target.isCreative() && !target.isSpectator() && mc.currentScreen == null && isEntityInSight(target) &&
    		mc.player.getTeam() != target.getTeam()) {
        	RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
        	
            mc.player.rotationYaw = rotations(target)[0];
            mc.player.rotationPitch = rotations(target)[1];
            
            if (timer == 0) {
            	if (mc.player.getCooledAttackStrength(0) == 1 && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && objectMouseOver != null) {
                	if (Criticals.mod.enabled) {
                		Criticals.mod.doCrit();
                	}
                    mc.clickMouse();
                }
            } else {
            	 if (mc.player.ticksExisted % timer == 0 && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && objectMouseOver != null) {
                 	if (Criticals.mod.enabled) {
                 		Criticals.mod.doCrit();
                 	}
                     mc.clickMouse();
                 }
            }
        }
    }
	
	public float[] rotations(EntityPlayer entity) {
		Random random = new Random();
		Entity player = (Entity) mc.player;
		int te = player.ticksExisted;
		
		if (te % 4 == 0) {
			if (random.nextBoolean()) { rand_y += 0.05; } else { rand_y -= 0.05; }
			if (rand_y >= 0.6) { rand_y -= 0.5; }
			if (rand_y <= -0.6) { rand_y += 0.5; }
			
			if (random.nextBoolean()) { rand_x += 0.02; } else { rand_x -= 0.02; }
			if (rand_x >= 0.3) { rand_x -= 0.1; }
			if (rand_x <= -0.3) { rand_x += 0.1; }
			
			if (random.nextBoolean()) { rand_z += 0.02; } else { rand_z -= 0.02; }
			if (rand_z >= 0.3) { rand_z -= 0.1; }
			if (rand_z <= -0.3) { rand_z += 0.1; }
		}
		
        double x = entity.posX - mc.player.posX + rand_x;
        double y = entity.posY - (mc.player.posY + mc.player.getEyeHeight()) + 1 + rand_y;
        double z = entity.posZ - mc.player.posZ + rand_z;

        double u = MathHelper.sqrt(x * x + z * z);

        float u2 = (float) (MathHelper.atan2(z, x) * (180D / Math.PI) - 90.0F);
        float u3 = (float) (-MathHelper.atan2(y, u) * (180D / Math.PI));

        return new float[]{u2, u3};
    }
	
	public boolean isEntityInSight(EntityPlayer target) {
	    if (target == null || !target.isEntityAlive()) {
	        return false;
	    }

	    Vec3d lookVec = mc.player.getLook(1.0F);
	    Vec3d entityPosition = new Vec3d(target.posX, target.posY + target.getEyeHeight(), target.posZ);
	    Vec3d thisPosition = new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);

	    Vec3d toEntityVec = entityPosition.subtract(thisPosition).normalize();

	    double dotProduct = lookVec.dotProduct(toEntityVec);
	    double angle = Math.acos(dotProduct);

	    double maxAngle = Math.toRadians(KillAura.fov);

	    double distance = thisPosition.distanceTo(entityPosition);
	    return angle < maxAngle && distance < 16.0D;
	}
}