package io.thedogofchaos.telepastriesextended.compat.top;

import io.thedogofchaos.telepastriesextended.Reference;
import io.thedogofchaos.telepastriesextended.blocks.cake.BlockCakeBase;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.InterModComms;

import java.util.function.Function;

public class TeleTOPCompat {
	public static void register(){
		// ?: Sends the 'getTheOneProbe' method to TheOneProbe
		InterModComms.sendTo("theoneprobe", "getTheOneProbe", GetTheOneProbe::new);
	}

	public static final class GetTheOneProbe implements Function<ITheOneProbe, Void> {
		@Override
		public Void apply(ITheOneProbe input){
			// ?: Registers PastryInfo as a provider for TOP to use.
			input.registerProvider(new PastryInfo());
			return null;
		}
	}

	public static final class PastryInfo implements IProbeInfoProvider {

		@Override
		public ResourceLocation getID(){ // ?: Returns this mod's ID for TOP to use to identify this provider.
			return new ResourceLocation(Reference.MOD_ID, "main");
		}

		@Override
		public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
			final Block block = world.getBlockState(data.getPos()).getBlock(); // ?: Gets the block looked at.
			if (block instanceof BlockCakeBase) { // ?: If the block is a TelePastry, show info related to the cake.
				probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
						.item(new ItemStack(block))
						.text(Component.translatable("telepastries.compat.bites_remaining").withStyle(ChatFormatting.GREEN))
						.progress(6 - blockState.getValue(BlockCakeBase.BITES), 6);
			}
		}
	}
}
