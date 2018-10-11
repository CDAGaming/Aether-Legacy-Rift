package com.legacy.aether.player.perks;

import net.minecraft.network.PacketBuffer;

public class CustomizedMoaSkin {

    private boolean shouldDefualt;

    private boolean wingMarkingGlow;

    private boolean markingGlow;

    private boolean eyeGlow;

    private int wingColor;

    private int wingMarkingColor;

    private int bodyColor;

    private int markingColor;

    private int eyeColor;

    private int outsideColor;

    public CustomizedMoaSkin() {
        this.shouldDefualt = true;
    }

    public static CustomizedMoaSkin readMoaSkin(PacketBuffer buf) {
        CustomizedMoaSkin skin = new CustomizedMoaSkin();

        skin.shouldUseDefualt(buf.readBoolean());
        skin.setWingColor(buf.readInt());
        skin.setWingMarkingColor(buf.readInt());
        skin.setBodyColor(buf.readInt());
        skin.setMarkingColor(buf.readInt());
        skin.setEyeColor(buf.readInt());
        skin.setOutsideColor(buf.readInt());

        return skin;
    }

    public void writeMoaSkin(PacketBuffer buf) {
        buf.writeBoolean(this.shouldDefualt);
        buf.writeBoolean(this.wingMarkingGlow);
        buf.writeBoolean(this.markingGlow);
        buf.writeBoolean(this.eyeGlow);
        buf.writeInt(this.wingColor);
        buf.writeInt(this.wingMarkingColor);
        buf.writeInt(this.bodyColor);
        buf.writeInt(this.markingColor);
        buf.writeInt(this.eyeColor);
        buf.writeInt(this.outsideColor);
    }

    public int getWingColor() {
        return this.wingColor;
    }

    public void setWingColor(int color) {
        this.wingColor = color;
    }

    public int getWingMarkingColor() {
        return this.wingMarkingColor;
    }

    public void setWingMarkingColor(int color) {
        this.wingMarkingColor = color;
    }

    public int getBodyColor() {
        return this.bodyColor;
    }

    public void setBodyColor(int color) {
        this.bodyColor = color;
    }

    public int getMarkingColor() {
        return this.markingColor;
    }

    public void setMarkingColor(int color) {
        this.markingColor = color;
    }

    public int getEyeColor() {
        return this.eyeColor;
    }

    public void setEyeColor(int color) {
        this.eyeColor = color;
    }

    public int getOutsideColor() {
        return this.outsideColor;
    }

    public void setOutsideColor(int color) {
        this.outsideColor = color;
    }

    public void shouldUseDefualt(boolean defualt) {
        this.shouldDefualt = defualt;
    }

    public boolean shouldUseDefualt() {
        return this.shouldDefualt;
    }

    public void shouldWingMarkingGlow(boolean glow) {
        this.shouldDefualt = glow;
    }

    public boolean isWingMarkingGlowing() {
        return this.shouldDefualt;
    }

    public void shouldMarkingGlow(boolean glow) {
        this.shouldDefualt = glow;
    }

    public boolean isMarkingGlowing() {
        return this.shouldDefualt;
    }

    public void shouldEyeGlow(boolean glow) {
        this.eyeGlow = glow;
    }

    public boolean isEyeGlowing() {
        return this.eyeGlow;
    }

}