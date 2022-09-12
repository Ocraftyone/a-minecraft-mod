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
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    public static void setupClient() {
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
    public void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        LogUtils.getLogger().info("HELLO FROM REGISTER BLOCK ENTITY RENDERERS");
        event.registerBlockEntityRenderer(ModBlockEntities.CORN_SHUCKER_BLOCK_ENTITY.get(), CornShuckerBlockEntityRenderer::new);
    }
    
    /*
    Not needed unless I use my own model
    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        ForgeModelBakery.addSpecialModel(new ResourceLocation(Constants.MOD_ID, "item/corn_shucker_special"));
    }*/
}
