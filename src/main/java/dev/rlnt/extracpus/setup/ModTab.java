package dev.rlnt.extracpus.setup;

import static dev.rlnt.extracpus.Constants.MOD_ID;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTab extends CreativeTabs {

    public ModTab() {
        super(MOD_ID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.CRAFTING_STORAGE_16384K.maybeItem().orElseThrow(NullPointerException::new));
    }
}
