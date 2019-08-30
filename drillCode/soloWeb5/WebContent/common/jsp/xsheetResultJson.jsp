<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.lang.reflect.*"%>
<%@page import="org.apache.log4j.Logger, net.sf.json.JSONObject"%>

<%@include file="/common/jsp/header_nologin.jsp"%><%--
Program Name  : xsheetResultJson.jsp
Description   : 범용 조회 Json 구성
Author        : 
History       : 
--%>
<%
HashMap etcMap = null;
HashMap ursmap = null;
String msg = null;
MemResultSet urs = null;
String S_PSN_INFO_AUTO_ENC_COLUMN_YN = StringUtil.nvl(commProp.getProperty("PSN_INFO_AUTO_ENC_COLUMN_YN"),"N");
String otpUseYn = StringUtil.nvl(commProp.getProperty("OTP_CHECK_USE_YN"),"N");
String _DSCLASS  = StringUtil.nvl(request.getParameter("S_DSCLASS"),"");
String _DSMETHOD = StringUtil.nvl(request.getParameter("S_DSMETHOD"),"");
ServerStartUtil serverStartUtil = new ServerStartUtil();

try
{
//  log.debug("xsheetReultJson.jsp ==== "+request.getAttribute("O_MSG"));
  etcMap = (HashMap)request.getAttribute("O_ETC");
  
  msg = StringUtil.nvl((String)request.getAttribute("O_MSG"));
  urs = (MemResultSet)request.getAttribute("O_RS");
  ursmap = (HashMap)request.getAttribute("O_RSMAP");
  if ( ursmap == null ) ursmap = new HashMap();
  if ( urs != null ) ursmap.put("Data", urs);
}
catch(Exception e)
{
  e.printStackTrace();
  msg = e.toString();
}

if ( etcMap == null ) etcMap = new HashMap();

Iterator etcIter = etcMap.keySet().iterator();
String etcKey, etcVal;
String dataKey, dataVal;

Map<String, Object> model = new HashMap<String, Object>();
ArrayList<Map<String, String>> Etc = new ArrayList<Map<String,String>>();

{
  ArrayList<Map<String, String>> Msg = new ArrayList<Map<String,String>>();
  Map<String, String> node = new HashMap<String, String>();
  node.put("Error",msg);
  Msg.add(node);
  model.put("Msg", Msg);
}

/*
while ( etcIter.hasNext() )
{
  Map<String, String> node = new HashMap<String, String>();

  etcKey = (String)etcIter.next();
  etcVal = StringUtil.nvl((String)etcMap.get(etcKey));

  node.put(etcKey,etcVal);
  Etc.add(node);
}

model.put("Etc", Etc);
*/

{
  Map<String, String> node = new HashMap<String, String>();
  while ( etcIter.hasNext() )
  {
    etcKey = (String)etcIter.next();
    etcVal = StringUtil.nvl((String)etcMap.get(etcKey));
    node.put(etcKey,etcVal);
  }
  if(node.size() > 0 ) Etc.add(node);

  model.put("Etc", Etc);
}



if ( ursmap != null )
{
  String key;
  Iterator iter = ursmap.keySet().iterator();
  while ( iter.hasNext() )
  {
    key = (String)iter.next();
    urs = (MemResultSet)ursmap.get(key);
    urs.beforeFirst();

    ArrayList<Map<String, String>> Data = new ArrayList<Map<String,String>>();

    int colLen = colLen = urs.getColumnCount();

    while ( urs.next() )
    {
      Map<String, String> node = new HashMap<String, String>();
      for ( int col = 1; col <= colLen; col++ )
      {
        dataKey = StringUtil.nvl(urs.getColumnName(col));
        if(dataKey.equals("ENC_VAL") || dataKey.equals("ENC_VAL2"))
        {
          dataVal = hunelCryptoUtil.getEncrypt3(StringUtil.nvl(urs.getString(col)),request);
        }
        else
        {
          if("Y".equals(S_PSN_INFO_AUTO_ENC_COLUMN_YN))
          {
              //자동암호화인경우
              
              if("Y".equals(otpUseYn))
                      {
                        if(!OtpEncrypt.checkNoOptAdmin(_DSCLASS, _DSMETHOD))
                        {
                          ArrayList<String> arrException = OtpEncrypt.getExceptList();
                          if( !(arrException.contains(_DSCLASS)))
                          {
                              _DSCLASS  = OtpEncrypt.getOtpValue(request, response, _DSCLASS);
                          }
                          if( !(arrException.contains(_DSMETHOD)))
                          {
                              _DSMETHOD = OtpEncrypt.getOtpValue(request, response, _DSMETHOD);
                          }
                        }
                      }
              
              boolean existEncColumn = serverStartUtil.getExistEncColMap(StringUtil.nvl(urs.getString(col), ""));
              boolean existExceEncColumn = serverStartUtil.getExistExceEncColMap(_DSCLASS+"."+_DSMETHOD+"."+StringUtil.nvl(urs.getString(col), ""));
      
              if(existEncColumn && !existExceEncColumn && !"".equals(StringUtil.nvl(urs.getString(col))))
              {
                dataVal = hunelCryptoUtil.getDecrypt(StringUtil.nvl(urs.getString(col)));
              }
              else
              {
                  dataVal = StringUtil.nvl(urs.getString(col));
              }
              
          }
          else
          {
            dataVal = StringUtil.nvl(urs.getString(col));
          }
        }

        node.put(dataKey,dataVal);
      }
      Data.add(node);
    }
    model.put(key, Data);
  }
}

JSONObject jsonObject = JSONObject.fromObject(model);

//System.out.println(jsonObject);   
//log.debug("Complete!");

%>
<%=jsonObject%>

