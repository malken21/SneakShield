package marumasa.sneak_shield;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;

public class SneakShield implements ModInitializer {
    @Override
    public void onInitialize() {
        // クライアント側の毎フレーム更新イベントにリスナー 登録
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            final ClientPlayerEntity player = client.player;
            final ClientPlayerInteractionManager interactionManager = client.interactionManager;

            // player が null または スニークしていない または interactionManager が null の場合 return
            if (player == null || !player.isSneaking() || interactionManager == null) return;


            // メインハンドが盾 の場合
            if (player.getMainHandStack().getItem() instanceof ShieldItem shieldItem)
                // メインハンドを使う
                shieldItem.use(client.world, client.player, Hand.MAIN_HAND);


                // オフハンドが盾 の場合
            else if (player.getOffHandStack().getItem() instanceof ShieldItem shieldItem)
                // オフハンドを使う
                shieldItem.use(client.world, client.player, Hand.OFF_HAND);

        });
    }
}
