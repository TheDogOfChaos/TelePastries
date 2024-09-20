package io.thedogofchaos.telepastriesextended.generator;

import io.thedogofchaos.telepastriesextended.generator.client.PastryBlockStateProvider;
import io.thedogofchaos.telepastriesextended.generator.client.PastryItemModelProvider;
import io.thedogofchaos.telepastriesextended.generator.server.PastryLootProvider;
import io.thedogofchaos.telepastriesextended.generator.server.PastryRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PastriesGenerator {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new PastryLootProvider(packOutput));
			generator.addProvider(event.includeServer(), new PastryRecipeProvider(packOutput));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new PastryBlockStateProvider(packOutput, helper));
			generator.addProvider(event.includeClient(), new PastryItemModelProvider(packOutput, helper));
		}
	}
}
