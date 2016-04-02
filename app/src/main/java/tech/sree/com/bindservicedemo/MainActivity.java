package tech.sree.com.bindservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView editTextMsg;
    MyBinderServices myBinderServices;
    boolean isBinder =  false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMsg = (TextView)findViewById(R.id.editText11);

        Intent intent =  new Intent(this,MyBinderServices.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void getMsgFromFirst(View V){
        String Msg = myBinderServices.getFirstMessage();
        editTextMsg.setText(Msg);

    }
    public void getMsgFromSecond(View V){
        editTextMsg.setText(myBinderServices.getSecondMessage());
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

         MyBinderServices.LocalService  localService= (MyBinderServices.LocalService)service;
            myBinderServices = localService.getMyBinderServices();
            isBinder = true ;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
isBinder = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(isBinder){
            unbindService(serviceConnection);
            isBinder = false;
        }
    }
}
