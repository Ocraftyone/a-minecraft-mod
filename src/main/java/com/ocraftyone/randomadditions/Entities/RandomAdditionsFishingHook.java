package com.ocraftyone.randomadditions.Entities;

import com.ocraftyone.randomadditions.inits.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RandomAdditionsFishingHook extends FishingHook {
    
    public RandomAdditionsFishingHook(Player player, Level level, int luck, int lureSpeed) {
        super(player, level, luck, lureSpeed);
    }
    
    public RandomAdditionsFishingHook(EntityType<RandomAdditionsFishingHook> randomAdditionsFishingHookEntityType, Level level) {
        super(randomAdditionsFishingHookEntityType, level);
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
    @NotNull
    public EntityType<?> getType() {
        return ModEntities.FISHING_HOOK.get();
    }

}
