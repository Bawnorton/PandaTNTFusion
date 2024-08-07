package me.sebastian420.PandaTNTQueue;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PandaTNTQueue implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("panda-tnt-queue");
	public static int tntCount = 0;

	@Override
	public void onInitialize() {
		PandaTNTConfig.loadOrGenerateConfig();
		ServerEntityEvents.ENTITY_LOAD.register(this::onEntityLoad);
		ServerEntityEvents.ENTITY_UNLOAD.register(this::onEntityUnload);
		LOGGER.info("PandaTNTQueue Started!");
	}

	private void onEntityUnload(Entity entity, ServerWorld serverWorld) {
		if (entity.getType() == EntityType.TNT) tntCount--;
	}

	private void onEntityLoad(Entity entity, ServerWorld serverWorld) {
		if (entity.getType() == EntityType.TNT) tntCount++;;
	}



}