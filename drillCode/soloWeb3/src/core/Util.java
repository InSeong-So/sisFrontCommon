package core;

public class Util
{
    public static String nvl(String str)
    {
        return nvl(str, "");
    }
    
    public static String nvl(String str, String defaultStr)
    {
        return (str == null || str.length() == 0) ? defaultStr : str;
    }
}
