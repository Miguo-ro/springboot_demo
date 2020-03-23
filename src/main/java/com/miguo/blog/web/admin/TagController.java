package com.miguo.blog.web.admin;

import com.miguo.blog.po.Tag;
import com.miguo.blog.service.TagService;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";

    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes,
                       @PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable) {
        Tag t = tagService.getTagByName(tag.getName());
        if(t!=null){
            bindingResult.rejectValue("name","nameError","标签已存在，不可重复添加!");
        }
        if (bindingResult.hasErrors()){
            return "admin/tags-input";
        }
        Tag saveTag = tagService.saveTag(tag);
        if(saveTag==null){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete( @PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String edit(@PathVariable Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute(tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String update(@PathVariable Long id,@Valid Tag tag,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        Tag t = tagService.getTag(id);
        if (t==null){
            bindingResult.rejectValue("name","nameError","标签已存在，不可重复添加！");
        }
        if (bindingResult.hasErrors()){
            return "admin/tags";
        }
        Tag tagUpdate = tagService.updateTag(id, tag);
        if (tagUpdate==null){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }
}
