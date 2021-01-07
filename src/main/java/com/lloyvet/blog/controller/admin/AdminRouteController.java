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
//
//    @ApiOperation("更新用户页面")
//    @PreAuthorize("hasAuthority('sys:user:edit')")
//    @GetMapping("/user/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getById(id));
//        return "admin/user/user-edit";
//    }
//
//
    @GetMapping("/menu/edit/{id}")
    public String editMenu(@PathVariable("id") Long id, Model model) {
        model.addAttribute("menu", menuService.getById(id));
        return "admin/menu/menu-edit";
    }
//
//    @ApiOperation("回复评论页面")
//    @PreAuthorize("hasAuthority('blog:comment:reply')")
//    @GetMapping("/comment/add")
//    public String replyComment(@RequestParam("pid") Long pid,
//                               @RequestParam("articleId") Long articleId,
//                               Model model) {
//        model.addAttribute("pid", pid);
//        model.addAttribute("articleId", articleId);
//        return "admin/comment/comment-add";
//    }
//
//    @ApiOperation("回复留言页面")
//    @PreAuthorize("hasAuthority('blog:message:reply')")
//    @GetMapping("/message/{pid}")
//    public String replyMessage(@PathVariable("pid") Long pid, Model model) {
//        model.addAttribute("pid", pid);
//        return "admin/message/message-add";
//    }
//
//    @ApiOperation("更新友链页面")
//    @PreAuthorize("hasAuthority('blog:link:edit')")
//    @GetMapping("/link/{id}")
//    public String editLink(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("link", linkService.getById(id));
//        return "admin/link/link-edit";
//    }
//
    @GetMapping("/photo/edit/{id}")
    public String editPhoto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("photo", photoService.getById(id));
        return "admin/photo/photo-edit";
    }
//
//    @ApiOperation("更新公告页面")
//    @PreAuthorize("hasAuthority('sys:notice:edit')")
//    @GetMapping("/notice/{id}")
//    public String editNotice(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("notice", noticeService.getById(id));
//        return "admin/notice/notice-edit";
//    }
//
//    @ApiOperation("更新本地存储页面")
//    @PreAuthorize("hasAuthority('sys:localstorage:edit')")
//    @GetMapping("/localStorage/{id}")
//    public String editLocalStorage(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("localStorage", localStorageService.getById(id));
//        return "admin/localstorage/localstorage-edit";
//    }
}
