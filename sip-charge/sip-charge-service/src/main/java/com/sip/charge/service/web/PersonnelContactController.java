package com.sip.charge.service.web;


import com.sip.charge.model.ChargePersonnelModel;
import com.sip.charge.model.PersonnelContactModel;
import com.sip.charge.service.service.IPersonnelContactService;
import com.sip.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/personnel-contact")
public class PersonnelContactController {

    @Resource
    private IPersonnelContactService personnelContactService;


    /**
     * 查询联系方式
     *
     * @param personnelId personnelId
     * @return ResponseEntity<List < PersonnelContactModel>>
     */
    @GetMapping("/gets/gets-by-personnel-id")
    public ResponseEntity<List<PersonnelContactModel>> getPersonnelContactsByStudentId(@RequestParam(value = "personnelId") String personnelId) {
        return ResponseEntity.ok(personnelContactService.getPersonnelContactsByStudentId(personnelId));
    }


    /**
     * 根据id删除数据
     *
     * @param contactIds ids
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/deletes/del-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(value = "contactIds", defaultValue = "") String contactIds) {
        personnelContactService.deletePersonnelContacts(Arrays.asList(contactIds.split(",")));
        return ResponseEntity.ok("delete success");
    }

    /**
     * 更新
     *
     * @param contactModels 数据
     * @return ResponseEntity<String>
     */
    @PutMapping("/puts")
    public ResponseEntity<String> updateModels(@RequestBody List<PersonnelContactModel> contactModels,
                                               @RequestParam(value = "personnelIds", defaultValue = "") String personnelIds) {
        personnelContactService.putPersonnelContacts(Arrays.asList(personnelIds.split(",")), contactModels);
        return ResponseEntity.ok("update success");
    }


}
