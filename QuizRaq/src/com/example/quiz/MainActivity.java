package com.example.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button button;
	int cont=0;
	
	private OnClickListener onclick= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent= new Intent (MainActivity.this,SegundaActivity.class);
			int radioSelect= ((RadioGroup) findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();
			switch (radioSelect){
			case R.id.radio1:
				respostaErrada().show();
				break;
			case R.id.radio2:
				respostaCerta().show();
				cont++;
				break;
			case R.id.radio3:
				respostaErrada().show();
				break;
			case R.id.radio4:
				respostaErrada().show();
				break;
			
			}
			
			intent.putExtra("pontuacao", cont);
			startActivity(intent);
		}
	};
	
	public Toast respostaCerta(){
		Context context= getApplicationContext();
		CharSequence text= "Certa resposta!";
		int duration= Toast.LENGTH_SHORT;
		
		Toast toast=  Toast.makeText(context, text, duration);
		
		return toast;
		
	}
	
	public Toast respostaErrada(){
		Context context= getApplicationContext();
		CharSequence text= "Que pena!Você errou.";
		int duration= Toast.LENGTH_SHORT;
		
		Toast toast=  Toast.makeText(context, text, duration);
		
		return toast;
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button= (Button) findViewById(R.id.button1);
        button.setOnClickListener(onclick);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
