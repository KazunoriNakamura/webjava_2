package jp.co.systena.tigerscave.application.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import jp.co.systena.tigerscave.rpgmodel.Warrior;
import jp.co.systena.tigerscave.rpgview.CharacterForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class DisplayController {

  @Autowired
  HttpSession session;

  String m_name;
  int m_partynum = 0;

  // 名前
  String[] arryName = new String[4];

  // 職業
  String[] arryJob  = new String[4];

  List<String[]> data = new ArrayList<>();

  // 敵HP
  int enemyHP = 100;

  // トップページ
  @RequestMapping(value = "/create",  method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView  mav) {

      // ラジオボタンの選択肢
      Map<String, String> jobMap = new LinkedHashMap<String, String>();
      jobMap.put("key_fighter", "戦士");
      jobMap.put("key_magician", "魔法使い");
      jobMap.put("key_warrior", "武闘家");

      mav.addObject("jobMapItems",jobMap);
      mav.addObject("jobForm", new CharacterForm());
      mav.setViewName("CharacterCreate");
      return mav;
  }

  @RequestMapping(value = "/command",  params = "more", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView more(ModelAndView  mav,@Valid CharacterForm jobForm) {
    Map<String, String> jobMap = new LinkedHashMap<String, String>();
    jobMap.put("key_fighter", "戦士");
    jobMap.put("key_magician", "魔法使い");
    jobMap.put("key_warrior", "武闘家");

    mav.addObject("jobMapItems",jobMap);

    mav.addObject("jobForm", new CharacterForm());

    mav.setViewName("CharacterCreate2");
    if (m_partynum <= 4) {
      String job = setSsession(jobForm);
      arryName[m_partynum] = jobForm.getInputName();
      arryJob[m_partynum] = job;
      m_partynum++;

    }
    else {
      mav.addObject("msg1","キャラ作成は４人までです!");
    }
    return mav;
  }

//コマンドページ
 @RequestMapping(value = "/command", method = RequestMethod.GET)
  public ModelAndView command(@Valid CharacterForm jobForm, ModelAndView mav) {

   if (m_partynum == 0) {
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
      else if (selectJob.equals("key_warrior"))
      {
        job ="武闘家";
        Warrior warrior = new Warrior();
        session.setAttribute("job", warrior);
      }

      m_name = jobForm.getInputName();
      mav.addObject("job", job);
      mav.addObject("name", jobForm.getInputName());
      mav.setViewName("command");
   }
   else {
     String job = setSsession(jobForm);
     arryName[m_partynum] = jobForm.getInputName();
     arryJob[m_partynum] = job;
     for( int i = 0; i <= m_partynum; i++ ){
       data.add(new String[] {arryJob[i], arryName[i]});
     }
     mav.addObject("data", data);
     mav.setViewName("command2");
   }

      return mav;
  }

 @RequestMapping(value = "/command",params = "return", method = RequestMethod.GET)
 public ModelAndView recommand(@Valid CharacterForm jobForm, ModelAndView mav) {
   mav.addObject("data", data);
   mav.setViewName("command2");
   return mav;
 }

 // 結果ページ
 @RequestMapping(value = "/result", params = "attack", method = RequestMethod.GET)
 public ModelAndView attack(ModelAndView mav) {

   enemyHP -= 10;
   String wepon;
   Job job = (Job) session.getAttribute("job");
   job.attack();
   wepon = job.attack;
   mav.addObject("wepon", wepon);
   m_name = arryName[0];
   mav.addObject("name", m_name);
   mav.setViewName("result");

   if (enemyHP == 0) {
     mav.addObject("msg1","敵をやっつけた！");
     enemyHP = 10;
   }
   return mav;
 }

 @RequestMapping(value = "/result", params = "heal", method = RequestMethod.GET)
 public ModelAndView heal(ModelAndView mav) {

   String healway;
   Job job = (Job) session.getAttribute("job");
   job.heal();
   healway = job.heal;
   mav.addObject("wepon", healway);
   mav.addObject("name", m_name);
   mav.setViewName("result");

   return mav;
 }

 String setSsession(CharacterForm jobForm)
 {
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
   else if (selectJob.equals("key_warrior"))
   {
     job ="武闘家";
     Warrior warrior = new Warrior();
     session.setAttribute("job", warrior);
   }

   return job;
 }
}
