package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * UVa #10305 - Ordering Tasks
 * @author Nicholas Moreles
 * 
 * Time limit: 	 3.000s
 * Running time: 0.225s
 * 
 * Problem: http://uva.onlinejudge.org/external/103/10305.pdf
 * 
 * Approach: Use a topological sorting algorithm to order a set of tasks with dependencies.
 *
 */
public class OrderingTasks10305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// get first instance
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		while (!(m == 0 && n == 0)) {
			boolean[][] graph = generateGraph(br, m, n);
			Queue<Integer> startNodes = getStartNodes(graph);
			printTaskOrder(findTaskOrder(graph, startNodes));
			
			// get next instance, m == n == 0 if done
			line = br.readLine();
			st = new StringTokenizer(line);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	
	private static boolean[][] generateGraph(BufferedReader br, int m, int n) throws IOException {
		boolean[][] graph = new boolean[n + 1][n + 1];
		for (int k = 0; k < m; k++) {
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph[i][j] = true;
		}
		return graph;
	}

	private static Queue<Integer> getStartNodes(boolean[][] graph) {
		Queue<Integer> startNodes = new LinkedList<>();
		
		/*
		 * Add the index of all columns in the graph that do not contain true values. This set will
		 * contain only those indices which do not have an edge leading towards them, indicating
		 * that this set will have no dependencies.
		 */
		for (int col = 1; col < graph[0].length; col++) {
			boolean possibleStartNode = true;
			int row = 1;
			while (possibleStartNode && row < graph.length) {
				possibleStartNode = !graph[row][col];
				row++;
			}
			if (possibleStartNode) {
				startNodes.add(col);
			}
		}
		return startNodes;
	}

	// makes use of topological sorting algorithm to find task order
	private static List<Integer> findTaskOrder(boolean[][] graph, Queue<Integer> startNodes) {
		List<Integer> taskOrder = new ArrayList<>();
		
		while (!startNodes.isEmpty()) {
			int currentNode = startNodes.remove();
			taskOrder.add(currentNode);
			
			/*
			 * remove all edges from the graph that originate at the current node, and add any nodes
			 * that no longer have incoming edges to the queue of nodes
			 */
			for (int col = 1; col < graph[0].length; col++) {
				if (!taskOrder.contains(col) && !startNodes.contains(col)) {
					graph[currentNode][col] = false;
					if (hasNoIncomingEdges(graph, col)) {
						startNodes.add(col);
					}
				}
			}
		}
		return taskOrder;
	}
	
	private static boolean hasNoIncomingEdges(boolean[][] graph, int col) {
		boolean noIncomingEdges = true;
		int row = 1;
		while (noIncomingEdges && row < graph.length) {
			noIncomingEdges = !graph[row][col];
			row++;
		}
		return noIncomingEdges;
	}
	
	private static void printTaskOrder(List<Integer> taskOrder) {
		StringBuilder sb = new StringBuilder();
		for (int task : taskOrder) {
			sb.append(task);
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
