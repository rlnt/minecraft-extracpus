package rlnt.extracpus.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rlnt.extracpus.Constants;
import rlnt.extracpus.init.ModBlocks;

public class CreativeTab extends CreativeTabs {
    public CreativeTab() {
        super(Constants.MOD_ID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.CRAFTING_STORAGE_16384k);
    }
}
