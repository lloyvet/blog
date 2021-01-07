package com.lloyvet.blog.vo;

import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.MenuTreeNode;
import com.lloyvet.blog.domain.Menu;
import com.lloyvet.blog.util.MenuTreeUtil;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zihao Shen
 */
@Data
public class InitInfoVO {

    private List<MenuVo> menuInfo;

    private HomeInfo homeInfo;

    private LogoInfo logoInfo;

    public static InitInfoVO init(List<Menu> menuList) {
        List<MenuVo> menuInfo = new ArrayList<>();
        for (Menu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getId());
            menuVO.setPid(e.getPid());
            menuVO.setHref(e.getHref());
            menuVO.setTitle(e.getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setTarget(e.getTarget());
            menuInfo.add(menuVO);
        }
        HomeInfo homeInfo = new HomeInfo();
        homeInfo.setHref(Constant.HOME_HREF);
        homeInfo.setTitle(Constant.HOME_TITLE);

        LogoInfo logoInfo = new LogoInfo();
        logoInfo.setTitle(Constant.LOGO_TITLE);
        logoInfo.setImage(Constant.LOGO_IMAGE);

        InitInfoVO initInfoVO = new InitInfoVO();
        initInfoVO.setMenuInfo(MenuTreeUtil.toTree(menuInfo, 0L));
        initInfoVO.setHomeInfo(homeInfo);
        initInfoVO.setLogoInfo(logoInfo);
        return initInfoVO;
    }

    @Data
    public static class HomeInfo {
        private String title;
        private String href;
    }

    @Data
    public static class LogoInfo {
        private String title;
        private String image;
    }

}
