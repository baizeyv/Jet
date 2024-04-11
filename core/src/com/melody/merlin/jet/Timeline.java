package com.melody.merlin.jet;

import java.util.List;

public interface Timeline<E extends Executable> {

    public void apply(Architecture architecture, float lastTime, float time, List<E> events);

    public int getPropertyID();

}
