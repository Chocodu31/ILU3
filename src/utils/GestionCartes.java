package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import cartes.Carte;

public class GestionCartes {

	public GestionCartes() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> T extraireV1(List<T> listeCartes) {
		if(listeCartes.isEmpty()) {
			throw new NullPointerException("liste vide");
		}
		T element;
		Random random = new Random();
		int randomIndex = random.nextInt(listeCartes.size());
		return element = listeCartes.remove(randomIndex);
	}
	
	public static <T> T extraireV2(List<T> liste) {
		if(liste.isEmpty()) {
			throw new NullPointerException("liste vide");
		}
		Random random = new Random();
		int randomIndex = random.nextInt(liste.size());
		ListIterator<T> iterateur = liste.listIterator(randomIndex);
		T element = iterateur.next();
		iterateur.remove();
		return element;
	}

	public static <T> List<T> melanger(List<T> liste) {
		List<T> listeMelangee = new ArrayList<>();
		
		while(!liste.isEmpty()) {
			listeMelangee.add(extraireV1(liste));
		}
		
		return listeMelangee;
	}

	public static <T> Boolean verifierMelange(List<T> listeNonMelangee, List<T> listeMelangee) {
		if(listeMelangee.size()!=listeNonMelangee.size()) {
			return false;
		}
		
		for(Object element : listeNonMelangee) {
			int frequenceNonMelangee = Collections.frequency(listeNonMelangee, element);
			int frequenceMelangee = Collections.frequency(listeMelangee,element);
			if (frequenceMelangee!=frequenceNonMelangee) return false;
		}
		
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		List<T> listeRassembler = new ArrayList<>();
		List<T> listeContains = new ArrayList<>();
		
		for(T element : liste) {
			if(!listeContains.contains(element)) {
				int frequence = Collections.frequency(liste, element);
				for (int i = 0; i<frequence;i++) {
					listeRassembler.add(element);
				}
				listeContains.add(element);
			}
		}
		return listeRassembler;
	}

	public static <T> Boolean verifierRassemblement(List<T> liste) {
		ListIterator<T> iterateur1 = liste.listIterator();
		while(iterateur1.hasNext()) {
			T elem1 = iterateur1.next();
			T elem2 = iterateur1.next();
			if(!elem1.equals(elem2)) {
				ListIterator<T> iterateur2 = liste.listIterator(iterateur1.nextIndex());
				while (iterateur2.hasNext()) {
					T elemVerif = iterateur2.next();
					if (elem1.equals(elemVerif)) return false;
				}
			}
		}
		return true;
	}
}
