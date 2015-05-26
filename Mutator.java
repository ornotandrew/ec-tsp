import java.util.Random;

public class Mutator {
	public double pSwap = 0.3;
	public double pInvert = 0.3;

	public void mutate(Chromosome[] clist) {
		for (Chromosome c: clist) {
			mutate(c);
		}
	}

	public void mutate(Chromosome c) {
		swap(c);
		invert(c);
		c.calculateCost();
	}
	
	public void swap(Chromosome c) {
		double roll = TSP.random.nextDouble();
		if (roll<=pSwap) {
			int a = TSP.random.nextInt(c.cityList.length);
			int b = TSP.random.nextInt(c.cityList.length);
			int tmp = c.cityList[a];
			c.cityList[a] = c.cityList[b];
			c.cityList[b] = tmp;
		}
	}

	public void invert(Chromosome c) {
		double roll = TSP.random.nextDouble();
		if (roll<=pInvert) {
			int len = TSP.random.nextInt(c.cityList.length);
			int startPos = TSP.random.nextInt(c.cityList.length - len);

			int[] cityListOrig = c.cityList.clone();

			for (int i=0; i<len; i++) {
				c.cityList[startPos+i] = cityListOrig[startPos+len-1-i];
			}
		}
	}
}

