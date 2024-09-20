package io.thedogofchaos.telepastriesextended;

import com.mojang.logging.LogUtils;
import io.thedogofchaos.telepastriesextended.compat.top.TeleTOPCompat;
import io.thedogofchaos.telepastriesextended.config.TeleConfig;
import io.thedogofchaos.telepastriesextended.handler.ExplosionHandler;
import io.thedogofchaos.telepastriesextended.init.TeleRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID) // ?: Defines this mod to Forge with the mod ID in the Reference class
public class TelePastries {
	public static final Logger LOGGER = LogUtils.getLogger(); // ?: Initialises the logger for use with logging.

	public TelePastries() {
		// ?: Registers an eventBus and the configs for the mod.
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(Type.COMMON, TeleConfig.commonSpec);
		FMLJavaModLoadingContext.get().getModEventBus().register(TeleConfig.class);

		eventBus.addListener(this::sendImc);

		// ?: Registers the Blocks, Items and Creative Tabs of the mod.
		TeleRegistry.BLOCKS.register(eventBus);
		TeleRegistry.ITEMS.register(eventBus);
		TeleRegistry.CREATIVE_MODE_TABS.register(eventBus);

		// ?: Adds a listener for explosions, for the ExplosionHandler to use to make this mod's cakes explosion-immune if the config is true.
		MinecraftForge.EVENT_BUS.addListener(ExplosionHandler::onExplosion);
	}

	public void sendImc(InterModEnqueueEvent event) { // ?: Mod Compatability.
		if (ModList.get().isLoaded("theoneprobe")) {
			TeleTOPCompat.register(); // ?: Compat for The One Probe.
		}
	}
}
