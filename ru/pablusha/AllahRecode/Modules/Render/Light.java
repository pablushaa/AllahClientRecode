package ru.pablusha.AllahRecode.Modules.Render;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Light extends Module {

    public Light() {
        super("Light", 0x00, Type.Render);
    }

    public void onEnable() {
        mc.gameSettings.gammaSetting = 1000000f;
    }

    public void onDisable() {
        mc.gameSettings.gammaSetting = 0f;
    }
}