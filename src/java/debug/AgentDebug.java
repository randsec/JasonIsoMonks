package debug;

import java.util.ArrayList;
import java.util.Iterator;

import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSemantics.ArithFunction;
import jason.asSyntax.Literal;

public class AgentDebug {
	
	private Agent ag;
	private ArrayList<String> functionsName;
	private ArrayList<Integer> functionsArity;
	
	public AgentDebug(Agent ag) {
		this.functionsName = new ArrayList<String>();
		this.functionsArity = new ArrayList<Integer>();
		this.ag = ag;
	}
	
	public boolean addFunction(String functionName, int functionArity) {
		return this.functionsName.add(functionName) && 
				this.functionsArity.add(functionArity);
	}
	
	public boolean delFunction(String functionName, int functionArity) {
		Boolean success = true;
		int indexName = this.functionsName.indexOf(functionName);
		int indexArity = this.functionsArity.indexOf(functionName);
		if (indexName != -1 && indexArity != -1 ) {
			this.functionsName.remove(indexName);
			this.functionsArity.remove(indexArity);
		} else { success = false; }
		return success;
	}
	
	public void showAgentInfo() {
		String tab = "      ";
		System.out.println("=== Agent Info ===");
		System.out.println("AgSource: \"" + this.ag.getASLSrc() + "\"");
		//System.out.println("Logger Name: " + this.ag.getLogger().getName());
		if ( this.functionsName != null && this.functionsName.size() > 0 ) {
			System.out.println("Functions: ");
			for (int i = 0; i < this.functionsName.size(); i++) {
				ArithFunction aFun = this.ag.getFunction(this.functionsName.get(i), this.functionsArity.get(i));
				if(aFun != null) {
					System.out.println(tab+"Function " + (i + 1) + ": " + aFun.toString());
					System.out.println(tab+tab+"Name: " + aFun.getName());
					System.out.println(tab+tab+"AllowUngroundTerms: " + aFun.allowUngroundTerms());
				} else { 
					System.out.println(tab+"Function " + (i + 1) + ": named \"" + 
					this.functionsName.get(i) + "\", with arity " + 
						this.functionsArity.get(i) + " not found"); 
				}
			}
		} else { System.out.println("No Function added"); }		
		ArrayList<Iterable<Literal>> BelsBases = new ArrayList<Iterable<Literal>>();
		BelsBases.add(this.ag.getInitialBels()); BelsBases.add(this.ag.getBB());
		for (int i = 0; i < BelsBases.size(); i++) {
			String wrapper, element;
			if (i == 0) { wrapper = "InitialBels"; element = "InitialBel"; } 
			else { wrapper = "BeliefBase"; element = "Belief"; }
			if (BelsBases.get(i).iterator().hasNext()) {
				System.out.println(wrapper + ": ");
				Iterator<Literal> iter = BelsBases.get(i).iterator();
				int pos = 0;
				while (iter.hasNext()) {
					Literal l = iter.next(); pos++;
					System.out.println(tab+ element + " " + pos + ":");
					System.out.println(tab+tab+"Functor: " + l.getFunctor());
					System.out.println(tab+tab+"Arity: " + l.getArity());
					System.out.println(tab+tab+"ErrorMsg: " + l.getErrorMsg());
					System.out.println(tab+tab+"Annots: " + l.getAnnots(l.getFunctor()));
				}
			} else { System.out.println(wrapper + " Empty"); }
		}
		System.out.println("==================");
	}
	
	public void showAgArchInfo(AgArch ag) {
		System.out.println("=== AgArch Info ===");
		System.out.println("AgName: " + ag.getAgName());
		System.out.println("isRunning: " + ag.isRunning());
		System.out.println("canSleep: " + ag.canSleep());
		System.out.println("toString: " + ag.toString());
	}
}
