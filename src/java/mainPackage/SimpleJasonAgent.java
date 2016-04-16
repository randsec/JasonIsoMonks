package mainPackage;
import java.util.ArrayList;
import java.util.List;

import jason.JasonException;
import jason.architecture.AgArch;
import jason.asSemantics.ActionExec;
import jason.asSemantics.Agent;
import jason.asSemantics.Circumstance;
import jason.asSemantics.TransitionSystem;
import jason.asSyntax.Literal;
import jason.runtime.Settings;

public class SimpleJasonAgent extends AgArch {
    public SimpleJasonAgent(String aslSrc) throws JasonException {
         // set up the Jason agent and the 
         // TransitionSystem (the BDI Engine where the AgentSpeak 
         // Semantics is implemented)

         Agent ag = new Agent();
         new TransitionSystem(ag, new Circumstance(), new Settings(), this);
         ag.initAg("src/asl/" + aslSrc); // demo.asl is the file containing the code of the agent
         this.run();
    }
    
    public String getAgName() {
        return super.getAgName();
    }

    public void run() {
        while (isRunning()) {
          // calls the Jason engine to perform one reasoning cycle
          getTS().reasoningCycle();
        }
    }

    // this method just add some perception for the agent
    public List<Literal> perceive() {
        List<Literal> l = new ArrayList<Literal>();
        l.add(Literal.parseLiteral("x(10)"));
        return l;
    }

    // this method gets the agent actions
    public void act(ActionExec action, List<ActionExec> feedback) {
        getTS().getLogger().info("Agent " + getAgName() + 
                             " is doing: " + action.getActionTerm());
        // return confirming the action execution was OK
        action.setResult(true);
        feedback.add(action);
    }

    public boolean canSleep() {
        return true;
    }

    public boolean isRunning() {
        return true;
    }

    public void sleep() {
    	try {   Thread.sleep(1000); } catch (InterruptedException e) {}
    }
    
    public void sendMsg(jason.asSemantics.Message m) throws Exception {
    }

    public void broadcast(jason.asSemantics.Message m) throws Exception {
    }

    public void checkMail() {
    }
}