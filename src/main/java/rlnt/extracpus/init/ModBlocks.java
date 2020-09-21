package rlnt.extracpus.init;

import appeng.api.definitions.IBlockDefinition;
import appeng.block.crafting.BlockCraftingUnit;
import appeng.block.crafting.ItemCraftingStorage;
import appeng.bootstrap.FeatureFactory;
import rlnt.extracpus.block.BlockCraftingStorage;
import rlnt.extracpus.client.render.CraftingStorageRendering;
import rlnt.extracpus.util.FeatureFactoryHelper;

public final class ModBlocks {

    public static IBlockDefinition CRAFTING_STORAGE_256K;
    public static IBlockDefinition CRAFTING_STORAGE_1024K;
    public static IBlockDefinition CRAFTING_STORAGE_4096K;
    public static IBlockDefinition CRAFTING_STORAGE_16384K;

    public static void initBlocks(FeatureFactory registry) {
        CRAFTING_STORAGE_256K = FeatureFactoryHelper.build(registry, FeatureFactoryHelper.useCustomModel(registry.block("crafting_storage_256k", () -> new BlockCraftingStorage(BlockCraftingUnit.CraftingUnitType.STORAGE_1K)).item(ItemCraftingStorage::new).rendering(new CraftingStorageRendering("crafting_storage_256k", BlockCraftingUnit.CraftingUnitType.STORAGE_1K))));
        CRAFTING_STORAGE_1024K = FeatureFactoryHelper.build(registry, FeatureFactoryHelper.useCustomModel(registry.block("crafting_storage_1024k", () -> new BlockCraftingStorage(BlockCraftingUnit.CraftingUnitType.STORAGE_4K)).item(ItemCraftingStorage::new).rendering(new CraftingStorageRendering("crafting_storage_1024k", BlockCraftingUnit.CraftingUnitType.STORAGE_4K))));
        CRAFTING_STORAGE_4096K = FeatureFactoryHelper.build(registry, FeatureFactoryHelper.useCustomModel(registry.block("crafting_storage_4096k", () -> new BlockCraftingStorage(BlockCraftingUnit.CraftingUnitType.STORAGE_16K)).item(ItemCraftingStorage::new).rendering(new CraftingStorageRendering("crafting_storage_4096k", BlockCraftingUnit.CraftingUnitType.STORAGE_16K))));
        CRAFTING_STORAGE_16384K = FeatureFactoryHelper.build(registry, FeatureFactoryHelper.useCustomModel(registry.block("crafting_storage_16384k", () -> new BlockCraftingStorage(BlockCraftingUnit.CraftingUnitType.STORAGE_64K)).item(ItemCraftingStorage::new).rendering(new CraftingStorageRendering("crafting_storage_16384k", BlockCraftingUnit.CraftingUnitType.STORAGE_64K))));
    }
}
