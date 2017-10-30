package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import enums.ALLELE;
import enums.BUILD;
import enums.COLOR;
import enums.LOCI;
import enums.MUTATION;
import enums.POINT;
import enums.SHADE;
import enums.TABBY;

public class Cat implements Serializable, Comparable<Cat> {
	public static int population = 435;
	private int ID;
	ALLELE[][] genotype;
	HashSet<MUTATION> mutations;
	Random rand = new Random();

	public Cat() {
		this(++population);
	}

	/**
	 * Should be used ONLY when importing old cats into new system or when
	 * called in default constructor.
	 * 
	 * @param ID
	 */
	public Cat(int ID) {
		this.ID = ID;
		genotype = new ALLELE[2][LOCI.values().length];
		genotype[0][getIndex(LOCI.SEX)] = ALLELE.X;
		mutations = new HashSet<MUTATION>();
	}

	/**
	 * 
	 * Note: this class has a natural ordering that should not be inconsistent
	 * with equals, but can be.
	 * 
	 * @param o
	 *            - the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Cat o) {
		return ID - o.ID;
	}

	@Override
	public String toString() {
		String s = ID + " -";
		for (int i = 0; i < genotype[0].length; i++) {
			s += " " + genotype[0][i] + genotype[1][i];
		}
		s += " - " + phenotype();
		s += "\n";
		return s;
	}

	/**
	 * randomizes entire genotype with no account to what it was before
	 */
	public void randomGeno() {
		LOCI[] values = LOCI.values();
		for (int j = 0; j < genotype[0].length; j++) {
			genotype[0][j] = values[j].random();
			genotype[1][j] = values[j].random();
		}
		genotype[0][getIndex(LOCI.SEX)] = ALLELE.X;
		for (MUTATION m : MUTATION.values()) {
			if (rand.nextInt(m.spontaneous) == 0) {
				mutations.add(m);
			}
		}
	}

	public void randomize(LOCI locus) {
		genotype[0][getIndex(locus)] = locus.random();
		genotype[1][getIndex(locus)] = locus.random();

		if (locus.equals(LOCI.SEX)) {
			genotype[0][getIndex(LOCI.SEX)] = ALLELE.X;
		}
	}

	/**
	 * Used when given a LOCI instance and need the corresponding index.
	 * 
	 * @param locus
	 *            LOCI value
	 * @return index related to parameter
	 */
	private int getIndex(LOCI locus) {
		return Arrays.asList(LOCI.values()).indexOf(locus);
	}

	public void setGender(boolean f) {
		genotype[1][getIndex(LOCI.SEX)] = f ? ALLELE.X : ALLELE.Y;
	}

	public void setBuild(ALLELE[] a) {
		genotype[0][getIndex(LOCI.BUILD)] = a[0];
		genotype[1][getIndex(LOCI.BUILD)] = a[1];
		genotype[0][getIndex(LOCI.BUILD2)] = a[2];
		genotype[1][getIndex(LOCI.BUILD2)] = a[3];
		genotype[0][getIndex(LOCI.BUILD3)] = a[4];
		genotype[1][getIndex(LOCI.BUILD3)] = a[5];
		genotype[0][getIndex(LOCI.BUILD4)] = a[6];
		genotype[1][getIndex(LOCI.BUILD4)] = a[7];
	}

