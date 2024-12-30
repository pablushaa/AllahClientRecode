package ru.pablusha.AllahRecode.Modules.Render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.ModuleSys.Module;

import ru.pablusha.AllahRecode.ModuleSys.Type;
import ru.pablusha.AllahRecode.Utils.RoundUtil;
import ru.pablusha.AllahRecode.Utils.Font.FontUtils;

public class HUD extends Module {
	public static Module mod;
	public HUD() {
		super("HUD", Keyboard.KEY_NONE, Type.Render);
		mod = this;
	}

	public void onRender2D() {
		int y = 5, box_y = 10, width = 10;
        final int[] counter = {1};
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRendererObj;
        
        for (Module module : AllahClient.loader.modules) { if (module.enabled) box_y += 10; }
        width += AllahClient.loader.modules.get(0).getWidth();

        ScaledResolution sr = new ScaledResolution(mc);
        String lol = AllahClient.clientName + " | " + Minecraft.getDebugFPS() + " FPS"; // гениальный нейминг moment 
        RoundUtil.drawSelectRoundedRect(0, 0, fr.getStringWidth(lol) + 20, 30, 0, 0, AllahClient.corner, 0, 0xCF0F0F0F);
        RoundUtil.drawSelectRoundedOutline(-2f, -2f, fr.getStringWidth(lol) + 20, 30, 0, 0, AllahClient.corner, 0, 2, HUD.rainbow(AllahClient.rainbowDelay, true));
        
        RoundUtil.drawSelectRoundedRect(mc.displayWidth / 2 - width, 0, mc.displayWidth / 2, box_y, 0, AllahClient.corner, 0, 0, 0xCF0F0F0F);
        RoundUtil.drawSelectRoundedOutline(mc.displayWidth / 2 - width, 0, mc.displayWidth, box_y, 0, AllahClient.corner, 0, 0, 2, HUD.rainbow(AllahClient.rainbowDelay, true));
        
        FontUtils.normal.drawStringWithShadow(lol, 5, 5, -1);
        FontUtils.normal.drawStringWithShadow("Salam, §6" + mc.getSession().getUsername(), 5,
                15, 0xffffff);
        
        
        for (Module module : AllahClient.loader.modules) {
            if(module.enabled) {
            	FontUtils.normal.drawStringWithShadow(module.name, sr.getScaledWidth() - 4 -
            			FontUtils.normal.getStringWidth(module.name), y, rainbow(counter[0] * AllahClient.rainbowDelay, false));
                y += 10;
                counter[0]++;
            }
        }
	}
	
	public static int rainbow(int delay, boolean alpha) {
		if (AllahClient.color == -1 || AllahClient.acolor == -1) {
	        double rainbowState = Math.ceil(System.currentTimeMillis() + delay) / 20.0;
	        rainbowState %= 360;
	        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
		} else {
			if (alpha) {
				return AllahClient.acolor;
			} else {
				return AllahClient.color;
			}
		}
    }
}