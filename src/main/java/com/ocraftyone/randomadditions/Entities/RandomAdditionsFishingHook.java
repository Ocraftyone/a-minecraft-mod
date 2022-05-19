package com.ocraftyone.randomadditions.Entities;

import com.ocraftyone.randomadditions.inits.ModEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.PlayMessages;

public class RandomAdditionsFishingHook extends FishingHook implements IEntityAdditionalSpawnData {
    
    public RandomAdditionsFishingHook(Player player, Level level, int luck, int lureSpeed) {
        super(player, level, luck, lureSpeed);
    }
    
    public RandomAdditionsFishingHook(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(level.getPlayerByUUID(spawnEntity.getAdditionalData().readUUID()), level, 0, 0);
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
    public EntityType<?> getType() {
        return ModEntities.FISHING_HOOK.get();
    }
    
    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            buffer.writeUUID(player.getUUID());
        }
    }
    
    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
    
    }
}
