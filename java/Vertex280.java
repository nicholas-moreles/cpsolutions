package com.nicholasmoreles.uva_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * UVa #280 - Vertex
 * @author Nicholas Moreles
 * 
 * Time limit: 	 3.000s
 * Running time: 2.075s
 * 
 * Approach: Create an adjacency matrix as the values are read in. When asked to find all
 * inaccessible vertices for a given vertex, perform a depth-first search and update a visited
 * matrix as vertices are accessed. When the search is complete, print out the unvisited vertices.
 *
 */
public class Vertex280 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numVertices;
		while ((numVertices = Integer.parseInt(br.readLine())) != 0) {
			solve(br, createGraph(br, numVertices));
		}
		
		br.close();
	}

	private static boolean[][] createGraph(final BufferedReader br, final int numVertices) throws NumberFormatException, IOException {
		final boolean[][] graph = new boolean[numVertices + 1][numVertices + 1]; // adjacency matrix
		
		int startVertex;
		StringTokenizer st = new StringTokenizer(br.readLine());
		while ((startVertex = Integer.parseInt(st.nextToken())) != 0) {
			int endVertex;
			while ((endVertex = Integer.parseInt(st.nextToken())) != 0) {
				graph[startVertex][endVertex] = true;
			}
			st = new StringTokenizer(br.readLine());
		}
		return graph;
	}
	
	private static void solve(final BufferedReader br, final boolean[][] graph) throws IOException {
		final StringTokenizer st = new StringTokenizer(br.readLine());
		final int numToSolve = Integer.parseInt(st.nextToken());
		for (int i = 0; i < numToSolve; i++) {
			printInaccessible(graph, Integer.parseInt(st.nextToken()));
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