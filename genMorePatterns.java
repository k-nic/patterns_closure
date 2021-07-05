import java.io.FileReader;
import java.util.*;

public class genMorePatterns {
	ArrayList<Pattern> patterns = new ArrayList<Pattern>();
	
	public static void main(String args[]) throws Exception{
		genMorePatterns q = new genMorePatterns();
		q.start();
		q.generateMorePatterns();
		q.goNext();
	}
	
	void goNext(){
		System.out.println("Initial Roads");		
		int n = patterns.size();
		Graph g = new Graph(n);
		for (int i=0;i<n-1;i++)
		for (int j=i+1;j<n;j++){
			Pattern pi = patterns.get(i);
			Pattern pj = patterns.get(j);
			if (pi.isDetalization(pj)) g.add(i+1,j+1);
			else if (pj.isDetalization(pi)) g.add(j+1,i+1);
		}
		
		g.run();
		g.printFinal();
	}
	
	
	void generateMorePatterns(){		
		System.out.println("Logs");
		
		for(int i=0;i<patterns.size();i++){					
			Pattern q = patterns.get(i);
			q = q.getDecremented();
			if (q!=null && !contains(q)){				
				patterns.add(q); 
				System.out.println(q.a.toString());
			}
		}
		
		
		for(int i=0;i<patterns.size();i++){
			System.out.println(i+" "+patterns.size());
			Pattern q = patterns.get(i);													
			for (int j=0;j<patterns.size();j++){
				Pattern pj = patterns.get(j);
				Pattern p2 = pj.onTail(q); 
				if (p2!=null && !contains(p2)) {
					patterns.add(p2);
					System.out.println(pj.a.toString() + " "+q.a.toString()+"->"+p2.a.toString());
				}					
										
				p2 = q.onTail(pj);
				if (p2!=null && !contains(p2)) {
					patterns.add(p2);												
					//System.out.println(pj.a.toString() + " "+q.a.toString()+"->"+p2.a.toString());
				}				
			}																		
		}
		
		System.out.println("Patterns");
		for(int i=0;i<patterns.size();i++) System.out.println((i+1)+" " +patterns.get(i).a.toString());		
	}
	
	
	void start() throws Exception{
		Scanner in = new Scanner(new FileReader("alphabet.txt"));
		while (in.hasNextLine()){
			String st = in.nextLine();			
		}
		in.close();
		
		//
		in = new Scanner(new FileReader("pi2.txt"));
		while(in.hasNextLine()){
			String st = in.nextLine();
			List<String> a = Arrays.asList(st.split(","));
			patterns.add(new Pattern(a));			
		}
		in.close();			
	}
	
	boolean contains(Pattern p2){
		for (Pattern p : patterns)
			if (p.a.equals(p2.a)) return true;
		return false;
	}
	
}

class Pattern{
	List<String> a = null;
	Pattern(List<String> a){
		this.a = a;		
	}
	
	boolean isDetalization(Pattern p2){
		if (a.size() < p2.a.size()) return false;
		int n = a.size();
		int m = p2.a.size();
		for (int i=1;i<=m;i++)
			if (!(a.get(n-i).equals(p2.a.get(m-i)) ||  p2.a.get(m-i).equals("*")))
				return false;
		return true;
	}
	
	Pattern onTail(Pattern p2){
		if (a.size() < p2.a.size()) return null;
		ArrayList<String> d = new ArrayList<String>();
		int n = a.size();
		int m = p2.a.size();
		for (int i=1;i<=m;i++)
			if (a.get(n-i).equals("*") || a.get(n-i).equals(p2.a.get(m-i))) d.add(0,p2.a.get(m-i));
			else if (p2.a.get(m-i).equals("*")) d.add(0,a.get(n-i));
			else return null;
		
		d.addAll(0,a.subList(0, n-m));
		return new Pattern(d);
	}
	
	Pattern getDecremented(){
		if (a.size() > 1) {
			List<String> d = a.subList(0, a.size()-1);
			return new Pattern(d);
		}
		
		return null;
	}	
}
