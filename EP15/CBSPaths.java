import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import java.lang.Iterable;
import java.util.Comparator;

public class CBSPaths {
	
	int N;
	Edge[] graph;

	int[] executives;
	int ministry;
	SeparateChainingHashST<String, Integer> airports;
	String[] index;

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

	CBSPaths () {
		int M, K;

		N = StdIn.readInt();
		M = StdIn.readInt();
		K = StdIn.readInt();

		graph = new Edge[N];
		executives = new int[K];
		airports = new SeparateChainingHashST<>();
		index = new String[N];

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

			graph(airports.get(from).intValue(), airports.get(to).intValue(), hours);			
		}

		for (int i = 0; i < K; i++) {
			executives[i] = airports.get(StdIn.readString()).intValue();
		}

		ministry = airports.get(StdIn.readString()).intValue();

		Iterable<Integer> testing = dijkstra(graph[executives[0]]);
		StdOut.println(index[executives[0]]);
		for (Integer i : testing) {
			StdOut.println(index[i.intValue()]);
		}
		StdOut.println();

	}

	private void graph (int from, int to, double weight) {
		if (graph[from] == null) {
			graph[from] = new Edge(from, 0.);
			graph[from].next = new Edge(to, weight);
		}
		else {
			Edge e = graph[from];
			while(e.next != null) e = e.next;
			e.next = new Edge(to, weight);
		}
	}

	public void printGraph() {

		for (int i = 0; i < N; i++) {
			Edge e = graph[i].next;
			String s = "";
			while (e != null) {
				 s = s + " " + index[e.to];
				 e = e.next;
			}

			StdOut.println(index[i]);
			StdOut.println(s);
		}
	}

	private class CompareEdge implements Comparator<Edge>{
		public int compare (Edge a, Edge b) {
			if (a.weight > b.weight) return 1;
			else if (a.weight < b.weight) return -1;
			return 0;
		}
	}

	private Iterable<Integer> dijkstra (Edge search) {
		MinPQ<Edge> onGoing = new MinPQ<>(new CompareEdge());
		Stack<Integer> path = new Stack<>();
		double[] distTo = new double[N];
		int[] father = new int[N];
		boolean[] marked = new boolean[N];
		int last = -1;

		for (int i = 0; i < N; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
			marked[i] = false;
		}
		
		onGoing.insert(search);
		father[search.to] = search.to;
		distTo[search.to] = 0;

		while(!onGoing.isEmpty()) {
			Edge e = graph[onGoing.min().to];
			Edge son = e.next;
			
			while (son != null) {
				
				if (!marked[son.to] && son.weight + distTo[e.to] < distTo[son.to]) {
					
					distTo[son.to] = son.weight + distTo[e.to];
					father[son.to] = e.to;
				}
				
				onGoing.insert(son);
				son = son.next;
			}

			last = onGoing.delMin().to;
			marked[last] = true;
		}

		while (last != -1 && father[last] != last) {
			path.push((Integer)last);
			last = father[last];
		}

		return path;		
	}

	public static void main (String[] args) {
		CBSPaths path = new CBSPaths();
		path.printGraph();
	}
}