package enums;

import java.util.Arrays;
import java.util.Random;

public enum LOCI {
	/*
	 * the start and end position relate to the indexes in ALLELE.values(). Be
	 * very careful when changing them.
	 */
	SEX(0, 2), BUILD(2, 6), BUILD2(2, 6), BUILD3(2, 6), BUILD4(2, 6), COLOR(6, 8), TANNING(8, 11),
			DILUTE(11, 13), BLEACHING(13, 15), AMBER(15, 17), TABBY(17, 20), TICKED(20, 22),
			SPOTTED(22, 24), MACKEREL(24, 26), ROSETTED(26, 28), SHADED(28, 30), GOLDEN(30, 32),
			POINT(32, 35), WHITE(35, 37);

	public final int start;
	public final int end;

	private LOCI(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public ALLELE random() {
		ALLELE[] alleles = Arrays.copyOfRange(ALLELE.values(), start, end);
		int sum = 0;
		int pick = new Random().nextInt(10) + 1;
		for (int i = 0; i < alleles.length; i++) {
			sum += alleles[i].pull;
			if (pick <= sum)
				return alleles[i];
		}

		return null;
	} // random()
}