	public void setBuild(BUILD b) {
		randomBuild();
		int[][] test = { { 0, getIndex(LOCI.BUILD) }, { 1, getIndex(LOCI.BUILD) }, { 0, getIndex(LOCI.BUILD2) },
				{ 1, getIndex(LOCI.BUILD2) }, { 0, getIndex(LOCI.BUILD3) }, { 1, getIndex(LOCI.BUILD3) },
				{ 0, getIndex(LOCI.BUILD4) }, { 1, getIndex(LOCI.BUILD4) } };
		Collections.shuffle(Arrays.asList(test));
		switch (b) {
		case PWILD:
			for (int i = getIndex(LOCI.BUILD); i <= getIndex(LOCI.BUILD4); i++) {
				genotype[0][i] = ALLELE.Wi;
				genotype[1][i] = ALLELE.Wi;
			}
			break;
		case PCOBBY:
			for (int i = getIndex(LOCI.BUILD); i <= getIndex(LOCI.BUILD4); i++) {
				genotype[0][i] = ALLELE.Co;
				genotype[1][i] = ALLELE.Co;
			}
			break;
		case PORIEN:
			for (int i = getIndex(LOCI.BUILD); i <= getIndex(LOCI.BUILD4); i++) {
				genotype[0][i] = ALLELE.Or;
				genotype[1][i] = ALLELE.Or;
			}
			break;
		case PSUBST:
			for (int i = getIndex(LOCI.BUILD); i <= getIndex(LOCI.BUILD4); i++) {
				genotype[0][i] = ALLELE.Su;
				genotype[1][i] = ALLELE.Su;
			}
			break;
		case WILD:
			for (int i = 0; i < 4; i++) { // TODO play around with these
											// numbers?
				genotype[test[i][0]][test[i][1]] = ALLELE.Wi;
			}
			break;
		case COBBY:
			for (int i = 0; i < 4; i++) {
				genotype[test[i][0]][test[i][1]] = ALLELE.Co;
			}
			break;
		case ORIEN:
			for (int i = 0; i < 5; i++) {
				genotype[test[i][0]][test[i][1]] = ALLELE.Or;
			}
			break;
		case SUBST:
			for (int i = 0; i < 5; i++) {
				genotype[test[i][0]][test[i][1]] = ALLELE.Or;
			}
			break;
		} // switch Case

	}

	public void randomBuild() {
		randomize(LOCI.BUILD);
		randomize(LOCI.BUILD2);
		randomize(LOCI.BUILD3);
		randomize(LOCI.BUILD4);
	}

	public void setColor(ALLELE[] a) {
		genotype[0][getIndex(LOCI.COLOR)] = a[0];
		genotype[1][getIndex(LOCI.COLOR)] = a[1];
		genotype[0][getIndex(LOCI.TANNING)] = a[2];
		genotype[1][getIndex(LOCI.TANNING)] = a[3];
		genotype[0][getIndex(LOCI.DILUTE)] = a[4];
		genotype[1][getIndex(LOCI.DILUTE)] = a[5];
		genotype[0][getIndex(LOCI.BLEACHING)] = a[6];
		genotype[1][getIndex(LOCI.BLEACHING)] = a[7];
		genotype[0][getIndex(LOCI.AMBER)] = a[8];
		genotype[1][getIndex(LOCI.AMBER)] = a[9];
	}

	public void setColor(COLOR c) {
		switch (c) {
		case BLACK:
			colorBlack();
			tanningBlack();
			diluteNo();
			break;
		case CHOC:
			colorBlack();
			tanningChoc();
			diluteNo();
			break;
		case CINNA:
			colorBlack();
			tanningCinn();
			diluteNo();
			break;
		case AMBER:
			colorBlack();
			diluteNo();
			amberYes();
			break;
		case ORANGE:
			colorOrange();
			diluteNo();
			break;
		case BLUE:
			colorBlack();
			tanningBlack();
			diluteYes();
			break;
		case LILAC:
			colorBlack();
			tanningChoc();
			diluteYes();
			break;
		case FAWN:
			colorBlack();
			tanningCinn();
			diluteYes();
			break;
		case LAMB:
			colorBlack();
			diluteYes();
			randomize(LOCI.BLEACHING);
			amberYes();
			break;
		case CREAM:
			colorOrange();
			diluteYes();
			break;
		case CARAM:
			colorBlack();
			randomize(LOCI.TANNING);
			bleached();
			amberNo();
			break;
		case APRI:
			colorOrange();
			bleached();
			break;
		}
	}

	public void setTorti() {
		genotype[rand.nextInt(2)][getIndex(LOCI.COLOR)] = ALLELE.O;
	}

