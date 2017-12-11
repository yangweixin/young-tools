package top.young.tools.entity;

public enum CommandEnum {
    enc("enc", "encrypt for car or phone", 1),
    dec("dec", "decrypt for car or phone", 2);

    String name;
    String desc;
    int index;

    private CommandEnum(String name, String desc, int index){
        this.name = name;
        this.index = index;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return index + ", " +
                name + '\t' +
                desc ;
    }
}
