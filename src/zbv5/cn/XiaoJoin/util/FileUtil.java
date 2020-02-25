package zbv5.cn.XiaoJoin.util;

import cn.nukkit.utils.Config;
import zbv5.cn.XiaoJoin.Main;
import zbv5.cn.XiaoJoin.lang.Lang;

import java.io.File;

public class FileUtil
{
    public static Config lang;
    public static Config config;

    public static void LoadFile()
    {
        File Lang_Yml = new File(Main.getInstance().getDataFolder(), "lang.yml");
        if (!Lang_Yml.exists())
        {
            Main.getInstance().saveResource("lang.yml", false);
        }
        lang = new Config(new File(Main.getInstance().getDataFolder() + "/lang.yml"));
        PrintUtil.PrintConsole("&3lang.yml &a加载.");
        Lang.LoadLang();
        File Config_Yml = new File(Main.getInstance().getDataFolder(), "config.yml");
        if (!Config_Yml.exists())
        {
            Main.getInstance().saveResource("config.yml", false);
        }
        config = new Config(new File(Main.getInstance().getDataFolder() + "/config.yml"));

        PrintUtil.PrintConsole("&3config.yml &a加载.");

        if(config.getBoolean("CancelJoinMessage"))
        {
            Main.CancelJoinMessage = true;
            PrintUtil.PrintConsole("&a取消原版进入/退出消息.");
        } else {
            Main.CancelJoinMessage = false;
            PrintUtil.PrintConsole("&c未取消原版进入/退出消息.&7(原因:已禁用)");
        }
        if(config.getBoolean("Delay.Enable"))
        {
            Main.Delay = true;
            Main.DelayTime = config.getInt("Delay.DelayTime");
            PrintUtil.PrintConsole("&a启用延迟执行.");
            PrintUtil.PrintConsole("&a延迟执行时间: &b"+Main.DelayTime+"秒");
        } else {
            Main.Delay = false;
            PrintUtil.PrintConsole("&c未启用延迟执行.&7(原因:已禁用)");
        }
    }
}
