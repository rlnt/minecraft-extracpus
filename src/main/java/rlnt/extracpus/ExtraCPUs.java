package rlnt.extracpus;

import appeng.bootstrap.FeatureFactory;
import appeng.bootstrap.IModelRegistry;
import appeng.bootstrap.components.*;
import appeng.core.features.AEFeature;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import rlnt.extracpus.client.ModelLoaderWrapper;
import rlnt.extracpus.init.ModBlocks;
import rlnt.extracpus.init.ModTab;
import rlnt.extracpus.tile.TileCraftingStorage;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, useMetadata = true)
@Mod.EventBusSubscriber
public class ExtraCPUs {

    // creative tab
    public static final CreativeTabs MOD_TAB = new ModTab();
    // AE 2 Feature Factory
    public static FeatureFactory ff = new FeatureFactory().features(AEFeature.CRAFTING_CPU);

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        // register the new tile entity for the new blocks
        GameRegistry.registerTileEntity(TileCraftingStorage.class, new ResourceLocation(Constants.MOD_ID, "TileCraftingStorage"));
        // add the new blocks to the feature factory
        ModBlocks.initBlocks(ff);
        // run pre init for new components in the feature factory
        ff.getBootstrapComponents(IPreInitComponent.class).forEachRemaining(component -> component.preInitialize(event.getSide()));
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        // run init for new components in the feature factory
        ff.getBootstrapComponents(IInitComponent.class).forEachRemaining(component -> component.initialize(event.getSide()));
    }

    // register the blocks with the feature factory
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();
        final Side side = FMLCommonHandler.instance().getEffectiveSide();
        ff.getBootstrapComponents(IBlockRegistrationComponent.class).forEachRemaining(block -> block.blockRegistration(side, registry));
    }

    // register the block items with the feature factory
    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        final Side side = FMLCommonHandler.instance().getEffectiveSide();
        ff.getBootstrapComponents(IItemRegistrationComponent.class).forEachRemaining(blockItem -> blockItem.itemRegistration(side, registry));
    }

    // register the new model with the feature factory
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        final IModelRegistry registry = new ModelLoaderWrapper();
        final Side side = FMLCommonHandler.instance().getEffectiveSide();
        ff.getBootstrapComponents(IModelRegistrationComponent.class).forEachRemaining(model -> model.modelRegistration(side, registry));
    }
}
