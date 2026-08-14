package com.flexindahard.greekmod.block.custom;

import net.minecraft.util.StringRepresentable;

public enum CarpetEnumProperty implements StringRepresentable {
    NORTH_WEST("north_west"),
    NORTH_EAST("north_east"),
    SOUTH_WEST("south_west"),
    SOUTH_EAST("south_east");

    private final String name;

     CarpetEnumProperty(String pName) {
        this.name = pName;
    }

    public String getSerializedName() {
        return this.name;
    }
}
