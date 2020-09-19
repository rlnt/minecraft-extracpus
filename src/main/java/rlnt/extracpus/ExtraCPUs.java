package rlnt.extracpus;

import java.util.HashMap;

import appeng.bootstrap.FeatureFactory;
import appeng.bootstrap.IModelRegistry;
import appeng.bootstrap.components.IBlockRegistrationComponent;
import appeng.bootstrap.components.IInitComponent;
import appeng.bootstrap.components.IItemRegistrationComponent;
import appeng.bootstrap.components.IModelRegistrationComponent;
import appeng.bootstrap.components.IPreInitComponent;
import appeng.core.features.AEFeature;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import rlnt.extracpus.client.ModelLoaderWrapper;
import rlnt.extracpus.init.CraftingStorageBlocks;
import rlnt.extracpus.tile.TileCraftingStorageTileEx;

@Mod(modid = ExtraCPUs.MOD_ID, name = ExtraCPUs.MOD_NAME, version = ExtraCPUs.VERSION, useMetadata = true)
@EventBusSubscriber
public class ExtraCPUs {

    public static final String MOD_ID = "extracpus";
    public static final String MOD_NAME = "Extra CPUs";
    public static final String VERSION = "@VERSION@";

	public static FeatureFactory ff = new FeatureFactory().features(AEFeature.CRAFTING_CPU);

	static HashMap<String, IModel> map;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {

		CraftingStorageBlocks.registerBlocks(ff);

		ff.getBootstrapComponents(IPreInitComponent.class).forEachRemaining(c -> c.preInitialize(event.getSide()));

		GameRegistry.registerTileEntity(TileCraftingStorageTileEx.class, new ResourceLocation(ExtraCPUs.MOD_ID, "TileCraftingStorageTileEx"));

	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		ff.getBootstrapComponents(IInitComponent.class).forEachRemaining(b -> b.initialize(event.getSide()));

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void modelRegistryEvent(ModelRegistryEvent event) {
		final IModelRegistry registry = new ModelLoaderWrapper();
		final Side side = FMLCommonHandler.instance().getEffectiveSide();
		ff.getBootstrapComponents(IModelRegistrationComponent.class)
				.forEachRemaining(b -> b.modelRegistration(side, registry));
	}

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		final Side side = FMLCommonHandler.instance().getEffectiveSide();
		ff.getBootstrapComponents(IBlockRegistrationComponent.class)
				.forEachRemaining(b -> b.blockRegistration(side, registry));
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		final Side side = FMLCommonHandler.instance().getEffectiveSide();
		ff.getBootstrapComponents(IItemRegistrationComponent.class)
				.forEachRemaining(b -> b.itemRegistration(side, registry));

	}


}
