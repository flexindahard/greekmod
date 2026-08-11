package com.flexindahard.greekmod.block.custom;

import net.minecraft.util.StringRepresentable;

public enum CarpetEnumProperty implements StringRepresentable {
    PLAIN("plain"),
    CORNER("corner"),
    MIDDLE("middle");

    private final String name;

     CarpetEnumProperty(String pName) {
        this.name = pName;
    }

    public String getSerializedName() {
        return this.name;
    }
}
