package com.melody.merlin.jet;

/**
 * 类似 spine 中的 Slot
 */
public class Pipe {
    final PipeData data;

    final Node node;

    Applicable app;

    private float appTime;

    public Pipe(PipeData data, Node node) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null.");
        if (node == null)
            throw new IllegalArgumentException("node cannot be null.");
        this.data = data;
        this.node = node;
        // TODO
    }
}
