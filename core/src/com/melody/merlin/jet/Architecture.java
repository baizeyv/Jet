package com.melody.merlin.jet;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似 spine 中的 skeleton
 */
public class Architecture {
    final ArchitectureData data;

    final List<Node> nodes;

    final List<Pipe> pipes;

    List<Pipe> drawOrder;

    final List<Updatable> updateCache = new ArrayList<>();

    final List<Node> updateCacheReset = new ArrayList<>();

    float time;

    float scaleX = 1, scaleY = 1;

    float x, y;

    public Architecture(ArchitectureData data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null.");
        this.data = data;

        nodes = new ArrayList<>(data.nodes.size());
        for (NodeData nodeData : data.nodes) {
            Node node;
            if (nodeData.parent == null)
                node = new Node(nodeData, this, null);
            else {
                Node parent = nodes.get(nodeData.parent.index);
                node = new Node(nodeData, this, parent);
                parent.children.add(node);
            }
            nodes.add(node);
        }

        pipes = new ArrayList<>(data.pipes.size());
        drawOrder = new ArrayList<>(data.pipes.size());
        for (PipeData pipeData : data.pipes) {
            Node node = nodes.get(pipeData.nodeData.index);
            Pipe pipe = new Pipe(pipeData, node);
            pipes.add(pipe);
            drawOrder.add(pipe);
        }

        updateCache();
    }

    public void updateCache() {
        List<Updatable> updateCache = this.updateCache;
        updateCache.clear();
        updateCacheReset.clear();

        int nodeCount = nodes.size();
        Object[] nodes = this.nodes.toArray();
        for (int i = 0; i < nodeCount; i++) {
            Node node = (Node) nodes[i];
            // TODO
        }
    }

    public void updateWorldTransform() {
        List<Updatable> updateCache = this.updateCache;
        for (int i = 0, n = updateCache.size(); i < n; i ++) {
            updateCache.get(i).update();
        }
    }

    public void updateWorldTransform(Node parent) {

    }
}
