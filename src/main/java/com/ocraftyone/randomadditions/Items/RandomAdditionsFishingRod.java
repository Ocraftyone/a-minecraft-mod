package com.ocraftyone.randomadditions.Items;

import com.ocraftyone.randomadditions.Entities.RandomAdditionsFishingHook;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class RandomAdditionsFishingRod extends FishingRodItem {
    
    private final Tier tier;
    private final int enchantability;
    
    public RandomAdditionsFishingRod(Properties properties, Tier tier) {
        super(properties);
        this.tier = tier;
        this.enchantability = tier.getEnchantmentValue();
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null) {
            if (!pLevel.isClientSide) {
                int i = pPlayer.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, pPlayer, (p_41288_) -> {
                    p_41288_.broadcastBreakEvent(pHand);
                });
            }
        
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_REEL_IN, pPlayer);
        } else {
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                pLevel.addFreshEntity(new RandomAdditionsFishingHook(pPlayer, pLevel, j, k));
            }
        
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_CAST, pPlayer);
        }
    
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
    
}
