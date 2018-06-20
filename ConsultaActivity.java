package br.com.TamCargo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;



public class ConsultaActivity extends Activity {
	TextView tvAwb,tvPeso,tvVolume,tvOrigem,tvDestino,tvProduto,tvEmissao,tvStatus;
	Message mensagem,messageLinkUrl;
	
	String linkUrl=null;
	

	ConexaoMain mThread;
	ProgressDialog mDialog;
//	static Handler handler;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent it = 	getIntent();
		Bundle parametros = it.getExtras();
		linkUrl=parametros.getString("chave");
			
		ativaThread();
		
		
	}	

	private void ativaThread() {
		mDialog = ProgressDialog.show(this, "Aguarde", "Processando...", false, false);
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message mensagem) {
				//chamo um método para melhor organização.
				atualizaInterface(mensagem);
			}
		};
		mThread = new ConexaoMain(linkUrl,handler);
		mThread.start();

	}
	public void atualizaInterface (Message mensagem){
		if (mensagem.what == 1) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvAwb = (TextView) findViewById(R.id.tvAwb);
			tvAwb.setText("AWB: "+texto);

		}
		if (mensagem.what == 2) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvEmissao = (TextView) findViewById(R.id.tvEmissao);
			tvEmissao.setText("Emissão: "+texto);

		}
		if (mensagem.what == 3) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvVolume = (TextView) findViewById(R.id.tvVolume);
			tvVolume.setText("Vol: "+texto);

		}
		if (mensagem.what == 4) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvProduto = (TextView) findViewById(R.id.tvProduto);
			tvProduto.setText("Produto: "+texto);

		}
		if (mensagem.what == 5) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvOrigem = (TextView) findViewById(R.id.tvOrigem);
			tvOrigem.setText("Origem: "+texto);

		}
		if (mensagem.what == 6) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvDestino = (TextView) findViewById(R.id.tvDestino);
			tvDestino.setText("Destino: "+texto);

		}
		if (mensagem.what == 7) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvPeso = (TextView) findViewById(R.id.tvPeso);
			tvPeso.setText("Peso "+texto+"Kg");

		}
		if (mensagem.what == 8) {
			//Converto o object para string (pois foi o que eu passei)
			String texto = (String) mensagem.obj;
			//defino no meu TextView o texto.
			tvStatus = (TextView) findViewById(R.id.tvStatus);
			tvStatus.setText("\n\n"+texto);
			
		}

		mDialog.cancel();
	}

	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_resultado, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
