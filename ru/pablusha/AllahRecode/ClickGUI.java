package ru.pablusha.AllahRecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;
import ru.pablusha.AllahRecode.Modules.Render.HUD;
import ru.pablusha.AllahRecode.Utils.MouseUtil;
import ru.pablusha.AllahRecode.Utils.RenderUtil;
import ru.pablusha.AllahRecode.Utils.RoundUtil;
import ru.pablusha.AllahRecode.Utils.Font.FontUtils;

public class ClickGUI extends GuiScreen {
	
	private float x, y, lastX, lastY, width, height;
	private int boxOpacity, modOpacity;
	private boolean dragging, binding;
	
	private Type selCat;
	private Module bindMod;
	
	public ClickGUI() {
		this.width = 450;
		this.height = 200;
		this.x = 20;
		this.y = 20;
		this.selCat = Type.Combat;
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.smoothDrag(mouseX, mouseY);
		
		//RenderUtil.drawRect(x, y, width, height, 0xFF0F0F0F);
		RoundUtil.drawRoundedRect(0, 0, mc.displayWidth, mc.displayHeight, 0, 0x44000000);
		RoundUtil.drawRoundedRect(x, y, width + x, height + y, AllahClient.corner, 0xCF0F0F0F);
		RoundUtil.drawSelectRoundedRect(x, y, 100 + x, 20 + y, AllahClient.corner, 0, 0, 0, 0x9F1F1F1F);
		RoundUtil.drawSelectRoundedRect(x, y + 20, 100 + x, height + y, 0, AllahClient.corner, 0, 0, 0x9F1F1F1F);

		int i = 0;
		for (Type type : Type.values()) {
			if (isSelectedCat(type)) {
	 			RenderUtil.drawRect(x, y + i + 20, 100, 24, HUD.rainbow(AllahClient.rainbowDelay, true));
			}
			else {
				RenderUtil.drawRect(x, y + i + 20, 100, 24, 0);
			}
			FontUtils.normal.drawCenteredString(type.name(), x + 50, y + 24 + i + (float) 10 / 2, -1);
			if (!isSelectedCat(type) && MouseUtil.isMouseOver(mouseX, mouseY, x, y + i + 20, 100, 24)) {
				RenderUtil.drawRect(x, y + i + 20, 100, 24, 0x22ffffff);
			}
			i += 24;
		}
		
		i = 0;
		int count = 0;
		
		FontUtils.normal.drawCenteredString("AllahClient", x + 50, y + 7, 0xf1ffffff);
		
		for (Module m : AllahClient.loader.getModulesInType(selCat)) {
			if(count % 2 == 0) {
				//RenderUtil.drawRect(x + 106, y + i + 6, (width - 112) / 2 - 3, 20, 0xFF1F1F1F);
				RoundUtil.drawRoundedRect(x + 106, y + i + 6, (width - 112) / 2 + x + 103, y + i + 26, AllahClient.corner / 2, 0xBF1F1F1F);
				FontUtils.normal.drawString(m.name, x + 112, y + i + 12, m.enabled ? HUD.rainbow(AllahClient.rainbowDelay * count, false) : 0xf1ffffff);
				FontUtils.normal.drawString(Keyboard.getKeyName(m.bind), x + 112 + 120, y + i + 12, m == bindMod ? 0xff888888 : 0xf1ffffff);
				if (m.enabled) {
					RoundUtil.drawRoundedOutline(x + 106, y + i + 6, (width - 112) / 2 + x + 103, y + i + 26, AllahClient.corner / 2, 1, HUD.rainbow(AllahClient.rainbowDelay * count, true));
				} else if (MouseUtil.isMouseOver(mouseX, mouseY, x + 106, y + i + 6, (width - 112) / 2 - 3, 20)) {
					RoundUtil.drawRoundedOutline(x + 106, y + i + 6, (width - 112) / 2 + x + 103, y + i + 26, AllahClient.corner / 2, 1, 0x88ffffff);
				}
			} 
			if(count % 2 == 1) {
				RoundUtil.drawRoundedRect(x + 106 + (width - 112) / 2, y + i + 6, width - 9 + x, 20 + y + i + 6, AllahClient.corner / 2, 0xBF1F1F1F);
				FontUtils.normal.drawString(m.name, x + 112 + (width - 112) / 2, y + i + 12, m.enabled ? HUD.rainbow(AllahClient.rainbowDelay * count, false) : 0xf1ffffff);
				FontUtils.normal.drawString(Keyboard.getKeyName(m.bind), x + 112 + (width - 112) / 2 + 120, y + i + 12, m == bindMod ? 0xff888888 : 0xf1ffffff);
				if (m.enabled) {
					RoundUtil.drawRoundedOutline(x + 106 + (width - 112) / 2, y + i + 6, width - 9 + x, 20 + y + i + 6, AllahClient.corner / 2, 1, HUD.rainbow(AllahClient.rainbowDelay * count, true));
				} else if (MouseUtil.isMouseOver(mouseX, mouseY, x + 106 + (width - 112) / 2, y + i + 6, (width - 112) / 2 - 3, 20)) {
					RoundUtil.drawRoundedOutline(x + 106 + (width - 112) / 2, y + i + 6, width - 9 + x, 20 + y + i + 6, AllahClient.corner / 2, 1, 0xff888888);
				}
				i += 25;
			}
			count++;
		}
		RoundUtil.drawRoundedOutline(x, y, width + x, height + y, AllahClient.corner, 2, HUD.rainbow(AllahClient.rainbowDelay, true));
	}
	
