package com.zhdoyu.demo.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-09
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectSubjectList(long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

}
