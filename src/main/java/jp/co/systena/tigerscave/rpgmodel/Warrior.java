package jp.co.systena.tigerscave.rpgmodel;

public class Warrior extends Job {

  @Override
  public void attack() {
    attack = "拳で攻撃した！ １０のダメージ！";
  }

  @Override
  public void heal() {
    heal = "薬草で回復した！";
  }
}
