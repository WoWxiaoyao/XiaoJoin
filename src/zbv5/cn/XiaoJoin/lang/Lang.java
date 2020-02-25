package zbv5.cn.XiaoJoin.lang;

import zbv5.cn.XiaoJoin.util.FileUtil;
import zbv5.cn.XiaoJoin.util.PrintUtil;

public class Lang
{
    public static String Prefix = "&6[&bXiaoJoin&6]";
    public static String NoPermission = "&c你没有权限这样做";
    public static String SuccessReload = "&a重载完成!";
    public static String FailReload = "&c重载失败!请前往控制台查看报错.";
    public static String NullCommand = "&c未知指令.";
    public static String NeedPlayer = "&c只有玩家才能执行该操作.";

    public static void LoadLang()
    {
        try
        {
            Prefix = FileUtil.lang.getString("Prefix");
            NoPermission = FileUtil.lang.getString("NoPermission");
            SuccessReload = FileUtil.lang.getString("SuccessReload");
            FailReload = FileUtil.lang.getString("FailReload");
            NullCommand = FileUtil.lang.getString("NullCommand");
            NeedPlayer = FileUtil.lang.getString("NeedPlayer");
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&3lang.yml &c读取出现问题!");
            e.printStackTrace();
        }
    }
}


