package ru.pablusha.AllahRecode.Modules.Render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;
import ru.pablusha.AllahRecode.Utils.RenderUtil;

public class StorageESP extends Module {
	public StorageESP() {
		super("StorageESP", 0x00, Type.Render);
	}
	
	public void onRender3D() {
		for(TileEntity entity : mc.world.loadedTileEntityList) {
			if (entity instanceof TileEntityChest || entity instanceof TileEntityEnderChest || entity instanceof TileEntityShulkerBox) {
				RenderUtil.blockESP(entity.getPos());
			}
		}
	}

}