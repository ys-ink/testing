package com.github.ys_ink;

import com.google.common.graph.Graph;
import com.google.common.graph.Traverser;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.Iterator;
import java.util.Set;

@SuppressWarnings({"UnstableApiUsage", "WeakerAccess", "UnusedReturnValue"})
public class GraphAssert<T> extends AbstractAssert<GraphAssert<T>, Graph<T>> {

  private GraphAssert(final Graph<T> actual) {
    super(actual, GraphAssert.class);
  }

  public static <T> GraphAssert<T> assertThat(final Graph<T> actual) {
    return new GraphAssert<>(actual);
  }

  public final GraphAssert<T> hasNode(final T node) {
    isNotNull();

    if (!actual.nodes().contains(node)) {
      failWithMessage("Expected node <%s>", node);
    }
    return null;
  }

  @SafeVarargs
  public final GraphAssert<T> hasNodesExactOrder(final T... nodes) {
    for (int i = 1; i < nodes.length; ++i) {
      final Iterator it = Traverser.forGraph(actual).depthFirstPostOrder(nodes[0]).iterator();
      final Object next = it.next();
      if (next != nodes[i]) {
        failWithMessage("Expected node <%s> at position <%s> but was <%s>", nodes[i], i, next);
      }
    }
    return this;
  }

  @SafeVarargs
  public final GraphAssert<T> hasNodesExactly(final T... nodes) {
    final Set<T> nodes1 = actual.nodes();
    Assertions.assertThat(nodes1).containsExactlyInAnyOrder(nodes);
    return this;
  }

  @SafeVarargs
  public final GraphAssert<T> hasNodes(final T... nodes) {
    Assertions.assertThat(actual.nodes()).contains(nodes);
    return this;
  }
}
