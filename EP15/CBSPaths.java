import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Queue;

public class CBSPaths {
	
	int N;
	int M;
	int K;

	Edge[] graph;
	double[] distTo;
	Queue<Integer> onGoing;
	SeparateChainingHashST<String, Integer> airports;

	CBSPaths (int n, int m, int k) {
		N = n; M = m; K = k;

		graph = new Edge[N];
		distTo = new double[N];
		onGoing = new Queue<> ();
		airports = new SeparateChainingHashST<>();

		String from, to;
		int hours; 

		for (int i = 0; i < M; i++) {
			
			from = StdIn.readString();
			to = StdIn.readString();
			hours = StdIn.readInt();

			if (n != N) {
				if (!airports.contains(from))
					airports.put(from, (Integer)airports.size());
				if (!airports.contains(to))
					airports.put(to, (Integer)airports.size());
			}

			graph(airports.get(from).intValue(), airports.get(to).intValue(), hours);			
		}
	}

	private class Edge {
		int to;
		double weight;
		Edge next;

		Edge (int v, double w) {
			to = v;
			weight = w;
			next = null;
		}
	}

	private void graph (int from, int to, double weight) {
		if (graph[from] == null) {
			graph[from] = new Edge(to, weight);
		}
		else {
			Edge e = graph[from];
			while(e.next != null) e = e.next;
			e.next = new Edge(to, weight);
		}
	}


	public static void main (String[] args) {

	}
}
