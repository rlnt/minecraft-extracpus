package dev.rlnt.extracpus.aehacks;

import appeng.bootstrap.IModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModelLoaderWrapper implements IModelRegistry {

    @Override
    public void registerItemVariants(Item item, ResourceLocation... names) {
        ModelBakery.registerItemVariants(item, names);
    }

    @Override
    public void setCustomModelResourceLocation(Item item, int metadata, ModelResourceLocation model) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, model);
    }

    @Override
    public void setCustomMeshDefinition(Item item, ItemMeshDefinition meshDefinition) {
        ModelLoader.setCustomMeshDefinition(item, meshDefinition);
    }

    @Override
    public void setCustomStateMapper(Block block, IStateMapper mapper) {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
}
