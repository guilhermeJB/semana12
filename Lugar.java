
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
	
	public void setEscalao(int n){
		this.escalao = n;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + escalao;
		result = prime * result + numLugar;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Lugar))
			return false;
		Lugar other = (Lugar) obj;
		if (escalao != other.escalao)
			return false;
		if (numLugar != other.numLugar)
			return false;
		return true;
	}
	
	//implementar hashCode e equals
}
