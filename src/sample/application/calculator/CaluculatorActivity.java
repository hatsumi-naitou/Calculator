package sample.application.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CaluculatorActivity extends Activity {
	
	public String num1 = new String();
	public String strTemp = "";
	public String strResult = "0";
	public Integer operator = 0;
	
	/*CaluculatorActivityのインスタンスを作るタイミングは、
	 * アンドロイドの仕様でマニフェストアクティビティの中にある、メインが実行されて
	 * onCreateが実行される。その結果、インスタンスがぽこっとできる。
	 * このプログラムでいうならばsuper.onCreateが実行され、setContenViewされるということ。
	*/
	
	//CaluculatorActivity num1, num2;   //クラス定義の中にCaluculatorActivity型の変数を定義する 


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caluculator);    //R.layoutの中のactivity_caluculatorをレイアウトとしてもってきますよ。
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_caluculator, menu);
        return true;
    }
    
/*    public void numKeyOnClick(View v){
    	//
    	// いろいろ考えてみた。
    	// 
    	// Button button = (Button)v;
    	//Button型を使いたいのでキャストを使う
    										//みているインスタンスは同じ。型を変えただけ。
    	Log.d("[buttonのtext]", button.getText().toString()); //とりあえず何か表示させたいよ、というとき。

    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);
    	tv.
    	//String st = tv.getText().toString();
    	//tv.setText(st);            //7を取り出す方法。表示領域に入れるという方法。
    	//et.setSelection(st);
    	//String stt = (String)((button)v).getText(); 
    	// なかなか正解にたどり着きません。
    	
    	//正解はこちら、、、
    	
    	//v.getText();   ←getTextを使いたのでドットでつなげてみたけど、getTextはViewクラスにはないというエラー。
    	Button button = (Button)v;     //button型で使いたい。キャストしよう。
    	Log.d("[buttonのtext]", button.getText().toString());  //buttonについてgetText（テキストを引っ張ってきたもの）をtoString（ストリング型に）する。
    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);   //（R.id.displayPnel）はTextView型だが、findViewByIdメソッドとしてはView型でかえってくるため、TextViewにキャストしてからtvへ代入。
    	tv.setText(tv.getText().toString() + button.getText().toString());    	//get~ゲッター　何か値を持ってくる。 set~セッター　フィールドに何か値を持たせる　という命名の慣習がある。
    	}*/
    
   public void numKeyOnClick(View v){    //ここはそもそもButtonじゃだめなのか？→メソッド名の指定をしているだけで引数の指定はしてない。andoroidはひとまず何でも持ってくる。それを抽象化されているのがViewクラス。なので。
	   String strInKey = (String) ((Button)v).getText();   //CharSequenceじゃ？　そうだけど、でも、Stringを継承しているので。
	   
	   if(strInKey.equals(".")){    //"."はnewしてインスタンスを作っている。これは書き方がよくない！これは、".".equals(strInKey)と一緒。nullポインタエクセプションで悩まなくていい。
		   	if(this.strTemp.length() == 0){		//押されたものがどっとで、入っている文字がないならば、
		   		this.strTemp = "0.";	//0から始まる小数点の数値の開始。
		   	}else{
		   		if(this.strTemp.indexOf(".") == -1){
		   			this.strTemp = this.strTemp + ".";	//押されたものがドットで、数字が入っているならば
		   		}
		   	}
	   }else{
		   this.strTemp = this.strTemp + strInKey;	//thisの中には、CaluculatorActivityのアドレスが入っている。インスタンスがある。
	   }											//newされているのは、マニュフェストアクティビティ→メインメソッド→インスタンスがぽこっとできる
	   
	   //TODO　インスタンス変数わたしてます
	   this.showNumber(this.strTemp);		//このshowNumberメソッドのおかしいところは？ → ？？
   }  
   
   
   private void showNumber(String strNum){		//自分のインスタンスの中でだけprivateは使えるため、インスタンスの外からは呼び出せない。onClickに使ったりすることはできない。
	   											//少し例外があるが、基本的には自分のインスタンスでしか使えない。
	   DecimalFormat form = new DecimalFormat("#,##0");    //この形で整形します（三桁ごとにカンマをつけます）頭に０がついていても見えないですよ。
	   String strDecimal = "";
	   String strInt = "";
	   String fText = "";
	   
	   if(strNum.length() > 0){
		   Integer decimalPoint = strNum.indexOf(".");
		   if(decimalPoint > -1){
			   strDecimal = strNum.substring(decimalPoint);
			   strInt = strNum.substring(0,decimalPoint);
		   }else{
			   strInt = strNum;
		   }
		   fText = form.format(Double.parseDouble(strInt)) + strDecimal;
	   }else fText = "0";

		((TextView)findViewById(R.id.displayPanel)).setText(fText);
   }
   
   
   public void operatorKeyOnClick(View v){
	   
	   if(operator != 0){
		   if(this.strTemp.length() > 0){
			   this.strResult = this.doCalc();
			   this.showNumber(this.strResult);
		   }
	   }else {
		   if(this.strTemp.length() > 0){
			   this.strResult = this.strTemp;
		   }
	   }
	   
	   this.strTemp = "";
	   
	   if(v.getId() == R.id.keypadEq){
		   this.operator = 0;					//イコールのときは、次に演算が行われることはないため、ゼロを代入しておく。
	   }else{
		   this.operator = v.getId();
	   }
   }
   
   private String doCalc(){
	   BigDecimal bd1 = new BigDecimal(this.strResult);
	   BigDecimal bd2 = new BigDecimal(this.strTemp);
	   BigDecimal result = BigDecimal.ZERO;					//BigDecimal型に定義されているクラス変数ZERO。
	   														//public
	   
	   switch(this.operator){
	   case R.id.keypadAdd:
		   result = bd1.add(bd2);
		   break;
	   case R.id.keypadSub:
		   result = bd1.subtract(bd2);
		   break;
	   case R.id.keypadMulti:
		   result = bd1.multiply(bd2);
		   break;
	   case R.id.keypadDiv:
		   if(!bd2.equals(BigDecimal.ZERO)){
			   result = bd1.divide(bd2, 12, 3);
		   }else{
			   Toast toast = Toast.makeText(this, R.string.toast_div_by_zero, 1000);
			   toast.show();
		   }
		   break;
	   }
	   if(result.toString().indexOf(".") >= 0){
		   return result.toString().replaceAll("¥¥.0+$|0+$","");
	   }else{
		   return result.toString();
	   }
	   
	   
   }
   
   
    //では、加算するときにはどうしたらよいか？　と、考えてみた。
    /*私の表記はこちら。
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
	*/
    /*先生の表記はこちら。*/
	    public void addKeyOnClick(View v){
	    	Log.d("[addKeyが呼ばれた確認]", "てすと");
	    	//String num1 = null; //表示されている数字の保存領域
	    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);
	    	Log.d("[tvのインスタンスか確認]", "tv.text: " + tv.getText().toString());
	    	Log.d(this.num1, "");   //インスタンスのドットで呼び出されているからthis。
	    	this.num1 = tv.getText().toString(); 
	    	tv.setText("0");
	    }
	    
	    /* 中で自分を呼び出そうとするとき、仮に出してくる変数がない。ないとこまる、ので、
	     * thisを使って、自分自身を呼び出す。だから、クラスは自分自身をさす。
	     * 　　　thisとはそういうこと！　慣れて！！ */
	    
	    
	 /*イコールについても表記をしとこう。*/
	    
	    public void equalKeyOnClick(View v){
	    	Log.d("[equalkeyが呼ばれたか確認]", "てすと");
	    	Log.d("[equalkeyでのnum1]", num1);
	    	
	    	//新しく表示された数字を取得

	    	
	    	//num1に保存した最初の数字を取得
	    	//上二つを足す
	    }

	    
}
