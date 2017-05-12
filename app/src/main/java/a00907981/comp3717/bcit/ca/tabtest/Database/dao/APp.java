package a00907981.comp3717.bcit.ca.tabtest.Database.dao;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoMaster;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;

/**
 * Created by Pkao on 2017-05-11.
 */

public class App extends Application{

    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "sbf-db-encrypted" : "sbf-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("diaperMagic") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
