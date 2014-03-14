package com.example.quiz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.TextureView;
import android.widget.TextView;

public class ResultadoActivity extends Activity {
	
	private TextView textResult;
	private String resp;
	int result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		result= this.getIntent().getIntExtra("pontuacao", 0);
		resp= "Fim de jogo! Sua Pontuacao e "+ Integer.toString(result);
		textResult= (TextView) findViewById(R.id.textResult);
		textResult.setText(resp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	
		
		
	}

}
