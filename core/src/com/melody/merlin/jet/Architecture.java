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
}
