import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GA_Simulation {

  // Use the instructions to identify the class variables, constructors, and methods you need
  public static Random rng;
  public ArrayList<Individual> population;
  public int n;
  public int k;
  public int r;
  public int c_0;
  public int c_max;
  public int g;
  public float m;

  public GA_Simulation(int n, int k, int r, int c_0, int c_max, int g, float m) {
    this.population = new ArrayList<>();
    this.n = n;
    this.k = k;
    this.r = r;
    this.c_0 = c_0;
    this.c_max = c_max;
    this.g = g;
    this.m = m;
  }

  public void init(Random rng) {
    //Initialize population of chosen size
    ArrayList<Individual> population = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      population.add(new Individual(c_0, k, rng));
    }
  }

  public void evolve(){
    rankPopulation(population);

    ArrayList<Individual> newGen = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int parent1Num = rng.nextInt(k);
      int parent2Num = rng.nextInt(k);
      Individual parent1 = population.get(parent1Num);
      Individual parent2 = population.get(parent2Num);

    }
  }

  /** Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * @param roundNumber: Which round of evolution are we on, from 0 to n
   * @param bestFitness: Fitness of top-ranked (most fit) individual
   * @param kthFitness: Fitness of kth-ranked individual
   * @param leastFitness: Fitness of lowest-ranked (least fit) individual
   * @param best: Individual with highest fitness
   * @return: Nothing, prints statistics to standard out
   */
  private void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /** Provided method that sorts population by fitness score, best first
   * @param pop: ArrayList of Individuals in the current generation
   * @return: Nothing. ArrayList is sorted in place
   */
  public void rankPopulation(ArrayList<Individual> pop) {
    // sort population by fitness
    Comparator<Individual> ranker = new Comparator<>() {
      // this order will sort higher scores at the front
      public int compare(Individual c1, Individual c2) {
        return (int)Math.signum(c2.getFitness()-c1.getFitness());
      }
    };
    pop.sort(ranker);
  }


  public static void main(String[] args) {
    // This first block of code establishes a random seed, which will make
    // it easier to test your code. The output should remain consistent if the
    // seed is the same. To run with a specific seed, you can run from the
    // command line like:
    //                    java GA_Simulation 24601

    long seed = System.currentTimeMillis(); // default
    if (args.length > 0) {
      try {
        seed = Long.parseLong(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Seed wasn't passed so using current time.");
      }
    }
    rng = new Random(seed);

    // Write your main below:

  }

}
