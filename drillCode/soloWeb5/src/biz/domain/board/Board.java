package biz.domain.board;

public class Board
{
    private int seq_no;
    
    private String title;
    
    private String content;
    
    private String writer;
    
    private int write_no;
    
    private String reg_date;
    
    private String mod_date;
    
    private String reg_ip;
    
    private String file_nm;
    
    private int view_cnt;
    
    public int getSeq_no()
    {
        return seq_no;
    }
    
    public void setSeq_no(int seq_no)
    {
        this.seq_no = seq_no;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getWriter()
    {
        return writer;
    }
    
    public void setWriter(String writer)
    {
        this.writer = writer;
    }
    
    public int getWrite_no()
    {
        return write_no;
    }
    
    public void setWrite_no(int write_no)
    {
        this.write_no = write_no;
    }
    
    public String getReg_date()
    {
        return reg_date;
    }
    
    public void setReg_date(String reg_date)
    {
        this.reg_date = reg_date;
    }
    
    public String getMod_date()
    {
        return mod_date;
    }
    
    public void setMod_date(String mod_date)
    {
        this.mod_date = mod_date;
    }
    
    public String getReg_ip()
    {
        return reg_ip;
    }
    
    public void setReg_ip(String reg_ip)
    {
        this.reg_ip = reg_ip;
    }
    
    public int getView_cnt()
    {
        return view_cnt;
    }
    
    public void setView_cnt(int view_cnt)
    {
        this.view_cnt = view_cnt;
    }
    
    public String getFile_nm()
    {
        return file_nm;
    }
    
    public void setFile_nm(String file_nm)
    {
        this.file_nm = file_nm;
    }
}
