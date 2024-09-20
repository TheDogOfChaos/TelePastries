package io.thedogofchaos.telepastriesextended.handler;

import io.thedogofchaos.telepastriesextended.blocks.BlockPastryBase;
import io.thedogofchaos.telepastriesextended.config.TeleConfig;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.ExplosionEvent;

public class ExplosionHandler { // ?: Makes all TelePastries immune to explosions if the explosionImmune config is true.
	public static void onExplosion(ExplosionEvent.Detonate event) {
		if (TeleConfig.COMMON.explosionImmune.get()) {
			final Level level = event.getLevel();
			event.getAffectedBlocks().removeIf((pos) -> level.isAreaLoaded(pos, 1) && level.getBlockState(pos).getBlock() instanceof BlockPastryBase);
		}
	}
}
