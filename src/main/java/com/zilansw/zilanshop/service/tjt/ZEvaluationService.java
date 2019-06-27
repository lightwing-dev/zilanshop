package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.dao.ZEvaluationDao;
import com.zilansw.zilanshop.pojo.ZEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tjt
 * @date 2019-06-27
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class ZEvaluationService {

    @Autowired
    private ZEvaluationDao zEvaluationDao;

    /**
     * 新增评论
     * @param zEvaluation
     * @return
     */
    public int insert(ZEvaluation zEvaluation){
        return zEvaluationDao.insert(zEvaluation);
    }

    /**
     * 修改评论
     * @param zEvaluation
     * @return
     */
    public int update(ZEvaluation zEvaluation){
        return zEvaluationDao.updateById(zEvaluation);
    }

    /**
     * 删除评论
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zEvaluationDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public List<ZEvaluation> selectAll(String gname, Integer pageIndex, Integer limit){
        return zEvaluationDao.selectPage(gname,pageIndex,limit);
    }

    public int selectCount(String gname){
        QueryWrapper<ZEvaluation> queryWrapper = new QueryWrapper<>();
        if (gname!="" && gname!=null){
            queryWrapper.eq("gname",gname);
        }
        return zEvaluationDao.selectCount(queryWrapper);
    }

}
