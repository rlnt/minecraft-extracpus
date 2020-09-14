package rlnt.extracpus.block;

import appeng.block.crafting.BlockCraftingUnit;
import rlnt.extracpus.tile.TileCraftingStorageTileEx;

public class BlockCraftingStorageEx extends BlockCraftingUnit {
    public BlockCraftingStorageEx(CraftingUnitType type) {
        super(type);
        this.setTileEntity(TileCraftingStorageTileEx.class);
    }
}
