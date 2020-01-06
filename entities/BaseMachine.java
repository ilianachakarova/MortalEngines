package entities;

import common.OutputMessages;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseMachine implements Machine {

    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        this.setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.healthPoints = healthPoints;

        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(name.trim().isEmpty()){
            throw new NullPointerException(OutputMessages.illegalMachineName);
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if(pilot == null){
            throw new IllegalArgumentException(OutputMessages.illegalPilot);
        }
        this.pilot = pilot;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    public void setAttackPoints(double attackPoints) {
        this.attackPoints =attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    public void setDefensePoints(double defensePoints) {

        this.defensePoints = defensePoints;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        if(this.healthPoints<0){
            this.healthPoints = 0;
        }else {
            this.healthPoints = healthPoints;
        }
    }

    @Override
    public List<String> getTargets() {
        return Collections.unmodifiableList(this.targets);
    }

    @Override
    public void attack(String target) {
        if(target == null || target.trim().isEmpty()){
            throw new IllegalArgumentException(OutputMessages.attackName);
        }
        this.targets.add(target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" *Health: ").append(this.getHealthPoints()).append(System.lineSeparator());
        sb.append(" *Attack: ").append(this.getAttackPoints()).append(System.lineSeparator());
        sb.append(" *Defense: ").append(this.getDefensePoints()).append(System.lineSeparator());


        return sb.toString().trim();
    }

    protected String convertTargetData(){
        String [] arr = new String[this.getTargets().size()];
        String targetOutput = "";
        if(this.getTargets().size()==0){
            targetOutput = "None";
        }else {
            arr = this.getTargets().toArray(arr);
            targetOutput = String.join(", ",arr);
        }
        return targetOutput;
    }
}
