import jason.asSyntax.*;

import jason.environment.*;

public class Abadia extends Environment {
	
	// any class members needed...
	int[] posLibro = {5,2};
	
	
	@Override
	public void init(String[] args) {
		
		// setting initial (global) percepts ...
		
		addPercept(Literal.parseLiteral("p(a)"));
		
		// if open-world is begin used, there can be
		
		// negated literals such as ...
		
		addPercept(Literal.parseLiteral("~q(b)"));
		
		// if this is to be perceived only by agent ag1
		
		addPercept("ag1", Literal.parseLiteral("p(a)"));
		
	}
	
	@Override
	public void stop() {
		
		// anything else to be done by the environment when
		
		// the system is stopped...
		
	}
	
	@Override
	
	public boolean executeAction(String ag, Structure act) {
		
		// this is the most important method, where the
		
		// effects of agent actions on perceptible properties
		
		// of the environment is defined
		return true;
		
	}
	
}