package com.quanta.content.blockentity.cable.component;

import com.quanta.core.transfer.TransferType;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.util.Collections;
import java.util.List;

public class CableTypeManager {

    private final ObjectArrayList<TransferType> types = new ObjectArrayList<>();
    private int typeMask;
    private final Runnable onChanged;

    public CableTypeManager(Runnable onChanged) {
        this.onChanged = onChanged;
    }

    public boolean addType(TransferType t) {
        if (types.contains(t)) {
            return false;
        }
        types.add(t);
        typeMask |= t.bitMask;
        onChanged.run();
        return true;
    }

    public boolean removeType(TransferType t) {
        if (!types.remove(t)) {
            return false;
        }
        typeMask &= ~t.bitMask;
        onChanged.run();
        return true;
    }

    public boolean hasType(TransferType t) {
        return (typeMask & t.bitMask) != 0;
    }

    public List<TransferType> getTypes() {
        return Collections.unmodifiableList(types);
    }

    public int getTypeMask() {
        return typeMask;
    }

    public TransferType getBaseType() {
        return types.isEmpty() ? TransferType.QUANTA : types.get(0);
    }

    public boolean isEmpty() {
        return types.isEmpty();
    }

    public int size() {
        return types.size();
    }

    public void clear() {
        types.clear();
        typeMask = 0;
    }

    public int[] getTypeIds() {
        return types.stream().mapToInt(TransferType::ordinal).toArray();
    }

    public void loadFromIds(int[] ids) {
        types.clear();
        typeMask = 0;
        for (int id : ids) {
            TransferType tt = TransferType.fromId(id);
            types.add(tt);
            typeMask |= tt.bitMask;
        }
    }
}