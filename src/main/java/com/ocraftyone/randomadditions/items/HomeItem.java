package com.ocraftyone.randomadditions.items;

import com.ocraftyone.randomadditions.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class HomeItem extends Item {
    
    private final String UUID_KEY = Constants.MOD_ID + ".player";
    private final String POS_BASE = Constants.MOD_ID + ".pos.";
    private final String POS_X_KEY = POS_BASE + "x";
    private final String POS_Y_KEY = POS_BASE + "y";
    private final String POS_Z_KEY = POS_BASE + "z";
    private String BOUND = Constants.MOD_ID + ".boundToPlayer";
    private String DIM = Constants.MOD_ID + ".dim";
    
    public HomeItem(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = itemstack.getOrCreateTag();
        if (!pLevel.isClientSide()) {
            if (pPlayer.isCrouching()) {
                if (tag.getBoolean(BOUND)) {
                    if (!compareUUIDs(tag.getUUID(UUID_KEY), pPlayer.getUUID())) {
                        pPlayer.displayClientMessage(new TextComponent("Bound to player: " + pPlayer.getName().getString()).withStyle(ChatFormatting.GREEN), false);
                    }
                } else {
                    pPlayer.displayClientMessage(new TextComponent("Bound to player: " + pPlayer.getName().getString()).withStyle(ChatFormatting.GREEN), false);
                    tag.putBoolean(BOUND, true);
                    tag.putUUID(UUID_KEY, pPlayer.getUUID());
                }
                tag.putString(DIM, pLevel.dimension().location().toString());
                tag.putDouble(POS_X_KEY, pPlayer.position().x());
                tag.putDouble(POS_Y_KEY, pPlayer.position().y());
                tag.putDouble(POS_Z_KEY, pPlayer.position().z());
                pPlayer.displayClientMessage(new TextComponent("Home location set!"), true);
                return InteractionResultHolder.success(itemstack);
            } else {
                if (tag.getBoolean(BOUND)) {
                    if (compareUUIDs(tag.getUUID(UUID_KEY), pPlayer.getUUID())) {
                        pPlayer.displayClientMessage(new TextComponent("Teleporting..."), true);
                        String dimLocation = tag.getString(DIM);
                        ResourceKey<Level> destination = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dimLocation));
                        //noinspection ConstantConditions
                        ((ServerPlayer) pPlayer).teleportTo(pLevel.getServer().getLevel(destination), tag.getDouble(POS_X_KEY), tag.getDouble(POS_Y_KEY), tag.getDouble(POS_Z_KEY), pPlayer.getYRot(), pPlayer.getXRot());
                        return InteractionResultHolder.success(itemstack);
                    } else {
                        pPlayer.displayClientMessage(new TextComponent("You are not the owner of this item").withStyle(ChatFormatting.RED), false);
                        return InteractionResultHolder.fail(itemstack);
                    }
                } else {
                    pPlayer.displayClientMessage(new TextComponent("No home has been set").withStyle(ChatFormatting.RED), false);
                    return InteractionResultHolder.fail(itemstack);
                }
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }
    
    public static boolean compareUUIDs(UUID uuid, UUID uuid1) {
        return uuid.toString().equals(uuid1.toString());
    }
    
    @Override
    public boolean isFoil(ItemStack pStack) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            //noinspection ConstantConditions
            if (tag.contains(BOUND) && tag.getBoolean(BOUND)) {
                return true;
            }
        }
        return super.isFoil(pStack);
    }
    
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent(Constants.MOD_ID + ".home.tooltip1"));
        pTooltipComponents.add(new TranslatableComponent(Constants.MOD_ID + ".home.tooltip2"));
        CompoundTag tag = pStack.getTag();
        //noinspection ConstantConditions
        if (pStack.hasTag() && tag.getBoolean(BOUND)) {
            if (pLevel == null) return;
            pTooltipComponents.add(new TextComponent("Owner: " + pLevel.getPlayerByUUID(tag.getUUID(UUID_KEY)).getName().getString()).withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(new TextComponent("Home Location: " + (int) tag.getDouble(POS_X_KEY) + ", " + (int) tag.getDouble(POS_Y_KEY) + ", " + (int) tag.getDouble(POS_Z_KEY)));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}