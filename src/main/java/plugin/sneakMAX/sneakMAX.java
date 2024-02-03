package plugin.sneakMAX;

import java.util.Arrays;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class sneakMAX extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        // プレイヤー情報の獲得
        Player player = e.getPlayer();

        // プレイヤーのインベントリ情報の取得
        ItemStack[] itemStacks = player.getInventory().getContents();
        // アイテム数が64以下の場合、所持数を64（MAX）にする
        Arrays.stream(itemStacks)
            .filter(
                item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
            .forEach(item -> item.setAmount(64));
        // プレイヤーのインベントリ情報を上書きする
        player.getInventory().setContents(itemStacks);

    }

}