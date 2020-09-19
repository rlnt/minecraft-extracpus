package rlnt.extracpus.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import appeng.api.definitions.IBlockDefinition;
import appeng.block.AEBaseTileBlock;
import appeng.bootstrap.BlockRenderingCustomizer;
import appeng.bootstrap.FeatureFactory;
import appeng.bootstrap.IBlockBuilder;
import appeng.bootstrap.IBlockRendering;
import appeng.bootstrap.IBootstrapComponent;
import appeng.bootstrap.IItemRendering;
import appeng.bootstrap.components.IBlockRegistrationComponent;
import appeng.bootstrap.components.IItemRegistrationComponent;
import appeng.bootstrap.components.IPreInitComponent;
import appeng.bootstrap.definitions.TileEntityDefinition;
import appeng.core.AEConfig;
import appeng.core.features.AEFeature;
import appeng.core.features.ActivityState;
import appeng.core.features.BlockDefinition;
import appeng.core.features.BlockStackSrc;
import appeng.core.features.TileDefinition;
import appeng.tile.AEBaseTile;
import appeng.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rlnt.extracpus.ExtraCPUs;

public class FeatureRegisterHelper {

	public static IBlockDefinition build(FeatureFactory factory, IBlockBuilder builder) {

		String registryName = getPrivateFieldValue("registryName", String.class, builder);
		TileEntityDefinition tileEntityDefinition = getPrivateFieldValue("tileEntityDefinition", TileEntityDefinition.class, builder);
		List<BiFunction<Block, Item, IBootstrapComponent>> bootstrapComponents = getPrivateFieldValue("bootstrapComponents", List.class, builder);

		if( !AEConfig.instance().areFeaturesEnabled((EnumSet<AEFeature>) getPrivateFieldValue("features", EnumSet.class, builder)) )
			return new TileDefinition(registryName, null, null );

		Block block = (Block) getPrivateFieldValue("blockSupplier", Supplier.class, builder).get();
		block.setRegistryName(ExtraCPUs.MOD_ID, registryName);
		block.setUnlocalizedName(ExtraCPUs.MOD_ID+"."+registryName);
		factory.addBootstrapComponent( (IBlockRegistrationComponent) ( side, registry ) -> registry.register( block ) );

		ItemBlock item = constructItemFromBlock(block, builder);

		if( item != null ) {
			item.setRegistryName( ExtraCPUs.MOD_ID, registryName );
			item.setUnlocalizedName(registryName);

			factory.addBootstrapComponent( (IItemRegistrationComponent) ( side, registry ) -> registry.register( item ) );
		}


		block.setCreativeTab( CreativeTabs.FOOD );

		// Register all extra handlers
		bootstrapComponents.forEach( component -> factory.addBootstrapComponent( component.apply( block, item ) ) );

		if( tileEntityDefinition != null && block instanceof AEBaseTileBlock ){
			( (AEBaseTileBlock) block ).setTileEntity( tileEntityDefinition.getTileEntityClass() );
			if(tileEntityDefinition.getName() == null )
				tileEntityDefinition.setName( registryName );


		}

		if( Platform.isClient() )
		{

			Object blockRendering = getPrivateFieldValue("blockRendering", IBlockRendering.class, builder);

			if( block instanceof AEBaseTileBlock )
			{
				AEBaseTileBlock tileBlock = (AEBaseTileBlock) block;
				invokeApply(blockRendering, factory, block, tileBlock.getTileEntityClass());
			}
			else
			{
				invokeApply(blockRendering, factory, block, null);
			}

			if( item != null ) {
				Object itemRendering = getPrivateFieldValue("itemRendering", IItemRendering.class, builder);
				invokeApply(itemRendering, factory, item);
			}
		}

		if( block instanceof AEBaseTileBlock )
		{
			factory.addBootstrapComponent( (IPreInitComponent) side ->
			{
				AEBaseTile.registerTileItem(
						tileEntityDefinition == null ? ( (AEBaseTileBlock) block ).getTileEntityClass() : tileEntityDefinition.getTileEntityClass(),
						new BlockStackSrc( block, 0, ActivityState.Enabled ) );
			} );

			if( tileEntityDefinition != null )
			{
				factory.tileEntityComponent.addTileEntity( tileEntityDefinition );
			}

			return new TileDefinition( registryName, (AEBaseTileBlock) block, item );
		}
		else
		{
			return  new BlockDefinition( registryName, block, item );
		}


	}

	public static IBlockBuilder useCustomItemModel(IBlockBuilder builder) {
		builder.rendering(new BlockRenderingCustomizer() {

			@Override
			@SideOnly( Side.CLIENT )
			public void customize(IBlockRendering rendering, IItemRendering itemRendering) {
				ModelResourceLocation model = new ModelResourceLocation( new ResourceLocation( ExtraCPUs.MOD_ID, getPrivateFieldValue("registryName", String.class, builder)), "inventory" );
				itemRendering.model( model ).variants( model );
			}
		});

		return builder;
	}


	public static <T> T getPrivateFieldValue(String fieldName, Class<T> clazz, Object instance) {

		try {

		Field f = instance.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		return (T) f.get(instance);
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private static ItemBlock constructItemFromBlock(Block block, IBlockBuilder instance) {

		try {
			Method m = instance.getClass().getDeclaredMethod("constructItemFromBlock", Block.class);
			m.setAccessible(true);
			return (ItemBlock) m.invoke(instance, block);
		}

		catch (Exception e) {
			return null;
		}

	}

	private static void invokeApply(Object instance, Object... objects) {

		try {

			for(Method m:instance.getClass().getDeclaredMethods()) {
				if(m.getName() == "apply"){
					m.setAccessible(true);
					m.invoke(instance, objects);
					break;

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}


	}




}
