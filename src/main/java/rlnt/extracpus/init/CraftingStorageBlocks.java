package rlnt.extracpus.init;

import appeng.api.definitions.IBlockDefinition;
import appeng.block.crafting.BlockCraftingUnit.CraftingUnitType;
import appeng.block.crafting.ItemCraftingStorage;
import appeng.bootstrap.FeatureFactory;
import appeng.core.features.AEFeature;
import rlnt.extracpus.block.BlockCraftingStorageEx;
import rlnt.extracpus.client.crafting.CraftingStorageEXRendering;
import rlnt.extracpus.util.FeatureRegisterHelper;

public class CraftingStorageBlocks {

	public static IBlockDefinition craftingStorage256k;
	public static IBlockDefinition craftingStorage1024k;
	public static IBlockDefinition craftingStorage4096k;
	public static IBlockDefinition craftingStorage16384k;

	public static void registerBlocks(FeatureFactory registry) {

		craftingStorage256k = FeatureRegisterHelper.build(registry, FeatureRegisterHelper.useCustomItemModel(registry
						.block("crafting_storage_256k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_1K))
						.item(ItemCraftingStorage::new).rendering(
								new CraftingStorageEXRendering("crafting_storage_256k", CraftingUnitType.STORAGE_1K))));
		craftingStorage1024k = FeatureRegisterHelper.build(registry, FeatureRegisterHelper.useCustomItemModel(registry
				.block("crafting_storage_1024k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_4K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_1024k", CraftingUnitType.STORAGE_4K))));
		craftingStorage4096k = FeatureRegisterHelper.build(registry, FeatureRegisterHelper.useCustomItemModel(registry
				.block("crafting_storage_4096k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_16K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_4096k", CraftingUnitType.STORAGE_16K))));
		craftingStorage16384k = FeatureRegisterHelper.build(registry, FeatureRegisterHelper.useCustomItemModel(registry
				.block("crafting_storage_16384k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_64K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_16384k", CraftingUnitType.STORAGE_64K))));

	}

}
