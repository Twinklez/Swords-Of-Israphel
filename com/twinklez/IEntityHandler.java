package com.twinklez;

import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet23VehicleSpawn;
 
public interface IEntityHandler 
{
	void handleThrowableEntity(NetClientHandler clientHandler,Entity spawnEntity, Entity throwerEntity,Packet23VehicleSpawn vehiclePacket);
}