package com.legacy.aether.api.freezable;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class FreezableFuel {

    private int timeGiven;

    private IItemProvider fuel;

    public FreezableFuel(IItemProvider fuel, int timeGiven) {
        this.fuel = fuel;
        this.timeGiven = timeGiven;
    }

    public int getTimeGiven() {
        return this.timeGiven;
    }

    public Item getFuel() {
        return this.fuel.asItem();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FreezableFuel) {
            FreezableFuel fuel = (FreezableFuel) obj;

            return this.getFuel().asItem() == fuel.getFuel().asItem();
        }

        return false;
    }

}