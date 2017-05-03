package comp4900.bcit.ca.smart_baby_feeding;

import android.app.Application;

import org.greenrobot.greendao.database.Database;


/**
 * Created by Pkao on 2017-05-02.
 */

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recipes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
