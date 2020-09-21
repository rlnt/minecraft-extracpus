package rlnt.extracpus.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rlnt.extracpus.Constants;

public class ModTab extends CreativeTabs {
    public ModTab() {
        super(Constants.MOD_ID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.CRAFTING_STORAGE_16384K.maybeBlock().get()));
    }
}