	public void setShading(ALLELE[] a) {
		genotype[0][getIndex(LOCI.SHADED)] = a[0];
		genotype[1][getIndex(LOCI.SHADED)] = a[1];
		genotype[0][getIndex(LOCI.GOLDEN)] = a[2];
		genotype[1][getIndex(LOCI.GOLDEN)] = a[3];

	}

	public void setShading(SHADE s, boolean b) {
		switch (s) {
		case GCHIN:
			setGolden();
			setChin();
			break;
		case GSHADE:
			setGolden();
			setShade();
			break;
		case NA:
			if (b) {
				setSilver();
			} else {
				setGolden();
			}
			shadedNo();
			break;
		case SCHIN:
			setSilver();
			setChin();
			break;
		case SSHADE:
			setSilver();
			setShade();
			break;
		}
	}

	public void setTabby(ALLELE[] a) {
		genotype[0][getIndex(LOCI.TABBY)] = a[0];
		genotype[1][getIndex(LOCI.TABBY)] = a[1];
		genotype[0][getIndex(LOCI.TICKED)] = a[2];
		genotype[1][getIndex(LOCI.TICKED)] = a[3];
		genotype[0][getIndex(LOCI.SPOTTED)] = a[4];
		genotype[1][getIndex(LOCI.SPOTTED)] = a[5];
		genotype[0][getIndex(LOCI.MACKEREL)] = a[6];
		genotype[1][getIndex(LOCI.MACKEREL)] = a[7];
		genotype[0][getIndex(LOCI.ROSETTED)] = a[8];
		genotype[1][getIndex(LOCI.ROSETTED)] = a[9];
	}

	public void setTabby(TABBY t, boolean b) {
		if (b) {
			setSelf();
			genotype[rand.nextInt(2)][getIndex(LOCI.TABBY)] = ALLELE.Ap;
		} else {
			genotype[0][getIndex(LOCI.TABBY)] = ALLELE.A;
			genotype[1][getIndex(LOCI.TABBY)] = ALLELE.A;
			if (rand.nextBoolean()) {
				genotype[rand.nextInt(2)][getIndex(LOCI.TABBY)] = ALLELE.a;
			}
		} // if charcoal
		switch (t) {
		case BRAID:
			roseYes();
			mackYes();
			spottedNo();
			break;
		case BROKE:
			roseNo();
			mackYes();
			partSpotted();
			break;
		case CLASS:
			roseNo();
			mackNo();
			spottedNo();
			break;
		case MACK:
			roseNo();
			mackYes();
			spottedNo();
			break;
		case MARB:
			roseYes();
			mackNo();
			spottedNo();
			break;
		case RESID:
			setTicked();
			genotype[rand.nextInt(2)][getIndex(LOCI.TICKED)] = ALLELE.t;
			random1();
			break;
		case ROSE:
			roseYes();
			randomize(LOCI.SPOTTED);
			genotype[rand.nextInt(2)][getIndex(LOCI.SPOTTED)] = ALLELE.Sp;
			randomize(LOCI.MACKEREL);
			break;
		case SELF:
			setSelf();
			randomize(LOCI.TICKED);
			random1();
			break;
		case SOKO:
			roseNo();
			partSpotted();
			mackNo();
			break;
		case SPOT:
			roseNo();
			genotype[0][getIndex(LOCI.SPOTTED)] = ALLELE.Sp;
			genotype[1][getIndex(LOCI.SPOTTED)] = ALLELE.Sp;
			randomize(LOCI.MACKEREL);
			break;
		case TICK:
			setTicked();
			random1();
			break;
		}
	}

	public void setPoints(ALLELE[] a) {
		genotype[0][getIndex(LOCI.POINT)] = a[0];
		genotype[1][getIndex(LOCI.POINT)] = a[1];
	}

