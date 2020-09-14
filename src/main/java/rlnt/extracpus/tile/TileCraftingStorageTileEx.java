package rlnt.extracpus.tile;

import appeng.tile.crafting.TileCraftingStorageTile;
import rlnt.extracpus.block.BlockCraftingStorageEx;

public class TileCraftingStorageTileEx extends TileCraftingStorageTile {

    private static final int KILO_SCALAR = 1024;

    @Override
    public int getStorageBytes() {
        if(this.world == null || this.notLoaded() || this.isInvalid()) return 0;

        final BlockCraftingStorageEx unit = (BlockCraftingStorageEx) this.world.getBlockState(this.pos).getBlock();
        switch(unit.type) {
            default:
            case STORAGE_1K:return 256 * KILO_SCALAR;
            case STORAGE_4K: return 1024 * KILO_SCALAR;
            case STORAGE_16K: return 4096 * KILO_SCALAR;
            case STORAGE_64K: return 16384 * KILO_SCALAR;
        }
    }
}
