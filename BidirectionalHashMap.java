import java.util.HashMap;
import java.util.LinkedList;

/**
 * Tabela bidireccional.
 */

public class BidirectionalHashMap<K, V> {

	private HashMap<K, V> hmOne;
	private HashMap<V, K> hmTwo;

	public BidirectionalHashMap() {
		hmOne = new HashMap<K, V>();
		hmTwo = new HashMap<V, K>();
	}

	// Delegacao.
	// -----------------------------------------------------------------------

	/**
	 * Dado um valor, obter a chave correspondente
	 * @param value - o valor
	 * @return K - a chave
	 */
	public K getKey(V value) {
		return hmTwo.get(value);
	}

	/**
	 * Dada uma chave, obter o valor correspondente
	 * @param key - a chave
	 * @return V - o valor
	 */
	public V getValue(K key) {
		return hmOne.get(key);
	}

	/**
	 * Adicionar um par chave-valor
	 * @param key - a chave
	 * @param value - o valor
	 */
	public void put(K key, V value) {
		hmOne.put(key, value);
		hmTwo.put(value, key);
	}

	/**
	 * Verificar se a tabela contem uma chave
	 * @param key -a chave
	 * @return true se contem a chave, false se nao contem.
	 */
	public boolean containsKey(K key) {
		return hmOne.containsKey(key);
	}

	/**
	 * verificar se a tabela contem um certo valor
	 * @param value - o valor
	 * @return true se contem o valor, false caso contrario.
	 */
	public boolean containsValue(V value){
		return hmTwo.containsKey(value);
	}

	/**
	 * Remover um par chave-valor, dada a chave.
	 * @param key - a chave.
	 * @return V - o valor previamente associado a chave.
	 */
	public V removeByKey(K key) {
		V resultado = hmOne.remove(key);
		hmTwo.remove(resultado);
		return resultado;
	}

	/**
	 * Remover um par chave-valor, dado o valor.
	 * @param value - o valor
	 * @return K - a chave associada ao valor antes da remocao do par.
	 */
	public K removeByValue(V value) {
		K resultado = hmTwo.remove(value);
		hmOne.remove(resultado);
		return resultado;
	}

	/**
	 * Retornar o tamanho da tabela (numero de pares chave-valor contidos).
	 * @return o tamanho da tabela.
	 */
	public int size() {
		return hmTwo.size();
	}

}
