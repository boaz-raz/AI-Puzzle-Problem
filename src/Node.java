
public class Node
{

	private State curState;
	private Node parent;
	private double cost; // cost to get to this state
	private double hCost; // heuristic cost
	private double fCost; // f(n) cost

	public Node(State s)
	{
		curState = s;
		parent = null;
		cost = 0;
		hCost = 0;
		fCost = 0;
	}

	public Node(Node prev, State s, double c, double h)
	{
		parent = prev;
		curState = s;
		cost = c;
		hCost = h;
		fCost = cost + hCost;
	}

	public State getCurState()
	{
		return curState;
	}

	public Node getParent()
	{
		return parent;
	}

	public double getCost()
	{
		return cost;
	}

	public double getHCost()
	{
		return hCost;
	}

	public double getFCost()
	{
		return fCost;
	}
}
