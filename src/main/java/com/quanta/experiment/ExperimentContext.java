package com.quanta.experiment;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.*;

public class ExperimentContext {
    private final Player player;
    private final Level level;
    private final Set<String> presentMachines = new HashSet<>();
    private final List<ItemStack> presentItems = new ArrayList<>();
    private int availableQuanta;
    private final Map<String, Object> measurements = new HashMap<>();
    
    public ExperimentContext(Player player) {
        this.player = player;
        this.level = player.level();
        scanSurroundings();
    }
    
    private void scanSurroundings() {
        // Escanear blocos em um raio de 5 blocos
        BlockPos.betweenClosed(
            player.blockPosition().offset(-5, -3, -5),
            player.blockPosition().offset(5, 3, 5)
        ).forEach(pos -> {
            // Verificar se é uma máquina Quanta
            // Adicionar a presentMachines
        });
        
        // Escanear inventário do jogador
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty()) {
                presentItems.add(stack.copy());
            }
        }
        
        // Calcular Quanta disponível (do Codex + implantes + baterias)
        availableQuanta = getTotalQuantaAvailable();
    }
    
    private int getTotalQuantaAvailable() {
        int total = 0;
        // TODO: Soma de Codex, implantes, baterias no inventário
        return total;
    }
    
    public boolean hasMachine(String machineId) {
        return presentMachines.contains(machineId);
    }
    
    public boolean hasItem(ItemStack item) {
        return presentItems.stream().anyMatch(i -> ItemStack.isSameItemSameComponents(i, item));
    }
    
    public int getAvailableQuanta() { return availableQuanta; }
    
    public void recordMeasurement(String key, Object value) {
        measurements.put(key, value);
    }
    
    public float getMeasurementConfidence() {
        // Calcular confiança baseada na precisão das medições
        // Quanto mais medições consistentes, maior a confiança
        return 0.85f; // Placeholder
    }
    
    public CompoundTag getCollectedData() {
        CompoundTag tag = new CompoundTag();
        // Salvar todas as medições
        return tag;
    }
}