package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private static final double HEALTH_POINTS = 200;
    private boolean aggressiveMode;
    private double attackPointsModifier;
    private double defensePointsModifier;


    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, HEALTH_POINTS);
        this.aggressiveMode = true;
        this.attackPointsModifier = 50.0;
        this.defensePointsModifier = 25.0;
    }


    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        this.aggressiveMode = !this.aggressiveMode;
        if(aggressiveMode == true){
            this.setHealthPoints((this.getAttackPoints())+50);
            this.setHealthPoints((this.getDefensePoints()-25));
        }else{
            this.setHealthPoints(this.getAttackPoints()-50);
            this.setHealthPoints(this.getDefensePoints()+25);
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String targetOutput = super.convertTargetData();

        String mode = this.aggressiveMode ? "ON" : "OFF";

        sb.append(" *Type: Fighter").append(System.lineSeparator());
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(" *Targets: ").append(targetOutput).append(System.lineSeparator());
        sb.append(" *AggressiveMode: ").append(mode).append(System.lineSeparator());

        return  sb.toString();
    }
}
