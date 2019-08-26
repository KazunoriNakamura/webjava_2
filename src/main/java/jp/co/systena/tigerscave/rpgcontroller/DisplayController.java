package jp.co.systena.tigerscave.rpgcontroller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgview.CharacterForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class DisplayController {

  // トップページ
  @RequestMapping(value = "/create", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView  mav) {

      // ラジオボタンの選択肢
      Map<String, String> jobMap = new LinkedHashMap<String, String>();
      jobMap.put("key_fighter", "戦士");
      jobMap.put("key_magician", "魔法使い");

      mav.addObject("jobMapItems",jobMap);

      mav.addObject("jobForm", new CharacterForm());
      return mav;
  }

//コマンドページ
  @RequestMapping(value = "/command", method = RequestMethod.GET)
  public String command(@ModelAttribute CharacterForm jobForm, Model model) {
      return "command";
  }
}
