package ed.tad;

public class Vetor {
	
	private final int tamanho;
	private final int minimo;
	private final int maximo;
	private final int vaga;
	private final int repete; //0 = não repete; 1 = pode repetir
	private int[] dados;
	
	public Vetor(int tamanho, int minimo, int maximo, int vaga, int repete) {
		this.tamanho = tamanho;
		this.minimo = minimo;
		this.maximo = maximo;
		this.vaga = vaga;
		this.repete = repete;
		
		this.dados = new int[this.tamanho];
		//todo: pensar em um algoritmo para pré preencher o vetor
	} 

	
}
