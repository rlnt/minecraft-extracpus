package rlnt.extracpus.client.render;

import appeng.block.crafting.BlockCraftingUnit;
import appeng.bootstrap.IBlockRendering;
import appeng.bootstrap.IItemRendering;
import appeng.client.render.crafting.CraftingCubeRendering;
import appeng.core.AppEng;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import rlnt.extracpus.Constants;

import java.util.HashMap;
import java.util.Map;

public class CraftingStorageRendering extends CraftingCubeRendering {

    private final String registryName;
    private final BlockCraftingUnit.CraftingUnitType type;

    public CraftingStorageRendering(String registryName, BlockCraftingUnit.CraftingUnitType type) {
        super(registryName, type);

        this.registryName = registryName;
        this.type = type;
    }

    @Override
    public void customize(IBlockRendering blockRendering, IItemRendering itemRendering) {
        super.customize(blockRendering, itemRendering);

        blockRendering.modelCustomizer((loc, model) -> model);

        // resource location
        ResourceLocation baseName = new ResourceLocation(Constants.MOD_ID, this.registryName);
        // standard blockstate model
        ModelResourceLocation defaultModel = new ModelResourceLocation(baseName, "normal");
        // built-in model
        String builtInName = "models/block/" + this.registryName + "/builtin";
        ModelResourceLocation builtInModel = new ModelResourceLocation(new ResourceLocation(AppEng.MOD_ID, builtInName), "normal");

        blockRendering.builtInModel(builtInName, new CraftingStorageModel(this.type));
        blockRendering.stateMapper(block -> this.mapState(block, defaultModel, builtInModel));
    }

    private Map<IBlockState, ModelResourceLocation> mapState(Block block, ModelResourceLocation defaultModel, ModelResourceLocation formedModel) {
        Map<IBlockState, ModelResourceLocation> result = new HashMap<>();
        for (IBlockState state : block.getBlockState().getValidStates()) {
            if (state.getValue(BlockCraftingUnit.FORMED)) {
                // always use the builtin model if the multiblock is formed
                result.put(state, formedModel);
            } else {
                // use the default model
                result.put(state, defaultModel);
            }
        }
        return result;
    }
}
