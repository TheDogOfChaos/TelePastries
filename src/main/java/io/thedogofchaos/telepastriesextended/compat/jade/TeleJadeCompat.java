package io.thedogofchaos.telepastriesextended.compat.jade;

import io.thedogofchaos.telepastriesextended.Reference;
import io.thedogofchaos.telepastriesextended.blocks.cake.BlockCakeBase;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;

@WailaPlugin
public class TeleJadeCompat implements IWailaPlugin {
	// TODO: Figure out how to annotate this.
	@Override
	public void register(IWailaCommonRegistration registration) {
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(PastryBodyHandler.INSTANCE, BlockCakeBase.class);
	}

	public static class PastryBodyHandler implements IBlockComponentProvider {
		private static final ResourceLocation BITES = new ResourceLocation(Reference.MOD_ID, "bites");
		public static final PastryBodyHandler INSTANCE = new PastryBodyHandler();

		@Override
		public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
			iTooltip.add(Component.translatable("telepastries.compat.bites_remaining" + (6 - blockAccessor.getBlockState().getValue(BlockCakeBase.BITES)) + " / 6").withStyle(ChatFormatting.GRAY));
		}

		@Override
		public ResourceLocation getUid() {
			return BITES;
		}
	}
}
