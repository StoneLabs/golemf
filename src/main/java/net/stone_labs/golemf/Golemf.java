package net.stone_labs.golemf;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Golemf implements DedicatedServerModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "golemf";
    public static final String MOD_NAME = "GolemF";
    public static final String VERSION = "1.2.0";

    public static boolean disableGolemSpawn = false;

    @Override
    public void onInitializeServer()
    {
        LOGGER.log(Level.INFO, "Initialized {} version {}", MOD_NAME, VERSION);

        // Set values from gamerules on server start
        ServerLifecycleEvents.SERVER_STARTED.register(server ->
        {
            disableGolemSpawn = server.getGameRules().get(DISABLE_GOLEM_SPAWN).get();
        });
    }

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_GOLEM_SPAWN = register("disableGolemSpawn", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true, (server, rule) ->
    {
        disableGolemSpawn = rule.get();
    }));

    private static <T extends GameRules.Rule<T>> GameRules.Key<T> register(String name, GameRules.Category category, GameRules.Type<T> type)
    {
        return GameRuleRegistry.register(name, category, type);
    }
}
