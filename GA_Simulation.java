import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Controls genetic algorithm simulation which evolves a population of individuals
 * Does this over several generations based on natural (fitness) selection and reproduction
 */
public class GA_Simulation {

  // Use the instructions to identify the class variables, constructors, and methods you need
  // Instance variables and arraylist
  public static Random rng;
  public ArrayList<Individual> population;
  public int n;
  public int k;
  public int r;
  public int c_0;
  public int c_max;
  public float m;
  public int g;

  /**
   * Constructs a genetic algorithm simulation with given parameters
   * @param n The number of individuals in each generation
   * @param k The number of winners allowed to reproduce each generation
   * @param r The number of evolution rounds to run
   * @param c_0 The initial chromosome size
   * @param c_max The maximum chromosome size
   * @param m The mutation rate per gene
   * @param g The number of possible gene states (called num_letters in Individual.java)
   */
  public GA_Simulation(int n, int k, int r, int c_0, int c_max, float m, int g) {
    this.population = new ArrayList<>();
    this.n = n;
    this.k = k;
    this.r = r;
    this.c_0 = c_0;
    this.c_max = c_max;
    this.m = m;
    this.g = g;
  }

  /**
   * Initializes population with randomly generated individuals
   */
  public void init() {
    //Initialize population of chosen size
    this.population = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      population.add(new Individual(c_0, g, rng));
    }
  }

  /**
   * Evolves population by selecting winners and creating a new generation
   */
  public void evolve(){
    rankPopulation(population);

    ArrayList<Individual> newGen = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int parentOneIndex = rng.nextInt(k);
      int parentTwoIndex = rng.nextInt(k);
      Individual parent1 = population.get(parentOneIndex);
      Individual parent2 = population.get(parentTwoIndex);
      newGen.add(new Individual(parent1, parent2, c_max, m, g, rng));
    }

    //updates first population with new generation
    this.population = newGen;
  }

  /** Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * @param roundNumber Which round of evolution are we on, from 0 to r
   * @param bestFitness Fitness of top-ranked (most fit) individual
   * @param kthFitness Fitness of kth-ranked individual
   * @param leastFitness Fitness of lowest-ranked (least fit) individual
   * @param best Individual with highest fitness
   */
  private void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /**
   * Describes current generation via printing fitness stats
   * @param roundNumber The current round number
   */
  public void describeGeneration(int roundNumber) {
    rankPopulation(population);

    Individual best = population.get(0);
    int bestFitness = best.getFitness();
    int leastFitness = population.get(population.size()-1).getFitness();
    int kIndex;

    if (k - 1 < population.size()) {
      kIndex = k - 1;
    } else {
      kIndex = population.size() - 1; //if there isn't enough individuals to find kth-one, use last individual as backup
    }

    int kthFitness = population.get(kIndex).getFitness();

    printGenInfo(roundNumber, bestFitness, kthFitness, leastFitness, best);
  }

  /** Provided method that sorts population by fitness score, best first
   * @param pop ArrayList of Individuals in the current generation
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

  /**
   * Runs complete genetic algorithm simulation from initialization to final generation
   */
  public void run() {
    this.init();
    this.describeGeneration(0); //initial population is at evolution round 0
    for (int round = 1; round <= r; round++) { //loops through evolution rounds 1 to r (inclusive)
      this.evolve();
      this.describeGeneration(round);
    }
    Individual solution = this.population.get(0);
    System.out.println("Final round best chromosome: " + solution);

  }

  /**
   * Main method to test various genetic algorithm simulations
   * @param args Command line arguments (can run code with a random seed)
   */
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
    //Tested three simulations with different parameters
    GA_Simulation simuOne = new GA_Simulation(100, 15, 100, 8, 20, .01f, 5);
    simuOne.run();

    GA_Simulation simuTwo = new GA_Simulation(50, 15, 50, 8, 10, .01f, 5);
    simuTwo.run();

    GA_Simulation simuThree = new GA_Simulation(25, 8, 15, 8, 7, .01f, 5);
    simuThree.run();
  }

}
