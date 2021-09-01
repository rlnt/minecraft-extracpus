package dev.rlnt.extracpus.aehacks;

import appeng.api.definitions.IBlockDefinition;
import appeng.block.AEBaseTileBlock;
import appeng.bootstrap.*;
import appeng.bootstrap.components.IBlockRegistrationComponent;
import appeng.bootstrap.components.IItemRegistrationComponent;
import appeng.bootstrap.components.IPreInitComponent;
import appeng.bootstrap.definitions.TileEntityDefinition;
import appeng.core.AEConfig;
import appeng.core.features.*;
import appeng.tile.AEBaseTile;
import appeng.util.Platform;
import dev.rlnt.extracpus.Constants;
import dev.rlnt.extracpus.ExtraCPUs;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings(
    { "unchecked", "ConstantConditions", "java:S1905", "java:S2637", "java:S2259", "java:S4449", "java:S3011" }
)
public class FeatureFactoryHelper {

    private FeatureFactoryHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static IBlockDefinition build(FeatureFactory factory, IBlockBuilder builder) {
        String registryName = getPrivateFieldValue("registryName", String.class, builder);
        TileEntityDefinition tileEntity = getPrivateFieldValue(
            "tileEntityDefinition",
            TileEntityDefinition.class,
            builder
        );
        List<BiFunction<Block, Item, IBootstrapComponent>> bootstrapComponents = getPrivateFieldValue(
            "bootstrapComponents",
            List.class,
            builder
        );

        if (
            !AEConfig
                .instance()
                .areFeaturesEnabled((EnumSet<AEFeature>) getPrivateFieldValue("features", EnumSet.class, builder))
        ) {
            return new TileDefinition(registryName, null, null);
        }

        // process block
        Block block = (Block) getPrivateFieldValue("blockSupplier", Supplier.class, builder).get();
        block.setRegistryName(Constants.MOD_ID, registryName);
        block.setTranslationKey(Constants.MOD_ID + "." + registryName);
        factory.addBootstrapComponent((IBlockRegistrationComponent) (side, registry) -> registry.register(block));

        // process block item
        ItemBlock blockItem = constructItemFromBlock(block, builder);
        if (blockItem != null) {
            blockItem.setRegistryName(Constants.MOD_ID, registryName);
            blockItem.setTranslationKey(Constants.MOD_ID + "." + registryName);
            factory.addBootstrapComponent(
                (IItemRegistrationComponent) (side, registry) -> registry.register(blockItem)
            );
        }

        // add to creative tab
        block.setCreativeTab(ExtraCPUs.MOD_TAB);

        // process the rest of the handlers
        if (bootstrapComponents != null) {
            bootstrapComponents.forEach(component -> factory.addBootstrapComponent(component.apply(block, blockItem)));
        }

        // process tile entity
        if (tileEntity != null && block instanceof AEBaseTileBlock) {
            ((AEBaseTileBlock) block).setTileEntity(tileEntity.getTileEntityClass());

            if (tileEntity.getName() == null) {
                tileEntity.setName(registryName);
            }
        }

        // process rendering
        if (Platform.isClient()) {
            Object blockRendering = getPrivateFieldValue("blockRendering", IBlockRendering.class, builder);

            if (block instanceof AEBaseTileBlock) {
                AEBaseTileBlock tile = (AEBaseTileBlock) block;
                invokeApply(blockRendering, factory, block, tile.getTileEntityClass());
            } else {
                invokeApply(blockRendering, factory, block, null);
            }

            if (blockItem != null) {
                Object itemBlockRendering = getPrivateFieldValue("itemRendering", IItemRendering.class, builder);
                invokeApply(itemBlockRendering, factory, blockItem);
            }
        }

        // return the block definition
        if (block instanceof AEBaseTileBlock) {
            factory.addBootstrapComponent(
                (IPreInitComponent) side ->
                    AEBaseTile.registerTileItem(
                        tileEntity == null
                            ? ((AEBaseTileBlock) block).getTileEntityClass()
                            : tileEntity.getTileEntityClass(),
                        new BlockStackSrc(block, 0, ActivityState.Enabled)
                    )
            );

            if (tileEntity != null) {
                factory.tileEntityComponent.addTileEntity(tileEntity);
            }

            return new TileDefinition(registryName, (AEBaseTileBlock) block, blockItem);
        } else {
            return new BlockDefinition(registryName, block, blockItem);
        }
    }

    public static IBlockBuilder useCustomModel(IBlockBuilder builder) {
        builder.rendering(
            new BlockRenderingCustomizer() {
                @SideOnly(Side.CLIENT)
                @Override
                public void customize(IBlockRendering rendering, IItemRendering itemRendering) {
                    ModelResourceLocation model = new ModelResourceLocation(
                        new ResourceLocation(
                            Constants.MOD_ID,
                            getPrivateFieldValue("registryName", String.class, builder)
                        ),
                        "inventory"
                    );
                    itemRendering.model(model).variants(model);
                }
            }
        );
        return builder;
    }

    @SuppressWarnings("unused")
    @Nullable
    private static <T> T getPrivateFieldValue(String field, Class<T> clazz, Object instance) {
        try {
            Field f = instance.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return (T) f.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static ItemBlock constructItemFromBlock(Block block, IBlockBuilder builder) {
        try {
            Method m = builder.getClass().getDeclaredMethod("constructItemFromBlock", Block.class);
            m.setAccessible(true);
            return (ItemBlock) m.invoke(builder, block);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static void invokeApply(Object instance, Object... objects) {
        try {
            for (Method m : instance.getClass().getDeclaredMethods()) {
                if (m.getName().equals("apply")) {
                    m.setAccessible(true);
                    m.invoke(instance, objects);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
