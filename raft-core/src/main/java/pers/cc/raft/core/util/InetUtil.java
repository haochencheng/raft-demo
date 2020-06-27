package pers.cc.raft.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-06-21 18:30
 **/

public class InetUtil {

    private static String selfIp;

    static {
        InetAddress inetAddress=null;
        try {
             inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
        }
        System.out.println(inetAddress.getCanonicalHostName());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());

    }


    public static String getSelfIp() {
        return selfIp;
    }

    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, str);
    }

    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
