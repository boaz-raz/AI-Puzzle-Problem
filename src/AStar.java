import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class AStar
{
	 // Initialization function for 8puzzle A*Search

    public static void search(int[] board, boolean d, char heuristic)
	{
		Node root = new Node(new PuzzleState(board));
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);

		int searchCount = 1; // counter for number of iterations

		while (!q.isEmpty()) // while the queue is not empty
		{
			Node tempNode = (Node) q.poll();


			if (!tempNode.getCurState().isGoal())
			{
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors();
				ArrayList<Node> nodeSuccessors = new ArrayList<Node>();

				for (int i = 0; i < tempSuccessors.size(); i++)
				{
					Node checkedNode;
					// make the node
					if (heuristic == 'o')
					{

						checkedNode = new Node(tempNode,
								tempSuccessors.get(i), tempNode.getCost()
										+ tempSuccessors.get(i).findCost(),
								((PuzzleState) tempSuccessors.get(i))
										.getOutOfPlace());
					}
					else
					{
						// See previous comment
						checkedNode = new Node(tempNode,
								tempSuccessors.get(i), tempNode.getCost()
										+ tempSuccessors.get(i).findCost(),
								((PuzzleState) tempSuccessors.get(i))
										.getManDist());
					}

					if (!checkRepeats(checkedNode))
					{
						nodeSuccessors.add(checkedNode);
					}
				}


				if (nodeSuccessors.size() == 0)
					continue;

				Node lowestNode = nodeSuccessors.get(0);

				for (int i = 0; i < nodeSuccessors.size(); i++)
				{
					if (lowestNode.getFCost() > nodeSuccessors.get(i)
							.getFCost())
					{
						lowestNode = nodeSuccessors.get(i);
					}
				}

				int lowestValue = (int) lowestNode.getFCost();

				for (int i = 0; i < nodeSuccessors.size(); i++)
				{
					if (nodeSuccessors.get(i).getFCost() == lowestValue)
					{
						q.add(nodeSuccessors.get(i));
					}
				}

				searchCount++;
			}
			else
			{
				Stack<Node> solutionPath = new Stack<Node>();
				solutionPath.push(tempNode);
				tempNode = tempNode.getParent();

				while (tempNode.getParent() != null)
				{
					solutionPath.push(tempNode);
					tempNode = tempNode.getParent();
				}
				solutionPath.push(tempNode);

				// The size of the stack before looping through and emptying it.
				int loopSize = solutionPath.size();

				for (int i = 0; i < loopSize; i++)
				{

				}
				System.out.println("The cost was: " + tempNode.getCost());
				if (d)
				{
					System.out.println("The number of nodes examined: "
							+ searchCount);
				}

				System.exit(0);
			}
		}

		System.out.println("Error! No solution found!");

	}

	private static boolean checkRepeats(Node n)
	{
		boolean retValue = false;
		Node checkNode = n;

		while (n.getParent() != null && !retValue)
		{
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				retValue = true;
			}
			n = n.getParent();
		}

		return retValue;
	}

}
