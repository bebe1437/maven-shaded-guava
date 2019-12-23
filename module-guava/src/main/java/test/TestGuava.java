package test;

import com.google.common.base.Objects;
import com.google.common.base.Utf8;


public class TestGuava {

    public static int hasCode(Object object){
        return Objects.hashCode(object);
    }

    public static int encodedLength(String str){
        return Utf8.encodedLength(str);
    }
}
