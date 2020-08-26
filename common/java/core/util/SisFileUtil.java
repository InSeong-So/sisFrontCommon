import java.io.File;

public class SisFileUtil
{
  static Logger log = Logger.getRootLogger();

  private String getImgDataUrlFromBufferedImage(File file, String CONTENT_TYPE) throws IOException
  {
    String rv = "data:"+CONTENT_TYPE+";base64,";
    FileInputStream fis = null;
    try
    {
      byte[] barr = new byte[(int) file.length()];
      fis = new FileInputStream(file);
      fis.read(barr, 0, barr.length-1);
      rv+= Base64.encodeBase64String(barr);
    }
    catch(Exception e)
    {
      // e.getMessage();
    }
    finally
    {
      if(fis != null)
      {
        fis.close();
      }
    }
    return rv;
  }

  private String getFileList(File file) throws IOException
  {
    // getFileList(new File('절대경로'));
    File[] fileList = file.listfiles();
    for(File f : fileList)
    {
      log.debug(f.getAbsolutePath());
      if(f.listFiles() != null)
      {
        getFileList(f);
      }
    }
  }
}
