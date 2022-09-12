package com.ocraftyone.randomadditions.blocks.entity;

import com.ocraftyone.randomadditions.inits.ModBlockEntities;
import com.ocraftyone.randomadditions.inits.ModBlocks;
import com.ocraftyone.randomadditions.util.ModItemStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CornShuckerBlockEntity extends BlockEntity implements MenuProvider {
    
    private final ModItemStackHandler itemStackHandler = new ModItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    
    public CornShuckerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CORN_SHUCKER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
    
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CornShuckerBlockEntity pBlockEntity) {
    
    }
    
    @Override
    public Component getDisplayName() {
        return ModBlocks.AUTO_CORN_SHUCKER.get().getName();
    }
    
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
    
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }
    
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        super.saveAdditional(pTag);
    }
    
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }
    
    public void dropContents() {
        Containers.dropContents(this.level, this.worldPosition, itemStackHandler.getAsList());
    }
}