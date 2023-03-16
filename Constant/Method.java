package Constant;

public enum Method{
    CHECK(0),
    DOWNLOAD(1),
    ADD(2),
    REMOVE(3);
    int abbrev;
    Method(int a){
        abbrev = a;
    }
    public int getAbbrev(){
        return abbrev;
    }
}