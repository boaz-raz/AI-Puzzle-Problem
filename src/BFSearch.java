import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSearch
{
	public static void search(int[] board, boolean d)
	{
		Node root = new Node(new PuzzleState(board));
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(root);

		performSearch(queue, d);
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

	public static void performSearch(Queue<Node> q, boolean d)
	{
		int searchCount = 1; // counter for number of iterations

		while (!q.isEmpty()) // while the queue is not empty
		{
			Node tempNode = (Node) q.poll();

			if (!tempNode.getCurState().isGoal()) // if tempNode is not the goal
													// state
			{
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors(); // generate tempNode's immediate
											// successors
				for (int i = 0; i < tempSuccessors.size(); i++)
				{
					Node newNode = new Node(tempNode,
							tempSuccessors.get(i), tempNode.getCost()
									+ tempSuccessors.get(i).findCost(), 0);

					if (!checkRepeats(newNode))
					{
						q.add(newNode);
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

				int loopSize = solutionPath.size();

				for (int i = 0; i < loopSize; i++)
				{
					tempNode = solutionPath.pop();
				}
				System.out.println("The cost was: " + tempNode.getCost());
				if (d)
				{
					System.out.println("The number of nodes examined: "
							+ searchCount);
				}

				System.exit(0);
				//Main.startGame();;
			}
		}

		System.out.println("Error! No solution found!");
	}
}
