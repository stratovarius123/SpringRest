package br.com.cadastro.resources.utils;

import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String valoresASerConvertidos){
		String [] vet = valoresASerConvertidos.split(",");
		List<Integer> listaDeNumeros = new ArrayList<Integer>();
		for (String string : vet) {
			listaDeNumeros.add(Integer.parseInt(string));
		}
		return listaDeNumeros;
	}
}
