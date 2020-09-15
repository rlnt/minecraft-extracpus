package rlnt.extracpus.init;

import appeng.api.definitions.ITileDefinition;
import appeng.block.crafting.BlockCraftingUnit.CraftingUnitType;
import appeng.block.crafting.ItemCraftingStorage;
import appeng.bootstrap.FeatureFactory;
import appeng.core.features.AEFeature;
import rlnt.extracpus.block.BlockCraftingStorageEx;
import rlnt.extracpus.client.crafting.CraftingStorageEXRendering;

public class CraftingStorageBlocks {

	public static ITileDefinition  craftingStorage256k;
	public static ITileDefinition  craftingStorage1024k;
	public static ITileDefinition  craftingStorage4096k;
	public static ITileDefinition  craftingStorage16384k;

	public static void registerBlocks(FeatureFactory registry) {

		FeatureFactory crafting = registry.features(AEFeature.CRAFTING_CPU);

		craftingStorage256k = crafting
				.block("crafting_storage_256k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_1K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_256k", CraftingUnitType.STORAGE_1K))
				.useCustomItemModel().build();
		craftingStorage1024k = crafting
				.block("crafting_storage_1024k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_4K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_1024k", CraftingUnitType.STORAGE_4K))
				.useCustomItemModel().build();
		craftingStorage4096k = crafting
				.block("crafting_storage_4096k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_16K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_4096k", CraftingUnitType.STORAGE_16K))
				.useCustomItemModel().build();
		craftingStorage16384k = crafting
				.block("crafting_storage_16384k", () -> new BlockCraftingStorageEx(CraftingUnitType.STORAGE_64K))
				.item(ItemCraftingStorage::new)
				.rendering(new CraftingStorageEXRendering("crafting_storage_16384k", CraftingUnitType.STORAGE_64K))
				.useCustomItemModel().build();

	}

}
