package com.melody.merlin.jet;

import java.util.ArrayList;
import java.util.List;

/**
 *  基本完成
 */
public class ArchitectureData {
    String name;
    final List<NodeData> nodes = new ArrayList<>();
    final List<PipeData> pipes = new ArrayList<>();
    final List<KeyFrameData> keyFrames = new ArrayList<>();
    final List<Rhythm> rhythms = new ArrayList<>();
    float x, y, width, height;
    String version, hash;
    float fps = 30;
    String imagesPath, audioPath;

    public List<NodeData> getNodes() {
        return nodes;
    }

    public NodeData findNode(String nodeName) {
        if (nodeName == null)
            throw new IllegalArgumentException("nodeName cannot be null.");
        List<NodeData> nodes = this.nodes;
        for (int i = 0, n = nodes.size(); i < n; i ++) {
            NodeData node = nodes.get(i);
            if (node.name.equals(nodeName))
                return node;
        }
        return null;
    }

    public List<PipeData> getPipes() {
        return pipes;
    }

    public PipeData findPipe(String pipeName) {
        if (pipeName == null)
            throw new IllegalArgumentException("pipeName cannon be null.");
        List<PipeData> pipes = this.pipes;
        for (int i = 0, n = pipes.size(); i < n; i ++) {
            PipeData pipe = pipes.get(i);
            if (pipe.name.equals(pipeName))
                return pipe;
        }
        return null;
    }

    public KeyFrameData findKeyFrame(String keyFrameDataName) {
        if (keyFrameDataName == null)
            throw new IllegalArgumentException("keyFrameDataName cannot be null.");
        for (KeyFrameData keyFrameData : keyFrames) {
            if (keyFrameData.name.equals(keyFrameDataName))
                return keyFrameData;
        }
        return null;
    }

    public List<KeyFrameData> getKeyFrames() {
        return keyFrames;
    }

    public List<Rhythm> getRhythms() {
        return rhythms;
    }

    public Rhythm findRhythm(String rhythmName) {
        if (rhythmName == null)
            throw new IllegalArgumentException("rhythmName cannot be null.");
        List<Rhythm> rhythms = this.rhythms;
        for (int i = 0, n = rhythms.size(); i < n; i ++) {
            Rhythm rhythm = rhythms.get(i);
            if (rhythm.name.equals(rhythmName))
                return rhythm;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public float getFps() {
        return fps;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }

    @Override
    public String toString() {
        return name != null ? name : super.toString();
    }
}
