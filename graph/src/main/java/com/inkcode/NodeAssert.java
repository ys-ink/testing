package com.inkcode;

import com.google.common.graph.Graph;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

@SuppressWarnings({"UnstableApiUsage", "WeakerAccess", "unused", "UnusedReturnValue"})
public class NodeAssert<T> extends AbstractAssert<NodeAssert<T>, Graph<T>> {
  private final T node;

  public NodeAssert(final Graph<T> graph, final T node) {
    super(graph, NodeAssert.class);

    this.node = node;
  }

  public static <T> NodeAssert<T> assertThat(final Graph<T> graph, final T node) {
    return new NodeAssert<>(graph, node);
  }

  @SafeVarargs
  public final NodeAssert<T> hasExactChildren(final T... nodes) {
    Assertions.assertThat(actual.successors(node)).containsExactlyInAnyOrder(nodes);
    return this;
  }

  public NodeAssert<T> hasNoChildren() {
    Assertions.assertThat(actual.successors(node)).isEmpty();
    return this;
  }
}