package com.example.tess;


import java.io.File;

import com.googlecode.tesseract.android.TessBaseAPI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView text;
	TessBaseAPI baseApi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    
		Button bt=new Button(getBaseContext());
	    bt=(Button)findViewById(R.id.button1);
	    
	      text=new TextView(getBaseContext());
	      text=(TextView)findViewById(R.id.textView1);
	    
	      baseApi=new TessBaseAPI();
	      baseApi.init("/mnt/sdcard/tesseract/", "eng");
	      
	    bt.setOnClickListener(new OnClickListener() {
            @Override
        	 public void onClick(View sourse) {
           // text.setText("sb");
            	//设置要ocr的图片bitmap
            	baseApi.setImage(getDiskBitmap("/mnt/sdcard/mypic.bmp"));
            	//根据Init的语言，获得ocr后的字符串
            	String text1= baseApi.getUTF8Text();
            	text.setText(text1);
            	//释放bitmap
            	baseApi.clear();
            }
        }
         );
	}

	private Bitmap getDiskBitmap(String pathString)
	{
		Bitmap bitmap = null;
		try
		{
			File file = new File(pathString);
			if(file.exists())
			{
				bitmap = BitmapFactory.decodeFile(pathString);
				
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		
		return bitmap;
	}
}