	public void setPoints(POINT p) {
		switch (p) {
		case BERM:
			setBurmese();
			break;
		case MINK:
			setBurmese();
			genotype[rand.nextInt(2)][getIndex(LOCI.POINT)] = ALLELE.cs;
			break;
		case NA:
			randomize(LOCI.POINT);
			genotype[rand.nextInt(2)][getIndex(LOCI.POINT)] = ALLELE.C;
			break;
		case SIAM:
			genotype[0][getIndex(LOCI.POINT)] = ALLELE.cs;
			genotype[1][getIndex(LOCI.POINT)] = ALLELE.cs;
			break;
		}
	}

	public void setWhite(ALLELE[] a) {
		genotype[0][getIndex(LOCI.WHITE)] = a[0];
		genotype[1][getIndex(LOCI.WHITE)] = a[1];
	}

	public void setWhite(boolean b) {
		if (b) {
			randomize(LOCI.WHITE);
			genotype[rand.nextInt(2)][getIndex(LOCI.WHITE)] = ALLELE.S;
		} else {
			genotype[0][getIndex(LOCI.WHITE)] = ALLELE.s;
			genotype[1][getIndex(LOCI.WHITE)] = ALLELE.s;
		}
	}

	private void setSelf() {
		genotype[0][getIndex(LOCI.TABBY)] = ALLELE.a;
		genotype[1][getIndex(LOCI.TABBY)] = ALLELE.a;
	}

	private void setTicked() {
		genotype[0][getIndex(LOCI.TICKED)] = ALLELE.Ta;
		genotype[1][getIndex(LOCI.TICKED)] = ALLELE.Ta;
	}

	private void random1() {
		randomize(LOCI.SPOTTED);
		randomize(LOCI.MACKEREL);
		randomize(LOCI.ROSETTED);
	}

	private void tickedNo() {
		genotype[0][getIndex(LOCI.TICKED)] = ALLELE.t;
		genotype[1][getIndex(LOCI.TICKED)] = ALLELE.t;
	}

	private void roseYes() {
		tickedNo();
		genotype[0][getIndex(LOCI.ROSETTED)] = ALLELE.ro;
		genotype[1][getIndex(LOCI.ROSETTED)] = ALLELE.ro;
	}

	private void roseNo() {
		tickedNo();
		randomize(LOCI.ROSETTED);
		genotype[rand.nextInt(2)][getIndex(LOCI.ROSETTED)] = ALLELE.Ro;
	}

	private void partSpotted() {
		spottedNo();
		genotype[rand.nextInt(2)][getIndex(LOCI.SPOTTED)] = ALLELE.Sp;
	}

	private void spottedNo() {
		genotype[0][getIndex(LOCI.SPOTTED)] = ALLELE.sp;
		genotype[1][getIndex(LOCI.SPOTTED)] = ALLELE.sp;
	}

	private void mackYes() {
		randomize(LOCI.MACKEREL);
		genotype[rand.nextInt(2)][getIndex(LOCI.MACKEREL)] = ALLELE.Mc;
	}

	private void mackNo() {
		genotype[0][getIndex(LOCI.MACKEREL)] = ALLELE.mc;
		genotype[1][getIndex(LOCI.MACKEREL)] = ALLELE.mc;
	}

	private void setBurmese() {
		genotype[0][getIndex(LOCI.POINT)] = ALLELE.cb;
		genotype[1][getIndex(LOCI.POINT)] = ALLELE.cb;
	}

	private void setSilver() {
		genotype[0][getIndex(LOCI.GOLDEN)] = ALLELE.g;
		genotype[1][getIndex(LOCI.GOLDEN)] = ALLELE.g;
	}

	private void setGolden() {
		randomize(LOCI.GOLDEN);
		genotype[rand.nextInt(2)][getIndex(LOCI.GOLDEN)] = ALLELE.G;
	}

	private void setChin() {
		genotype[0][getIndex(LOCI.SHADED)] = ALLELE.I;
		genotype[1][getIndex(LOCI.SHADED)] = ALLELE.I;
	}

	private void shadedNo() {
		genotype[0][getIndex(LOCI.SHADED)] = ALLELE.i;
		genotype[1][getIndex(LOCI.SHADED)] = ALLELE.i;
	}

