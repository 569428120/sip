package com.sip.charge.service.web;


import com.sip.charge.model.ChargeProjectModel;
import com.sip.charge.service.service.IChargeProjectService;
import com.sip.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/charge-project")
public class ChargeProjectController {

    @Resource
    private IChargeProjectService chargeProjectService;

    /**
     * 获取分页数据
     *
     * @return ResponseEntity<PageResult>
     */
    @GetMapping("/gets/page")
    public ResponseEntity<PageResult> getListPage(ChargeProjectModel projectModel,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        return ResponseEntity.ok(chargeProjectService.getListPage(projectModel, page, pageSize));
    }

    /**
     * 根据项目ID查询项目数据
     *
     * @param projectId 项目ID
     * @return ResponseEntity<ChargeProjectModel>
     */
    @GetMapping("/gets/get-by-id")
    public ResponseEntity<ChargeProjectModel> getModelById(@RequestParam(value = "projectId", defaultValue = "") Long projectId) {
        return ResponseEntity.ok(chargeProjectService.selectById(projectId));
    }

    /**
     * 根据项目id删除数据
     *
     * @param projectIds 项目id
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/deletes/del-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(value = "projectIds", defaultValue = "") String projectIds) {
        chargeProjectService.deleteByIds(Arrays.asList(projectIds.split(",")));
        return ResponseEntity.ok("delete success");
    }

    /**
     * 删除数据
     *
     * @param projectModel 数据
     * @return ResponseEntity<String>
     */
    @PutMapping("/updates/update-by-id")
    public ResponseEntity<String> updateModel(@RequestBody ChargeProjectModel projectModel) {
        chargeProjectService.updateProject(projectModel);
        return ResponseEntity.ok("update success");
    }


    @PostMapping("posts")
    public ResponseEntity<String> createProject(@RequestBody ChargeProjectModel projectModel) {
        chargeProjectService.createProject(projectModel);
        return ResponseEntity.ok("create success");
    }


}
