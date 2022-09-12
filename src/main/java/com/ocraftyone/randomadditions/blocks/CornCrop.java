/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.blocks;

import com.ocraftyone.randomadditions.inits.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

@SuppressWarnings("NullableProblems")
public class CornCrop extends CropBlock {
    public static final BooleanProperty UPPER = BooleanProperty.create("upper");
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);
    
    private static final VoxelShape[] SHAPES = {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    
    private static final VoxelShape[] UPPER_SHAPES = {
            null,
            null,
            null,
            null,
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };
    
    
    public CornCrop(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0).setValue(UPPER, false));
    }
    
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN_KERNEL.get();
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
                    && (pLevel.getRawBrightness(pPos, 0) >= 8 || pLevel.canSeeSky(pPos))
                    && this.getAge(pLevel.getBlockState(down)) >= this.getUpperActiveAge();
        }
        return super.canSurvive(pState, pLevel, pPos);
    }
    
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pState.getValue(this.getUpperProperty())) return;
        //noinspection deprecation
        if (!pLevel.isAreaLoaded(pPos, 1)) {
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        }
        float f = getGrowthSpeed(this, pLevel, pPos);
        int age = this.getAge(pState);
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            if (age < this.getMaxAge()) {
                if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / f) + 1) == 0)) {
                    growCropToAge(age + 1, pLevel, pPos);
                    ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }
    
    public BlockState getStateForLower(int age) {
        return super.getStateForAge(age).setValue(this.getUpperProperty(), false);
    }
    
    public BlockState getStateForUpper(int age) {
        return super.getStateForAge(age).setValue(this.getUpperProperty(), true);
    }
    
    public int getUpperActiveAge() {
        return 4;
    }
    
    @Override
    public int getMaxAge() {
        return 8;
    }
    
    
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int age = pState.getValue(this.getAgeProperty());
        return pState.getValue(this.getUpperProperty()) ? UPPER_SHAPES[age] : SHAPES[age];
    }
    
    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        if (pState.is(this)) {
            return !this.isMaxAge(pState);
        }
        return true;
    }
    
    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        if (pState.getValue(this.getUpperProperty())) {
            BlockPos lowerPos = pPos.below();
            BlockState lowerState = pLevel.getBlockState(lowerPos);
            if (lowerState.is(this)) {
                CornCrop lowerCrop = (CornCrop) lowerState.getBlock();
                lowerCrop.growCrops(pLevel, lowerPos, lowerState);
            }
            return;
        }
        int boneMealedAge = this.getAge(pState) + this.getBonemealAgeIncrease(pLevel);
        int maxAge = this.getMaxAge();
        if (boneMealedAge > maxAge) {
            boneMealedAge = maxAge;
        }
        
        growCropToAge(boneMealedAge, (ServerLevel) pLevel, pPos);
    }
    
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    
    public void growCropToAge(int age, ServerLevel pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, this.getStateForLower(age), 2);
        BlockPos above = pPos.above();
        if (age >= this.getUpperActiveAge() && this.defaultBlockState().canSurvive(pLevel, pPos.above())
                && (pLevel.isEmptyBlock(above) || (pLevel.getBlockState(above).is(this)
                && pLevel.getBlockState(above).getValue(this.getUpperProperty())))) {
            pLevel.setBlockAndUpdate(pPos.above(), getStateForUpper(age));
        }
    }
    
    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (pState.getValue(this.getAgeProperty()) > this.getUpperActiveAge()) {
            if (pState.getValue(this.getUpperProperty())) {
                BlockPos below = pPos.below();
                if (pLevel.getBlockState(below).is(this)) {
                    pLevel.destroyBlock(below, false, pPlayer);
                }
            } else {
                BlockPos above = pPos.above();
                if (pLevel.getBlockState(above).is(this)) {
                    pLevel.destroyBlock(above, false, pPlayer);
                }
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }
    
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem() instanceof BoneMealItem) {
            return InteractionResult.PASS;
        }
        if (pState.getValue(this.getUpperProperty())) {
            BlockPos lowerPos = pPos.below();
            BlockState lowerState = pLevel.getBlockState(lowerPos);
            if (lowerState.is(this)) {
                CornCrop lowerCrop = (CornCrop) lowerState.getBlock();
                lowerCrop.use(lowerState, pLevel, lowerPos, pPlayer, pHand, pHit);
            } else return InteractionResult.FAIL;
            return InteractionResult.SUCCESS;
        }
        
        if (isMaxAge(pState) && !pLevel.isClientSide()) {
            pLevel.removeBlock(pPos.above(), false);
            dropResources(pState, pLevel, pPos, null, pPlayer, pPlayer.getItemInHand(pHand));
            growCropToAge(3, (ServerLevel) pLevel, pPos);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
