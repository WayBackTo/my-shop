package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController{

    @Autowired
    TbUserService tbUserService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = new TbUser();

        return tbUser;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(Model model){
        TbUser tbUser = new TbUser();
        model.addAttribute("tbUser",tbUser);
        return "user_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    @ResponseBody
    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = BaseResult.success();
        System.out.println(ids);
        return baseResult;
    }

}
