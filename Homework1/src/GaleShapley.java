import java.util.ArrayList;
import java.util.Arrays;


public class GaleShapley {
		
	public static void galeShapleyAlgorithm(ArrayList<ArrayList<Integer>> employers, 
			ArrayList<ArrayList<Integer>> candidates) { 
		
		/* Create empty ArrayList that hold each employer/candidate's current candidate/employer.
		   0 is the default value where an employer/candidate does not have a current 
		   candidate/employer. Any other number indicates the employer/candidate. Each (index + 1)
		   specifies the current company (index 0 is company 1). */
		ArrayList<Integer> occEmployers = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
		ArrayList<Integer> occCandidates = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
	
		/* Iterate as long as an employer that does not have a candidate exists */ 
		while (occEmployers.contains(0)) {
			/* Get the first employer between 1-7 that does not have a current candidate. 
			   Retrieve its topmost candidate of a employer and then pop it from their preference
			   list because it is no longer viable after processing it */
			int freeEmployer = occEmployers.indexOf(0) + 1;
			int priorityCandidate = employers.get(freeEmployer - 1).get(0);
			employers.get(freeEmployer - 1).remove(0);
			
			if (occCandidates.get(priorityCandidate - 1) == 0) {
				/* If their top candidate does not have an employer, set both to occupied 
				   with each other */
				occCandidates.set(priorityCandidate - 1, freeEmployer);
				occEmployers.set(freeEmployer - 1, priorityCandidate);
			} else {
				/* Otherwise, retrieve the current employer of the candidate and compare the
				   two indices. The lower-numbered index indicating most preferred employer
				   will be selected. */
				int currEmployer = occCandidates.get(priorityCandidate - 1);
				int currEmployerIndex = 
						candidates.get(priorityCandidate - 1).indexOf(currEmployer);
				int freeEmployerIndex = 
						candidates.get(priorityCandidate - 1).indexOf(freeEmployer);
				if (currEmployerIndex < freeEmployerIndex) {
					/* The candidate's current employer's index is less than the free
					   employer's, so they prefer the current employer over the free
					   employer */
					continue;
				} else {
					/* The candidate decides the free employer is better. Then, 
					   the appropriate changes are made in the occupied lists, and 
					   the current employer is set to 0 so that it can be recognized
					   as a free employer later on */
					occEmployers.set(freeEmployer - 1, priorityCandidate);
					occEmployers.set(currEmployer - 1, 0);
					occCandidates.set(priorityCandidate - 1, freeEmployer);
				}
			}
		}
		
		/* Printing out the choices */
		System.out.println(occEmployers);
		System.out.println(occCandidates);
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> employers = new ArrayList<>();
		employers.add(new ArrayList<>(Arrays.asList(1,6,4,3,2,5,7)));
		employers.add(new ArrayList<>(Arrays.asList(4,3,7,1,6,5,2)));
		employers.add(new ArrayList<>(Arrays.asList(1,6,4,2,3,5,7)));
		employers.add(new ArrayList<>(Arrays.asList(1,3,6,2,4,7,5)));
		employers.add(new ArrayList<>(Arrays.asList(1,6,7,2,3,5,4)));
		employers.add(new ArrayList<>(Arrays.asList(4,7,6,1,5,3,2)));
		employers.add(new ArrayList<>(Arrays.asList(1,5,4,3,6,7,2)));

		ArrayList<ArrayList<Integer>> candidates = new ArrayList<>();
		candidates.add(new ArrayList<>(Arrays.asList(3,7,4,2,1,5,6)));
		candidates.add(new ArrayList<>(Arrays.asList(4,2,7,5,1,3,6)));
		candidates.add(new ArrayList<>(Arrays.asList(7,2,3,4,1,6,5)));
		candidates.add(new ArrayList<>(Arrays.asList(4,6,2,3,1,7,5)));
		candidates.add(new ArrayList<>(Arrays.asList(1,7,5,2,4,3,6)));
		candidates.add(new ArrayList<>(Arrays.asList(5,4,1,7,3,2,6)));
		candidates.add(new ArrayList<>(Arrays.asList(7,6,3,4,5,1,2)));		
	
		galeShapleyAlgorithm(new ArrayList<>(employers), candidates);
	}
}
