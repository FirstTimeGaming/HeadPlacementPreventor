package firsttimegaming.headplacementpreventor;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class HeadplacementPreventorClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Listen for player interactions
		// Register the event to intercept block placement
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			// Only process if this is on the client side
			if (!world.isClient()) {
				return ActionResult.PASS;
			}

			// Check if the player is holding a player head in their main hand
			if (player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.PLAYER_HEAD) {
				// Cancel block placement and notify the player
				player.sendMessage(Text.literal("Placing player heads is disabled!"), true);
				return ActionResult.FAIL;  // Prevents the action from completing
			}

			return ActionResult.PASS;  // Allow the action if it's not a player head
		});
	}
}