package enums;

public enum MUTATION {
	BUNNY(50, 2, "Bunny"), LION(50, 2, "Lion"), CHIM(100, 5, "Chim"), FANG(10000, 3, "Fangs");
	
	public final int spontaneous;
	public final int half;
	public final String phenotype;
	
	private MUTATION(int spontaneous, int half, String phenotype){
		this.spontaneous = spontaneous;
		this.half = half;
		this.phenotype = phenotype;
	}
	
	@Override
	public String toString(){
		return phenotype;
	}
}
