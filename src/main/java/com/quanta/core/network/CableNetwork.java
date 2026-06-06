package com.quanta.core.network;

import com.quanta.core.transfer.GasStack;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.*;

public class CableNetwork {
    private final UUID id;
    private final Set<BlockPos> cables = new ObjectOpenHashSet<>();
    private boolean dirty;
    private int quantaBuffer, maxQuanta, maxItems, maxFluid, maxGas;
    private final List<ItemStack> itemBuffer = new ArrayList<>();
    private FluidStack fluidBuffer = FluidStack.EMPTY;
    private GasStack gasBuffer = GasStack.EMPTY;

    public CableNetwork(UUID id) { this.id = id; }
    public UUID getId() { return id; }
    public int size() { return cables.size(); }
    public boolean isEmpty() { return cables.isEmpty(); }
    public boolean isDirty() { return dirty; }
    public void clearDirty() { dirty = false; }
    public Set<BlockPos> getCables() { return Collections.unmodifiableSet(cables); }
    public void addCable(BlockPos pos) { if (cables.add(pos)) dirty = true; }
    public void removeCable(BlockPos pos) { if (cables.remove(pos)) dirty = true; }

    public void merge(CableNetwork o) {
        if (o == this) return;
        cables.addAll(o.cables); 
        quantaBuffer += o.quantaBuffer; 
        itemBuffer.addAll(o.itemBuffer);
        if (fluidBuffer.isEmpty()) fluidBuffer = o.fluidBuffer;
        else if (!o.fluidBuffer.isEmpty() && fluidBuffer.is(o.fluidBuffer.getFluid())) fluidBuffer.grow(o.fluidBuffer.getAmount());
        if (gasBuffer.isEmpty()) gasBuffer = o.gasBuffer;
        else if (!o.gasBuffer.isEmpty() && gasBuffer.isGasEqual(o.gasBuffer)) gasBuffer.setAmount(gasBuffer.getAmount() + o.gasBuffer.getAmount());
        updateCapacities(o.maxQuanta, o.maxItems, o.maxFluid, o.maxGas); dirty = true;
    }

    public void updateCapacities(int q, int i, int f, int g) { 
        maxQuanta = Math.max(maxQuanta, q); 
        maxItems = Math.max(maxItems, i); 
        maxFluid = Math.max(maxFluid, f); 
        maxGas = Math.max(maxGas, g); 
    }

    public void clearAllBuffers() { 
        quantaBuffer = 0; itemBuffer.clear(); 
        fluidBuffer = FluidStack.EMPTY; 
        gasBuffer = GasStack.EMPTY; 
    }

    public int getQuantaStored() { return quantaBuffer; } 
    public int getMaxQuanta() { return maxQuanta; }
    public int addQuanta(int a) { 
        int s = Math.min(a, maxQuanta - quantaBuffer); 
        quantaBuffer += s; return s; 
    }

    public int extractQuanta(int a) { 
        int e = Math.min(a, quantaBuffer); 
        quantaBuffer -= e; return e; 
    }

    public int getItemCount() { 
        int c = 0; for (var s : itemBuffer) c += s.getCount(); 
        return c; 
    }

    public int getMaxItems() { return maxItems; } 
    public int getItemSlots() { return itemBuffer.size(); }
    public ItemStack getItem(int s) { return s < itemBuffer.size() ? itemBuffer.get(s) : ItemStack.EMPTY; }
    public void addItem(ItemStack s) { itemBuffer.add(s); }
    public ItemStack extractItem(int max) { 
        if (itemBuffer.isEmpty()) return ItemStack.EMPTY; 
        var s = itemBuffer.get(0); 
        int e = Math.min(max, s.getCount()); 
        var r = s.copyWithCount(e); s.shrink(e); 
        if (s.isEmpty()) itemBuffer.remove(0); 
        return r; 
    }

    public FluidStack getFluid() { return fluidBuffer; } public int getMaxFluid() { return maxFluid; }
    public int fillFluid(FluidStack s) { if (s.isEmpty()) return 0; if (fluidBuffer.isEmpty()) { int a = Math.min(s.getAmount(), maxFluid); fluidBuffer = s.copy(); fluidBuffer.setAmount(a); return a; } if (!fluidBuffer.is(s.getFluid())) return 0; int a = Math.min(s.getAmount(), maxFluid - fluidBuffer.getAmount()); fluidBuffer.grow(a); return a; }
    public FluidStack drainFluid(int max) { if (fluidBuffer.isEmpty()) return FluidStack.EMPTY; int e = Math.min(max, fluidBuffer.getAmount()); var r = fluidBuffer.copy(); r.setAmount(e); fluidBuffer.shrink(e); return r; }

    public int getMaxGas() { return maxGas; }
    public int fillGas(GasStack s) { if (s.isEmpty()) return 0; if (gasBuffer.isEmpty()) { int a = Math.min(s.getAmount(), maxGas); gasBuffer = s.copy(); gasBuffer.setAmount(a); return a; } if (!gasBuffer.isGasEqual(s)) return 0; int a = Math.min(s.getAmount(), maxGas - gasBuffer.getAmount()); gasBuffer.setAmount(gasBuffer.getAmount() + a); return a; }
    public GasStack drainGas(int max) { if (gasBuffer.isEmpty()) return GasStack.EMPTY; int e = Math.min(max, gasBuffer.getAmount()); var r = gasBuffer.copy(); r.setAmount(e); gasBuffer.setAmount(gasBuffer.getAmount() - e); return r; }
}