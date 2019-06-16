package com.sip.charge.service.web;


import com.sip.charge.model.ChargeDetailsModel;
import com.sip.charge.service.service.IChargeDetailsService;
import com.sip.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 收费详情
 */
@RestController
@RequestMapping("/charge-details")
public class ChargeDetailsController {

    @Resource
    private IChargeDetailsService chargeDetailsService;

    /**
     * 获取数据
     *
     * @return ResponseEntity<PageResult>
     */
    @GetMapping("/gets/all")
    public ResponseEntity<List<ChargeDetailsModel>> getListPage(@RequestParam(value = "projectId") Long projectId,
                                                                @RequestParam(value = "name", defaultValue = "") String name) {

        return ResponseEntity.ok(chargeDetailsService.getTreeList(projectId, name));
    }

    /**
     * 查询收费详情item
     *
     * @param detailId 项目ID
     * @return ResponseEntity<ChargeProjectModel>
     */
    @GetMapping("/gets/get-by-id")
    public ResponseEntity<ChargeDetailsModel> getModelById(@RequestParam(value = "detailId", defaultValue = "") Long detailId) {
        return ResponseEntity.ok(chargeDetailsService.selectById(detailId));
    }

    /**
     * 根据项目id删除数据
     *
     * @param detailIds 项目id
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/deletes/del-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(value = "detailIds", defaultValue = "") String detailIds) {
        chargeDetailsService.deleteByIds(Arrays.asList(detailIds.split(",")));
        return ResponseEntity.ok("delete success");
    }

    /**
     * 更新数据
     *
     * @param chargeDetailsModel 数据
     * @return ResponseEntity<String>
     */
    @PutMapping("/updates/update-by-id")
    public ResponseEntity<String> updateModel(@RequestBody ChargeDetailsModel chargeDetailsModel) {
        chargeDetailsService.updateDetails(chargeDetailsModel);
        return ResponseEntity.ok("update success");
    }


    @PostMapping("posts")
    public ResponseEntity<String> createProject(@RequestBody ChargeDetailsModel chargeDetailsModel) {
        chargeDetailsService.createProject(chargeDetailsModel);
        return ResponseEntity.ok("create success");
    }


}
