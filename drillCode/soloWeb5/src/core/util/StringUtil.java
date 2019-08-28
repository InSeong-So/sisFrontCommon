package core.util;

public class StringUtil
{
    // oracle NVL 기능 구현 01
    public static String nvl(String str)
    {
        return nvl(str, "");
    }
    
    // oracle NVL 기능 구현 02
    public static String nvl(String str, String defaultStr)
    {
        return (str == null || str.length() == 0) ? defaultStr : str;
    }
    
    // 문자열 배열에 포함된 값이 있는지 확인
    public static boolean contains(String arrStr[], String str)
    {
        for (int n = 0; n < arrStr.length; n++)
            if (str.equals(arrStr[n]))
                return true;
            
        return false;
    }
    
    // 문자열 null 체크 01
    public static boolean isNull(String str)
    {
        return str == null || str.length() == 0;
    }
    
    // 문자열 null 체크 02
    public static String isNull(String source, String value)
    {
        String retVal;
        if (source == null || source.trim().equals("") || source.trim().equals("null"))
            retVal = value;
        else
            retVal = source.trim();
        return retVal;
    }
    
    // 문자열 null 체크 03
    public static int isNull(String source, int value)
    {
        int retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Integer.parseInt(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 04
    public static long isNull(String source, long value)
    {
        long retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Long.parseLong(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 05
    public static float isNull(String source, float value)
    {
        float retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Float.parseFloat(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
    
    // 문자열 null 체크 06
    public static double isNull(String source, double value)
    {
        double retVal;
        try
        {
            if (source == null || source.trim().equals(""))
                retVal = value;
            else
                retVal = Double.parseDouble(source);
        }
        catch (Exception e)
        {
            retVal = value;
        }
        return retVal;
    }
}
