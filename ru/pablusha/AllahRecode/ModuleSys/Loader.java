package ru.pablusha.AllahRecode.ModuleSys;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import ru.pablusha.AllahRecode.AllahClient;
import ru.pablusha.AllahRecode.Modules.Combat.*;
import ru.pablusha.AllahRecode.Modules.Misc.*;
import ru.pablusha.AllahRecode.Modules.Movement.*;
import ru.pablusha.AllahRecode.Modules.Player.*;
import ru.pablusha.AllahRecode.Modules.Render.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Loader {
	public static ArrayList<Module> modules = new ArrayList<>();
	
	public void loadModules() {
		Minecraft mc = Minecraft.getMinecraft();
		
		addModule(new AutoArmor());
		addModule(new AutoTotem());
		addModule(new Criticals());
		addModule(new CrystalAura());
		addModule(new KillAura());
		addModule(new TriggerBot());
		addModule(new Velocity());
		
		addModule(new AirJump());
		addModule(new Fly());
		addModule(new InventoryMove());
		addModule(new Lift());
		addModule(new NoSlowdown());
		addModule(new Speed());
		
		addModule(new AntiOverlay());
		addModule(new ClickGui());
		addModule(new HUD());
		addModule(new Light());
		addModule(new NoPotionRender());
		addModule(new NoScoreBoard());
		addModule(new NoWeather());
		addModule(new PlayerESP());
		addModule(new StorageESP());
		addModule(new TargetHUD());
		
		addModule(new FastPlace());
		addModule(new NoFall());
		addModule(new Portals()); 
		
		addModule(new CustomButtons());
		addModule(new CustomMenu());
		addModule(new Panic());
	}
	
	public void addModule(Module m) {
		this.modules.add(m);
	}
	
	public static ArrayList<Module> getModulesInType(Type t) {
        ArrayList<Module> mods = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.type.name().equalsIgnoreCase(t.name())) {
                mods.add(m);
            }
        }
        return mods;
    }
	
	public static Module getModByName(String name) {
		for(Module m : AllahClient.loader.modules) {
			if(!m.name.trim().equalsIgnoreCase(name) && !m.toString().equalsIgnoreCase(name.trim())) continue;
			return m;
		}
		return null;
	}
	
	public void sortModulesByWidth() {
        Collections.sort(modules, new Comparator<Module>() {
            @Override
            public int compare(Module m1, Module m2) {
                return Integer.compare(-m1.getWidth(), -m2.getWidth());
            }
        });
    }
}
