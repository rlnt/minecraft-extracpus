package rlnt.extracpus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import rlnt.extracpus.creative.CreativeTab;
import rlnt.extracpus.tile.TileCraftingStorageTileEx;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, useMetadata = true)
public class ExtraCPUs {
    // creative tab
    public static final CreativeTabs MOD_TAB = new CreativeTab();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(TileCraftingStorageTileEx.class, new ResourceLocation(Constants.MOD_ID, "TileCraftingStorageTileEx"));
    }
}
