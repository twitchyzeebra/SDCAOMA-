package net.amod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.amod.items.Foods;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.world.loot.entry.ItemEntry;
import net.minecraft.world.loot.function.LootingEnchantLootFunction;
import net.minecraft.world.loot.function.SetCountLootFunction;
import net.minecraft.world.loot.UniformLootTableRange;
import net.minecraft.world.loot.condition.RandomChanceWithLootingLootCondition;
import net.amod.blocks.glasses;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.amod.blocks.coal;
import net.fabricmc.fabric.api.registry.FuelRegistry;
//import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
//import net.minecraft.world.loot.LootPool;
//import net.minecraft.world.loot.condition.LootCondition;
//import net.minecraft.world.loot.ConstantLootTableRange;
//import net.minecraft.item.Items;


public class amod implements ModInitializer{
    public static final String modID = "amod";
    private static final Identifier x = new Identifier("minecraft", "entities/squid");
    public static final Block glow_glass = new glasses();
    public static final Block comp_coal = new coal();


    @Override
    public void onInitialize(){
        reg();
        pools();
        }
        
    
    public static void reg(){ //registers items and blocks
        Foods.calimari = new Item(genSets().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.5f).build()));
        Registry.register(Registry.ITEM, genID("calimari"),Foods.calimari);
        Foods.cooked_calimari = new Item(genSets().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(1f).build()));
        Registry.register(Registry.ITEM, genID("cooked_calimari"),Foods.cooked_calimari);
        Registry.register(Registry.BLOCK, genID("glow_glass"), glow_glass);
        Registry.register(Registry.ITEM, genID("glow_glass"), new BlockItem(glow_glass, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, genID("comp_coal"), comp_coal);
        BlockItem coal = new BlockItem(comp_coal, new Item.Settings().group(ItemGroup.MISC));
        Registry.register(Registry.ITEM, genID("comp_coal"), coal);
        FuelRegistry.INSTANCE.add(coal, 32000);
    }    

    public static void pools(){ //registers pool additions
        FabricLootPoolBuilder s = FabricLootPoolBuilder.builder().withCondition(RandomChanceWithLootingLootCondition.builder(0.7f,0.1f))
        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.3f,3.0f)))
        .withEntry(ItemEntry.builder(Foods.calimari))
        .withFunction(LootingEnchantLootFunction.builder(UniformLootTableRange.between(0.25f,0.65f)));
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (x.equals(id)) {
                supplier.withPool(s);
            }
        });
    }
    
    public static Item.Settings genSets(){ //To make lines shorter
        return new Item.Settings();
    }

    public static Identifier genID(String name) { //To make lines shorter
        return new Identifier(modID, name);
    }
}