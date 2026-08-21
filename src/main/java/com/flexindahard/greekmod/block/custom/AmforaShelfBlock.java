package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AmforaShelfBlock extends GenericModBlock {

    public static final IntegerProperty AMFORA_QUANTITY;

    static {
        AMFORA_QUANTITY = IntegerProperty.create("amfora_quantity", 0, 2);
    }

    public AmforaShelfBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(AMFORA_QUANTITY, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(AMFORA_QUANTITY);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Item amforaItem = ModBlocks.AMFORA_NO_PATTERN.get().asItem();
        int amfora_quantity = pState.getValue(AMFORA_QUANTITY);
        if (!pLevel.isClientSide){
            if(pPlayer.getMainHandItem().is(amforaItem) && amfora_quantity < 2){
                pLevel.setBlock(pPos, pState.cycle(AMFORA_QUANTITY), 3);
                return InteractionResult.SUCCESS;
            }
            else if (!(pPlayer.getMainHandItem().is(amforaItem)) && pState.getValue(AMFORA_QUANTITY) > 0) {
                pLevel.setBlock(pPos, pState.setValue(AMFORA_QUANTITY, pState.getValue(AMFORA_QUANTITY) - 1), 3);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if(!pPlayer.isCreative())
        {
            pLevel.playSound(null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1f ,1f);
            for (int i = 0; i < pState.getValue(AMFORA_QUANTITY); i++)
            {
                popResource(pLevel, pPos, ModBlocks.AMFORA_NO_PATTERN.get().asItem().getDefaultInstance());
            }
        }
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return SoundType.CHISELED_BOOKSHELF;
    }
}
