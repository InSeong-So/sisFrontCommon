public class XssFilter {
	
  // XSS필터 : 크로스 사이트 스크립트[Cross Site Scription]
	public String doReplaceXss(String value) {
		value = value.replaceAll("&amp;", "&");
		value = value.replaceAll("&#35;", "#");
		value = value.replaceAll("&#59;", ";");
		value = value.replaceAll("&#92;", "\\\\");
		value = value.replaceAll("&lt;" , "<");
		value = value.replaceAll("&gt;" , ">");
		value = value.replaceAll("&#40;", "(");
		value = value.replaceAll("&#41;", ")");
		value = value.replaceAll("&#39;", "'");
		value = value.replaceAll("&quot;", "\"");
		value = value.replaceAll("&#36;" , "\\$");
		value = value.replaceAll("&#42;" , "*");
		value = value.replaceAll("&#43;" , "+");
		value = value.replaceAll("&#124;", "|");
		value = value.replaceAll("&#46;" , "\\.");
		value = value.replaceAll("&#63;" , "\\?");
		value = value.replaceAll("&#91;" , "\\[");
		value = value.replaceAll("&#93;" , "\\]");
		value = value.replaceAll("&#94;" , "\\^");
		value = value.replaceAll("&#123;", "\\{");
		value = value.replaceAll("&#125;", "\\}");
		value = value.replaceAll("&#33;" , "!");
		value = value.replaceAll("&#37;" , "%");
		value = value.replaceAll("&#44;" , ",");
		value = value.replaceAll("&#45;" , "-");
		value = value.replaceAll("&#47;" , "/");
		value = value.replaceAll("&#58;" , ":");
		value = value.replaceAll("&#61;" , "=");
		value = value.replaceAll("&#64;" , "@");
		value = value.replaceAll("&#95;" , "_");
		value = value.replaceAll("&#96;" , "`");
		value = value.replaceAll("&#126;", "~");

		return value;
	}

	public String deReplaceXss(String value){
		value = value.replaceAll("&", "&amp;");
		value = value.replaceAll("#", "&#35;");
		value = value.replaceAll(";", "&#59;");
		value = value.replaceAll("\\\\", "&#92;");
		value = value.replaceAll("<" , "&lt;");
		value = value.replaceAll(">" , "&gt;");
		value = value.replaceAll("(", "&#40;");
		value = value.replaceAll(")", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll("\\$" , "&#36;");
		value = value.replaceAll("*" , "&#42;");
		value = value.replaceAll("+" , "&#43;");
		value = value.replaceAll("|", "&#124;");
		value = value.replaceAll("\\." , "&#46;");
		value = value.replaceAll("\\?" , "&#63;");
		value = value.replaceAll("\\[" , "&#91;");
		value = value.replaceAll("\\]" , "&#93;");
		value = value.replaceAll("\\^" , "&#94;");
		value = value.replaceAll("\\{", "&#123;");
		value = value.replaceAll("\\}", "&#125;");
		value = value.replaceAll("!" , "&#33;");
		value = value.replaceAll("%" , "&#37;");
		value = value.replaceAll("," , "&#44;");
		value = value.replaceAll("-" , "&#45;");
		value = value.replaceAll("/" , "&#47;");
		value = value.replaceAll(":" , "&#58;");
		value = value.replaceAll("=" , "&#61;");
		value = value.replaceAll("@" , "&#64;");
		value = value.replaceAll("_" , "&#95;");
		value = value.replaceAll("`", "&#96;");
		value = value.replaceAll("~", "&#126;");

		return value;
	}
}