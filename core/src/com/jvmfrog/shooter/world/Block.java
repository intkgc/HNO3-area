package com.jvmfrog.shooter.world;

import com.jvmfrog.curve.registry.Registry;
import com.jvmfrog.curve.registry.RegistryObject;

public class Block extends RegistryObject {
    public static Registry<Block> registry = new Registry<>("block");

    @Override
    public void init() {
    }
}
