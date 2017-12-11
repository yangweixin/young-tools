package top.young.tools;

import org.apache.commons.lang3.StringUtils;
import top.young.tools.entity.CommandEnum;
import top.young.tools.service.DecryptService;
import top.young.tools.util.EnDeCryptUtil;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0 || (args.length == 1 && "-h".equals(args[0])) ){
            printHelpInfo();
        }else{
            String command = args[0];
            if(command.equals(CommandEnum.enc.name())){
                if(args.length < 2 || StringUtils.isEmpty(args[1])){
                    System.out.println("at least 1 param is required.");
                    return ;
                }
                System.out.println("encrypt output: "+ EnDeCryptUtil.encString(args[1]));
            }
            else if(command.equals(CommandEnum.dec.name())){
                DecryptService decryptService = new DecryptService();
                decryptService.doDecrypt(args);
            }else{
                printHelpInfo();
            }
        }
    }

    private static void  printHelpInfo(){
        System.out.println("please choose a command as the first param from below: ");
        for(CommandEnum e : CommandEnum.values()){
            System.out.println(e);
        }
    }
}
