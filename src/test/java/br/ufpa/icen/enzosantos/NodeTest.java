package br.ufpa.icen.enzosantos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NodeTest {
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testEquals() {
        final Node<Integer> node = Node.of(1);
        assertEquals(node, node);
        assertNotEquals(node, 1);
        assertNotEquals(node, Node.of(2));
        assertEquals(node, Node.of(1));
    }
}