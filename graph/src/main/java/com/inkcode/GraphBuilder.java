package com.inkcode;

import com.google.common.graph.Graph;
import com.google.common.graph.MutableGraph;

import java.util.Stack;

@SuppressWarnings({"UnstableApiUsage", "WeakerAccess", "UnusedReturnValue"})
public class GraphBuilder<T> {

  private final MutableGraph<T> graph;
  private final Stack<T> nodeStack = new Stack<>();

  private boolean graphBuilt;

  public GraphBuilder(final MutableGraph<T> graph) {
    this.graph = graph;
  }

  @SuppressWarnings("unused")
  public static <T> GraphBuilder<T> directed(final Class<T> clazz) {
    return new GraphBuilder<>(com.google.common.graph.GraphBuilder.directed().build());
  }

  public static <T> GraphBuilder<T> directed() {
    return new GraphBuilder<>(com.google.common.graph.GraphBuilder.directed().build());
  }

  public GraphBuilder<T> addNode(final T node) {
    graph.addNode(node);
    if (!nodeStack.isEmpty()) {
      graph.putEdge(nodeStack.peek(), node);
    }
    nodeStack.push(node);
    return this;
  }

  public Graph<T> build() {
    if (graphBuilt) {
      throw new UnsupportedOperationException("Graph already built!");
    }
    if (!nodeStack.isEmpty()) {
      throw new UnsupportedOperationException(
          "done() not called enough. Expected " + nodeStack.size() + " more done() call");
    }

    graphBuilt = true;
    return graph;
  }

  public GraphBuilder<T> done() {
    nodeStack.pop();
    return this;
  }
}
