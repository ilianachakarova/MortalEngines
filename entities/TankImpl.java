package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private static final double HEALTH_POINTS = 100;
    private boolean defenseMode;
    private double attackPointsModifier;
    private double defensePointsModifier;
    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, HEALTH_POINTS);
        this.defenseMode = true;
        this.attackPointsModifier = 40.0;
        this.defensePointsModifier = 30.0;
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }

    @Override
    public void toggleDefenseMode() {
        this.defenseMode = !this.defenseMode;
        if(defenseMode){
            this.setAttackPoints(-40);
            this.setDefensePoints(30);
        }else {
            this.setAttackPoints(40);
            this.setDefensePoints(-30);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String targetOutput = super.convertTargetData();

        String mode = this.defenseMode ? "ON" : "OFF";

        sb.append(" *Type: Tank").append(System.lineSeparator());
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(" *Targets: ").append(targetOutput).append(System.lineSeparator());
        sb.append(" *DefenseMode: ").append(mode).append(System.lineSeparator());

        return  sb.toString();
    }
}
