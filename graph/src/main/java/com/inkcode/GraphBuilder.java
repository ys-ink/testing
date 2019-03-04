package com.inkcode;

import com.google.common.graph.Graph;
import com.google.common.graph.MutableGraph;

@SuppressWarnings({"UnstableApiUsage", "WeakerAccess", "UnusedReturnValue"})
public class GraphBuilder<T> {

  private final MutableGraph<T> graph;

  private T lastAddedNode;
  private T secondLastAddedNode;

  public GraphBuilder(final MutableGraph<T> graph) {
    this.graph = graph;
  }

  public static <T> GraphBuilder<T> directed(final Class<T> clazz) {
    return new GraphBuilder<>(com.google.common.graph.GraphBuilder.directed().build());
  }

  public GraphBuilder<T> addNode(final T node) {
    graph.addNode(node);
    secondLastAddedNode = lastAddedNode;
    lastAddedNode = node;
    return this;
  }

  public Graph<T> build() {
    return graph;
  }

  public GraphBuilder<T> connectNodes() {
    if (secondLastAddedNode == null || lastAddedNode == null) {
      throw new IllegalArgumentException("Need to nodes to connect!");
    }

    graph.putEdge(secondLastAddedNode, lastAddedNode);
    return this;
  }
}
