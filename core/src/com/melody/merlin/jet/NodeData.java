package com.melody.merlin.jet;

/**
 * node data
 */
public class NodeData {

    final int index;

    final String name;

    final NodeData parent;

    float length;

    float x, y, scaleX = 1, scaleY = 1, rotation;

    TransformMode transformMode = TransformMode.normal;

    public NodeData(int index, String name, NodeData parent) {
        if (index < 0)
            throw new IllegalArgumentException("index must be >= 0.");
        if (name == null)
            throw new IllegalArgumentException("name cannot be null.");
        this.index = index;
        this.name = name;
        this.parent = parent;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public NodeData getParent() {
        return parent;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public TransformMode getTransformMode() {
        return transformMode;
    }

    public void setTransformMode(TransformMode transformMode) {
        this.transformMode = transformMode;
    }

    @Override
    public String toString() {
        return name;
    }

    public static enum TransformMode {
        normal,
        onlyTranslation,
        noRotationOrReflection,
        noScale,
        noScaleOrReflection;

        public static final TransformMode[] values = TransformMode.values();
    }

}
