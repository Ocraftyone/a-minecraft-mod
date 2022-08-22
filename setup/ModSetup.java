package com.ocraftyone.pkgname;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ModSetup.MODID)
@Mod.EventBusSubscriber
public class ModSetup {
    public static final String MODID = "pkgname";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public ModSetup() {
        
    }
}
