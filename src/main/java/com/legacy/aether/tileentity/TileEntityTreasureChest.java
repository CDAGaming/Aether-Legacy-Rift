package com.legacy.aether.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityTreasureChest extends TileEntityChest {

    private boolean locked = true;

    private int kind = 0;

    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);

        this.locked = par1nbtTagCompound.getBoolean("locked");
        this.kind = par1nbtTagCompound.getInteger("dungeonType");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);

        par1nbtTagCompound.setBoolean("locked", this.locked);
        par1nbtTagCompound.setInteger("dungeonType", this.kind);

        return par1nbtTagCompound;
    }

    public void unlock(EntityPlayer player) {
        this.locked = false;

        this.fillWithLoot(player);

        if (!this.world.isRemote) {
            this.sendToAllInOurWorld(player.getServer(), this.getUpdatePacket());
        }
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new SPacketUpdateTileEntity(this.pos, 191, var1);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        super.openInventory(player);

        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockState().getBlock(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
            this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockState().getBlock());
        }
    }

    private void sendToAllInOurWorld(MinecraftServer server, SPacketUpdateTileEntity pkt) {
        if (server != null) {
            server.getPlayerList().sendPacketToAllPlayers(pkt);
        }
    }

    public boolean isLocked() {
        return this.locked;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

}