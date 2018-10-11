package com.legacy.aether.api.enchantment;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class EnchantmentFuel {

    private int timeGiven;

    private IItemProvider fuel;

    public EnchantmentFuel(IItemProvider fuel, int timeGiven) {
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
        if (obj instanceof EnchantmentFuel) {
            EnchantmentFuel fuel = (EnchantmentFuel) obj;

            return this.getFuel().asItem() == fuel.getFuel().asItem();
        }

        return false;
    }

}