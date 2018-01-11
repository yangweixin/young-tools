package top.young.tools.service;

import org.apache.commons.lang3.StringUtils;
import top.young.tools.util.EnDeCryptUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DecryptService {
    public void doDecrypt(String[] params){
        if(params.length < 3){
            System.out.println("command param length is not enough,at least 2 required");
            return ;
        }
        String option = params[1];
        String path = params[2];
        switch (option){
            case "single":
                System.out.println("-----> After Decrpty= " + EnDeCryptUtil.decString(path));
                break;
            case "file":
                File file = new File(path);
                if( !file.exists() ){
                    System.out.println("file '"+ path +"' not exists");
                    return ;
                }
                decryptFromFile(file);
                break;
            default:
                System.out.println("option is not avaliable, select one from [single/file], followed by [string/path]");
        }
    }

    public void decryptFromFile(File in){

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {

            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(in));
            reader = new BufferedReader(streamReader);

            List<String> enList = new ArrayList<>();
            String tmpStr = "";
            while( (tmpStr = reader.readLine()) != null){
                enList.add(tmpStr);
            }

            List<String> deList = new ArrayList<>();
            for (String enStr : enList){
                deList.add(EnDeCryptUtil.decString(enStr));
            }

            File out = new File("out.txt");
            out.createNewFile();
            OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(out),"UTF-8");
            writer = new BufferedWriter(streamWriter);
            for (String tmp : deList){
                writer.write(tmp);
                writer.newLine();
            }

            writer.flush();
            System.out.println("\n----->descrpt finished! out put file ./out.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(reader != null)
                    reader.close();
                if(writer != null){
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String[] tmp = {"","","-single","5773958A5E04B7C187427EC6FED1036D"};
        new DecryptService().doDecrypt(tmp);
    }
}
