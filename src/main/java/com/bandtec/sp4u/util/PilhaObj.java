package com.bandtec.sp4u.util;

public class PilhaObj<T> {
	private int topo;
	private T[] pilha;

	public PilhaObj(int tamanho) {

		pilha = (T[]) new Object[tamanho];
		topo = -1;
	}

	public boolean isEmpty() {
		return topo == -1;
	}

	public boolean isFull() {
		return topo == pilha.length -1;
	}

	public void push(T info) {
		if (isFull()) {
			System.out.println("A pilha está cheia");
		} else {
			pilha[++topo] = info;
		}
	}

	public T pop() {
		if (isEmpty()) {
			return null;
		}
		return pilha[topo--];
	}

	public T peek() {
		return isEmpty() ? null : pilha[topo];
	}

	public void exibe() {
		if (isEmpty()) {
			System.out.println("A pilha está vazia");
		} else {
			//System.out.println("Elementos da pilha: \n ");
			for (int i = topo; i >= 0; i--) {
				System.out.println("Elemento " + i + ": " + pilha[i]);
			}
		}
	}

	public T[] getPilha() {
		T[] pilhaOrdenada = (T[]) new Object[pilha.length];
		for (int i = 0; i < pilha.length; i++) {
			pilhaOrdenada[i] = pop();
		}
		return pilhaOrdenada;
	}
}
