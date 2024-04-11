package com.melody.merlin.jet;

import com.badlogic.gdx.math.Vector2;
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
        updateWorldTransform(x, y, scaleX, scaleY, rotation);
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
                float lb = JetUtils.cosDeg(rotationY) * scaleY;
                float lc = JetUtils.sinDeg(rotation) * scaleX;
                float ld = JetUtils.sinDeg(rotationY) * scaleY;
                a = pa * la + pb * lc;
                b = pa * lb + pb * ld;
                c = pc * la + pd * lc;
                d = pc * lb + pd * ld;
                return;
            }
            case onlyTranslation: {
                float rotationY = rotation + 90;
                a = JetUtils.cosDeg(rotation) * scaleX;
                b = JetUtils.cosDeg(rotationY) * scaleY;
                c = JetUtils.sinDeg(rotation) * scaleX;
                d = JetUtils.sinDeg(rotationY) * scaleY;
                break;
            }
            case noRotation: {
                float s = pa * pa + pc * pc, prx;
                if (s > 0.0001f) {
                    s = Math.abs(pa * pd - pb * pc) / s;
                    pb = pc * s;
                    pd = pa * s;
                    prx = (float) Math.atan2(pc, pa) * JetUtils.radDeg;
                } else {
                    pa = 0;
                    pc = 0;
                    prx = (float)(90 - Math.atan2(pd, pb) * JetUtils.radDeg);
                }
                float rx = rotation - prx;
                float ry = rotation - prx + 90;
                float la = JetUtils.cosDeg(rx) * scaleX;
                float lb = JetUtils.cosDeg(ry) * scaleY;
                float lc = JetUtils.sinDeg(rx) * scaleX;
                float ld = JetUtils.sinDeg(ry) * scaleY;
                a = pa * la - pb * lc;
                b = pa * lb - pb * ld;
                c = pc * la + pd * lc;
                d = pc * lb + pd * ld;
                return;
            }
            case noScale: {
                float cos = JetUtils.cosDeg(rotation), sin = JetUtils.sinDeg(rotation);
                float za = (pa * cos + pb * sin) / architecture.scaleX;
                float zc = (pc * cos + pd * sin) / architecture.scaleY;
                float s = (float)Math.sqrt(za * za + zc * zc);
                if (s > 0.00001f) s = 1 / s;
                za *= s;
                zc *= s;
                s = (float)Math.sqrt(za * za + zc * zc);
                if (pa * pd - pb * pc < 0 != architecture.scaleX < 0 != architecture.scaleY < 0) s = -s;
                float r = (float) (JetUtils.PI / 2 + Math.atan2(zc, za));
                float zb = (float) (Math.cos(r) * s);
                float zd = (float) (Math.sin(r) * s);
                float la = JetUtils.cosDeg(0) * scaleX;
                float lb = JetUtils.cosDeg(90) * scaleY;
                float lc = JetUtils.sinDeg(0) * scaleX;
                float ld = JetUtils.sinDeg(90) * scaleY;
                a = za * la + zb * lc;
                b = za * lb + zb * ld;
                c = zc * la + zd * lc;
                d = zc * lb + zd * ld;
                break;
            }
        }
        a *= architecture.scaleX;
        b *= architecture.scaleX;
        c *= architecture.scaleY;
        d *= architecture.scaleY;
    }

    public Vector2 worldToLocal(Vector2 world) {
        if (world == null) throw new IllegalArgumentException("world cannot be null.");
        float invDet = 1 / (a * d - b * c);
        float x = world.x - worldX, y = world.y - worldY;
        world.x = x * d * invDet - y * b * invDet;
        world.y = y * a * invDet - x * c * invDet;
        return world;
    }

    public Vector2 localToWorld(Vector2 local) {
        if (local == null) throw new IllegalArgumentException("local cannot be null.");
        float x = local.x, y = local.y;
        local.x = x * a + y * b + worldX;
        local.y = x * c + y * d + worldY;
        return local;
    }

    public float worldToLocalRotation (float worldRotation) {
        float sin = JetUtils.sinDeg(worldRotation), cos = JetUtils.cosDeg(worldRotation);
        return (float) (Math.atan2(a * sin - c * cos, d * cos - b * sin) * JetUtils.radDeg + rotation);
    }

    public NodeData getData() {
        return data;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }

    @Override
    public String toString() {
        return data.name;
    }
}
