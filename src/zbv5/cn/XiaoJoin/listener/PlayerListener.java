package zbv5.cn.XiaoJoin.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import zbv5.cn.XiaoJoin.Main;
import zbv5.cn.XiaoJoin.util.Util;

public class PlayerListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(Main.CancelJoinMessage)
        {
            e.setJoinMessage("");
        }
        Player p = e.getPlayer();
        Util.Formats(p,true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        if(Main.CancelJoinMessage)
        {
            e.setQuitMessage("");;
        }
        Player p = e.getPlayer();
        Util.Formats(p,false);
    }
}
