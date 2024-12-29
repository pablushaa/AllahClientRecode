package ru.pablusha.AllahRecode.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ru.pablusha.AllahRecode.ModuleSys.Module;
import ru.pablusha.AllahRecode.ModuleSys.Type;

public class AutoArmor extends Module {
	private Item blockAir;
	public AutoArmor() {
		super("AutoArmor", 0x00, Type.Combat);
	}
	
	public void onEnable() {
		this.blockAir = Item.getItemFromBlock(Blocks.AIR);
	}
	
    public void onUpdate() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer && !(Minecraft.getMinecraft().currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] bestArmorSlots = new int[4];
        final int[] bestArmorValues = new int[4];
        for (int armorType = 0; armorType < 4; ++armorType) {
            final ItemStack oldArmor = Minecraft.getMinecraft().player.inventory.armorItemInSlot(armorType);
            if (oldArmor != null && oldArmor.getItem() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
            }
            bestArmorSlots[armorType] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack stack = Minecraft.getMinecraft().player.inventory.getStackInSlot(slot);
            if (stack != null && stack.getItem() instanceof ItemArmor) {
                final ItemArmor armor = (ItemArmor)stack.getItem();
                final int armorType2 = this.getArmorType(armor);
                final int armorValue = armor.damageReduceAmount;
                if (armorValue > bestArmorValues[armorType2]) {
                    bestArmorSlots[armorType2] = slot;
                    bestArmorValues[armorType2] = armorValue;
                }
            }
        }
        for (int armorType = 0; armorType < 4; ++armorType) {
            int slot2 = bestArmorSlots[armorType];
            if (slot2 != -1) {
                final ItemStack oldArmor2 = Minecraft.getMinecraft().player.inventory.armorItemInSlot(armorType);
                if (oldArmor2 == null || !this.isEmptySlot(oldArmor2) || Minecraft.getMinecraft().player.inventory.getFirstEmptyStack() != -1) {
                    if (slot2 < 9) {
                        slot2 += 36;
                    }
                    if(mc.player.ticksExisted % 8 == 0) {
    	                mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, Minecraft.getMinecraft().player);
    	                mc.playerController.windowClick(0, slot2, 0, ClickType.QUICK_MOVE, Minecraft.getMinecraft().player);
                    }
                    break;
                }
            }
        }
    }
    
    public int getArmorType(final ItemArmor armor) {
        return armor.armorType.ordinal() - 2;
    }
    
    public boolean isEmptySlot(final ItemStack slot) {
        return slot.getItem() == this.blockAir;
    }
}