
public class Lugar {
	
	private int numLugar;
	private int escalao;
	
	public Lugar(int numLugar, int escalao){
		this.numLugar = numLugar;
		this.escalao = escalao;
	}
	
	public Lugar(int numLugar){
		this.numLugar = numLugar;
	}

	public int obterNumero() {
		return numLugar;
	}

	public int obterEscalao() {
		return escalao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + numLugar;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lugar other = (Lugar) obj;
		
		
		if (numLugar != other.numLugar)
			return false;
		return true;
	}
}
