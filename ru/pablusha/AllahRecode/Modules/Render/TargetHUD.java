package ru.pablusha.AllahRecode.Modules.Render;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;
import ru.pablusha.AllahRecode.Utils.RenderUtil;
import ru.pablusha.AllahRecode.Utils.RoundUtil;
import ru.pablusha.AllahRecode.Utils.Font.FontUtils;

public class TargetHUD extends Module {
	public static int range = 10;

	public TargetHUD() {
		super("TargetHUD", 0x00, Type.Render);
	}
	
	public void onRender2D() {
		RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
		
		try {
        	EntityPlayer target  = mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.player).min(Comparator.comparing(entityPlayer ->
            	entityPlayer.getDistanceToEntity(mc.player))).filter(entityPlayer -> entityPlayer.getDistanceToEntity(mc.player) <= range).orElse(null);
        	
        	final float health = Math.round(target.getHealth());
        	if (target != null) {
        		double dist = mc.player.getDistanceToEntity((Entity) target);
        		BigDecimal bd = new BigDecimal(dist);
        		bd = bd.setScale(2, RoundingMode.HALF_UP);
        		dist = bd.doubleValue();
        		
        		RoundUtil.drawRoundedRect(mc.displayWidth / 4f - 100, mc.displayHeight / 2f - 110, mc.displayWidth / 4f + 100, mc.displayHeight / 2f - 60, AllahClient.corner, 0xCF0F0F0F);
        		RoundUtil.drawRoundedOutline(mc.displayWidth / 4f - 100, mc.displayHeight / 2f - 110, mc.displayWidth / 4f + 100, mc.displayHeight / 2f - 60, AllahClient.corner, 2, 
        				HUD.rainbow(AllahClient.rainbowDelay, true));
        		FontUtils.normal.drawString(target.getName(), mc.displayWidth / 4f - 50, mc.displayHeight / 2f - 100, -1);
        		FontUtils.normal.drawString("HP: " + (int) health + " / " + (int) target.getMaxHealth() + ", Dist: " + dist + "m", 
        				mc.displayWidth / 4f - 50, mc.displayHeight / 2f - 88, -1);
        		
        		RenderUtil.drawRect(mc.displayWidth / 4f - 50, mc.displayHeight / 2f - 75, 120, 5, new Color(255,0,0,255).getRGB());
        		RenderUtil.drawRect(mc.displayWidth / 4f - 50, mc.displayHeight / 2f - 75, 120 / target.getMaxHealth() * health, 5, new Color(127,106,0,255).getRGB());
        		try {
            		drawHead(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(target.getUniqueID()).getLocationSkin(), 10, 10);
        		}  catch (Exception ignored) {}
        	}
		} catch (Exception ignored) {}
	}
	public void drawHead(ResourceLocation skin, int width, int height) {
		GL11.glColor4f(1, 1, 1, 1);
	    mc.getTextureManager().bindTexture(skin);
	    Gui.drawScaledCustomSizeModalRect(mc.displayWidth / 4f - 90, mc.displayHeight / 2f - 100, 8, 8, 8, 8, 30, 30, 64, 64);
	}
}