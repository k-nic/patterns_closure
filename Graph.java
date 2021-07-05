import java.util.*;

public class Graph {
	int maxn = 100001,n;
	Node[] a = new Node[maxn];
	int[] w = new int[maxn];
	boolean[] used = new boolean[maxn];
	int[][] way;
	
	public static void main(String args[]){
		Graph g = new Graph(5);
		g.add(1,2);
		g.add(1,5);
		g.add(1,4);
		g.add(2,3);		
		g.add(3,4);
		g.add(5,2);
		g.run();
		g.printFinal();
	}
	
	
	public Graph(int n){
		this.n = n;
		for (int i=0;i<maxn;i++)
			a[i] = new Node();
		
		way = new int[n+1][n+1];
	}
	
	void add(int v, int u){
		System.out.println("road "+v+" -> "+u);
		a[v].children.add(u);
	}
	
	void run(){		
		for (int v=1;v<=n;v++){
			Arrays.fill(used, false);
			dfs(v,v);
		}
	}
		
	void printFinal(){
		System.out.println("Final Graph");
		for (int v=1;v<=n;v++)
			for (int u : a[v].children){
				String pair = v+"-"+u;
				if (way[v][u]!=3) System.out.println(pair);
				else System.out.println("removed "+pair);
			}
	}
	
	
	void dfs(int p, int v){
		used[v] = true;
		for (int u : a[v].children){						
			int k = way[p][u];						
			if (v == p){
				if (k == 0) way[p][u] = 1;
				else if (k == 2) way[p][u] = 3;
			} else {
				if (k == 0) way[p][u] = 2;
				else if (k == 1) way[p][u] = 3;
			}			
			if (!used[u]) dfs(p,u);
		}
		
	}
	
}

class Node{
	ArrayList<Integer> children = new ArrayList<Integer>();
}
