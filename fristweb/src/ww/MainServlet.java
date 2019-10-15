package ww;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

//问题在于两个目录并不相同!!!!!!
@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String url= request.getParameter("url");//把show.jsp传来的URL作为参数
        String name1= null;//调用抓取方法,返回小说名
        try {
            name1 = catchText(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("name",name1);//将name参数加入request属性
        request.getRequestDispatcher("download.jsp").forward(request, response);//转到download.jsp
    }


    /************************数据库相关的方法开始**************************/
    public void setDatabase(String name,String url) throws
            ClassNotFoundException, SQLException, FileNotFoundException {//把新的小说加入数据库

            Class.forName("com.mysql.jdbc.Driver");
            String name1="root";
            String password="31284679";
            String url1="jdbc:mysql://localhost:3306/Fdb";
            Connection connection=DriverManager.getConnection(url1,name1,password);
            if (connection!=null) System.out.println("链接成功");//连接数据库
            String sql="insert into novelSet(name,url,text)values(?,?,?)";
            PreparedStatement pa=connection.prepareStatement(sql);
            File file=new File(url);
            InputStream inputStream=new FileInputStream(file);
            pa.setString(1,name);
            pa.setString(2,url);
            pa.setAsciiStream(3,inputStream,file.length());
            int row=pa.executeUpdate();
            if (row>0)
                System.out.println("更新了"+row+"行数据");
            else System.out.println("失败");
            pa.close();
            connection.close();



    }

   public boolean findDatabase(String name) throws ClassNotFoundException, SQLException, IOException {
       Class.forName("com.mysql.jdbc.Driver");
       String name1="root";
       String password="31284679";
       String url1="jdbc:mysql://localhost:3306/Fdb";
       Connection connection=DriverManager.getConnection(url1,name1,password);
       if (connection!=null) System.out.println("链接成功");//连接数据库
       String sql="select text from novelSet where name=?";
       PreparedStatement pa=connection.prepareStatement(sql);//查询小说名
       pa.setString(1,name);
       ResultSet resultSet=pa.executeQuery();
        if (resultSet.next()){//如果查询的到数据，就创建一个新的txt文件把数据库里的数据导入这个txt
            String s=this.getClass().getResource("/").getPath();

            String pathname="/Users/chenzhenghui/IdeaProjects/test/fristweb/web/text/"+name+".txt";
            System.out.println("查询到已有数据");
            File file=new File(pathname);
            if (!file.exists())
                file.createNewFile();

            InputStream inputStream=resultSet.getAsciiStream("text");
            byte[] text=inputStream.readAllBytes();
            FileOutputStream outputStream=new FileOutputStream(file);
            outputStream.write(text);
            outputStream.close();
            connection.close();
            pa.close();
            return true;
        }
        else {//查不到就返回false
            connection.close();
            pa.close();
        return false;
        }
   }
/************************数据库相关的方法结束**************************/

    /************************辅助方法开始**************************/

    public static String findname(String starturl, String constent) throws IOException {
            Document test = Jsoup.connect(starturl).get();
            Elements xx=test.select("a[href="+constent+"]");
            String end="";
            for( Element element : xx ){//拿到小说名字
                end=element.text();
                break;


            }
            return end;
        }
     public static String findHref(String name,String starturl) throws IOException {

            Document test = Jsoup.connect(starturl).get();
            Elements xx=test.select("a[href~=^/cbook]");
            String end="";
            for( Element element : xx ){//拿到章节目录的超链接
                if (element.text().equals(name)){
                    end=element.attr("href");
                    break;
                }

            }
            return end;
        }
    /************************辅助方法结束**************************/

    /************************爬取主方法开始**************************/
    public String firstFindNovle(String name,String url,String end) throws IOException, SQLException, ClassNotFoundException {
        //try {
        String s=this.getServletContext().getRealPath("");

        //String pathname=s+"web/text/"+name+".txt";
            String pathname= "/Users/chenzhenghui/IdeaProjects/test/fristweb/web/text/"+name+".txt";
            File file = new File(pathname);

            file.createNewFile();

            Writer out = new FileWriter(file);

            while (true) {
                Document document=Jsoup.connect(url).get();
                String title=document.title();
                Elements elements=document.select("div[id=content]");
                for (Element element:elements){
                    String rawText=element.text()+"\n"+"\n";
                    String text=rawText.replace(" ","\n");
                    out.write(title);
                    out.write(text);
                }
                String next= "http://www.yuetutu.com"+findHref("下一章",url);

                if (next.equals(end)){

                    out.close();
                    break;
                }
                else url=next;
            }

            out.close();
            setDatabase(name,pathname);
            return name;
        //}
        //catch (Exception e){
            /*System.out.println("爬取出现了问题");
            return "错误";*/
        //}
    }
    /************************爬取主方法结束**************************/


    /************************真!主方法开始**************************/
        public  String catchText(String geturl) throws SQLException, IOException, ClassNotFoundException {
                String starturl = geturl;
                String constent = findHref("章节目录", starturl);
                String end = "http://www.yuetutu.com" + constent;
                String name=findname(starturl, constent);
                if (findDatabase(name))
                    return name;
                else {
                    System.out.println("新的小说");
                   return firstFindNovle(name,starturl,end);
            }


        }
    /************************真!主方法结束**************************/







    }
