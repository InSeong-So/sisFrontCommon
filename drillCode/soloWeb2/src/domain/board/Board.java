package domain.board;

public class Board
{
    private int seq_no;
    
    private String title;
    
    private String content;
    
    private String writer;
    
    private String reg_date;
    
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
    
    public String getReg_date()
    {
        return reg_date;
    }
    
    public void setReg_date(String reg_date)
    {
        this.reg_date = reg_date;
    }
    
    public int getView_cnt()
    {
        return view_cnt;
    }
    
    public void setView_cnt(int view_cnt)
    {
        this.view_cnt = view_cnt;
    }
    
}
