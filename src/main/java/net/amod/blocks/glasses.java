package net.amod.blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.Blocks;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class glasses extends GlassBlock{ 
    public glasses(){
        super(FabricBlockSettings.copy(Blocks.GLASS).lightLevel(14).build()); //Like glass but brighter
    }
    

}