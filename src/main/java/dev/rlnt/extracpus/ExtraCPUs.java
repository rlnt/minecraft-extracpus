package dev.rlnt.extracpus;

import static dev.rlnt.extracpus.Constants.MOD_ID;

import appeng.bootstrap.FeatureFactory;
import appeng.bootstrap.IModelRegistry;
import appeng.bootstrap.components.*;
import appeng.core.features.AEFeature;
import dev.rlnt.extracpus.aehacks.ModelLoaderWrapper;
import dev.rlnt.extracpus.block.CraftingStorageTile;
import dev.rlnt.extracpus.setup.ModBlocks;
import dev.rlnt.extracpus.setup.ModTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = MOD_ID, useMetadata = true)
@Mod.EventBusSubscriber
@SuppressWarnings("java:S1118")
public class ExtraCPUs {

    // creative tab
    public static final CreativeTabs MOD_TAB = new ModTab();
    // AE 2 Feature Factory
    public static final FeatureFactory FF = new FeatureFactory().features(AEFeature.CRAFTING_CPU);

    public static final boolean EXISTING_DEPENDENCY =
        Loader.isModLoaded("extracells") || Loader.isModLoaded("aeadditions");

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Logger logger = event.getModLog();
        if (!EXISTING_DEPENDENCY) {
            logger.warn("There is no dependency mod loaded for ExtraCPUs! It will not add any features.");
            return;
        }

        // register the new tile entity for the new blocks
        GameRegistry.registerTileEntity(
            CraftingStorageTile.class,
            new ResourceLocation(Constants.MOD_ID, "TileCraftingStorage")
        );
        // add the new blocks to the feature factory
        ModBlocks.init();
        // run pre init for new components in the feature factory
        FF
            .getBootstrapComponents(IPreInitComponent.class)
            .forEachRemaining(component -> component.preInitialize(event.getSide()));
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        if (!EXISTING_DEPENDENCY) return;

        // run init for new components in the feature factory
        FF
            .getBootstrapComponents(IInitComponent.class)
            .forEachRemaining(component -> component.initialize(event.getSide()));
    }

    // register the blocks with the feature factory
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (!EXISTING_DEPENDENCY) return;

        IForgeRegistry<Block> registry = event.getRegistry();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        FF
            .getBootstrapComponents(IBlockRegistrationComponent.class)
            .forEachRemaining(block -> block.blockRegistration(side, registry));
    }

    // register the block items with the feature factory
    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        if (!EXISTING_DEPENDENCY) return;

        IForgeRegistry<Item> registry = event.getRegistry();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        FF
            .getBootstrapComponents(IItemRegistrationComponent.class)
            .forEachRemaining(blockItem -> blockItem.itemRegistration(side, registry));
    }

    // register the new model with the feature factory
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        if (!EXISTING_DEPENDENCY) return;

        IModelRegistry registry = new ModelLoaderWrapper();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        FF
            .getBootstrapComponents(IModelRegistrationComponent.class)
            .forEachRemaining(model -> model.modelRegistration(side, registry));
    }
}
