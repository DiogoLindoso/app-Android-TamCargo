package br.com.TamCargo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.*;
import android.os.Handler;
import android.os.Message;
import android.text.style.AlignmentSpan.Standard;


class ConexaoMain  extends Thread  {
	private String status;
	private String enderecoWeb;
	private Handler handler;


	public ConexaoMain(String linkUrl, Handler handler2) {
		enderecoWeb = linkUrl;
		this.handler=handler2;
	}
	public void run() {

		processarDados(enderecoWeb);

	}

	private void processarDados(String enderecoWeb) {

		try {
			System.out.println("endere�oweb=  "+enderecoWeb);
			// URL url = new URL(urlNome);

			URL urlNome = new URL(enderecoWeb);
			System.out.println("OBJETO URL"+urlNome);
			HttpURLConnection urlConnection = (HttpURLConnection) urlNome.openConnection();
			//BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(urlNome.openStream(),"ISO-8859-1"));
			String linha = in.readLine();	
			int i=0;
			linha.getBytes("UTF-8");

			System.out.println("iniciando a pesquisa");         

			while( (linha=in.readLine()) != null ){
				//express�es de pesquisa
				Matcher pesquisa = Pattern.compile("(957.[0-9]{4}.[0-9]{7}-[0-9]).*Emiss.o</span>&nbsp;(\\s\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}).*Volumes</span>&nbsp;\\s(\\d{1,}).*Produto</span>&nbsp;(.*)</td>.*Origem</span>&nbsp;\\s(\\w{3}\\s/\\s.*)</td><td><span.*Destino</span>&nbsp;\\s(\\w{3}\\s/\\s.*)</td><td><span.*Volume\\(s\\)</span>&nbsp;\\s(\\d*\\.\\d*)&nbsp;.*Recebido por</span></td></tr>(.*)</td><td></td></tr></table>").matcher(linha);

				i++;
				pesquisa.find();

				if( i==85){
					status=pesquisa.group(8);
					status=status.replaceAll("<tr style=\'background:#E9E9E9\'>|<tr style=\'background:#D9D9D9\'>", "\n ");
					status=status.replaceAll("<td>|&nbsp;|</tr>", "");
					status=status.replaceAll("</td>", "  ");
					//status=status.replaceAll("�", "a");

					int j=0;
					for(j=1;j<=7;j++){
						Message mensagem = new Message();
						mensagem.what=j;
						mensagem.obj=pesquisa.group(j).toString();
						handler.sendMessage(mensagem);
					System.out.println(pesquisa.group(j).toString());
					}
					Message mensagem = new Message();
					mensagem.what=8;
					mensagem.obj=this.status;
					handler.sendMessageDelayed(mensagem,3000);
					System.out.println(pesquisa.group(j).toString());
					
				}

			}
			in.close();
			urlConnection.disconnect();
System.out.println("Fim");

		} 
		catch (MalformedURLException e){
			System.out.println("Erro ao criar URL. Formato inv�lido.");
			System.exit(1);
		} catch (IOException e2) {
			System.out.println("Erro ao acessar URL.");
			System.exit(1);
		}

	}

}




