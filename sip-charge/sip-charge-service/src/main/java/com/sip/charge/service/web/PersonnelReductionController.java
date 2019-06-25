package com.sip.charge.service.web;


import com.sip.charge.model.PersonnelReductionModel;
import com.sip.charge.service.service.IPersonnelReductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/personnel-reduction")
public class PersonnelReductionController {

    @Resource
    private IPersonnelReductionService personnelReductionService;

    /**
     * 查询
     *
     * @param personnelId personnelId
     * @return ResponseEntity<List < PersonnelContactModel>>
     */
    @GetMapping("/gets/gets-by-personnel-id")
    public ResponseEntity<List<PersonnelReductionModel>> getPersonnelReductionsByStudentId(@RequestParam(value = "personnelId") String personnelId) {
        return ResponseEntity.ok(personnelReductionService.getPersonnelReductionsByStudentId(personnelId));
    }


    /**
     * 根据id删除数据
     *
     * @param reductionIds ids
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/deletes/del-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(value = "reductionIds", defaultValue = "") String reductionIds) {
        personnelReductionService.deletePersonnelReductions(Arrays.asList(reductionIds.split(",")));
        return ResponseEntity.ok("delete success");
    }

    /**
     * 更新
     *
     * @param reductionModel 数据
     * @return ResponseEntity<String>
     */
    @PostMapping("/posts/post-by-personnel-ids")
    public ResponseEntity<String> addPersonnelReductionModel(@RequestBody PersonnelReductionModel reductionModel,
                                                             @RequestParam(value = "personnelIds", defaultValue = "") String personnelIds) {
        personnelReductionService.addPersonnelReductions(Arrays.asList(personnelIds.split(",")), reductionModel);
        return ResponseEntity.ok("update success");
    }


    /**
     * 更新
     *
     * @param reductionModels 数据
     * @return ResponseEntity<String>
     */
    @PutMapping("/puts")
    public ResponseEntity<String> updateModels(@RequestBody List<PersonnelReductionModel> reductionModels,
                                               @RequestParam(value = "personnelIds", defaultValue = "") String personnelIds) {
        personnelReductionService.putPersonnelReductions(Arrays.asList(personnelIds.split(",")), reductionModels);
        return ResponseEntity.ok("update success");
    }

}
