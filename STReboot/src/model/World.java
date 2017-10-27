package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import enums.LOCI;
import enums.MUTATION;

public class World implements Serializable {
	ArrayList<Cat> cats = new ArrayList<Cat>();
	Random rand = new Random();
	
	public void addCat() {
		cats.add(new Cat());
	}
	
	public void addCat(Cat c) {
		cats.add(c);
	}
	
	public void addPremade() {
		Cat c = new Cat();
		c.randomGeno();
		cats.add(c);
	}

	public void removeCat(int id) {
		cats.remove(getCat(id));
	}
	
	public void sortUp(){
		Collections.sort(cats);
	}
	
	public void breedCat(Cat dam, Cat sire, int min, int max) {
		int size = 1;
		if (max - min > 0) {
			size = findSize(min, max);
		} else if (max == min) {
			size = max;
		}
		for (int j = 0; j < size; j++) {
			Cat kitten = new Cat();
			for (int i = 0; i < LOCI.values().length; i++) {
				kitten.genotype[0][i] = rand.nextBoolean() ? dam.genotype[0][i] : dam.genotype[1][i];
				kitten.genotype[1][i] = rand.nextBoolean() ? sire.genotype[0][i] : sire.genotype[1][i];
			} // for LOCI.length
			for (MUTATION m : MUTATION.values()) {
				if (dam.hasMutation(m) && sire.hasMutation(m)) {
					kitten.giveMutation(m);
				} else if (dam.hasMutation(m) && sire.hasMutation(m)) {
					if (rand.nextInt(m.half) == 0) {
						kitten.giveMutation(m);
					}
				} else {
					if (rand.nextInt(m.spontaneous) == 0) {
						kitten.giveMutation(m);
					}
				}
			} // for mutations
			cats.add(kitten);
		} // for size
	}

	private int findSize(int min, int max) {
		int random = rand.nextInt(100) + 1;
		int range = max-min+1;
		System.out.println(range);
		System.out.println(random);
		// numbers are chosen as 1/3-2/3, 1/6-2/6-3/6, etc
		if (range == 6) {
			if (random >= 1 && random <= 29) return 0 + min;
			else if (random <= 53) return 1 + min;
			else if (random <= 72) return 2 + min;
			else if (random <= 86) return 3 + min;
			else if (random <= 96) return 4 + min;
			else return 5 + min;
		}else if (range == 5) {
			if (random >= 1 && random <= 33) return 0 + min;
			else if (random <= 60) return 1 + min;
			else if (random <= 80) return 2 + min;
			else if (random <= 93) return 3 + min;
			else return 4 + min;
		} else if (range == 4) {
			if (random >= 1 && random <= 40) return 0 + min;
			else if (random <= 70) return 1 + min;
			else if (random <= 90) return 2 + min;
			else return 3 + min;
		} else if (range == 3) {
			if (random >= 1 && random <= 50) return 0 + min;
			else if (random <= 83) return 1 + min;
			else return 2 + min;
		} else if (range == 2) {
			System.out.println("Range == " + 2);
			if (random >= 1 && random <= 67) {
				System.out.println("Should be 1?");
				return (0 + min);
			}
			else return 1 + min;
		}
		return 1;
	}

	public void changeID(Cat cat, int id) {
		cat.setID(id);
	}

	/**
	 * 
	 * @param id
	 * @return the cat associated with the id or null if no cat is associated with the id
	 */
	public Cat getCat(int id) {
		for (Cat c : cats) {
			if (c.getID() == id) {
				return c;
			}
		}
		return null;
	}
	
	public int getLastID(){
		if (cats.size() > 0) {
			return cats.get(cats.size()-1).getID();
		} else {
			return 435;
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		for (Cat c : cats) {
			s += c;
		}
		return s;
	}
}
