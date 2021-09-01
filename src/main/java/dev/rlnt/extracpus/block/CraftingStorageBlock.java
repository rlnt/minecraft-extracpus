package dev.rlnt.extracpus.block;

import appeng.block.crafting.BlockCraftingUnit;

public class CraftingStorageBlock extends BlockCraftingUnit {

    public CraftingStorageBlock(CraftingUnitType type) {
        super(type);
        this.setTileEntity(CraftingStorageTile.class);
    }
}
