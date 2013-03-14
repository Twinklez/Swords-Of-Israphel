package com.twinklez;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxySOI extends CommonProxySOI
{
	@Override
	public void registerRenderInfo()
	{
		MinecraftForgeClient.preloadTexture("/soi/blocks/terrain.png");
        MinecraftForgeClient.preloadTexture("/soi/gui/items.png");
	}

}
