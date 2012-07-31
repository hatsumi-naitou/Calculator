package sample.application.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaluculatorActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caluculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_caluculator, menu);
        return true;
    }
    
    public void numKeyOnClick(View v){
    	Button button = (Button)v;          //Button型を使いたいのでキャストを使う
    										//みているインスタンスは同じ。型を変えただけ。
    	Log.d("[buttonのtext]", button.getText().toString()); //とりあえず何か表示させたいよ、というとき。

    	/*TextView et = (TextView)this.findViewById("@+id/displayPanel");
    	SharedPreferences pref = this.
    	et.setText(button.text);            //7を取り出す方法。表示領域に入れるという方法。*/
    }
}
