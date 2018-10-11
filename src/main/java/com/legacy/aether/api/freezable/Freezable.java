package com.legacy.aether.api.freezable;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class Freezable {

    private int timeRequired;

    private IItemProvider input;

    private IItemProvider output;

    public Freezable(IItemProvider input, IItemProvider output, int timeRequired) {
        this.input = input;
        this.output = output;
        this.timeRequired = timeRequired;
    }

    public int getTimeRequired() {
        return this.timeRequired;
    }

    public Item getInput() {
        return this.input.asItem();
    }

    public Item getOutput() {
        return this.output.asItem();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Freezable) {
            Freezable freezable = (Freezable) obj;

            boolean inputCheck = this.getInput().asItem() == freezable.getInput().asItem();
            boolean outputCheck = this.getOutput().asItem() == freezable.getOutput().asItem();

            return inputCheck && outputCheck;
        }

        return false;
    }

}