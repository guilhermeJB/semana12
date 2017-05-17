import java.util.HashMap;

public class Gestor {

	private int numEscaloes;
	private int numLugares;
	private int estrategia;
	private int maxLugaresPorEscalao;
	private int[] numLugaresNoEscalao;
	private BidirectionalHashMap<Lugar, Funcionario> map;
	private HashMap<String, Funcionario> funcionarios;


	public Gestor(int escaloes, int nLugares, int estrategiaAtribuicao) {
		this.numEscaloes = escaloes;
		this.numLugares = nLugares;
		this.estrategia = estrategiaAtribuicao;
		this.maxLugaresPorEscalao = nLugares / escaloes;
		map = new BidirectionalHashMap<Lugar, Funcionario>();
		funcionarios = new HashMap<String, Funcionario>();
		numLugaresNoEscalao = new int[escaloes];
	}

	/**
	 * total lugares atribuidos
	 * @return
	 */
	public int totalAtribuidos() {
		return map.size();
	}

	/**
	 * Calcula o total de lugares atribuidos de um dado escalao
	 * @param escalao
	 * @return
	 */
	public int atribuidosNoEscalao(int escalao) {
		return numLugaresNoEscalao[escalao - 1];
	}

	/**
	 * regista um funcionario
	 * @param nome
	 * @param escalao
	 * @return
	 */
	public boolean registar(String nome, int escalao) {
		if(funcionarios.get(nome) == null){
			Funcionario func = new Funcionario(nome, escalao);
			funcionarios.put(nome,  func);
			return true;
		}
		return false;
	}

	/**
	 * atribui um lugar ao funcionario com o nome dado
	 * @param nome
	 * @return
	 */
	public boolean atribuir(String nome) {
		Funcionario func = funcionarios.get(nome);
		if(func == null || (totalAtribuidos() == numLugares) ||
				map.containsValue(func))
			return false;
		
		Lugar l = null;
		boolean done = false;
		int inicioLugares = (numEscaloes - func.obterEscalao())
				* maxLugaresPorEscalao;
		int fimLugares = inicioLugares + maxLugaresPorEscalao;
		
		for(int i = inicioLugares; i < fimLugares && !done; i++){
			l = new Lugar(i, func.obterEscalao());
			done = !map.containsKey(l);
		}		
		if(estrategia == 1){
			if(numLugaresNoEscalao[func.obterEscalao() - 1] == maxLugaresPorEscalao)
				return false;
			map.put(l, func);
			numLugaresNoEscalao[func.obterEscalao() - 1]++;
		}else{
			int escalao = func.obterEscalao();
			if(!done){
				for(int j = numEscaloes - 1; j > numEscaloes - func.obterEscalao() && !done; j++)
					for(int i = j * maxLugaresPorEscalao; i < (j + 1) * maxLugaresPorEscalao && !done; i++){
						l = new Lugar(i, func.obterEscalao());
						done = !map.containsKey(l);
					}
					escalao = numEscaloes - (l.obterNumero() / maxLugaresPorEscalao);
					return false;
				}
			map.put(l,  func);
			numLugaresNoEscalao[escalao - 1]++;
			}
		return true;
	}


	/**
	 * determina o dono do lugar com o numero dado
	 * @param numero
	 * @return
	 */
	public Funcionario obterDono(int numero){
		return map.getValue(new Lugar(numero - 1));
	}


	/**
	 * determina o numero do lugar atribuido ao funcionario com o nome dado
	 * @param nome
	 * @return
	 */
	public int obterNumero(String nome) {
		Lugar l = map.getKey(funcionarios.get(nome));
		return l == null ? -1 : l.obterNumero() + 1;
	}

	/**
	 * remover uma atribuicao dado o nome do funcionario
	 * @param nome
	 * @return
	 */
	public Lugar removerAtribuicaoPorNome(String nome) {
		Funcionario func = funcionarios.get(nome);
		Lugar lugar = null;
		if(func != null){
			lugar = map.removeByValue(func);
			lugar = new Lugar(lugar.obterNumero() + 1, lugar.obterEscalao());
			numLugaresNoEscalao[lugar.obterEscalao() - 1]--;
		}
		return lugar;
	}

	/**
	 * remover uma atribuicao dado o numero de um lugar
	 * @param numero
	 * @return
	 */
	public Lugar removerAtribuicaoPorNumero(int numero) {
		Funcionario func = map.getValue(new Lugar(numero - 1));
		Lugar lugar = null;
		if(func != null){
			lugar = map.removeByValue(func);
			lugar = new Lugar(lugar.obterNumero() + 1, lugar.obterEscalao());
			numLugaresNoEscalao[lugar.obterEscalao() - 1]--;
		}
		return lugar;
	}
}
