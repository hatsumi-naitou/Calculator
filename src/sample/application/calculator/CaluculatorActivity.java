package sample.application.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaluculatorActivity extends Activity {
	
	int intadd1, intadd2;
	

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
    	/*
    	 * いろいろ考えてみた。
    	 * 
    	 * Button button = (Button)v;          //Button型を使いたいのでキャストを使う
    										//みているインスタンスは同じ。型を変えただけ。
    	Log.d("[buttonのtext]", button.getText().toString()); //とりあえず何か表示させたいよ、というとき。

    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);
    	tv.
    	//String st = tv.getText().toString();
    	//tv.setText(st);            //7を取り出す方法。表示領域に入れるという方法。
    	//et.setSelection(st);
    	//String stt = (String)((button)v).getText(); 
    	 * なかなか正解にたどり着きません。*/
    	
    	/*正解は、、、*/
    	//v.getText();   ←getTextを使いたのでドットでつなげてみたけど、getTextはViewクラスにはないというエラー。
    	Button button = (Button)v;     //button型で使いたい。キャストしよう。
    	Log.d("[buttonのtext]", button.getText().toString());  //buttonについてgetText（テキストを引っ張ってきたもの）をtoString（ストリング型に）する。
    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);   //（R.id.displayPnel）はTextView型だが、findViewByIdメソッドとしてはView型でかえってくるため、TextViewにキャストしてからtvへ代入。
    	/*ログを使って、TextView型のオブジェクトかどうかを確認してみる。*/
    	//Log.d("[tvのインスタンスかどうかを確認]", "tv.text: " + tv.getText().toString()); 
    	//get~ゲッター　何か値を持ってくる。 set~セッター　フィールドに何か値を持たせる　という命名の慣習がある。
    	tv.setText(tv.getText().toString() + button.getText().toString());
    	}
    
    //加算するときにはどうしたらよいか？
	    public void addKeyOnClick(View v){
	    	Button button = (Button)v;
	    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);
	    	String add = (tv.getText().toString());
	    	Integer intadd1 = Integer.parseInt(add);
	    	tv.setText("0");
	    	tv.setText(tv.getText().toString());
	    	Integer intadd2 = Integer.parseInt(tv.getText().toString());
	    	//tv.setText(intadd1 + intadd2);
	    }  
    			
}
