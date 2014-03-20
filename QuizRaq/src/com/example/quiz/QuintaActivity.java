package com.example.quiz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuintaActivity extends Activity {
	
	private Button button;
	private int cont;
	
	public OnClickListener onclick= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent= new Intent(QuintaActivity.this, ResultadoActivity.class);
			int radioSelect= ((RadioGroup) findViewById(R.id.radioGroup5)).getCheckedRadioButtonId();
			if(radioSelect==R.id.radioCerto){
				respostaCorreta().show();
				cont++;
			}else{
				respostErrada().show();
			}
			
			intent.putExtra("pontuacao", cont);
			startActivity(intent);
		}
			

	};
	
	public Toast respostaCorreta(){
		Context context= getApplicationContext();
		CharSequence text="Certa resposta!";
		int duration= Toast.LENGTH_SHORT;
		
		Toast toast= Toast.makeText(context, text, duration);
		return toast;
	}
	
	public Toast  respostErrada(){
		Context context= getApplicationContext();
		CharSequence text= "Que pena! Você errou";
		int duration= Toast.LENGTH_SHORT;
		
		Toast toast= Toast.makeText(context, text, duration);
		return toast;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quinta);
         cont= this.getIntent().getIntExtra("pontuacao", cont);
		
		button= (Button) findViewById(R.id.button5);
		button.setOnClickListener(onclick);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quinta, menu);
		return true;
	}

}
