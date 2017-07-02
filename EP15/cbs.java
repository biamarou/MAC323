import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import java.util.LinkedList;
import java.util.Arrays;

public class cbs {
	
	int N, M, K;
	int[] executives;
	int ministry;
	SeparateChainingHashST<String, Integer> airports;
	String[] index;
	DijkstraAllPairsSP allPaths;

	EdgeWeightedDigraph graph;
	LinkedList<String> safe;

	cbs () {

		N = StdIn.readInt();
		M = StdIn.readInt();
		K = StdIn.readInt();

		executives = new int[K];
		index = new String[N];
		airports = new SeparateChainingHashST<>();
		graph = new EdgeWeightedDigraph(N);

		String from, to;
		int hours; 

		for (int i = 0; i < M; i++) {
			
			from = StdIn.readString();
			to = StdIn.readString();
			hours = StdIn.readInt();

			if (airports.size() != N) {
				if (!airports.contains(from)) {
					airports.put(from, (Integer)airports.size());
					index[airports.size() - 1] = from;
				}
				if (!airports.contains(to)) {
					airports.put(to, (Integer)airports.size());
					index[airports.size() - 1] = to;
				}
			}
			graph.addEdge(new DirectedEdge(airports.get(from).intValue(), 
				          airports.get(to).intValue(), (double)hours));
		}

		for (int i = 0; i < K; i++) {
			executives[i] = airports.get(StdIn.readString()).intValue();
		}

		ministry = airports.get(StdIn.readString()).intValue();

		allPaths = new DijkstraAllPairsSP(graph);
		safe = safePaths();
	}

	private LinkedList<String> safePaths () {
		double ministryDist;
		boolean safe = true;
		LinkedList<String> safePlaces = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			if (i != ministry) {
				safe = true;
				ministryDist = allPaths.dist(ministry, i);
				for (int j = 0; j < K && safe; j++) {
					if (allPaths.dist(executives[j], i) > ministryDist)
						safe = false;
				}
				if (safe) {
					safePlaces.add(index[i]);
				}
			}
		}

		return safePlaces;
	}

	public void printSafePlaces () {

		if (safe.isEmpty()) 
			StdOut.println("VENHA COMIGO PARA CURITIBA");

		else {
			String[] s = (String[])safe.toArray();
			Arrays.sort(s);
			for (int i = 0; i < s.length; i++)
				StdOut.println(s[i]);
		}

	}


	public static void main (String[] args) {
		cbs path = new cbs();
		path.printSafePlaces();
	}
}