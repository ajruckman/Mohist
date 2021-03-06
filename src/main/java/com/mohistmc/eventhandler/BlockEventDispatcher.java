package com.mohistmc.eventhandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlock;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockEventDispatcher {

    public static boolean isDropItems;

    @SubscribeEvent(receiveCanceled = true)
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (!event.getWorld().isRemote()) {
            CraftBlock craftBlock = CraftBlock.at(event.getWorld(), event.getPos());
            BlockBreakEvent breakEvent = new BlockBreakEvent(craftBlock, ((ServerPlayerEntity) event.getPlayer()).getBukkitEntity());
            breakEvent.setCancelled(event.isCanceled());
            breakEvent.setExpToDrop(event.getExpToDrop());
            Bukkit.getPluginManager().callEvent(breakEvent);
            event.setCanceled(breakEvent.isCancelled());
            event.setExpToDrop(breakEvent.getExpToDrop());
            isDropItems = breakEvent.isDropItems();
        }
    }
}
