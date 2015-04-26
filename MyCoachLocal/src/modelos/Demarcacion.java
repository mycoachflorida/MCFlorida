package modelos;

import controladores.IdiomaController;

	
public class Demarcacion {
	
	private String index;
	
	public Demarcacion(String indexDem){
		index=indexDem;
	}
	
	public String toString(){
		return IdiomaController.getInstance("es").getTraduccion(this.index);
	}
	
	public String getIndex() {
		return index;
	}

}