	private void setShade() {
		shadedNo();
		genotype[rand.nextInt(2)][getIndex(LOCI.SHADED)] = ALLELE.I;
	}

	private void colorBlack() {
		genotype[0][getIndex(LOCI.COLOR)] = ALLELE.o;
		genotype[1][getIndex(LOCI.COLOR)] = ALLELE.o;
	}

	private void colorOrange() {
		genotype[0][getIndex(LOCI.COLOR)] = ALLELE.O;
		genotype[1][getIndex(LOCI.COLOR)] = ALLELE.O;
		randomize(LOCI.TANNING);
		randomize(LOCI.AMBER);
	}

	private void tanningBlack() {
		randomize(LOCI.TANNING);
		genotype[rand.nextInt(2)][getIndex(LOCI.TANNING)] = ALLELE.B;
		amberNo();
	}

	private void tanningChoc() {
		ALLELE[] a = { ALLELE.b, ALLELE.b, ALLELE.bl, ALLELE.bl };
		Collections.shuffle(Arrays.asList(a));
		genotype[0][getIndex(LOCI.TANNING)] = a[0];
		genotype[1][getIndex(LOCI.TANNING)] = a[1];
		genotype[rand.nextInt(2)][getIndex(LOCI.TANNING)] = ALLELE.b;
		amberNo();
	}

	private void tanningCinn() {
		genotype[0][getIndex(LOCI.TANNING)] = ALLELE.bl;
		genotype[1][getIndex(LOCI.TANNING)] = ALLELE.bl;
		amberNo();
	}

	private void diluteYes() {
		genotype[0][getIndex(LOCI.DILUTE)] = ALLELE.d;
		genotype[1][getIndex(LOCI.DILUTE)] = ALLELE.d;
		randomize(LOCI.BLEACHING);
		genotype[rand.nextInt(2)][getIndex(LOCI.BLEACHING)] = ALLELE.Dm;
	}

	private void diluteNo() {
		randomize(LOCI.DILUTE);
		genotype[rand.nextInt(2)][getIndex(LOCI.DILUTE)] = ALLELE.D;
		randomize(LOCI.BLEACHING);
	}

	private void bleached() {
		diluteYes();
		genotype[0][getIndex(LOCI.BLEACHING)] = ALLELE.dm;
		genotype[1][getIndex(LOCI.BLEACHING)] = ALLELE.dm;
	}

	private void amberYes() {
		genotype[0][getIndex(LOCI.AMBER)] = ALLELE.e;
		genotype[1][getIndex(LOCI.AMBER)] = ALLELE.e;
		randomize(LOCI.TANNING);
	}

	private void amberNo() {
		randomize(LOCI.AMBER);
		genotype[rand.nextInt(2)][getIndex(LOCI.AMBER)] = ALLELE.E;
	}

	private String phenotype() {
		String pheno = "";
		pheno += sexPheno();
		pheno += buildPheno();
		pheno += colorPheno();
		pheno += patternPheno();
		pheno += pointPheno();
		pheno += whitePheno();
		for (MUTATION m : mutations) {
			pheno += ", " + m;
		}
		return pheno;
	}

	private String sexPheno() {
		switch (genotype[1][getIndex(LOCI.SEX)]) {
		case X:
			return "Female ";
		default:
			return "Male ";

		} // switch
	} // sexPheno()

	private String buildPheno() {		
		int w = 0;
		int c = 0;
		int o = 0;
		int s = 0;
		for (int i = 0; i < genotype[0].length; i++) {
			for (int j = 0; j < genotype.length; j++) {
				try {
					switch (genotype[j][i]) {
					case Su:
						s++;
						break;
					case Or:
						o++;
						break;
					case Co:
						c++;
						break;
					case Wi:
						w++;
						break;
					default:
					} // switch
				} catch (java.lang.NullPointerException e) {
					System.out.println("J is: " + j);
					System.out.println("I is: " + i);
					e.printStackTrace();
				}
			} // for genotype.length
		} // for Build ALLELEs
		if (w == 8) {
			return "Pure Wild ";
		} else if (c == 8) {
			return "Pure Cobby ";
		} else if (o == 8) {
			return "Pure Oriental ";
		} else if (s == 8) {
			return "Pure Substantial ";
		} else if (w >= c && w >= o && w >= s) {
			return "Wild ";
		} else if (c >= o && c >= s) {
			return "Cobby ";
		} else if (o >= s) {
			return "Oriental ";
		}
		return "Substantial ";
	} // buildPheno()

