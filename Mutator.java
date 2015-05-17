import java.util.Random;

public class Mutator {
	Random random;

	public Mutator() {
		random = new Random();
	}

	public void mutate(Chromosome[] clist) {
		for (Chromosome c: clist) {
			mutate(c);
		}
	}

	public void mutate(Chromosome c) {
		// Between 3 and half the chromosome
		int invertLen = random.nextInt(c.cityList.length/2 - 3) + 3;
		int invertStart = random.nextInt(c.cityList.length - invertLen);

		invert(c, invertLen, invertStart);
		c.calculateCost();
	}

	public void invert(Chromosome c, int len, int startPos) {
		int[] cityListOrig = c.cityList.clone();

		for (int i=0; i<len; i++) {
			c.cityList[startPos+i] = cityListOrig[startPos+len-1-i];
		}
	}
}

