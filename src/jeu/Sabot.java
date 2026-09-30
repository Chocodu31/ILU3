package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	
	private int nbCartes;
	private Carte[] stockCartes;
	private int nombreOperations = 0;
	
	public Sabot(Carte[] cartes) {
		this.nbCartes = cartes.length;
		this.stockCartes = cartes;
	}

	public Boolean estVide() {
		return nbCartes == 0;
	}
	
	public Carte[] ajouterCarte(Carte carteAAjouter) {
		for(int i = 0; i<stockCartes.length; i++) {
			if (stockCartes[i]==null) {
				nbCartes++;
				nombreOperations++;
				stockCartes[i] = carteAAjouter;
				return stockCartes;
			}
		}
		throw new IllegalStateException("Capacité MAX atteinte");
	}

	public Carte piocher() {
		Iterator<Carte> iter = iterator();
		if(!iter.hasNext()) {
			throw new IllegalStateException("Sabot vide");
		}
		Carte carte = iter.next();
		iter.remove();
		return carte;
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			while (indiceIterateur < stockCartes.length && stockCartes[indiceIterateur] == null) {
				indiceIterateur++;
			}
			return indiceIterateur < stockCartes.length;
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();
			if(hasNext()) {
				Carte carte = stockCartes[indiceIterateur];
				nextEffectue = true;
				indiceIterateur++;
				return carte;
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (!nextEffectue) {
            	throw new IllegalStateException("manque un next");
            }
            nbCartes--;
            nombreOperations++;
            nombreOperationsReference = nombreOperations;
            stockCartes[indiceIterateur-1] = null;
            nextEffectue = false;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationsReference) {
            	throw new ConcurrentModificationException();
            }
		} 
	}
}
