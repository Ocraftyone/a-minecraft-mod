package com.ocraftyone.randomadditions.Entites;

import com.ocraftyone.randomadditions.inits.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class RandomAdditionsFishingHook extends FishingHook {
    
    private final Tier tier;
    
    public RandomAdditionsFishingHook(Player player, Level level, int luck, int lureSpeed, Tier tier) {
        super(player, level, luck, lureSpeed);
        this.tier = tier;
    }
    
    public RandomAdditionsFishingHook(EntityType<RandomAdditionsFishingHook> randomAdditionsFishingHookEntityType, Level level) {
        super(randomAdditionsFishingHookEntityType, level);
        this.tier = null;
    }
    
    @Override
    protected boolean shouldStopFishing(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();
        boolean flag = mainHandItem.getItem() instanceof FishingRodItem;
        boolean flag1 = offhandItem.getItem() instanceof FishingRodItem;
        if (!player.isRemoved() && player.isAlive() && (flag || flag1) && !(this.distanceToSqr(player) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }
    
    @Override
    @ParametersAreNonnullByDefault
    protected void catchingFish(BlockPos p_37146_) {
        super.catchingFish(p_37146_);
        if (tier != null && tier.equals(Tiers.NETHERITE)) {
            this.timeUntilLured = 1;
        }
    }
    
    @Override
    @NotNull
    public EntityType<?> getType() {
        return ModEntities.FISHING_HOOK.get();
    }

}
