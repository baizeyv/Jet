package com.melody.merlin.jet;

import com.melody.merlin.jet.utils.JetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似 spine 中的 bone
 */
public class Node implements Updatable {

    final NodeData data;

    final Architecture architecture;

    final Node parent;

    final List<Node> children = new ArrayList<>();

    float x, y, scaleX, scaleY, rotation;

    float ax, ay, ascaleX, ascaleY, arotation;

    boolean appliedValid;

    float a, b, worldX;

    float c, d, worldY;

    public Node(NodeData data, Architecture architecture, Node parent) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null.");
        if (architecture == null)
            throw new IllegalArgumentException("architecture cannot be null");
        this.data = data;
        this.architecture = architecture;
        this.parent = parent;
        setToSetupPose();
    }

    /**
     * copy constructor
     * @param node
     * @param architecture
     * @param parent
     */
    public Node(Node node, Architecture architecture, Node parent) {
        if (node == null)
            throw new IllegalArgumentException("node cannot be null.");
        if (architecture == null)
            throw new IllegalArgumentException("architecture cannot be null.");
        this.architecture = architecture;
        this.parent = parent;
        data = node.data;
        x = node.x;
        y = node.y;
        scaleX = node.scaleX;
        scaleY = node.scaleY;
        rotation = node.rotation;
    }

    /**
     * set this node's local transform to the setup pose
     */
    public void setToSetupPose() {
        NodeData data = this.data;
        x = data.x;
        y = data.y;
        scaleX = data.scaleX;
        scaleY = data.scaleY;
        rotation = data.rotation;
    }

    @Override
    public void update() {

    }

    /**
     * computes the world transform using the parent node and this node's local transform
     */
    public void updateWorldTransform() {

    }

    /**
     * computes the world transform using the parent node and the specified local transform. Child nodes are not updated.
     * @param x
     * @param y
     * @param scaleX
     * @param scaleY
     * @param rotation
     */
    public void updateWorldTransform(float x, float y, float scaleX, float scaleY, float rotation) {
        ax = x;
        ay = y;
        ascaleX = scaleX;
        ascaleY = scaleY;
        arotation = rotation;
        appliedValid = true;

        Node parent = this.parent;
        if (parent == null) { // root node
            Architecture architecture = this.architecture;
            float rotationY = rotation + 90, sx = architecture.scaleX, sy = architecture.scaleY;
            a = JetUtils.cosDeg(rotation) * scaleX * sx;
            b = JetUtils.cosDeg(rotationY) * scaleY * sx;
            c = JetUtils.sinDeg(rotation) * scaleX * sy;
            d = JetUtils.sinDeg(rotationY) * scaleY * sy;
            worldX = x * sx + architecture.x;
            worldY = y * sy + architecture.y;
            return;
        }

        float pa = parent.a, pb = parent.b, pc = parent.c, pd = parent.d;
        worldX = pa * x + pb * y + parent.worldX;
        worldY = pc * x + pd * y + parent.worldY;

        switch (data.transformMode) {
            case normal: {
                float rotationY = rotation + 90;
                float la = JetUtils.cosDeg(rotation) * scaleX;
                break;
            }
        }
    }

    public void updateAppliedTransform() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
