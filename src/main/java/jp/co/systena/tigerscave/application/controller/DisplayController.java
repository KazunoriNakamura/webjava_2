package jp.co.systena.tigerscave.application.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgmodel.Fighter;
import jp.co.systena.tigerscave.rpgmodel.Job;
import jp.co.systena.tigerscave.rpgmodel.Magician;
import jp.co.systena.tigerscave.rpgview.CharacterForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class DisplayController {

  @Autowired
  HttpSession session;

  String m_name;

  // トップページ
  @RequestMapping(value = "/create", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView  mav) {

      // ラジオボタンの選択肢
      Map<String, String> jobMap = new LinkedHashMap<String, String>();
      jobMap.put("key_fighter", "戦士");
      jobMap.put("key_magician", "魔法使い");

      mav.addObject("jobMapItems",jobMap);

      mav.addObject("jobForm", new CharacterForm());

      mav.setViewName("CharacterCreate");

      return mav;
  }

//コマンドページ
 @RequestMapping(value = "/command", method = RequestMethod.GET)
  public ModelAndView command(@Valid CharacterForm jobForm, ModelAndView mav) {

      String selectJob =  jobForm.getSelectedJob();
      String job = "戦士";
      if (selectJob.equals("key_fighter")) {
        Fighter fighter = new Fighter();

        session.setAttribute("job", fighter);
      }
      else if (selectJob.equals("key_magician"))
      {
        job ="魔法使い";
        Magician magician = new Magician();
        session.setAttribute("job", magician);
      }

      m_name = jobForm.getInputName();
      mav.addObject("job", job);
      mav.addObject("name", jobForm.getInputName());
      mav.setViewName("command");
      return mav;
  }

 // 結果ページ
 @RequestMapping(value = "/result", method = RequestMethod.GET)
 public ModelAndView result(ModelAndView mav) {

   String wepon;
   Job job = (Job) session.getAttribute("job");
   job.attack();
   wepon = job.attack;
   mav.addObject("wepon", wepon);
   mav.addObject("name", m_name);
   mav.setViewName("result");

   return mav;
 }
}
