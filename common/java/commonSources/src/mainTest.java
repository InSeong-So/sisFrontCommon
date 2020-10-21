import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class mainTest
{
    public static void main(String[] args)
    {
        parseXml(loadXml("/Users/soinseong/Documents/workspace/python/siscrawl/venv/src2/restaurant.xml"));
    }
    
    private static void parseXml(String xml)
    {
        Document doc = Jsoup.parse(xml);
        StringBuilder queryBuilder;
        StringBuilder columnNames;
        StringBuilder values;
        
        for (Element row : doc.select("row"))
        {
            // Start the query   
            queryBuilder = new StringBuilder("insert into mr_restaruant(");
            columnNames = new StringBuilder();
            values = new StringBuilder();
            
            for (int x = 0; x < row.children().size(); x++)
            {
                // || x == 7
                // || x == 15
                if (x == 3 || x == 4 || x == 5 || x == 6)
                {
                    
                    // Append the column name and it's value 
                    columnNames.append("`" + row.children().get(x).tagName() + "`");
                    values.append("'"+row.children().get(x).text()+"'");
                    
                    if (x != row.children().size() - 1)
                    {
                        // If this is not the last item, append a comma
                        columnNames.append(",");
                        values.append(",");
                    }
                    else
                    {
                        // Otherwise, add the closing paranthesis
                        columnNames.append(")");
                        values.append(")");
                    }
                }
                else
                {
                    if (x == row.children().size() - 1)
                    {
                        columnNames.append(")");
                        values.append(")");
                    }
                    else
                    {
                        continue;
                    }
                    
                }
            }
            
            // Add the column names and values to the query
            queryBuilder.append(columnNames);
            queryBuilder.append(" values(");
            queryBuilder.append(values);
            
            // Print the query
            System.out.println(queryBuilder);
        }
    }
    
    private static String loadXml(String filePath)
    {
        try
        {
            StringBuilder xml = new StringBuilder();
            Scanner scanner = new Scanner(new File(filePath)); // i.e. C:\\testXML.xml
            
            while (scanner.hasNextLine())
            {
                xml.append(scanner.nextLine());
            }
            return xml.toString();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
