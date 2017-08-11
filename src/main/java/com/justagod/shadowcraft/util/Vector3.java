package com.justagod.shadowcraft.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Yuri on 08.07.17.
 */
public class Vector3 {
    private float x;
    private float y;
    private float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 readFromNBT(NBTTagCompound compound) {
        float x = compound.getFloat("x");
        float y = compound.getFloat("y");
        float z = compound.getFloat("z");

        return new Vector3(x, y, z);
    }

    public static Vector3 readFromByteBuf(ByteBuf buf) {
        float x = buf.readFloat();
        float y = buf.readFloat();
        float z = buf.readFloat();

        return new Vector3(x, y, z);
    }

    public float getX() {
        return x;
    }

    public int getXInt() {
        return (int) x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getYInt() {
        return (int) y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public int getZInt() {
        return (int) z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public double getDistanceTo(Vector3 vec) {
        return Math.sqrt((vec.x - x) * (vec.x - x) + (vec.y - y) * (vec.y - y) + (vec.z - z) * (vec.z - z));
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.setFloat("x", x);
        compound.setFloat("y", y);
        compound.setFloat("z", z);
    }

    public void writeToByteBuf(ByteBuf buf) {
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (Float.compare(vector3.x, x) != 0) return false;
        if (Float.compare(vector3.y, y) != 0) return false;
        return Float.compare(vector3.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }


}
