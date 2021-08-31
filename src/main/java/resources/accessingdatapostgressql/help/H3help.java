package resources.accessingdatapostgressql.help;

import com.uber.h3core.H3Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class H3help {
    public final H3Core h3 = H3Core.newInstance();
    private int res = 9;
    public H3help() throws IOException {}
    public List<Long> queryVal(long h) {
        List<Long> para = new ArrayList<>();
        if (res == 9) {
            this.res --;
            para.add(h);
            return para;
//            return "('/x" + h + "')";
        }
        this.res --;
        long par = h3.h3ToParent(h, res);
        List<Long> l = h3.h3ToChildren(par, 9);
        return l;
//        String temp = "(";
//        for (long i : l) {
//            temp += "'/x" + i+ "',";
//        }
//        this.res --;
//        return temp.substring(0, temp.length() - 1)+")";
    }
    public static byte[] longToBytes(long l) {
        byte[] result = new byte[Long.BYTES];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= Byte.SIZE;
        }
        return result;
    }
}
