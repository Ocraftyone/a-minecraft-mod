/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.client;

import com.mojang.logging.LogUtils;
import com.ocraftyone.randomadditions.Constants;
import com.ocraftyone.randomadditions.client.renderer.CornShuckerBlockEntityRenderer;
import com.ocraftyone.randomadditions.client.renderer.ModifiedFishingHookRenderer;
import com.ocraftyone.randomadditions.inits.ModBlockEntities;
import com.ocraftyone.randomadditions.inits.ModBlocks;
import com.ocraftyone.randomadditions.inits.ModEntities;
import com.ocraftyone.randomadditions.inits.ModItems;
import com.ocraftyone.randomadditions.util.AnimationUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.FORGE)
public class ClientHandler {
    @SubscribeEvent
    public static void onTickClientTick(TickEvent.ClientTickEvent event) {
        AnimationUtil.tick();
    }
    
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        resetAnimationTick(event);
    }
    
    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        resetAnimationTick(event);
    }
    
    private static void resetAnimationTick(WorldEvent event) {
        LevelAccessor world = event.getWorld();
        if (world.isClientSide() && world instanceof ClientLevel) {
            AnimationUtil.reset();
        }
    }
    
    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void onFMLClientSetup(FMLClientSetupEvent event) {
            registerFishingRod(ModItems.IRON_FISHING_ROD.get());
            registerFishingRod(ModItems.GOLD_FISHING_ROD.get());
            registerFishingRod(ModItems.DIAMOND_FISHING_ROD.get());
            registerFishingRod(ModItems.NETHERITE_FISHING_ROD.get());
            
            registerBlockRenders();
        }
        
        public static void registerBlockRenders() {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CORN_CROP.get(), RenderType.cutout());
        }
        
        public static void registerFishingRod(Item fishingRod) {
            ItemProperties.register(fishingRod, new ResourceLocation("cast"), (stack, level, entity, i) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    boolean isMainhand = entity.getMainHandItem() == stack;
                    boolean isOffHand = entity.getOffhandItem() == stack;
                    if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                        isOffHand = false;
                    }
                    return (isMainhand || isOffHand) && entity instanceof Player && ((Player) entity).fishing != null ? 1.0F : 0.0F;
                }
            });
        }
        
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            LogUtils.getLogger().info("HELLO FROM REGISTER ENTITY RENDERERS");
            event.registerEntityRenderer(ModEntities.FISHING_HOOK.get(), ModifiedFishingHookRenderer::new);
        }
        
        @SubscribeEvent
        public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            LogUtils.getLogger().info("HELLO FROM REGISTER BLOCK ENTITY RENDERERS");
            event.registerBlockEntityRenderer(ModBlockEntities.CORN_SHUCKER_BLOCK_ENTITY.get(), CornShuckerBlockEntityRenderer::new);
        }
        
        @SubscribeEvent
        public static void onModelRegistry(ModelRegistryEvent event) {
            ForgeModelBakery.addSpecialModel(new ResourceLocation(Constants.MOD_ID, "block/auto_corn_shucker_spin"));
        }
    }
}
