package ru.pablusha.AllahRecode.Utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class RoundUtil {
	final static Minecraft mc = Minecraft.getMinecraft();
	final static FontRenderer fr = mc.fontRendererObj;

	public static void enableGL2D() {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
    }

    public static void disableGL2D() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
    }
    /* 
     * 
     * NORMAL
     * 
     */
    
    /**
     * @param x : X pos
     * @param y : Y pos
     * @param x1 : X2 pos
     * @param y1 : Y2 pos
     * @param radius : round of edges;
     * @param color : color;
     */

    public static void drawSmoothRoundedRect(float x, float y, float x1, float y1, float radius, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        int i;
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glPopAttrib();
        GL11.glLineWidth(1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
    public static void drawRoundedRect(float x, float y, float x1, float y1, float radius, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        int i;
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glPopAttrib();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
    /**
     * @param x : X pos
     * @param y : Y pos
     * @param x1 : X2 pos
     * @param y1 : Y2 pos
     * @param radius : round of edges;
     * @param lineWidth : width of outline line;
     * @param color : color;
     */
    
    public static void drawRoundedOutline(float x, float y, float x1, float y1, float radius,float lineWidth, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        int i;
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glPopAttrib();
        GL11.glLineWidth(1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
    
    /*
     * 
     * SELECTED EDGES
     * 
     */
    
    /**
     * @param x : X pos
     * @param y : Y pos
     * @param x1 : X2 pos
     * @param y1 : Y2 pos
     * @param radius1 : round of left top edges;
     * @param radius2 : round of right top edges;
     * @param radius3 : round of left bottom edges;
     * @param radius4 : round of right bottom edges;
     * @param color : color;
     */
    
    public static void drawSelectRoundedRect(float x, float y, float x1, float y1, float radius1,float radius2,float radius3,float radius4, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(9);
        int i;
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius1 + Math.sin(i * Math.PI / 180.0D) * radius1 * -1.0D, y + radius1 + Math.cos(i * Math.PI / 180.0D) * radius1 * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius2 + Math.sin(i * Math.PI / 180.0D) * radius2 * -1.0D, y1 - radius2 + Math.cos(i * Math.PI / 180.0D) * radius2 * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius3 + Math.sin(i * Math.PI / 180.0D) * radius3, y1 - radius3 + Math.cos(i * Math.PI / 180.0D) * radius3); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius4 + Math.sin(i * Math.PI / 180.0D) * radius4, y + radius4 + Math.cos(i * Math.PI / 180.0D) * radius4); 
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glPopAttrib();
        GL11.glLineWidth(1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }

    /**
     * @param x : X pos
     * @param y : Y pos
     * @param x1 : X2 pos
     * @param y1 : Y2 pos
     * @param radius1 : round of left top edges;
     * @param radius2 : round of right top edges;
     * @param radius3 : round of left bottom edges;
     * @param radius4 : round of right bottom edges;
     * @param lineWidth : width of outline line;
     * @param color : color;
     */
    
    public static void drawSelectRoundedOutline(float x, float y, float x1, float y1, float radius1,float radius2,float radius3,float radius4,float lineWidth, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        int i;
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x + radius1 + Math.sin(i * Math.PI / 180.0D) * radius1 * -1.0D, y + radius1 + Math.cos(i * Math.PI / 180.0D) * radius1 * -1.0D); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x + radius2 + Math.sin(i * Math.PI / 180.0D) * radius2 * -1.0D, y1 - radius2 + Math.cos(i * Math.PI / 180.0D) * radius2 * -1.0D); 
        for (i = 0; i <= 90; i += 3)
          GL11.glVertex2d(x1 - radius3 + Math.sin(i * Math.PI / 180.0D) * radius3, y1 - radius3 + Math.cos(i * Math.PI / 180.0D) * radius3); 
        for (i = 90; i <= 180; i += 3)
          GL11.glVertex2d(x1 - radius4 + Math.sin(i * Math.PI / 180.0D) * radius4, y + radius4 + Math.cos(i * Math.PI / 180.0D) * radius4); 
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glPopAttrib();
        GL11.glLineWidth(1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
      public static void setColor(int color) {
        float a = (color >> 24 & 0xFF) / 255.0F;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;
        GL11.glColor4f(r, g, b, a);
      }
      
      /*
       * 
       * GRADIENT 
       * 
       */
      
      /**
       * @param x : X pos
       * @param y : Y pos
       * @param x1 : X2 pos
       * @param y1 : Y2 pos
       * @param radius : round of edges;
       * @param color : color;
       * @param color2 : color2;
       * @param color3 : color3;
       * @param color4 : color4;
       */
      public static void drawRoundedGradientRectCorner(float x, float y, float x1, float y1, float radius, int color, int color2, int color3, int color4) {
    	  setColor(-1);
          GL11.glEnable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_TEXTURE_2D);
          GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          GL11.glEnable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_SMOOTH);
          
          GL11.glPushAttrib(0);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          x *= 2.0D;
          y *= 2.0D;
          x1 *= 2.0D;
          y1 *= 2.0D;
          GL11.glEnable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_TEXTURE_2D);
          setColor(color);
          GL11.glEnable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_SMOOTH);
          GL11.glBegin(9);
          int i;
          for (i = 0; i <= 90; i += 3)
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
          setColor(color2);
          for (i = 90; i <= 180; i += 3)
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
          setColor(color3);
          for (i = 0; i <= 90; i += 3)
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
          setColor(color4);
          for (i = 90; i <= 180; i += 3)
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
          GL11.glEnd();
          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_LINE_SMOOTH);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glScaled(2.0D, 2.0D, 2.0D);
          GL11.glPopAttrib();
          

          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_FLAT);
          setColor(-1);
        }
      

      /**
       * @param x : X pos
       * @param y : Y pos
       * @param x1 : X2 pos
       * @param y1 : Y2 pos
       * @param width : width of line;
       * @param radius : round of edges;
       * @param color : color;
       * @param color2 : color2;
       * @param color3 : color3;
       * @param color4 : color4;
       */
      public static void drawRoundedGradientOutlineCorner(float x, float y, float x1, float y1, float width, float radius, int color, int color2, int color3, int color4) {
    	  setColor(-1);
          GL11.glEnable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_TEXTURE_2D);
          GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          GL11.glEnable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_SMOOTH);
          
          GL11.glPushAttrib(0);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          x *= 2.0D;
          y *= 2.0D;
          x1 *= 2.0D;
          y1 *= 2.0D;
          GL11.glEnable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_TEXTURE_2D);
          setColor(color);
          GL11.glEnable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_SMOOTH);
          GL11.glLineWidth(width);
          GL11.glBegin(GL11.GL_LINE_LOOP);
          int i;
          for (i = 0; i <= 90; i += 3)
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
          setColor(color2);
          for (i = 90; i <= 180; i += 3)
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
          setColor(color3);
          for (i = 0; i <= 90; i += 3)
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
          setColor(color4);
          for (i = 90; i <= 180; i += 3)
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
          GL11.glEnd();
          GL11.glLineWidth(1);
          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_LINE_SMOOTH);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glScaled(2.0D, 2.0D, 2.0D);
          GL11.glPopAttrib();
          

          GL11.glEnable(GL11.GL_TEXTURE_2D);
          GL11.glDisable(GL11.GL_BLEND);
          GL11.glDisable(GL11.GL_LINE_SMOOTH);
          GL11.glShadeModel(GL11.GL_FLAT);
          setColor(-1);
        }

    public static void drawImage(ResourceLocation image, float x, float y, float width, float height, float alpha) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1f, 1f, 1f, alpha);
        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glPopMatrix();

        GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    public static void drawImage(ResourceLocation image, int x, int y, float width, float height, float alpha) {
        GL11.glPushMatrix();
        GL11.glDisable((int) 2929);
        GL11.glEnable((int) 3042);
        GL11.glDepthMask((boolean) false);
        OpenGlHelper.glBlendFunc((int) 770, (int) 771, (int) 1, (int) 0);
        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, alpha);
        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, (float) 0.0f, (float) 0.0f, (int) width, (int) height,
                (float) width, (float) height);
        GL11.glDepthMask((boolean) true);
        GL11.glDisable((int) 3042);
        GL11.glEnable((int) 2929);
        GL11.glPopMatrix();

        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, 1f);
    }

    public static void drawImage(ResourceLocation image, float x, float y, float width, float height) {
        GL11.glDisable((int) 2929);
        GL11.glEnable((int) 3042);
        GL11.glDepthMask((boolean) false);
        OpenGlHelper.glBlendFunc((int) 770, (int) 771, (int) 1, (int) 0);
        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, 1f);
        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, (float) 0.0f, (float) 0.0f, (int) width, (int) height, (float) width, (float) height);
        GL11.glDepthMask((boolean) true);
        GL11.glDisable((int) 3042);
        GL11.glEnable((int) 2929);
    }
}
