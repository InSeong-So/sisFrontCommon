package core.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public class FormatUtil
{
    static DecimalFormat sm_DF = new DecimalFormat("###,###,###.##########");
    
    static SimpleDateFormat sm_SDF_YMD = new SimpleDateFormat("yyyyMMdd");
    
    static SimpleDateFormat sm_SDF_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
    
    public static String formatComma(double num)
    {
        return sm_DF.format(num);
    }
    
    public static String formatComma(double num, String format)
    {
        return new DecimalFormat(format).format(num);
    }
    
    public static String formatCommaScale(double num, int scale)
    {
        return new DecimalFormat("###,###,###." + StringUtil.repeat("0", scale)).format(num);
    }
    
    public static String formatDate(String format, Calendar cal)
    {
        return new SimpleDateFormat(format).format(cal.getTime());
    }
    
    public static String formatDate(String format)
    {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
    
    public static String getYmdhms()
    {
        return sm_SDF_YMDHMS.format(Calendar.getInstance().getTime());
    }
    
    public static String getYmdhms(HttpServletRequest request)
    {
        SimpleDateFormat lo_SDF_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            lo_SDF_YMDHMS.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getFormatDate(HttpServletRequest request, String format)
    {
        SimpleDateFormat lo_SDF_YMDHMS = new SimpleDateFormat(format);
        try
        {
            lo_SDF_YMDHMS.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMDHMS.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getYmd()
    {
        return sm_SDF_YMD.format(Calendar.getInstance().getTime());
    }
    
    public static String getYmd(HttpServletRequest request)
    {
        SimpleDateFormat lo_SDF_YMD = new SimpleDateFormat("yyyyMMdd");
        try
        {
            lo_SDF_YMD.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            return lo_SDF_YMD.format(Calendar.getInstance().getTime());
        }
        catch (Exception e)
        {
            return lo_SDF_YMD.format(Calendar.getInstance().getTime());
        }
    }
    
    public static String getLastYmdOfMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        return sm_SDF_YMD.format(cal.getTime());
    }
    
    public static Calendar makeCalendar(String yyyyMMddHHmmss)
    {
        int parseInt;
        int i = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(1, Integer.parseInt(yyyyMMddHHmmss.substring(0, 4)));
        cal.set(2, Integer.parseInt(yyyyMMddHHmmss.substring(4, 6)) - 1);
        cal.set(5, Integer.parseInt(yyyyMMddHHmmss.substring(6, 8)));
        if (yyyyMMddHHmmss.length() >= 10)
        {
            parseInt = Integer.parseInt(yyyyMMddHHmmss.substring(8, 10));
        }
        else
        {
            parseInt = 0;
        }
        cal.set(11, parseInt);
        if (yyyyMMddHHmmss.length() >= 12)
        {
            parseInt = Integer.parseInt(yyyyMMddHHmmss.substring(10, 12));
        }
        else
        {
            parseInt = 0;
        }
        cal.set(12, parseInt);
        if (yyyyMMddHHmmss.length() >= 14)
        {
            i = Integer.parseInt(yyyyMMddHHmmss.substring(12, 14));
        }
        cal.set(13, i);
        return cal;
    }
    
    public static String formatCommaString(String numStr, String format)
    {
        try
        {
            numStr = new DecimalFormat(format).format(Double.parseDouble(numStr));
        }
        catch (Exception e)
        {
        }
        return numStr;
    }
    
    public static String formatDateString(String dateStr, String format)
    {
        try
        {
            dateStr = formatDate(format, makeCalendar(dateStr));
        }
        catch (Exception e)
        {
        }
        return dateStr;
    }
    
    public static void main(String[] args)
    {
        System.out.println(formatCommaString("33432432432421321", "###,###"));
        System.out.println(formatDateString("20060101", "yyyy.MM.dd"));
        System.out.println(getLastYmdOfMonth());
        System.out.println(makeCalendar("20061205230507"));
        System.out.println(formatComma(1.23456789E8d, "#####,##"));
        System.out.println(formatCommaScale(12345.6789d, 20));
        System.out.println(makeCalendar("20100101121010"));
    }
    
}
