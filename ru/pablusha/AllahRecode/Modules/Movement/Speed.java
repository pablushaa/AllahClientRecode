package ru.pablusha.AllahRecode.Modules.Movement;

import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class Speed extends Module {
    public Speed() {
        super("Speed", 0x00, Type.Movement);
    }

    public void onUpdate() {
        if(this.enabled) {
            if(mc.player.onGround) {
                mc.player.motionX *= 1.65;
                mc.player.motionZ *= 1.65;
            }
        }
    }
}