	private String colorPheno() {
		String pheno = "";
		if (isOrange()) {
			if (isDilute()) {
				if (isBleached()) {
					pheno = "Apricot ";
				} else {
					pheno = "Cream ";
				}
			} else {
				pheno = "Orange ";
			}
		} else {
			if (isAmber()) {
				if (isDilute()) {
					pheno = "Light Amber ";
				} else {
					pheno = "Amber ";
				}
			} else {
				if (isDilute() && isBleached()) {
					pheno = "Caramel ";
				} else if (isDilute()) {
					if (isBlack()) {
						pheno = "Blue ";
					} else if (isChoc()) {
						pheno = "Lilac ";
					} else {
						pheno = "Fawn ";
					} // if black
				} else {
					if (isBlack()) {
						pheno = "Black ";
					} else if (isChoc()) {
						pheno = "Chocolate ";
					} else {
						pheno = "Cinnamon ";
					} // if black
				} // if dilute
			} // if amber
		} // if orange
		if (genotype[0][getIndex(LOCI.COLOR)] != genotype[1][getIndex(LOCI.COLOR)]) {
			if (hasWhite()) {
				pheno += "Calico ";
			} else {
				pheno += "Tortoiseshell ";
			} // if has white
		} // if torti
		return pheno;
	}

	private String patternPheno() {
		String pheno = "";
		if (hasShaded()) {
			if (genotype[0][getIndex(LOCI.SHADED)] != genotype[1][getIndex(LOCI.SHADED)]) {
				if (isSilver()) {
					return "Silver Shaded ";
				} else {
					return "Golden Shaded ";
				} // if silver
			} else {
				if (isSilver()) {
					return "Silver Chinchilla ";
				} else {
					return "Golden Chinchilla ";
				} // if silver
			} // if shaded
		} else {
			if (isSelf()) {
				if (isSilver()) {
					return "Smoked ";
				} else {
					return "Self ";
				} // if isSilver()
			} else {
				if (isSilver()) {
					pheno = "Silver ";
				} // if isSilver()
				if (!hasTabby()) {
					if (genotype[0][getIndex(LOCI.TABBY)] != genotype[1][getIndex(LOCI.TABBY)]) {
						pheno += "Charcoal ";
					} // if charcoal
				}
				pheno += findTabby();
			} // if isSelf()
		} // if hasShaded()
		return pheno;
	}

	private String pointPheno() {
		if (hasPoints()) {
			if (genotype[0][getIndex(LOCI.POINT)] != genotype[1][getIndex(LOCI.POINT)]) {
				return "Mink ";
			} else if (genotype[0][getIndex(LOCI.POINT)] == ALLELE.cb) {
				return "Burmese ";
			}
			return "Siamese ";
		}
		return "";
	}

	private String whitePheno() {
		if (hasWhite()) {
			if (genotype[0][getIndex(LOCI.COLOR)] == genotype[1][getIndex(LOCI.COLOR)]) {
				return "with White";
			}
		}
		return "";
	}

