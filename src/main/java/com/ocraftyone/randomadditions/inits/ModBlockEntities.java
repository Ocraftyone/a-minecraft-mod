/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Constants;
import com.ocraftyone.randomadditions.blocks.entity.CornShuckerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.MOD_ID);
    
    //REGISTER BLOCK ENTITIES HERE
    public static final RegistryObject<BlockEntityType<CornShuckerBlockEntity>> CORN_SHUCKER_BLOCK_ENTITY = register("corn_shucker_block_entity", () -> BlockEntityType.Builder.of(CornShuckerBlockEntity::new, ModBlocks.AUTO_CORN_SHUCKER.get()).build(null));
    
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, Supplier<? extends BlockEntityType<T>> supplier) {
        return BLOCK_ENTITY_REGISTRY.register(name, supplier);
    }
}
