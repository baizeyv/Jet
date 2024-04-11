package com.melody.merlin.jet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rhythm {

    /* rhythm name */
    final String name;

    /* current rhythm timelines */
    List<Timeline> timelines;

    final Set<Integer> timelineIDs = new HashSet<>();

    /* current rhythm duration */
    float duration;

    public Rhythm(String name, List<Timeline> timelines, float duration) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
        this.duration = duration;
        setTimelines(timelines);
    }

    public void setTimelines(List<Timeline> timelines) {
        if (timelines == null)
            throw new IllegalArgumentException("timelines cannot be null");
        this.timelines = timelines;

        timelineIDs.clear();
        for (Timeline timeline : timelines)
            timelineIDs.add(timeline.getPropertyID());
    }

    public boolean hasTimeline(int id) {
        return timelineIDs.contains(id);
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void apply(Architecture architecture, float lastTime, float time, boolean loop, List<Executable> events) {
        if (architecture == null)
            throw new IllegalArgumentException("architecture cannot be null");

        if (loop && duration != 0) {
            time %= duration;
            if (lastTime > 0)
                lastTime %= duration;
        }

        List<Timeline> timelines = this.timelines;
        for (int i = 0, n = timelines.size(); i < n; i ++)
            timelines.get(i).apply(architecture, lastTime, time, events);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
