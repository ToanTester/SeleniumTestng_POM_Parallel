package anhtester.com.helpers;

import java.io.File;

public class SystemHelpers {
    //Hàm lấy đường dẫn tuyệt đối của Project
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}
