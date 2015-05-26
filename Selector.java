import java.util.ArrayList;
import java.util.Arrays;

public class Selector {

	public int numChildren;

	public Chromosome[] select(Chromosome[] p) {
		numChildren = p.length * 5;
		// find the children
		Chromosome.shuffleChromosomes(p);
		Chromosome[] winners = tournament(p, 25);
		Chromosome.shuffleChromosomes(winners);
		Chromosome[] children = randomCross(winners);
		TSP.mutator.mutate(children);
		Chromosome[] childSurvivors = elitist(children, 500);

		// find the parents
		Arrays.sort(p);

		Chromosome[] all = new Chromosome[1000];
		for (int i=0; i<500; i++)
			all[i] = p[i];
		for (int i=0; i<500; i++)
			all[i+500] = childSurvivors[i];

		Arrays.sort(all);
		Chromosome[] survivors = new Chromosome[500];
		for (int i=0; i<500; i++) {
			survivors[i] = all[i];
		}

		// return childSurvivors;
		return survivors;
	}

	public Chromosome[] randomCross(Chromosome[] clist) {
		Chromosome[] children = new Chromosome[numChildren];

		Chromosome[] childPair;
		int a, b;

		for (int i=0; i<numChildren; i++) {
			// Randomly choose parents, make 2 children
			a = TSP.random.nextInt(clist.length);	
			b = TSP.random.nextInt(clist.length);	
			childPair = TSP.recombinator.recombinate(clist[a], clist[b]);

			if (childPair[0].cost < childPair[1].cost)
				children[i] = childPair[0];
			else
				children[i] = childPair[1];
		}

		return children;
	}

	public Chromosome[] tournament(Chromosome[] clist, int numSets) {
		int setSize = clist.length/numSets;
		int resultLen = (int) Math.ceil(clist.length/(double) setSize);
		Chromosome[] results = new Chromosome[resultLen];

		for (int i=0; i<clist.length; i+=setSize) {
			// add the best chromosome in each range
			Chromosome best = clist[i];
			for (int j=1; j<setSize; j++) {
				if (i+j>=clist.length)
					break;
				else if (clist[i+j].cost < best.cost)
					best = clist[i+j];
			}
			results[i/setSize] = best;
		}

		return results;
	}

	public Chromosome[] elitist(Chromosome[] clist, int n) {
		Chromosome[] e = new Chromosome[n];

		Arrays.sort(clist);
		for (int i=0; i<n; i++) {
			e[i] = clist[i];
		}

		return e;
	}

}

