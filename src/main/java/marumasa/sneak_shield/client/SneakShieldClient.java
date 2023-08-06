package marumasa.sneak_shield.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class SneakShieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // クライアント側の毎フレーム更新イベントにリスナー 登録
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            final ClientPlayerEntity player = client.player;
            final ClientPlayerInteractionManager interactionManager = client.interactionManager;

            // player が null または スニークしていない または interactionManager が null の場合 return
            if (player == null || !player.isSneaking() || interactionManager == null) return;


            // メインハンドが盾 の場合
            if (player.getMainHandStack().getItem() == Items.SHIELD)
                // メインハンドを使う
                interactionManager.interactItem(client.player, Hand.MAIN_HAND);

                // オフハンドが盾 の場合
            else if (player.getOffHandStack().getItem() == Items.SHIELD)
                // オフハンドを使う
                interactionManager.interactItem(client.player, Hand.OFF_HAND);

        });
    }
}
