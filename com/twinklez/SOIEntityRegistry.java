package com.twinklez;
 
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.network.packet.Packet23VehicleSpawn;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
 
// TODO: Auto-generated Javadoc
/**
 * The Class ModEntityHelper makes registering and handling easy.
 *
 * @author lockNload147
 */
public class SOIEntityRegistry {
 
        /** The start entity id. */
        private static int startEntityId = -1;
 
        /** The start vehicle type. */
        private static int startVehicleType = -1;
 
        /** occupied vehicle Ids. */
        private static final int[] occupiedVehicleType;
 
        /** The base mod. */
        private BaseMod baseMod;
 
        /** The class to type map. */
        public Map<Class<? extends Entity>, Integer> classToTypeMap;
 
        /** The type to class map. */
        public Map<Integer, Class<? extends Entity>> typeToClassMap;
 
        /** The entity handlers. */
        private static List<IEntityHandler> entityHandlers;
 
        static {
                occupiedVehicleType = new int[] { 1, 10, 11, 12, 50, 51, 60, 61, 62,
                                63, 64, 65, 70, 72, 73, 75, 90 };
                entityHandlers = new ArrayList<IEntityHandler>();
        }
 
        /**
         * Instantiates a new mod entity helper.
         *
         * @param baseMod
         *            the base mod
         */
        public SOIEntityRegistry(BaseMod baseMod) {
                this.baseMod = baseMod;
                classToTypeMap = new HashMap<Class<? extends Entity>, Integer>();
                typeToClassMap = new HashMap<Integer, Class<? extends Entity>>();
        }
 
        /**
         * Adds the entity with a unique Entity Id.
         *
         * @param entityClass
         *            the entity class
         */
        public void addEntity(Class<? extends Entity> entityClass) {
                addEntity(entityClass, getUniqueEntityId());
        }
 
        /**
         * Adds the entity with entity id.
         *
         * @param entityClass
         *            the entity class
         * @param entityId
         *            the entity id
         */
        public void addEntity(Class<? extends Entity> entityClass, int entityId) {
                ModLoader.registerEntityID(entityClass, entityClass.getSimpleName(),
                                entityId);
        }
 
        /**
         * Adds the entity with tracking with a unique type id.
         *
         * @param entityClass
         *            the entity class
         * @param distance
         *            the distance
         * @param updateFrequency
         *            the update frequency
         * @param tracking
         *            the tracking
         */
        public void addEntityWithTracking(Class<? extends Entity> entityClass,
                        int distance, int updateFrequency, boolean tracking) {
                addEntityWithTracking(entityClass, getUniqueVehicleType(), distance,
                                updateFrequency, tracking);
        }
 
        /**
         * Adds the entity with tracking.
         *
         * @param entityClass
         *            the entity class
         * @param type
         *            the type
         * @param distance
         *            the distance
         * @param updateFrequency
         *            the update frequency
         * @param tracking
         *            the tracking
         */
        public void addEntityWithTracking(Class<? extends Entity> entityClass,
                        int type, int distance, int updateFrequency, boolean tracking) {
                classToTypeMap.put(entityClass, type);
                typeToClassMap.put(type, entityClass);
                addEntity(entityClass);
                ModLoader.addEntityTracker(baseMod, entityClass, type, distance,
                                updateFrequency, tracking);
        }
 
        /**
         * Spawn entity.
         *
         * @param type
         *            the type
         * @param worldClient
         *            the world client
         * @param x
         *            the x
         * @param y
         *            the y
         * @param z
         *            the z
         * @return the entity
         */
        public Entity spawnEntity(int type, World worldClient, double x, double y,
                        double z) {
 
                Class<? extends Entity> entityClass = typeToClassMap.get(type);
                if (entityClass != null)
                        try {
                                return entityClass.getConstructor(World.class, Double.TYPE,
                                                Double.TYPE, Double.TYPE).newInstance(worldClient, x,
                                                y, z);
                        } catch (InstantiationException e) {
                                e.printStackTrace();
                        } catch (IllegalAccessException e) {
                                Logger.getLogger("Minecraft")
                                                .log(Level.SEVERE,
                                                                "Make sure " + entityClass
                                                                                + "constructor is public", e);
                        } catch (IllegalArgumentException e) {
                                Logger.getLogger("Minecraft")
                                                .log(Level.SEVERE,
                                                                "Make sure "
                                                                                + entityClass
                                                                                + "constructor has the arguments World.class, double, double,double",
                                                                e);
                        } catch (InvocationTargetException e) {
                                e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                                Logger.getLogger("Minecraft").log(
                                                Level.SEVERE,
                                                "Missing constructor (World.class,double,double,double) in "
                                                                + entityClass, e);
                        } catch (SecurityException e) {
                                e.printStackTrace();
                        }
 
                return null;
        }
 
        /**
         * Gets the spawn packet.
         *
         * @param entity
         *            the entity
         * @param type
         *            the type
         * @return the spawn packet
         */
        public Packet23VehicleSpawn getSpawnPacket(Entity entity, int type) {
                return new Packet23VehicleSpawn(entity, type);
        }
 
        /**
         * Gets the type by entity.
         *
         * @param entityClass
         *            the entity class
         * @return the type by entity
         */
        public int getTypeByEntity(Class<? extends Entity> entityClass) {
                return classToTypeMap.get(entityClass);
        }
 
        /**
         * Gets the type by entity.
         *
         * @param entityName
         *            the entity name
         * @return the type by entity
         */
        public int getTypeByEntity(String entityName) {
                try {
                        return classToTypeMap.get(Class.forName(entityName));
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
 
                return -1;
        }
 
        /**
         * Gets the entity by name.
         *
         * @param type
         *            the type
         * @return the entity by name
         */
        public Class<? extends Entity> getEntityByName(int type) {
                return typeToClassMap.get(type);
        }
 
        /**
         * Adds the entity handler.
         *
         * @param handlerEntity the handler entity
         */
        public static void addEntityHandling(IEntityHandler handlerEntity) {
                entityHandlers.add(handlerEntity);
        }
 
        /**
         * Handle entity handler.
         *
         * @param netClientHandler the net client handler
         * @param spawnEntity the spawn entity
         * @param throwerEntity the thrower entity
         * @param vehiclePacket the vehicle packet
         */
        public static void handleEntityHandler(NetClientHandler netClientHandler,
                        Entity spawnEntity, Entity throwerEntity,
                        Packet23VehicleSpawn vehiclePacket) {
                for (IEntityHandler handler : entityHandlers) {
                        handler.handleThrowableEntity(netClientHandler, spawnEntity,
                                        throwerEntity, vehiclePacket);
                }
        }
 
        /**
         * Gets the unique entity id.
         *
         * @return the unique entity id
         */
        public static int getUniqueEntityId() {
                do {
                        startEntityId++;
                        if (startEntityId > 255)
                                Logger.getLogger("Minecraft").log(Level.WARNING,
                                                "Entity Id is greater than 255: " + startEntityId);
                } while (EntityList.getStringFromID(startEntityId) != null);
 
                return startEntityId;
        }
 
        /**
         * Gets the unique vehicle type.
         *
         * @return the unique vehicle type
         */
        public static int getUniqueVehicleType() {
                do {
                        startVehicleType++;
                        if (startVehicleType > 255)
                                Logger.getLogger("Minecraft")
                                                .log(Level.WARNING,
                                                                "Vehicle type is greater than 255: "
                                                                                + startVehicleType);
                } while (Arrays.binarySearch(occupiedVehicleType, startVehicleType) > -1);
 
                return startVehicleType;
        }
}