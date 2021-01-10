package com.lloyvet.blog.controller.admin;


import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zihao Shen
 * 路由
 */
@Controller
@RequestMapping("/admin")
public class AdminRouteController {

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    PhotoService photoService;

    @Autowired
    MenuService menuService;

    @GetMapping("/page/{moduleName}/{pageName}")
    public ModelAndView getPage(@PathVariable("moduleName") String moduleName, @PathVariable("pageName") String pageName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/" + moduleName + "/" + pageName);
        return modelAndView;
    }

    @GetMapping("/article/{id}")
    public String editArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.getById(id));
        model.addAttribute("tagList", tagService.listTagByArticleId(id));
        return "admin/article/article-edit";
    }
    @GetMapping("/category/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "admin/category/category-edit";
    }

    @GetMapping("/tag/{id}")
    public String editTag(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tag", tagService.getById(id));
        return "admin/tag/tag-edit";
    }

    @GetMapping("/menu/edit/{id}")
    public String editMenu(@PathVariable("id") Long id, Model model) {
        model.addAttribute("menu", menuService.getById(id));
        return "admin/menu/menu-edit";
    }
    @GetMapping("/photo/edit/{id}")
    public String editPhoto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("photo", photoService.getById(id));
        return "admin/photo/photo-edit";
    }

}
