package ed.tad;

public class ListaLigada {

	private No inicio;

	public boolean enqueue(No e) {

		try {
			if (isEmpty()) {
				inicio = e;
			} else {
				No fim = getFim();
				fim.setProximo(e);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public No dequeue() {

		if (inicio == null) {
			return null;
		}

		No e = inicio;
		inicio = e.getProximo();
		e.setProximo(null);
		return e;

	}

	public No getInicio() {
		return inicio;
	}

	public No getFim() {
		if (isEmpty()) {
			return inicio;
		}
		No e = inicio;
		while (e.getProximo() != null) {
			e = e.getProximo();
		}
		return e;
	}

	public boolean isEmpty() {
		return inicio == null;
	}

}
