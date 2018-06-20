package br.com.TamCargo;


import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
//	static final String EXTRA_MESSAGE = "br.com.TamCargo.MainActivity";
	Button btButton1;
	String linkUrl=null;
	TextView tvAwb,tvPeso,tvVolume,tvOrigem,tvDestino,tvProduto,tvEmissao,tvStatus;
	private EditText etCodIata,etSequencia,etAwb,etDigito;

	//ConexaoMain mThread;
	//ProgressDialog mDialog;
	//static Handler handler;

	private String iata = null ,sequencia = null ,awb = null, digito = null;
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);

		btButton1 = (Button) findViewById(R.id.button1);
		btButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { //ao cliclar no botão chama a função consulta

				etCodIata = (EditText) findViewById(R.id.etCodIata);
				iata = etCodIata.getText().toString();		

				etSequencia = (EditText) findViewById(R.id.etSequencia);
				sequencia = etSequencia.getText().toString();

				etAwb = (EditText) findViewById(R.id.etAwb);
				awb = etAwb.getText().toString();

				etDigito = (EditText) findViewById(R.id.etDigito);
				digito = etDigito.getText().toString();		

				if(verificaConexao()){

					Consulta consulta = new Consulta(iata, sequencia, awb, digito);
					consulta.SetLink();

					if(consulta.verificaCampos()){

						Toast msgCampos = Toast.makeText(getApplicationContext(), "Consultando", Toast.LENGTH_LONG);
						msgCampos.show();
						linkUrl=consulta.getLink();
						System.out.println("recebi "+linkUrl);
						//chamaTelaResultado();
					
		//				ativaThread();
						
				/*		Message messageLinkUrl = new Message();
						messageLinkUrl.what=99;
						messageLinkUrl.obj=linkUrl;
						Handler handlerLink = new Handler();
						handlerLink.sendMessage(messageLinkUrl);
					*/
						chamaTelaResultado();
						//setContentView(R.layout.activity_resultado);
						

					}
					
				}
			}
		});
				
	}
	private void chamaTelaResultado(){
		Intent it = new Intent(this, ConsultaActivity.class);
		Bundle parametros = new Bundle();
		parametros.putString("chave", linkUrl);
		it.putExtras(parametros);
		startActivity(it);

	}
/*	public void enviaMenssagem(View view) {
		// Do something in response to button

		String teste= "test";
		Intent intencao = new Intent(this, Status.class);
		intencao.putExtra(EXTRA_MESSAGE, teste);
		startActivity(intencao);
	}*/
	/* Função para verificar existência de conexão com a internet
	 */
	public  boolean verificaConexao() {
		boolean conectado;
		ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectivtyManager.getActiveNetworkInfo() != null
				&& conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected()) {
			conectado = true;
		} else {
			conectado = false;
			Toast c = Toast.makeText(getApplicationContext(), "Ative sua conexão com a internet!", Toast.LENGTH_LONG);
			c.show();
		}
		return conectado;
	}

/*	private void ativaThread() {
		mDialog = ProgressDialog.show(this, "Aguarde", "Processando...", false, false);
		handler = new Handler(){
			@Override
			public void handleMessage(Message mensagem) {
				//chamo um método para melhor organização.
				//updateUI(mensagem);
			}
		};
		mThread = new ConexaoMain(linkUrl,handler);
		mThread.start();

	} */
/*
	private void updateUI(Message mensagem) {


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
			tvStatus.setText(Html.fromHtml("\n\n"+texto));

		}

		mDialog.cancel();
		System.out.println("inicio");
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
