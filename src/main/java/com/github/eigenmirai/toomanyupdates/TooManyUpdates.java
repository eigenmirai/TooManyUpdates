package com.github.eigenmirai.toomanyupdates;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = UpdateManagerMod.MOD_ID,
        name = UpdateManagerMod.NAME,
        version = UpdateManagerMod.VERSION,
        useMetadata = true,
        clientSideOnly = true
)
public class UpdateManagerMod {
    public static final String MOD_ID = "TooManyUpdates";
    public static final String NAME = "TooManyUpdates";
    public static final String VERSION = "1.0.0";

    public static final String chatPrefix = "§5§lTooManyUpdates §8»§r ";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new Chatting());
    }
}