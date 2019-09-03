package jp.co.systena.tigerscave.rpgmodel;

public class Magician extends Job {

  @Override
  public void attack() {
    attack = "魔法で攻撃した！";
  }

  @Override
  public void heal() {
    heal = "魔法で回復した！";
  }
}
