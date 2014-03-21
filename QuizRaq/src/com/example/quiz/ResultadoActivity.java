package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends Activity {
	
	private TextView textResult;
	private String resp;
	int result;
	private Button button;
	public OnClickListener onClick= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent= new Intent(ResultadoActivity.this,MainActivity.class);
			startActivity(intent);
			
		}
	};
	
     private Button buttonSair;
	public OnClickListener onclickSair= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			// TODO Auto-generated method stub
			Intent intents = new Intent(Intent.ACTION_MAIN);
			intents.addCategory(Intent.CATEGORY_HOME);
			intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intents);	
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		result= this.getIntent().getIntExtra("pontuacao", result);
		resp= "Fim de jogo! Sua Pontuacao e "+ Integer.toString(result);
		textResult= (TextView) findViewById(R.id.textResult);
		textResult.setText(resp);
		
		button=(Button) findViewById(R.id.buttonJogNov);
		button.setOnClickListener(onClick);
		
		buttonSair= (Button) findViewById(R.id.buttonSair);
		buttonSair.setOnClickListener(onclickSair);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	
		
		
	}

}
