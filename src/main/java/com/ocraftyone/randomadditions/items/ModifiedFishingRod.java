package com.ocraftyone.randomadditions.items;

import com.ocraftyone.randomadditions.entities.RandomAdditionsFishingHook;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class ModifiedFishingRod extends FishingRodItem {
    
    private final Tier tier;
    private final int enchantability;
    
    public ModifiedFishingRod(Properties properties, Tier tier) {
        super(properties);
        this.tier = tier;
        this.enchantability = tier.getEnchantmentValue();
    }
    
    @NotNull
    @ParametersAreNonnullByDefault
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null) {
            if (!pLevel.isClientSide) {
                int i = pPlayer.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, pPlayer, (p_41288_) -> p_41288_.broadcastBreakEvent(pHand));
            }
            
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_REEL_IN, pPlayer);
        } else {
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                int lure = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int luck = EnchantmentHelper.getFishingLuckBonus(itemstack);
                if (Tiers.IRON.equals(tier)) {
                    lure = 1;
                    luck = 2;
                } else if (Tiers.GOLD.equals(tier)) {
                    lure = 2;
                    luck = 2;
                } else if (Tiers.DIAMOND.equals(tier)) {
                    lure = 4;
                    luck = 3;
                } else if (Tiers.NETHERITE.equals(tier)) {
                    lure += 5;
                    luck += 10;
                }
                lure = Math.min(5, lure);
                pLevel.addFreshEntity(new RandomAdditionsFishingHook(pPlayer, pLevel, luck, lure, tier));
            }
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_CAST, pPlayer);
        }
        
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
    
    public int getEnchantability() {
        return enchantability;
    }
}
