package com.nicholasmoreles.uva_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * UVa #280 - Vertex
 * @author Nicholas Moreles
 * 
 * Time limit: 	 3.000s
 * Running time: 2.689s
 * 
 * Approach: Create an adjacency matrix as the values are read in. When asked to find all
 * inaccessible vertices for a given vertex, perform a depth-first search and update a visited
 * matrix as vertices are accessed. When the search is complete, print out the unvisited vertices.
 *
 */
public class Vertex280 {
	
	public static void main(String[] args) {
		final Scanner in = new Scanner(System.in);
		
		int numVertices;
		while ((numVertices = in.nextInt()) != 0) {
			solve(in, createGraph(in, numVertices));
		}
		
		in.close();
	}

	private static boolean[][] createGraph(final Scanner in, final int numVertices) {
		final boolean[][] graph = new boolean[numVertices + 1][numVertices + 1]; // adjacency matrix
		
		int startVertex;
		while ((startVertex = in.nextInt()) != 0) {
			int endVertex;
			while ((endVertex = in.nextInt()) != 0) {
				graph[startVertex][endVertex] = true;
			}
		}
		return graph;
	}
	
	private static void solve(final Scanner in, final boolean[][] graph) {
		final int numToSolve = in.nextInt();
		for (int i = 0; i < numToSolve; i++) {
			printInaccessible(graph, in.nextInt());
		}
	}
	
	private static void printInaccessible(final boolean[][] graph, final int startVertex) {
		final boolean[] visited = new boolean[graph.length];
		
		// code necessarily duplicated here to avoid visiting 'vertex' in the first call
		for (int endVertex = 1; endVertex <= graph.length - 1; endVertex++) {
			if (graph[startVertex][endVertex]) {
				visit(endVertex, graph, visited);
			}
		}
		
		final List<Integer> inaccessibleVertices = new ArrayList<>();
		for (int i = 1; i <= graph.length - 1; i++) {
			if (!visited[i]) {
				inaccessibleVertices.add(i);
			}
		}
		
		System.out.print(inaccessibleVertices.size());
		for (final int inaccessibleVertex : inaccessibleVertices) {
			System.out.print(" " + inaccessibleVertex);
		}
		System.out.println();
	}
	
	// depth-first search to find all connected vertices
	private static void visit(final int startVertex, final boolean[][] graph, final boolean[] visited) {
		if (!visited[startVertex]) {
			visited[startVertex] = true;
			for (int endVertex = 1; endVertex <= graph.length - 1; endVertex++) {
				if (graph[startVertex][endVertex]) {
					visit(endVertex, graph, visited);
				}
			}
		}
	}
}