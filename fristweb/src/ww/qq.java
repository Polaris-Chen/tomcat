package ww;



import java.io.*;
import java.nio.file.Path;

public class qq {


    public void Sss() throws IOError, IOException {
        File file=new File("fristweb/web/image/12.txt");
        System.out.println(file);
        file.createNewFile();
        String ss=file.getAbsolutePath();
        System.out.println(ss);




    }

    public static void main(String[] args) throws IOException {
        qq a=new qq();
        a.Sss();
    }

}
