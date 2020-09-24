package cn.zys.common;

/**
 * @program: road-health
 * @description: OftenFinalMessage
 * @author: xiaozhang6666
 * @create: 2020-09-21 20:21
 **/
public class OftenFinalMessage {

    //跨域地址设置
    public static final String Host_Addr = "http://127.0.0.1:8083";
    //zookeeper地址
    private static final String QQ_Config_Ip = "62.234.118.219";
    private static final Integer QQ_Config_Prot = 2182;
    private static final String LocalHsot_Config_Ip = "127.0.0.1";
    private static final Integer LocalHsot_Config_Prot = 2181;

    //62.234.118.219   localhost
    public static final String Host_Zookeeper_Addr = LocalHsot_Config_Ip;
    public static final Integer Host_Zookeeper_Prot = LocalHsot_Config_Prot;
    public static final Integer Protocol_Port = 8881;

    //常用静态常量
    public static final Integer VAR_NULL = null;
}
