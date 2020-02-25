package zbv5.cn.XiaoJoin;

import cn.nukkit.Nukkit;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import zbv5.cn.XiaoJoin.lang.Lang;
import zbv5.cn.XiaoJoin.listener.PlayerListener;
import zbv5.cn.XiaoJoin.util.FileUtil;
import zbv5.cn.XiaoJoin.util.PrintUtil;

public class Main extends PluginBase implements Listener
{
    private static Main instance;
    public static boolean PlaceholderAPI = false;
    public static boolean CancelJoinMessage = true;
    public static boolean Delay = true;
    public static int DelayTime = 10;
    public static Main getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        PrintUtil.PrintConsole("&e======== &bXiaoJoin &e> &d开始加载 &e========");
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
        {
            PlaceholderAPI = true;
            PrintUtil.PrintConsole("&a检测到PlaceholderAPI插件.");
        }else {
            PrintUtil.PrintConsole("&c未检测到PlaceholderAPI插件,大量变量失效.");
        }
        FileUtil.LoadFile();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        PrintUtil.PrintConsole("&e======== &bXiaoJoin &e> &a加载成功 &e========");
    }

    @Override
    public void onDisable()
    {
        PrintUtil.PrintConsole("&c插件卸载");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("XiaoJoin"))
        {
            if (args.length == 0)
            {
                PrintUtil.PrintCommandSender(sender,false,"&6========= [&bXiaoJoin&6] =========");
                PrintUtil.PrintCommandSender(sender,false,"&6/XiaoJoin &areload &7- &c重载配置文件");
                return true;
            }
            if(args[0].equalsIgnoreCase("reload"))
            {
                if(!sender.hasPermission("XiaoJoin.commands.reload"))
                {
                    PrintUtil.PrintCommandSender(sender,true, Lang.NoPermission);
                    return false;
                }
                try
                {
                    FileUtil.LoadFile();
                    PrintUtil.PrintCommandSender(sender,true,Lang.SuccessReload);
                    return true;
                } catch (Exception e)
                {
                    PrintUtil.PrintCommandSender(sender,true,Lang.FailReload);
                    e.printStackTrace();
                }
                return false;
            }
            PrintUtil.PrintCommandSender(sender,true, Lang.NullCommand);
            return false;

        }
        return false;
    }
}
