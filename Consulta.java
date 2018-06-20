package br.com.TamCargo;


public class Consulta extends MainActivity {
	private String url="http://www.tamcargo.com.br/ste/jsp/consultaHistoricoAWBAction.jhtml?hdnConsulta=AWB&listaPesquisaAWB=";
	private int numSequencia=-1,numAwb=-1,numDigito=-1;
	private String strIata=null,strSequencia=null,strAwb=null,strDigito=null;
	private Boolean camposPrenchidos=false;
	public Consulta()
	{

	}
	public Consulta(String iata, String sequencia, String awb, String digito)	
	{	
		if(((iata != "957") && (!sequencia.isEmpty())) && (!awb.isEmpty() && !digito.isEmpty())){
			this.numSequencia=Integer.parseInt(sequencia);		
			this.numAwb=Integer.parseInt(awb);
			this.numDigito=Integer.parseInt(digito);
			this.strIata=iata;
			this.strSequencia=sequencia;
			this.strAwb=awb;
			this.strDigito=digito;
		}
	}

	public void SetLink(){ //concatena o valores digitados pelo usuário e cria um endereço url

		//teste link com awb 957 6000 504607 5		
		if(strAwb!=null && strDigito!=null && strIata!=null && strSequencia!=null){
			camposPrenchidos=true;
			int aux = ((numSequencia + numAwb)%7)-1; //formula que encontra o digito verificador

			if ( aux != numDigito){ //testa se o digito informado pelo usuário é válido
				//	Toast toast = Toast.makeText(getApplicationContext(),"AWB Inválida",Toast.LENGTH_SHORT);
				//	toast.show();
				/*AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setMessage("AWB inválida");
				alerta.setTitle("Atenção");
				alerta.setNeutralButton("OK",null);
				alerta.show();
				 */}else{
					 System.out.println("awb valida");
					 url = url + strIata +";"+ strSequencia +";"+ strAwb +";"+ strDigito;
					 System.out.println("enviando: "+url);

				 }
		}else{
			//Toast msgCampos = Toast.makeText(getApplicationContext(), "Todos os campos devem sem preenchidos", Toast.LENGTH_LONG);
			//msgCampos.show();
		}

	}

	public String getLink(){
		return url;
	}
	public Boolean verificaCampos(){

		return camposPrenchidos;
	}

}
