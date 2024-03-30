package jogo.ed;

public class ListaDuplamenteLigadaCircular {

	private No inicio;
	private int qtd;

	public void _imprimeElemento(No e) { // Ex1
		System.out.println(e.getId());
	}

	private void _autoConex(No e) {
		e.setProximo(e);
		e.setAnterior(e);
	}

	private boolean _insereEmVazio(No e) {
		if (this.qtd > 0) {
			return false;
		}
		inicio = e;
		return true;
	}

	private No _removeUnico() {
		if (isEmpty() || qtd > 1) {
			return null;
		}
		No e = inicio;
		inicio = null;
		qtd = 0;
		return e;
	}

	public void insereInicio(No e) {
		if (isEmpty()) {
			_insereEmVazio(e);
		} else {
			e.setProximo(inicio);
			e.setAnterior(inicio.getAnterior());
			inicio.setAnterior(e);
			e.getAnterior().setProximo(e);
			inicio = e;
		}
		qtd++;
	}

	public void insereUltimo(No e) {
		if (isEmpty()) {
			_insereEmVazio(e);
		} else {
			e.setProximo(inicio);
			e.setAnterior(inicio.getAnterior());
			inicio.getAnterior().setProximo(e);
			inicio.setAnterior(e);
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
		inicio.getAnterior().setProximo(inicio.getProximo());
		inicio.getProximo().setAnterior(inicio.getAnterior());
		inicio = inicio.getProximo();
		e.setProximo(e);
		e.setAnterior(e);
		qtd--;
		return e;
	}

	public No removeFim() {
		if (isEmpty()) {
			return null;
		}
		if (qtd == 1) {
			return _removeUnico();
		}
		No e = inicio.getAnterior();
		inicio.setAnterior(e.getAnterior());
		e.getAnterior().setProximo(inicio);
		e.setProximo(e);
		e.setAnterior(e);
		qtd--;
		return e;
	}

	public No getPosHorario(No e, int pos) {
		if (pos > qtd || pos <= 0) {
			return null;
		}
		int posAtu = 1;
		No eAux = e; // * criar ponteiro de deslocamento
		while (posAtu < pos) {
			eAux = eAux.getProximo();
			posAtu++;
		}
		return eAux;
	}

	public No getPosAntiHorario(No e, int pos) {
		if (pos > qtd || pos <= 0) {
			return null;
		}
		int posAtu = 1;
		No eAux = e;
		while (posAtu < pos) {
			eAux = eAux.getAnterior();
			posAtu++;
		}
		return eAux;
	}

	public boolean removeElemento(No e) {
		if (isEmpty() || e == null) {
			return false;
		}
		if (qtd == 1) {
			_removeUnico();
		} else {
			if (e == inicio) {
				inicio = e.getProximo();
			}
			e.getAnterior().setProximo((e.getProximo()));
			e.getProximo().setAnterior((e.getAnterior()));
			_autoConex(e);
			qtd--;
		}
		return true;
	}

	public boolean insereHorario(No eNovo, No eAtual) {
		if (eNovo == null) {
			return false;
		}
		if (qtd <= 1 || eAtual == inicio) {
			insereInicio(eNovo);
		} else if (eAtual == inicio.getAnterior()) {
			insereUltimo(eNovo);
		} else {
			eNovo.setProximo(eAtual);
			eNovo.setAnterior(eAtual.getAnterior());
			eAtual.getAnterior().setProximo(eNovo);
			eAtual.setAnterior(eNovo);
			qtd++;
		}
		return true;
	}

	public boolean insereAntihorario(No eNovo, No eAtual) {

		if (eNovo == null) {
			return false;
		}
		if (isEmpty()) {
			insereInicio(eNovo);
		} else {
			eNovo.setProximo(eAtual.getProximo());
			eNovo.setAnterior(eAtual);
			eAtual.getProximo().setAnterior(eNovo);
			eAtual.setProximo(eNovo);
			if (eAtual == inicio) { // * se o atual for o inicio, deslocando-se no anti-horario -> o novo passa a
									// ser o inicio.
				inicio = eNovo;
			}
			qtd++;
		}
		return true;
	}

	public void imprimeHorario(Jogador[] jogadores) {
		if (isEmpty()) {
			System.out.println("A lista esta' vazia");
		} else {
			No e = getInicio();
			int posAtu = 1;
			while (posAtu <= getQtd()) {
				_imprimeElemento(e);
				e = e.getProximo();
				posAtu++;
			}
		}
	}

	public void imprimeAntiHorario() {
		if (isEmpty()) {
			System.out.println("A lista esta' vazia");
		} else {
			No e = getInicio();
			int posAtu = 1;
			while (posAtu <= getQtd()) {
				_imprimeElemento(e);
				e = e.getAnterior();
				posAtu++;
			}
		}
	}

	public No getInicio() {
		return inicio;
	}

	public No getUltimo() {
		if (isEmpty()) {
			return null;
		}
		return inicio.getAnterior();
	}

	public boolean isEmpty() {
		return inicio == null;
	}

	public int getQtd() {
		return qtd;
	}

	public void destroi() {
		inicio = null;
		qtd = 0;
	}
}
