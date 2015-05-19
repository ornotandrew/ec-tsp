import java.util.ArrayList;

class Chromosome implements Comparable<Chromosome> {

    /**
     * The list of cities, which are the genes of this chromosome.
     */
    public int[] cityList;

    /**
     * The cost of following the cityList order of this chromosome.
     */
    public double cost;

    public City[] cities;

    /**
     * @param cities The order that this chromosome would visit the cities.
     */
    Chromosome(City[] cities) {
    	this.cities = cities;
        cityList = new int[cities.length];
        //cities are visited based on the order of an integer representation [o,n] of each of the n cities.
        for (int x = 0; x < cities.length; x++) {
            cityList[x] = x;

        }

        //shuffle the order so we have a random initial order
        for (int y = 0; y < cityList.length; y++) {
            int temp = cityList[y];
            int randomNum = TSP.random.nextInt(cityList.length);
            cityList[y] = cityList[randomNum];
            cityList[randomNum] = temp;
        }

        calculateCost(cities);
    }

    /**
     * Copy constructor
     * @param c The Chromosome object to copy
     */
    Chromosome(Chromosome c) {
    	cities = c.cities.clone();
		cityList = c.cityList.clone();
		cost = c.getCost();
    }

    /**
     * Calculate the cost of the specified list of cities.
     *
     * @param cities A list of cities.
     */
    void calculateCost(City[] cities) {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }
    }

    void calculateCost() {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }
    }

    /**
     * Get the cost for this chromosome. This is the amount of distance that
     * must be traveled.
     */
    double getCost() {
        return cost;
    }

    /**
     * @param i The city you want.
     * @return The ith city.
     */
    int getCity(int i) {
        return cityList[i];
    }

    /**
     * Set the order of cities that this chromosome would visit.
     *
     * @param list A list of cities.
     */
    void setCities(int[] list) {
        for (int i = 0; i < cityList.length; i++) {
            cityList[i] = list[i];
        }
    }

    /**
     * Set the index'th city in the city list.
     *
     * @param index The city index to change
     * @param value The city number to place into the index.
     */
    void setCity(int index, int value) {
        cityList[index] = value;
    }

    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static void sortChromosomes(Chromosome[] chromosomes, int num) {
        Chromosome ctemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < num - 1; i++) {
                if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
                    ctemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = ctemp;
                    swapped = true;
                }
            }
        }
    }

	public static void shuffleChromosomes(Chromosome[] c) {
		Chromosome tmp;
		for (int i = c.length - 1; i > 0; i--) {
			int index = TSP.random.nextInt(i + 1);
			tmp = c[index];
			c[index] = c[i];
			c[i] = tmp;
		}
	}

	/**
	 * Reveal the chromosome
	 */
	public String toString() {
		String s = "";

		for (int i: cityList) {
			s += i + " ";
		}

		return  s;
	}

	@Override
	public int compareTo(Chromosome c) {
		return Double.compare(cost, c.cost);
	}
}
