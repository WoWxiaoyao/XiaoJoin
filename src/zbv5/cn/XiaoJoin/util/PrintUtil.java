package zbv5.cn.XiaoJoin.util;

import cn.nukkit.Nukkit;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import zbv5.cn.XiaoJoin.Main;
import zbv5.cn.XiaoJoin.lang.Lang;

import java.util.List;

public class PrintUtil
{

    public static void PrintConsole(String s)
    {
        Main.getInstance().getServer().getConsoleSender().sendMessage(cc(Lang.Prefix+s));
    }
    public static void PrintPlayer(Player p, String s)
    {
        p.sendMessage(cc(s));
    }
    public static void PrintAllPlayer(String s)
    {
        //for(Player p:Main.getInstance().getServer().getOnlinePlayers().values())
       // {
       //     p.sendMessage(s);
        //}
        Main.getInstance().getServer().broadcastMessage(s);
    }

    public static String cc(String s)
    {
        s = TextFormat.colorize('&', s);
        return s;
    }

    public static void PrintCommandSender(CommandSender sender, Boolean prefix, String s)
    {
        if(prefix)
        {
            sender.sendMessage(cc(Lang.Prefix+s));
        } else {
            sender.sendMessage(cc(s));
        }
    }

    public static void Run(Player p, List<String> RunList)
    {
        try{
            for(String s:RunList)
            {
                if(Main.PlaceholderAPI)
                {
                    s = PlaceholderAPI.getInstance().translateString(s,p);
                }
                s = cc(s.replace("{player}",p.getName()));
                if(s.startsWith("[message]"))
                {
                    s=s.replace("[message]", "");
                    PrintPlayer(p,s);
                }
                if(s.startsWith("[bc]"))
                {
                    s=s.replace("[bc]", "");
                    PrintAllPlayer(s);
                }
                if(s.startsWith("[console]"))
                {
                    s=s.replace("[console]", "");
                    Main.getInstance().getServer().dispatchCommand(Main.getInstance().getServer().getConsoleSender(),s);
                }
                if(s.startsWith("[player]"))
                {
                    s=s.replace("[player]", "");
                    Main.getInstance().getServer().dispatchCommand(p, s);
                }
                if(s.startsWith("[title]"))
                {
                    s=s.replace("[title]", "");
                    try
                    {
                        String[] ss = s.split(",");
                        if(ss.length == 1)
                        {
                            p.sendTitle(s);
                        }
                        if(ss.length == 2)
                        {
                            p.sendTitle(ss[0],ss[1]);
                        }
                        if(ss.length == 5)
                        {
                            p.sendTitle(ss[0],ss[1],Integer.parseInt(ss[2]),Integer.parseInt(ss[3]),Integer.parseInt(ss[4]));
                        }
                    }
                    catch (Exception e)
                    {
                        PrintUtil.PrintConsole("&3Run出现问题 &c执行Title出现问题");
                        e.printStackTrace();
                    }
                }
                if(s.startsWith("[ActionBar]"))
                {
                    s=s.replace("[ActionBar]", "");
                    try
                    {
                        String[] ss = s.split(",");
                        if(ss.length == 1)
                        {
                            p.sendActionBar(s);
                        }
                        if(ss.length == 4)
                        {
                            p.sendActionBar(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]),Integer.parseInt(ss[3]));
                        }
                    }
                    catch (Exception e)
                    {
                        PrintUtil.PrintConsole("&3Run出现问题 &c执行ActionBar出现问题");
                        e.printStackTrace();
                    }
                }
                if(s.startsWith("[op]"))
                {
                    boolean op = p.isOp();
                    p.setOp(true);
                    try
                    {
                        s=s.replace("[op]", "");
                        Main.getInstance().getServer().dispatchCommand(p, s);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        p.setOp(op);
                    }
                    catch (Exception e)
                    {
                        PrintUtil.PrintConsole("&3Run出现问题 &c执行OP指令操作出现问题");
                        e.printStackTrace();
                        p.setOp(false);
                    }
                }
                if(s.startsWith("[playerchat]"))
                {
                    s=s.replace("[playerchat]", "");
                    p.chat(s);
                }
            } }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&cRun执行出现问题");
            e.printStackTrace();
        }
    }
}
