import com.sun.jna.*;

public class Add {
    public interface CAdd extends Library {
        CAdd INSTANCE = (CAdd) Native.loadLibrary(("DebugTest"), CAdd.class);
        int add(int a, int b);
    }

    public static void test() {
        System.out.println(CAdd.INSTANCE.add(1, 2));
    }
}
