import java.util.HashSet;

public class Recombinator {
	Chromosome[] childPair;

	public Recombinator() {
		childPair = new Chromosome[2];
	}

	public Chromosome[] recombinate(Chromosome a, Chromosome b) {
		return cutCrossFill(a, b);
	}

	// 8 Queens type
	public Chromosome[] cutCrossFill(Chromosome p1, Chromosome p2) {
		// Number of cities is the same in each genotype
		int numCities = p1.cityList.length;

		Chromosome c1 = new Chromosome(p1);
		Chromosome c2 = new Chromosome(p2);


		int crossoverLen = numCities/2 - 1;
		int startPos = numCities - crossoverLen;

		// Keep track of what's in the first section
		HashSet<Integer> c1CheckList = new HashSet<Integer>();
		HashSet<Integer> c2CheckList = new HashSet<Integer>();

		for (int i=0; i<startPos; i++) {
			c1CheckList.add(c1.cityList[i]);
			c2CheckList.add(c2.cityList[i]);
		}
		
		// Crossover
		int c1pos = startPos;
		int c2pos = startPos;

		for (int i=0; i<numCities; i++) {
			// Child 1 gets attributes from Parent 2
			if (!c1CheckList.contains(p2.cityList[i])) {
				c1.cityList[c1pos] = p2.cityList[i];
				c1pos++;
			}

			// Child 2 gets attributes from Parent 1
			if (!c2CheckList.contains(p1.cityList[i])) {
				c2.cityList[c2pos] = p1.cityList[i];
				c2pos++;
			}

		}

		c1.calculateCost();
		c2.calculateCost();

		childPair[0] = c1;
		childPair[1] = c2;

		return childPair;
	}
}

