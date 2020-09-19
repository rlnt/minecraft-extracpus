package rlnt.extracpus.client.crafting;

import java.util.HashMap;
import java.util.Map;

import appeng.block.crafting.BlockCraftingUnit;
import appeng.block.crafting.BlockCraftingUnit.CraftingUnitType;
import appeng.bootstrap.IBlockRendering;
import appeng.bootstrap.IItemRendering;
import appeng.client.render.crafting.CraftingCubeRendering;
import appeng.client.render.crafting.CraftingMonitorTESR;
import appeng.core.AppEng;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import rlnt.extracpus.ExtraCPUs;

public class CraftingStorageEXRendering extends CraftingCubeRendering {

	private final String registryName;

	private final BlockCraftingUnit.CraftingUnitType type;

	public CraftingStorageEXRendering(String registryName, CraftingUnitType type) {
		super(registryName, type);

		this.registryName = registryName;
		this.type = type;
	}

	@Override
	public void customize(IBlockRendering rendering, IItemRendering itemRendering) {

		super.customize(rendering, itemRendering);

		ResourceLocation baseName = new ResourceLocation( ExtraCPUs.MOD_ID, this.registryName );

			rendering.modelCustomizer( ( loc, model ) -> model );

		// This is the standard blockstate model
		ModelResourceLocation defaultModel = new ModelResourceLocation( baseName, "normal" );

		// This is the built-in model
		String builtInName = "models/block/" + this.registryName + "/builtin";
		ModelResourceLocation builtInModelName = new ModelResourceLocation( new ResourceLocation( AppEng.MOD_ID, builtInName ), "normal" );

		rendering.builtInModel( builtInName, new CraftingStorageEXModel( this.type ) );

		rendering.stateMapper( block -> this.mapState( block, defaultModel, builtInModelName ) );

	}

	private Map<IBlockState, ModelResourceLocation> mapState(Block block, ModelResourceLocation defaultModel, ModelResourceLocation formedModel) {
		Map<IBlockState, ModelResourceLocation> result = new HashMap<>();
		for(IBlockState state : block.getBlockState().getValidStates()) {
			if(state.getValue(BlockCraftingUnit.FORMED)) {
				// Always use the builtin model if the multiblock is formed
				result.put(state, formedModel);
			} else {
				// Use the default model
				result.put(state, defaultModel);
			}
		}
		return result;
	}

}
