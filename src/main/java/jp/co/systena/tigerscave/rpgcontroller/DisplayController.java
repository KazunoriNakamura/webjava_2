package jp.co.systena.tigerscave.rpgcontroller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jp.co.systena.tigerscave.rpgview.CharacterForm;

public class DisplayController {

  // トップページ
  @RequestMapping(value = "/create")
  public String index(Model model) {

      // ラジオボタンの選択肢
      Map<String, String> jobMap = new LinkedHashMap<String, String>();
      jobMap.put("key_java", "戦士");
      jobMap.put("key_python", "魔法使い");

      model.addAttribute("jobMapItems",jobMap);

      // ビューの表示時にインスタンスを渡さないとエラーが出るので、その対策
      model.addAttribute("characterForm", new CharacterForm());
      return "index";
  }

//確認ページ
  @RequestMapping(value = "/command", method = RequestMethod.GET)
  public String confirm(@ModelAttribute CharacterForm characterForm, Model model) {
      return "confirm";
  }
}
