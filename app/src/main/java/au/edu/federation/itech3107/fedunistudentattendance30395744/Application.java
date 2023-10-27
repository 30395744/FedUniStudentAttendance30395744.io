package au.edu.federation.itech3107.fedunistudentattendance30395744;

import org.litepal.LitePal;

/**
 * Application  初始化
 */
public class Application extends android.app.Application {
    public  static  String loginAccount="";
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