	@Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		int i = 0;
		
		for(Type type : Type.values()) {
			if(MouseUtil.isMouseOver(mouseX, mouseY, x, y + 20 + i, 100, 24)) {
				this.selCat = type;
			}
			i += 24;
		}
		
		if(mouseButton == 0) {
			i = 0;
			int count = 0;
			AllahClient.fileMgr.saveMods();
			for (Module m : AllahClient.loader.getModulesInType(selCat)) {
				if(MouseUtil.isMouseOver(mouseX, mouseY, x + 106, y + i + 6, (width - 112) / 2 - 3, 20) && count % 2 == 0) {
					m.toggle();
					mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1f));
					return;
				} else if (MouseUtil.isMouseOver(mouseX, mouseY, x + 106 + (width - 112) / 2, y + i + 6, (width - 112) / 2 - 3, 20) && count % 2 == 1) {
					m.toggle();
					mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1f));
					return;
				}
				if(count % 2 == 1) {
					i += 25;
				}
				count++;
			}
		} else if (mouseButton == 1) {
			i = 0;
			int count = 0;
			for (Module m : AllahClient.loader.getModulesInType(selCat)) {
				if(MouseUtil.isMouseOver(mouseX, mouseY, x + 106, y + i + 6, (width - 112) / 2 - 3, 20) && count % 2 == 0) {
					this.binding = !this.binding;
					bindMod = m;
					return;
				} else if(MouseUtil.isMouseOver(mouseX, mouseY, x + 106 + (width - 112) / 2, y + i + 6, (width - 112) / 2 - 3, 20) && count % 2 == 1) {
					this.binding = !this.binding;
					bindMod = m;
					return;
				}
				if(count % 2 == 1) {
					i += 25;
				}
				count++;
			}
		}
		
		if(isOverTop(mouseX, mouseY)) {
			this.dragging = true;
			this.lastX = mouseX - x;
			this.lastY = mouseY - y;
		}
	}
	
	
	@Override
    public void mouseReleased(int mouseX, int mouseY, int state)  {
		super.mouseReleased(mouseX, mouseY, state);
		
		this.dragging = false;
	}
	
	@Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(this.binding) {
			if (keyCode != Keyboard.KEY_DELETE) {
				bindMod.bind = keyCode;
				this.binding = false;
				bindMod = null;
			} else {
				bindMod.bind = 0;
				this.binding = false;
				bindMod = null;
			}
		}
	}
	
	private void smoothDrag(int mouseX, int mouseY) {
		if(dragging) {
			this.x = mouseX - lastX;
			this.y = mouseY - lastY;
		}
	}
	
	private boolean isOverTop(int mouseX, int mouseY) {
		return MouseUtil.isMouseOver(mouseX, mouseY, x, y, width, 20);
	}
	
	private boolean isSelectedCat(Type type) {
		return selCat.equals(type);
	}
}