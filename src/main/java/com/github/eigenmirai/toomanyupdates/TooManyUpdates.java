package com.github.eigenmirai.toomanyupdates;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = TooManyUpdates.MOD_ID,
        name = TooManyUpdates.NAME,
        version = TooManyUpdates.VERSION,
        useMetadata = true,
        clientSideOnly = true
)
public class TooManyUpdates {
    public static final String MOD_ID = "toomanyupdates";
    public static final String NAME = "TooManyUpdates";
    public static final String VERSION = "1.0.0";

    public static final Logger LOGGER = LogManager.getLogger(TooManyUpdates.class);
    public static final String chatPrefix = "§5§lTooManyUpdates §8»§r ";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new Chatting());
    }
}
