import java.util.ArrayList;
import java.util.Arrays;

public class Selector {

	public Chromosome[] select(Chromosome[] p) {
		Chromosome.shuffleChromosomes(p);
		Chromosome[] winners = tournament(p, p.length/10);
		Chromosome.shuffleChromosomes(winners);
		Chromosome[] c = randomCross(winners);
		TSP.mutator.mutate(c);
		return c;
	}

	public Chromosome[] randomCross(Chromosome[] clist) {
		Chromosome[] children = new Chromosome[500];

		Chromosome[] childPair;
		int a, b;

		for (int i=0; i<500; i++) {
			// Randomly choose parents, make 2 children
			// then add the fittest one
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

	// Tournament
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

}

