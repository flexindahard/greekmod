package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.Greekmod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

// singleton class
public final class ModKeybindings {
        public static final ModKeybindings INSTANCE = new ModKeybindings();

        private ModKeybindings(){}

    public static final String CATEGORY = "key.categories." + Greekmod.MODID;

    public final KeyMapping noobKey = new KeyMapping(
            "key." + Greekmod.MODID + ".noob_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_P, -1),
            CATEGORY
    );

    public final KeyMapping hermesJumpKey = new KeyMapping(
            "key." + Greekmod.MODID + ".hermes_jump_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_V, -1),
            CATEGORY
    );
}
