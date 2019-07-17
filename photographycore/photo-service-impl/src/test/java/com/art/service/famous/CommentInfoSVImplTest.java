package com.art.service.famous;

import com.art.ServiceApplication;
import com.art.beans.famous.CommentInformation;
import com.art.util.famous.Constans;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.art.util.famous.WslixiaoliangUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

/**
 * 评论信息测试类
 * @author wslixiaoliang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CommentInfoSVImplTest {

    @Autowired(required = false)
    private ICommentInfoSV commentInfoSV;
    private static Log LOGGER = LogFactory.getLog(CommentInfoSVImplTest.class);

    /**
     * 新增,查询,测试评论信息
     */
    @Test
    public void testAddCommentInformation()
    {

        Map<String,Object> params = new HashMap();
        String nowTime = WslixiaoliangUtil.date2String(new Date(), Constans.YYYY_MM_DD_HH_MM_SS);

        LOGGER.info("=================当前时间：年-月-日 时：分：秒 "+nowTime+"============================");
        long commentId = WslixiaoliangUtil.getPrimaryKey(nowTime);
        LOGGER.info("=================评论信息ID："+commentId+"============================");

        String nowTime2 = WslixiaoliangUtil.date2String(new Date(), Constans.YYYY_MM_DD_HH_MM_SS);
        LOGGER.info("=================当前时间：年-月-日 时：分：秒 "+nowTime2+"============================");
        long productionId = WslixiaoliangUtil.getPrimaryKey(nowTime2);
        LOGGER.info("=================评论信息ID："+commentId+"============================");

        String nowTime3 = WslixiaoliangUtil.date2String(new Date(), Constans.YYYY_MM_DD_HH_MM_SS);
        LOGGER.info("=================当前时间：年-月-日 时：分：秒 "+nowTime3+"============================");
        long consumerId = WslixiaoliangUtil.getPrimaryKey(nowTime3);
        LOGGER.info("=================评论信息ID："+commentId+"============================");

        //数据库为dateTime，mapper为TIMSTAMP，Java为String即可；
        String commentTime = WslixiaoliangUtil.date2String(new Date(), Constans.YYYY_MM_DD_HH_MM_SS);

        params.put("commentId",commentId);
        params.put("productionId",productionId);
        params.put("consumerId",consumerId);
        params.put("commentContent","这是多么牛逼的一篇文章，赞！");
        params.put("commentTime",commentTime);
        commentInfoSV.addCommentInformation(params);


        //查询：评论信息
        Map map = new HashMap();
        map.put("commentId",commentId);
        List<CommentInformation> list = commentInfoSV.getCommentInformations(map);

        for(CommentInformation commentInformation :list){
            LOGGER.info("==============="+commentInformation.getCommentContent()+"");
            LOGGER.info("==============="+commentInformation.getCommentTime()+"");
        }


        //删除评论信息
        Map amap = new HashMap();
        amap.put("commentId","2019071609385273144");
        commentInfoSV.deleteCommentInfoById(amap);
        commentInfoSV.deleteCommentInfoById(map);
        List<CommentInformation> commentList = commentInfoSV.getCommentInformations(amap);
        int size = commentList.size();

    }









}
