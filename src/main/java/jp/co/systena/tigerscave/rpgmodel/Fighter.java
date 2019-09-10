package jp.co.systena.tigerscave.rpgmodel;

public class Fighter extends Job {

  @Override
  public void attack() {
    attack = "剣で攻撃した！ １０のダメージ！";
  }

  @Override
  public void heal() {
    heal = "薬草で回復した！";
  }

}
