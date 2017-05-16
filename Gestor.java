import java.util.HashMap;

public class Gestor {
	private int escaloes;
	private int nLugares;
	private int estrategia; 
	private int totalLugares;
	private BidirectionalHashMap<Lugar, Funcionario> lugaresOc;
	private HashMap<String, Funcionario> funcionarios;
	private int[] lugaresEscalao;
	private boolean[] lugares;

	public Gestor(int escaloes, int nLugares, int estrategiaAtribuicao) {
		this.escaloes = escaloes;
		this.nLugares = nLugares;
		this.estrategia = estrategiaAtribuicao;
		this.totalLugares = nLugares / escaloes;
		lugaresOc = new BidirectionalHashMap<Lugar, Funcionario>();
		funcionarios = new HashMap<String, Funcionario>();
		lugaresEscalao = new int[escaloes];
		lugares = new boolean[nLugares];
	}

	public int totalAtribuidos() {
		return lugaresOc.size();
	}

	public int atribuidosNoEscalao(int escalao) {
		return lugaresEscalao[escalao - 1];
	}

	public boolean registar(String nome, int escalao) {
		Funcionario f = funcionarios.get(nome);
		if(f == null){
			f = new Funcionario(nome, escalao);
			funcionarios.put(nome, f);
			return true;
		}
		return false;
	}

	public boolean atribuir(String nome){
		Funcionario f = funcionarios.get(nome);
		if(!funcionarios.containsKey(nome) || (totalAtribuidos() == nLugares) || (lugaresOc.containsValue(f)))
			return false;
		Lugar l = null;
		boolean done = false;
		boolean notAtribuido = false;
		int inicio = (escaloes - f.obterEscalao()) * (totalLugares) + 1;
		int fimEscalao = inicio - 1 + totalLugares;
		for(int i = inicio; i <= fimEscalao && !done; i++){
			if(lugares[i - 1] == false){
				l = new Lugar(i, f.obterEscalao());
				done = !lugaresOc.containsKey(l);
			}
		}
		
		if(!done)
			return false;
		
		lugares[l.obterNumero() - 1] = true;
		if(estrategia == 1){
			if(lugaresEscalao[f.obterEscalao() - 1] ==  totalLugares)
				return false;
			lugaresOc.put(l,  f);
			lugaresEscalao[f.obterEscalao() - 1]++;	
			return true;
		}else{
			int escalao = f.obterEscalao();
			if (!done) {
				for (int j = this.escaloes - 1; j > this.escaloes - f.obterEscalao() && !done; j--)
					for (int i = j * totalLugares; i < (j + 1) * totalLugares && !done; i++) {
						l = new Lugar(i, f.obterEscalao());
						done = !lugaresOc.containsKey(l);
					}
				escalao = escaloes - (l.obterNumero() / totalLugares);
				return false;
			}
			lugaresOc.put(l, f);
			lugaresEscalao[escalao - 1]++;

		}
		return false;
	}

	public Funcionario obterDono(int numero) {
		boolean done = false;
		Lugar l = null;
		for(int i = escaloes; i >= 1 && !done; i--){
			if(i / totalLugares == 1){ //tenho o num do lugar. como calc escalao?
				l = new Lugar(numero, i);
				done = true;
			}
		}
		return lugaresOc.getValue(l);
	}

	public int obterNumero(String nome){
		Funcionario f = funcionarios.get(nome);
		Lugar l = lugaresOc.getKey(f);
		if(l == null)
			return -1;
		else
			return l.obterNumero();
	}

	public Lugar removerAtribuicaoPorNome(String nome) {
		Funcionario f = funcionarios.get(nome);
		Lugar l = null;
		if(f != null){
			l = lugaresOc.removeByValue(f);
			l = new Lugar(l.obterNumero(), l.obterEscalao());
			lugaresEscalao[l.obterEscalao() - 1]--;
		}
		return l;
	}

	public Lugar removerAtribuicaoPorNumero(int numero) {
		Funcionario f = lugaresOc.getValue(new Lugar(numero - 1));
		Lugar l = null;
		if(f != null){
			l = lugaresOc.removeByValue(f);
			l = new Lugar(l.obterNumero(), l.obterEscalao());
			lugaresEscalao[l.obterEscalao() - 1]--;
		}
		return l;
	}
}