	private String findTabby() {
		if (genotype[0][getIndex(LOCI.TICKED)] == ALLELE.Ta && genotype[1][getIndex(LOCI.TICKED)] == ALLELE.Ta) {
			return "Ticked ";
		} else if (genotype[0][getIndex(LOCI.TICKED)] != genotype[1][getIndex(LOCI.TICKED)]) {
			return "Residual ";
		} else {
			if (hasSpots() && !isDomestic()) {
				return "Rosetted ";
			} else if (hasSpots()) {
				if (genotype[0][getIndex(LOCI.SPOTTED)] == genotype[1][getIndex(LOCI.SPOTTED)]) {
					return "Spotted ";
				} else {
					if (hasMackerel()) {
						return "Broken ";
					} else {
						return "Sokoke ";
					} // hasMackerel()
				} // if Spsp or spSp
			} else {
				if (isDomestic()) {
					if (hasMackerel()) {
						return "Mackerel ";
					} else {
						return "Classic ";
					} // hasMackerel()
				} else {
					if (hasMackerel()) {
						return "Braided ";
					} else {
						return "Marbled ";
					} // hasMackerel()
				} // if isDomestic()
			} // if spots
		} // if Ticked
	} // findTabby()

	private boolean isOrange() {
		return genotype[0][getIndex(LOCI.COLOR)] == ALLELE.O && genotype[1][getIndex(LOCI.COLOR)] == ALLELE.O;
	}

	private boolean isBlack() {
		return genotype[0][getIndex(LOCI.TANNING)] == ALLELE.B || genotype[1][getIndex(LOCI.TANNING)] == ALLELE.B;
	}

	private boolean isChoc() {
		return genotype[0][getIndex(LOCI.TANNING)] == ALLELE.b || genotype[1][getIndex(LOCI.TANNING)] == ALLELE.b;
	}

	private boolean isDilute() {
		return genotype[0][getIndex(LOCI.DILUTE)] == ALLELE.d && genotype[1][getIndex(LOCI.DILUTE)] == ALLELE.d;
	}

	private boolean isBleached() {
		return genotype[0][getIndex(LOCI.BLEACHING)] == ALLELE.dm && genotype[1][getIndex(LOCI.BLEACHING)] == ALLELE.dm;
	}

	private boolean isAmber() {
		return genotype[0][getIndex(LOCI.AMBER)] == ALLELE.e && genotype[1][getIndex(LOCI.AMBER)] == ALLELE.e;
	}

	private boolean hasTabby() {
		return genotype[0][getIndex(LOCI.TABBY)] == ALLELE.A || genotype[1][getIndex(LOCI.TABBY)] == ALLELE.A;
	}

	private boolean isSelf() {
		return genotype[0][getIndex(LOCI.TABBY)] == ALLELE.a && genotype[1][getIndex(LOCI.TABBY)] == ALLELE.a;
	}

	private boolean hasSpots() {
		return genotype[0][getIndex(LOCI.SPOTTED)] == ALLELE.Sp || genotype[1][getIndex(LOCI.SPOTTED)] == ALLELE.Sp;
	}

	private boolean hasMackerel() {
		return genotype[0][getIndex(LOCI.MACKEREL)] == ALLELE.Mc || genotype[1][getIndex(LOCI.MACKEREL)] == ALLELE.Mc;
	}

	private boolean isDomestic() {
		return genotype[0][getIndex(LOCI.ROSETTED)] == ALLELE.Ro || genotype[1][getIndex(LOCI.ROSETTED)] == ALLELE.Ro;
	}

	private boolean hasShaded() {
		return genotype[0][getIndex(LOCI.SHADED)] == ALLELE.I || genotype[1][getIndex(LOCI.SHADED)] == ALLELE.I;
	}

	private boolean isSilver() {
		return genotype[0][getIndex(LOCI.GOLDEN)] == ALLELE.g && genotype[1][getIndex(LOCI.GOLDEN)] == ALLELE.g;
	}

	private boolean hasPoints() {
		return genotype[0][getIndex(LOCI.POINT)] != ALLELE.C && genotype[1][getIndex(LOCI.POINT)] != ALLELE.C;
	}

	private boolean hasWhite() {
		return genotype[0][getIndex(LOCI.WHITE)] == ALLELE.S || genotype[1][getIndex(LOCI.WHITE)] == ALLELE.S;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID() {
		return ID;
	}

	public static int getPopulation() {
		return population;
	}

	public boolean hasMutation(MUTATION m) {
		return mutations.contains(m);
	}

	public void giveMutation(MUTATION m) {
		mutations.add(m);
	}
}