package net.amod.blocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class coal extends Block{ 
    public coal(){
        super(FabricBlockSettings.copy(Blocks.COAL_BLOCK).build()); //Is coal but is compressed
    }
    

}