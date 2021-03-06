package com.intelasers.repository;

import com.intelasers.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Long>, JpaSpecificationExecutor<TemplateEntity> {

    /**
     * 根据id查询模板
     * @param id
     * @return
     */
    @Query(value = "select * from tb_template where id = ?1", nativeQuery = true)
    TemplateEntity getTemplateById(Long id);

    /**
     *
     * 根据名字模糊查询模板
     * @param templateName
     * @return
     */
    @Query(value = "select * from tb_template tt where tt.template_name like CONCAT('%',:templateName,'%')", nativeQuery = true)
    List<TemplateEntity> getTemplateByName(@Param("templateName") String templateName);


    /**
     * 按条件查找模板--不分用户权限
     * @return
     */
    @Query(value = "select * from tb_template tt where tt.template_name like CONCAT('%',:templateName,'%') and "+
            "tt.id =:templateId and " +
            "tt.template_desc like CONCAT('%',:templateDesc,'%') " +
            "order by tt.id desc", nativeQuery = true)
    List<TemplateEntity> findAllTemplates(@Param("templateId")Long templateId,@Param("templateName")  String templateName,@Param("templateDesc") String templateDesc);

    /**
     * 列出所有模板
     * @return
     */
    @Query(value = "select * from tb_template ", nativeQuery = true)
    List<TemplateEntity> listAllTemplates();


}
