package rlnt.extracpus.block;

import appeng.block.crafting.BlockCraftingUnit;
import rlnt.extracpus.tile.TileCraftingStorage;

public class BlockCraftingStorage extends BlockCraftingUnit {
    public BlockCraftingStorage(CraftingUnitType type) {
        super(type);
        this.setTileEntity(TileCraftingStorage.class);
    }
}
