package dev.rlnt.extracpus.block;

import appeng.tile.crafting.TileCraftingStorageTile;

public class CraftingStorageTile extends TileCraftingStorageTile {

    @Override
    public int getStorageBytes() {
        if (this.world == null || this.notLoaded() || this.isInvalid()) return 0;

        final CraftingStorageBlock unit = (CraftingStorageBlock) this.world.getBlockState(this.pos).getBlock();
        final int kiloScalar = 1024;
        switch (unit.type) {
            default:
            case STORAGE_1K:
                return 256 * kiloScalar;
            case STORAGE_4K:
                return 1024 * kiloScalar;
            case STORAGE_16K:
                return 4096 * kiloScalar;
            case STORAGE_64K:
                return 16384 * kiloScalar;
        }
    }
}
