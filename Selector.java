import java.util.ArrayList;
import java.util.Arrays;

public class Selector {
	
	public Chromosome[] select(Chromosome[] p, Chromosome[] c) {
		return elitist(p, c);
	}

	// Choose best candidates from children and parents
	public Chromosome[] elitist(Chromosome[] p, Chromosome[] c) {
		Chromosome[] all = new Chromosome[p.length*2];

		for (int i=0; i<p.length; i++) {
			all[i] = p[i];
			all[i+p.length] = c[i];
		}

		Arrays.sort(all);
		Chromosome[] elite = new Chromosome[p.length];

		for (int i=0; i<p.length; i++) {
			elite[i] = all[i];
		}

		return elite;
	}

}

