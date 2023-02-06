import java.util.ArrayList;
import java.util.Arrays;


public class GaleShapley {
		
	public static void galeShapleyAlgorithm(ArrayList<ArrayList<Integer>> employers, 
			ArrayList<ArrayList<Integer>> candidates) { 
		/* occ for occupied */
		ArrayList<Integer> occEmployers = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
		ArrayList<Integer> occCandidates = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
	
		while (occEmployers.contains(0)) {
			int freeCompany = occEmployers.indexOf(0) + 1;
			int priorityCandidate = employers.get(freeCompany - 1).get(0);
			employers.get(freeCompany - 1).remove(0);
			
			if (occCandidates.get(priorityCandidate - 1) == 0) {
				occCandidates.set(priorityCandidate - 1, freeCompany);
				occEmployers.set(freeCompany - 1, priorityCandidate);
			} else {
				int currCompany = occCandidates.get(priorityCandidate - 1);
				int currCompanyIndex = 
						candidates.get(priorityCandidate - 1).indexOf(currCompany);
				int freeCompanyIndex = 
						candidates.get(priorityCandidate - 1).indexOf(freeCompany);
				if (currCompanyIndex < freeCompanyIndex) {
					continue;
				} else {
					occEmployers.set(freeCompany - 1, priorityCandidate);
					occEmployers.set(currCompany - 1, 0);
					occCandidates.set(priorityCandidate - 1, freeCompany);
				}
			}
		}
		
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
