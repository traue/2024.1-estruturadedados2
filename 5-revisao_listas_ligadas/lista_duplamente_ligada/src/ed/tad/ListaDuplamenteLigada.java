package ed.tad;

public class ListaDuplamenteLigada {

	private No inicio;
	private No ultimo;
	private int qtd;

	private void _printElem(No e) {
		System.out.println(e.getNome() + " | " + e.getValor());
	}

	private void _insereEmVazio(No e) {
		ultimo = e;
		inicio = e;
	}

	private No _removeUnico() {
		No e = inicio;
		inicio = null;
		ultimo = null;
		qtd = 0;
		return e;
	}

	public void insereInicio(No e) {

		if (isEmpty()) {
			_insereEmVazio(e);
		} else {
			e.setProximo(inicio);
			inicio.setAnterior(e);
			inicio = e;
		}
		qtd++;

	}

	public void insereUltimo(No e) {

		if (isEmpty()) {
			_insereEmVazio(e);
		} else {
			ultimo.setProximo(e);
			e.setAnterior(ultimo);
			ultimo = e;
		}
		qtd++;

	}

	public No removeInicio() {
		if (isEmpty()) {
			return null;
		}
		if (qtd == 1) {
			return _removeUnico();
		}
		No e = inicio;
		inicio = e.getProximo();
		inicio.setProximo(null);
		e.setProximo(null);
		qtd--;
		return e;
	}

	public No removeUltimo() {
		if (isEmpty()) {
			return null;
		}
		if (qtd == 1) {
			return _removeUnico();
		}
		No e = ultimo;
		ultimo = e.getAnterior();
		e.setAnterior(null);
		qtd--;
		return e;
	}

	public void imprimeParaFim() {
		if (isEmpty()) {
			System.err.println("Lista vazia!");
		} else {
			No e = inicio;
			while (e != null) {
				_printElem(e);
				e = e.getProximo();
			}
		}
	}

	public void imprimeParaInicio() {
		if (isEmpty()) {
			System.err.println("Lista vazia!");
		} else {
			No e = ultimo;
			while (e != null) {
				_printElem(e);
				e = e.getAnterior();
			}
		}
	}

	public No getPosDoInicio(int posicao) {
		if (posicao > qtd || posicao <= 0) {
			return null;
		}
		int posAtual = 1;
		No e = inicio;
		while (posAtual < posicao) {
			e = e.getProximo();
			posAtual++;
		}
		return e;
	}

	public No getPosDoFim(int posicao) {
		if (posicao > qtd || posicao <= 0) {
			return null;
		}
		int posAtual = 1;
		No e = ultimo;
		while (posAtual < posicao) {
			e = e.getAnterior();
			posAtual++;
		}
		return e;
	}

	public No getElemDoInicio(int num) {
		if (isEmpty()) {
			return null;
		}
		No e = inicio;
		while (e.getValor() != num) {
			e = e.getProximo();
		}
		return e;

	}

	public No getElemDoFim(int num) {
		if (isEmpty()) {
			return null;
		}
		No e = ultimo;
		while (e.getValor() != num) {
			e = e.getAnterior();
		}
		return e;

	}

	public String inserePosParaFim(int posicao, No e) {
		if (posicao > qtd + 1 || posicao <= 0 || e == null) {
			return "ERRO: um ou mais parametros com problemas!!";
		}
		if (posicao == 1) {
			insereInicio(e);
		} else {
			if (posicao == qtd + 1) {
				insereUltimo(e);
			} else {
				No cursor = getPosDoInicio(posicao - 1);
				e.setProximo(cursor.getProximo());
				e.setAnterior(cursor);
				cursor.getProximo().setAnterior(e);
				cursor.setProximo(e);
				qtd++;
			}
		}
		return "";
	}

	public No removePosParaInicio(int posicao) {
		if (isEmpty() || posicao > qtd + 1 || posicao <= 0) {
			return null;
		}
		if (posicao == 1) {
			return removeUltimo();
		} else if (posicao == qtd) {
			return removeInicio();
		}
		No cursor = getPosDoFim(posicao);
		cursor.getAnterior().setProximo(cursor.getProximo());
		cursor.getProximo().setAnterior(cursor.getAnterior());
		cursor.setProximo(null);
		cursor.setAnterior(null);
		qtd--;
		return cursor;
	}

	public No getInicio() {
		return inicio;
	}

	public No getUltimo() {
		return ultimo;
	}

	public boolean isEmpty() {
		return inicio == null;
	}

	public int getQtd() {
		return qtd;
	}

	public void destroi() {
		inicio = null;
		ultimo = null;
		qtd = 0;
	}

}
