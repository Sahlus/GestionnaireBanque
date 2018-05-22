package com.example.GBanque.controller;


import com.example.GBanque.dao.BanqueAccountDAO;
import com.example.GBanque.exception.BanqueTransactionException;
import com.example.GBanque.form.SendMoneyForm;
import com.example.GBanque.model.BanqueAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BanqueAccountDAO banqueAccountDAO;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String showBanqueAccount(Model model){
        List<BanqueAccountInfo> list = banqueAccountDAO.listBanqueAccountInfo();
        model.addAttribute("accountInfos",list);

        return "accountsPage";
    }

    @RequestMapping(value = "/sendMoneyForm",method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model){

        SendMoneyForm form= new SendMoneyForm(1L,2L,700d);
        model.addAttribute("sendMoneyForm",form);
        return "sendMoneyPage";
    }

    @RequestMapping(value = "/sendMoney",method = RequestMethod.POST)
    public String processSendMoney(Model model,SendMoneyForm sendMoneyForm){

        System.out.println("Send Money"+sendMoneyForm.getAmount());

        try {
            banqueAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(),sendMoneyForm.getToAccountId(),sendMoneyForm.getAmount());
        }catch (BanqueTransactionException e){
            model.addAttribute("errorMessage","Error"+e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }




}
