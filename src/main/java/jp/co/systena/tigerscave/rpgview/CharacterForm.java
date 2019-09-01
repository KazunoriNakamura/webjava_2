package jp.co.systena.tigerscave.rpgview;

public class CharacterForm {

  // 選択されたラジオボタン
  private String selectedJob;

 // 入力された名前
  private String inputName;

  public String getSelectedJob() {
        return selectedJob;
  }

  public void setSelectedJob(String selectedJob) {
        this.selectedJob = selectedJob;
  }

  public String getInputName() {
    return inputName;
}

  public void setInputName(String inputName) {
    this.inputName= inputName;
}
}
