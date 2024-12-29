package ru.pablusha.AllahRecode;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import de.florianmichael.viamcp.ViaMCP;

public class AllahMenu extends GuiScreen {
    public AllahMenu() {
        super();
    }

    @Override
    public void initGui() {
        int i = this.height / 4 + 48;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, i + 72 + 12, 98, 20, "Options"));
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    	if (keyCode == Keyboard.KEY_ESCAPE) {
    		return;
    	}
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("background.jpg"));
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, this.width, this.height);

        this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);

        String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Username", "Author", "Quit"};

        int count = 0;
        for (String name : buttons) {
            float x = (width/buttons.length) * count + (width/buttons.length) / 2 + 8 - mc.fontRendererObj.getStringWidth(name)/2;

            float y = height - 20;

            boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT;
            this.drawCenteredString(mc.fontRendererObj, name, (width/buttons.length) * count + (width/buttons.length) / 2 + 8, height - 20, hovered ? 0xf3b48b : -1);
            count++;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2f, height/2f, 0);
        GlStateManager.scale(3.5, 3.5, 1);
        GlStateManager.translate(-(width/2f), -(height/2f), 0);
        this.drawCenteredString(mc.fontRendererObj, "AllahClient", width/2, height/2 - mc.fontRendererObj.FONT_HEIGHT/2, -1);
        GlStateManager.popMatrix();
        this.drawCenteredString(mc.fontRendererObj, AllahClient.version, width/2, height/2 - mc.fontRendererObj.FONT_HEIGHT/2 + 20, -1);
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Username", "Author", "Quit"};

        int count = 0;
        for (String name : buttons) {
            float x = (width/buttons.length) * count + (width/buttons.length) / 2 + 8 - mc.fontRendererObj.getStringWidth(name)/2;
            float y = height - 20;

            if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
                switch (name) {
                    case "Singleplayer":
                        mc.displayGuiScreen(new GuiWorldSelection(this));
                        break;
                    case "Author":
                        Desktop desktop = Desktop.getDesktop();
                        if (desktop.isSupported(Desktop.Action.OPEN)) {
                            try {
                                desktop.browse(java.net.URI.create("https://github.com/pablushaa"));
                                break;
                            } catch (IOException ex) {
                                System.out.println("sss");
                            }
                        }
                        break;
                    case "Multiplayer":
                        mc.displayGuiScreen(new GuiMultiplayer(this));
                        break;
                    case "Settings":
                        mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                        break;
                    case "Username":
                    	mc.displayGuiScreen(new UserMenu());
                    	break;
                    case "Quit":
                        mc.shutdown();
                }
            }

            count++;
        }
    }
}