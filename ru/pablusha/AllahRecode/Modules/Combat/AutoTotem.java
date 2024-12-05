package ru.pablusha.AllahRecode.Modules.Combat;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class AutoTotem extends Module {
    public AutoTotem() {
        super("AutoTotem", 0x00, Type.Combat);
    }
    
    public void onUpdate() {
    	if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {
            Item offhand = mc.player.getHeldItemOffhand().getItem();
            if(getItemCount(mc.player.inventoryContainer, Items.field_190929_cY) > 0 && !offhand.equals(Items.field_190929_cY)) {
                swap(getItemSlot(mc.player.inventoryContainer, Items.field_190929_cY), 45);
            }
        }
    }
    
    int getItemCount(Container container, Item item) {
        int itemCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (container.getSlot(i).getHasStack()) {
                final ItemStack is = container.getSlot(i).getStack();
                if (is.getItem() == item) {
                    itemCount += is.func_190916_E();
                }
            }
        }
        return itemCount;
    }
    
    int getItemSlot(Container container, Item item) {
        int slot = 0;
        for (int i = 9; i < 45; ++i) {
            if (container.getSlot(i).getHasStack()) {
                ItemStack is = container.getSlot(i).getStack();
                if (is.getItem() == item)
                    slot = i;
            }
        }
        return slot;
    }
    
    void swap(int slot, int hotbarNum) {
        mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(mc.player.inventoryContainer.windowId, hotbarNum, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
        mc.playerController.updateController();
    }
}