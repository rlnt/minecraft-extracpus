package dev.rlnt.extracpus.setup;

import static dev.rlnt.extracpus.Constants.*;
import static dev.rlnt.extracpus.ExtraCPUs.FF;

import appeng.api.definitions.IBlockDefinition;
import appeng.block.crafting.BlockCraftingUnit.CraftingUnitType;
import appeng.block.crafting.ItemCraftingStorage;
import dev.rlnt.extracpus.aehacks.FeatureFactoryHelper;
import dev.rlnt.extracpus.aehacks.client.CraftingStorageRendering;
import dev.rlnt.extracpus.block.CraftingStorageBlock;

@SuppressWarnings("unused")
public class ModBlocks {

    public static final IBlockDefinition CRAFTING_STORAGE_256K = registerBlock(
        STORAGE_256K_ID,
        CraftingUnitType.STORAGE_1K
    );
    public static final IBlockDefinition CRAFTING_STORAGE_1024K = registerBlock(
        STORAGE_1024K_ID,
        CraftingUnitType.STORAGE_4K
    );
    public static final IBlockDefinition CRAFTING_STORAGE_4096K = registerBlock(
        STORAGE_4096K_ID,
        CraftingUnitType.STORAGE_16K
    );
    public static final IBlockDefinition CRAFTING_STORAGE_16384K = registerBlock(
        STORAGE_16384K_ID,
        CraftingUnitType.STORAGE_64K
    );

    private ModBlocks() {
        throw new IllegalStateException("Utility class");
    }

    private static IBlockDefinition registerBlock(String id, CraftingUnitType type) {
        return FeatureFactoryHelper.build(
            FF,
            FeatureFactoryHelper.useCustomModel(
                FF
                    .block(id, () -> new CraftingStorageBlock(type))
                    .item(ItemCraftingStorage::new)
                    .rendering(new CraftingStorageRendering(id, type))
            )
        );
    }

    public static void init() {
        // utility function to load the class
    }
}
