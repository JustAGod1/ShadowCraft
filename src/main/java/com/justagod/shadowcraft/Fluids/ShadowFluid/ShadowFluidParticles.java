package com.justagod.shadowcraft.Fluids.ShadowFluid;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 1:47
 */
public class ShadowFluidParticles extends EntityReddustFX {

    float reddustParticleScale;





    public ShadowFluidParticles(World world) {
        super(world, 0, 0, 0, 0, 0, 0);
    }



    public ShadowFluidParticles(World par1World, double x, double y, double z, float scale, int maxAge) {
        super(par1World, x, y, z, 0.0f, 0.0f, 0.0f);

        float var12 = (float)Math.random() * 0.4F + 0.6F;
        float f4 = (float)Math.random() * 0.2F + 0.3F;
        this.particleRed = 0;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;
        this.particleGreen = 0;//((float)(Math.random() * 0.20000000298023224D) + 1F) * f4;
        this.particleBlue = 0;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;
        this.particleScale *= scale;
        this.reddustParticleScale = this.particleScale;
        this.particleMaxAge = maxAge;
        this.noClip = true;
        this.particleAlpha = 1;
        this.particleAge = 0;



    }

    @Override
    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        super.renderParticle(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }
}
