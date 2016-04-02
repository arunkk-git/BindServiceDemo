package tech.sree.com.bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ananth on 4/2/2016.
 */
public class MyBinderServices extends Service {

    private IBinder mIBinder = (IBinder) new LocalService();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    public class LocalService extends Binder {

        MyBinderServices getMyBinderServices() {
            return MyBinderServices.this;
        }
    };
        public String getFirstMessage(){
            return "Hello From * First * Message";
        }
        public String getSecondMessage(){
            return "Hello From **SECOND**  Message";
        }


}
