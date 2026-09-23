package cartes;

public class JeuDeCartes {
	private Configuration[] typeDeCartes = new Configuration[19];
	
	public JeuDeCartes() {
		typeDeCartes[0] = new Configuration(new Borne(15), 10);
		typeDeCartes[1] = new Configuration(new Borne(50), 10);
		typeDeCartes[2] = new Configuration(new Borne(75), 10);
		typeDeCartes[3] = new Configuration(new Borne(100), 12);
		typeDeCartes[4] = new Configuration(new Borne(200), 4);
		typeDeCartes[5] = new Configuration(new Parade(Type.FEU), 14);
		typeDeCartes[6] = new Configuration(new FinLimite(), 6);
		typeDeCartes[7] = new Configuration(new Parade(Type.ESSENCE), 6);
		typeDeCartes[8] = new Configuration(new Parade(Type.CREVAISON), 6);
		typeDeCartes[9] = new Configuration(new Parade(Type.ACCIDENT), 6);
		typeDeCartes[10] = new Configuration(new Attaque(Type.FEU), 5);
		typeDeCartes[11] = new Configuration(new DebutLimite(), 4);
		typeDeCartes[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
		typeDeCartes[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
		typeDeCartes[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);
		typeDeCartes[15] = new Configuration(new Botte(Type.FEU), 1);
		typeDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1);
		typeDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1);
		typeDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1);
	}

	public String affichageJeuDeCartes() {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i<typeDeCartes.length; i++) {
			text.append(typeDeCartes[0].nbExemplaires).append(" ").append(typeDeCartes[i].getCarte().toString()).append("\n");
		}
		return text.toString();
	}
	
	public Carte[] donnerCartes() {
		int total = 0;
		for (Configuration config : typeDeCartes) {
			total += config.getNbExemplaires();
		}
		Carte[] jeu = new Carte[total];
		for (Configuration config : typeDeCartes) {
			for(int i=0; i<jeu.length; i++) {
				jeu[i++] = config.carte;
			}
		}
		return jeu;
	}
	
	private class Configuration extends Carte {
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}
}
