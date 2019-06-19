package com.sip.charge.service.web;

import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.service.service.IChargePersonnelService;
import com.sip.charge.vo.ChargePersonnelVo;
import com.sip.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 人员详情
 */
@RestController
@RequestMapping("/charge-personnel")
public class ChargePersonnelController {
    @Resource
    private IChargePersonnelService chargePersonnelService;

    /**
     * 获取分页数据
     *
     * @return ResponseEntity<PageResult>
     */
    @GetMapping("/gets/page")
    public ResponseEntity<PageResult> getListPage(ChargePersonnelModel personnelModel,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        return ResponseEntity.ok(chargePersonnelService.getChargePersonnelVoListPage(personnelModel, page, pageSize));
    }

    /**
     * 查询单个数据
     *
     * @param personnelId 项目ID
     * @return ResponseEntity<ChargeProjectModel>
     */
    @GetMapping("/gets/get-by-id")
    public ResponseEntity<ChargePersonnelVo> getChargePersonnelVoById(@RequestParam(value = "personnelId", defaultValue = "") Long personnelId) {
        return ResponseEntity.ok(chargePersonnelService.getChargePersonnelVoById(personnelId));
    }

    /**
     * 根据项目id删除数据
     *
     * @param personnelIds ids
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/deletes/del-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(value = "personnelIds", defaultValue = "") String personnelIds) {
        chargePersonnelService.deleteChargePersonnel(Arrays.asList(personnelIds.split(",")));
        return ResponseEntity.ok("delete success");
    }

    /**
     * 更新
     *
     * @param personnelModel 数据
     * @return ResponseEntity<String>
     */
    @PutMapping("/updates/update-by-id")
    public ResponseEntity<String> updateModel(@RequestBody ChargePersonnelModel personnelModel) {
        chargePersonnelService.updatePersonnel(personnelModel);
        return ResponseEntity.ok("update success");
    }

    /**
     * 创建
     *
     * @param personnelModel personnelModel
     * @return ResponseEntity<String>
     */
    @PostMapping("posts")
    public ResponseEntity<String> createProject(@RequestBody ChargePersonnelModel personnelModel) {
        chargePersonnelService.createPersonnel(personnelModel);
        return ResponseEntity.ok("create success");
    }

}
