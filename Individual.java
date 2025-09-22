import java.util.ArrayList;
import java.util.Random;

public class Individual {

    /**
     * Chromosome stores the individual's genetic data as an ArrayList of characters
     * Each character represents a gene
     */
    ArrayList<Character> chromosome;
    //int size;

    /**
     * Inital constructor to generate initial population members
     * @param c_0 The initial chromosome size
     * @param num_letters The number of letters available to choose from
     */
    public Individual(int c_0, int num_letters, Random rng) {
        chromosome = new ArrayList<>(c_0);
        for (int i = 0; i < c_0; i++) {
            chromosome.add(randomLetter(num_letters, rng));
        }
    }

    /**
     * Second constructor to create parents and offspring chromosomes
     * @param parent1 The first parent chromosome
     * @param parent2 The second parent chromosome
     * @param c_max The maximum chromosome size
     * @param m The chances per round of mutation in each gene
     */
    public Individual(Individual parent1, Individual parent2, int c_max, float m, int num_letters, Random rng) {
        chromosome = new ArrayList<>();

        int prefixLength = rng.nextInt(parent1.chromosome.size()) + 1;
        int suffixLength = rng.nextInt(parent2.chromosome.size()) + 1;
        
        for (int i = 0; i < prefixLength; i++) {
            chromosome.add(parent1.chromosome.get(i));
        }

        //calculate the starting index for suffix, used as for loop's starting condition
        int startSuffixIndex = parent2.chromosome.size() - suffixLength;
        for (int i = startSuffixIndex; i < parent2.chromosome.size(); i++) {
            chromosome.add(parent2.chromosome.get(i));
        }

        while (chromosome.size() > c_max) {
            chromosome.remove(chromosome.size() - 1);
        }

        for (int i = 0; i < chromosome.size(); i++) {
            if (doesMutate(num_letters, rng)) {
                chromosome.set(i, randomLetter(num_letters, rng));
            }

        
        }

    }

    /**
     * Provided method to choose a letter at random, in the range from A to the number of states indicated
     * @param num_letters The number of letters available to choose from (number of possible states)
     * @param rng The random number generator being used for the current run
     * @return The letter as a Character
     */
    private Character randomLetter(int num_letters, Random rng) {
        return Character.valueOf((char)(65 + rng.nextInt(num_letters)));
    }

    /**
     * Provided method to determine whether a given gene will mutate based on the parameter ***m***
     * @param m the mutation rate
     * @param rng The random number generator being used for the current run
     * @return true if a number randomly chosen between 0 and 1 is less than ***m***, else false
     */
    private Boolean doesMutate(float m, Random rng) {
        float randomNum = rng.nextInt(100) / 100f;
        return randomNum < m;
    }

    private int getFitness(Individual chromosome) {
        
    }

    /**
     * Expresses the individual's chromosome as a String, for display purposes
     * @return The chromosome as a String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
            builder.append(ch);
        }
        return builder.toString();
    }




    public static void main(String[] args) {
        // This code will set a random seed when you're testing Individual (i.e., running it without GA_Simulation)
        Random rng = new Random(System.currentTimeMillis());

        // You can pass rng, as defined above, to your constructors.
        Individual i = new Individual(8, 4, rng);

    }

}
