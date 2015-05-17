import java.util.Random;

public class Recombinator {
	Random random;
	Chromosome[] childPair;

	public Recombinator() {
		random = new Random();
		childPair = new Chromosome[2];
	}

	public Chromosome[] recombinate(Chromosome[] clist) {
		return consecCross(clist);
	}

	public Chromosome[] consecCross(Chromosome[] clist) {
		// Make children with each pair of consecutive parents
		Chromosome[] children = new Chromosome[clist.length];

		for (int i=0; i<clist.length; i+=2) {
			Chromosome[] kids = noDuplicate(clist[i], clist[i+1]);
			children[i] = kids[0];
			children[i+1] = kids[1];
		}

		return children;
	}

	// 8 Queens type
	public Chromosome[] noDuplicate(Chromosome p1, Chromosome p2) {
		// Number of cities is the same in each genotype
		int numCities = p1.cityList.length;

		Chromosome c1 = new Chromosome(p1);
		Chromosome c2 = new Chromosome(p2);

		int crossoverLen = numCities/2 - 1;
		int startPos = numCities - crossoverLen;
		
		// Crossover
		int c1pos = startPos;
		int c2pos = startPos;

		for (int i=0; i<numCities; i++) {
			// Child 1 gets attributes from Parent 2
			if (!contains(c1.cityList, p2.cityList[i], startPos)) {
				c1.cityList[c1pos] = p2.cityList[i];
				c1pos++;
			}

			// Child 2 gets attributes from Parent 1
			if (!contains(c2.cityList, p1.cityList[i], startPos)) {
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

	// Check if an element is in the first n elements of an array
	public boolean contains(int[] arr, int elem, int n) {
		for (int i=0; i<n; i++) {
			if (arr[i]==elem){
				return true;
			}
		}
		return false;
	}
}

