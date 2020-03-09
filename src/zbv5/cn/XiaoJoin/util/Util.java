package zbv5.cn.XiaoJoin.util;

import cn.nukkit.Player;
import cn.nukkit.utils.ConfigSection;
import zbv5.cn.XiaoJoin.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Util
{

    public static void Formats(Player p,Boolean join)
    {
        List<String> RunList = new ArrayList<>();
        int priority = 2147483647;
        try
        {
            ConfigSection c = FileUtil.config.getSection("Formats");
            for (String formats : c.getKeys(false))
            {
                if(p.hasPermission("XiaoJoin.formats."+formats))
                {
                    ConfigSection cc = FileUtil.config.getSection("Formats."+formats);
                    if(cc.getInt("priority") < priority)
                    {
                        if(join)
                        {
                            RunList = cc.getStringList("join");
                        } else {
                            RunList = cc.getStringList("quit");
                        }
                    }
                }
            }
            if((join)&&(Main.Delay))
            {
                Delay(p,RunList,Main.DelayTime);
            } else {
                PrintUtil.Run(p,RunList);
            }
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&3config.yml &c读取出现问题!&7(读取Formats出现问题)");
            e.printStackTrace();
        }
    }

    public static void Delay (Player p,List<String> RunList,int i)
    {
        Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), new Runnable()
        {
            public void run()
            {
                if(p.isOnline())
                {
                    PrintUtil.Run(p,RunList);
                }
            }
        }, i*20);
    }
}
