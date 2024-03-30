package jogo.ed;

public class ListaLigadaJogo extends ListaDuplamenteLigadaCircular {

	@Override
	public void _imprimeElemento(No e) { // Ex1
		int pos = e.getPosicao();
		String stuff = "";
		if (pos < 10)
			stuff = " ";
		System.out.print(stuff + e.getPosicao() + " | ");
		STATUSCASA status = e.getStatus();
		switch (status) {
		case LIVRE:
			System.out.print("---");
			break;
		case MARCADA:
			System.out.print("* " + e.getJogador());
			break;
		case PROPRIETARIA:
			System.out.print("**" + e.getJogador());
		}

	}

	/**
	 * Faz a renumeracao das casas da lista (sentido horario)
	 */
	private void _renumera() {
		if (isEmpty()) {
			return;
		}
		No e = getInicio();
		for (int pos = 1; pos <= getQtd(); pos++) {
			e.setPosicao(pos);
			e = e.getProximo();
		}
	}

	@Override
	public void insereInicio(No e) {
		super.insereInicio(e);
		_renumera();
	}

	@Override
	public void insereUltimo(No e) {
		super.insereUltimo(e);
		e.setPosicao(getQtd());
	}

	@Override
	public boolean insereHorario(No eNovo, No eAtual) {
		super.insereHorario(eNovo, eAtual);
		_renumera();
		return true;
	}

	@Override
	public No removeInicio() {
		No e = super.removeInicio();
		_renumera();
		return e;
	}

	@Override
	public boolean removeElemento(No e) {
		if (super.removeElemento(e)) {
			_renumera();
			return true;
		} else {
			return false;
		}
	}

	public void imprimeHorario(Jogador jogadores[]) {
		if (isEmpty()) {
			System.err.println("A lista está vazia");
			return;
		}
		No e = getInicio();
		int posAtu = 1;
		while (posAtu <= getQtd()) {
			_imprimeElemento(e);
			System.out.print(" |");

			for (int i = 0; i < jogadores.length; i++) {
				if (jogadores[i].getCasa() == e) {
					System.out.printf(" %d", i);
				}
			}

			System.out.println("");
			e = e.getProximo();
			posAtu++;
		}
	}

	@Override
	public void imprimeAntiHorario() {
		if (isEmpty()) {
			System.err.println("A lista está vazia");
			return;
		}
		No e = getInicio();
		int posAtu = 1;
		while (posAtu <= getQtd()) {
			_imprimeElemento(e);
			e = e.getAnterior();
			posAtu++;
		}

	}

}
