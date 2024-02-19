package com.github.eigenmirai.toomanyupdates;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Chatting {
    Minecraft mc = Minecraft.getMinecraft();
    private boolean sentMessage = false;

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (!(event.entity instanceof EntityPlayer) || mc.thePlayer == null) return;
        EntityPlayer eventPlayer = (EntityPlayer) event.entity;
        if (eventPlayer.getUniqueID().equals(mc.thePlayer.getUniqueID())) {

            if (!Loader.isModLoaded("skytils")) {
                mc.thePlayer.addChatMessage(new ChatComponentText(TooManyUpdates.chatPrefix + "Did not detect skytils, why do you even have this mod."));
                sentMessage = true;
                return;
            }

            GuiHandler.cancelledUpdates.forEach((name, url) -> {
                String msg = String.format("Cancelled %s update GUI, you can download it by clicking ", name);
                ChatComponentText button = new ChatComponentText(ChatFormatting.YELLOW + ChatFormatting.BOLD.toString() + "HERE");

                ChatStyle style = new ChatStyle();
                style.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
                style.setChatHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.YELLOW + url)
                ));

                button.setChatStyle(style);
                mc.thePlayer.addChatMessage(new ChatComponentText(TooManyUpdates.chatPrefix + msg).appendSibling(button));
            });
            sentMessage = true;
        }
    }
}
