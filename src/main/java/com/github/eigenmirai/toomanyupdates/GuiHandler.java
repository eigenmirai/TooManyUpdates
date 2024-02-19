package com.github.eigenmirai.toomanyupdates;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GuiHandler {
    public static Map<String, String> cancelledUpdates = new HashMap();

    @SubscribeEvent
    public void onGuiOpen(GuiScreenEvent event) {
        GuiScreen gui = event.gui;
        if (gui.getClass().getName().equals("gg.skytils.skytilsmod.gui.RequestUpdateGui")) {
            System.out.println("Skytils Update GUI detected, closing GUI...");
            Minecraft.getMinecraft().displayGuiScreen(null);

            String url;
            try {
                Class<?> skytilsUpdateCheckerClass = Class.forName("gg.skytils.skytilsmod.core.UpdateChecker");
                Method getterMethod = skytilsUpdateCheckerClass.getDeclaredMethod("getUpdateDownloadURL");

                Constructor<?> constructor = skytilsUpdateCheckerClass.getDeclaredConstructors()[0];
                constructor.setAccessible(true);
                getterMethod.setAccessible(true);

                url = (String) getterMethod.invoke(constructor.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            cancelledUpdates.put("Skytils", url);

        }
    }
}
