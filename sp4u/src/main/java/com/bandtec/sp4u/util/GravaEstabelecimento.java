package com.bandtec.sp4u.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

import com.bandtec.sp4u.domain.entities.Estabelecimento;

public class GravaEstabelecimento {

	private static FileWriter arq = null;
	private static Formatter saida = null;
	private static boolean deuRuim = false;
	private static String nomeArquivo = "estabelecimento" + ".csv";
	
	public static void gravaLista(ListaObj<Estabelecimento> lista) {

		try {
			arq = new FileWriter(nomeArquivo, true);
			saida = new Formatter(arq);
		} catch (IOException erro) {
			System.err.println("Erro ao abrir arquivo");
			System.exit(1);
		}

		try {

			for (int i = 0; i < lista.getTamanho(); i++) {
				
				Estabelecimento estab = lista.getElemento(i);
				saida.format("%d;%s;%s;%s;%s;%s;%.2f;%s%n", estab.getId(), estab.getNome(), estab.getCnpj(), estab.getCep(),estab.getMediaPreco(),estab.getEndereco(),estab.getNota(),estab.getDescricao());
			}

		} catch (FormatterClosedException erro) {
			System.err.println("Erro ao gravar no arquivo");
			deuRuim = true;
		} finally {

			saida.close();
			try {
				arq.close();
			} catch (IOException erro) {
				System.err.println("Erro ao fechar arquivo.");
				deuRuim = true;
			}
			if (deuRuim) {
				System.exit(1);
			}
		}
	}
}
