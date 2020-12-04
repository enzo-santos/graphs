package br.ufpa.icen.enzosantos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EdgeTest {

    @Test
    void testEquals() {
        final Edge<String> e0 = new Edge<>("a", "b", 1);
        assertEquals(e0, e0);
        assertNotEquals(e0, null);
        assertNotEquals(e0, 0xC0FFEE);
        assertNotEquals(e0, new Edge<>("c", "d", 2));
        assertNotEquals(e0, new Edge<>("c", "d", 1));
        assertNotEquals(e0, new Edge<>("c", "b", 1));
        assertEquals(e0, new Edge<>("a", "b", 1));
    }

    @Test
    void testHashCode() {
        final Edge<String> e0 = new Edge<>("a", "b", 1);
        assertEquals(e0.hashCode(), new Edge<>("a", "b", 1).hashCode());
        assertNotEquals(e0.hashCode(), new Edge<>("a", "b", 2).hashCode());
    }
}