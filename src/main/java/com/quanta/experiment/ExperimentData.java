package com.quanta.experiment;

import net.minecraft.nbt.CompoundTag;
import java.util.*;

public class ExperimentData {
    private final UUID experimentId;
    private final ExperimentHypothesis hypothesis;
    private final List<ExperimentStep> steps;
    private ExperimentStatus status;
    private float confidence;
    private CompoundTag resultData;
    
    public ExperimentData(ExperimentHypothesis hypothesis) {
        this.experimentId = UUID.randomUUID();
        this.hypothesis = hypothesis;
        this.steps = new ArrayList<>();
        this.status = ExperimentStatus.DRAFT;
        this.confidence = 0;
    }
    
    public void addStep(ExperimentStep step) {
        steps.add(step);
    }
    
    public void execute(ExperimentContext context) {
        status = ExperimentStatus.RUNNING;
        
        for (ExperimentStep step : steps) {
            if (!step.execute(context)) {
                status = ExperimentStatus.FAILED;
                return;
            }
        }
        
        analyzeResults(context);
    }
    
    private void analyzeResults(ExperimentContext context) {
        this.confidence = 0.5f;
        this.status = ExperimentStatus.PARTIAL;
        this.resultData = context.getCollectedData();
    }
    
    public UUID getExperimentId() { return experimentId; }
    public ExperimentHypothesis getHypothesis() { return hypothesis; }
    public ExperimentStatus getStatus() { return status; }
    public float getConfidence() { return confidence; }
}