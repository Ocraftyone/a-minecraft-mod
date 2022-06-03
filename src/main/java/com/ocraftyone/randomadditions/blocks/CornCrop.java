package com.ocraftyone.randomadditions.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CornCrop extends CropBlock  {
    private final Supplier<? extends ItemLike> seedItem;
    public static final BooleanProperty UPPER = BooleanProperty.create("upper");
    
    public CornCrop(Properties properties, Supplier<? extends ItemLike> seed) {
        super(properties);
        this.seedItem = seed;
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0).setValue(UPPER, false));
    }
    
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return this.seedItem.get();
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, UPPER);
    }
    
    public BooleanProperty getUpperProperty() {
        return UPPER;
    }
    
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos down = pPos.below();
        if (pLevel.getBlockState(down).is(this)) {
            return !pLevel.getBlockState(down).getValue(this.getUpperProperty())
                    && super.canSurvive(pState, pLevel, pPos);
        }
        return super.canSurvive(pState, pLevel, pPos);
    }
}
