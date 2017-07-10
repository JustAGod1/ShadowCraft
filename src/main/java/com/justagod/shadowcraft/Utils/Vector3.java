package com.justagod.shadowcraft.Utils;

import net.minecraft.world.World;

/**
 * Created by Yuri on 08.07.17.
 */
public class Vector3 {
    private float x;
    private float y;
    private float z;
    private World world;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float x, float y, float z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getDistanceTo(Vector3 vec) {
        return Math.sqrt((vec.x - x) * (vec.x - x) + (vec.y - y) * (vec.y - y) + (vec.z - z) * (vec.z - z));
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
