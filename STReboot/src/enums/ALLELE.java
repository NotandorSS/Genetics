package enums;

public enum ALLELE {
	/*
	 * random algorithm assumes each row's number sums to 10.
	 */
	X(5), Y(5),
	Wi(4), Co(3), Or(2), Su(1),
	O(5), o(5),
	B(4), b(3), bl(3),
	D(5), d(5),
	Dm(5), dm(5),
	E(5), e(5),
	A(4), Ap(2), a(4),
	Ta(5), t(5),
	Sp(5), sp(5),
	Mc(5), mc(5),
	Ro(7), ro(3),
	I(2), i(8),
	G(6), g(4),
	cs(3), cb(3), C(4), 
	S(5), s(5);
	
	public final int pull;
	
	private ALLELE(int pull) {
		this.pull = pull;
	}
}