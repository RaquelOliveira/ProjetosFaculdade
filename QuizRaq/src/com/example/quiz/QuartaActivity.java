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

public class QuartaActivity extends Activity {
	
	private Button button;
	int cont;
	
	public OnClickListener onclick= new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent= new Intent(QuartaActivity.this,ResultadoActivity.class);
			int radioSelect= ((RadioGroup)findViewById(R.id.radioGroup4)).getCheckedRadioButtonId();
			
			if(radioSelect==R.id.radio1Act4){
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
		setContentView(R.layout.activity_quarta);
		cont= this.getIntent().getIntExtra("pontuacao", 0);
		
		button= (Button) findViewById(R.id.button4);
		button.setOnClickListener(onclick);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quarta, menu);
		return true;
	}

}
