package com.miguo.blog.web.admin;

import com.miguo.blog.po.Type;
import com.miguo.blog.service.TypeService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String List(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult ,RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName!=null){
           bindingResult.rejectValue("name","nameError","分类已存在，不可重复添加！");
       }

        if (bindingResult.hasErrors()){
           return "admin/types-input";
       }
        Type t = typeService.saveType(type);
       if(t==null){
           redirectAttributes.addFlashAttribute("message","新增失败");
       }else {
           redirectAttributes.addFlashAttribute("message","新增成功");
       }
       return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
    model.addAttribute("type",typeService.getType(id));
    return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String post(@Valid Type type, BindingResult bindingResult,@PathVariable Long id,RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName!=null){
            bindingResult.rejectValue("name","nameError","分类已存在，不可重复添加！");
        }

        if (bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
