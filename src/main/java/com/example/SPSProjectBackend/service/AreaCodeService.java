package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.AreaCodeDTO;
import com.example.SPSProjectBackend.repository.GldeptinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaCodeService {

    @Autowired
    private GldeptinRepository gldeptinRepository;

    public AreaCodeDTO getAreaCodeByApplicationId(String applicationId) {
        AreaCodeDTO areaCode = gldeptinRepository.findAreaCodeByApplicationId(applicationId);
        if (areaCode == null) {
            return null;
        }
        return areaCode;
    }
}

//@Service
//public class AreaCodeService {
//
//    @Autowired
//    private GldeptinRepository gldeptinRepository;
//
//    public AreaCodeDTO getAreaCodeByApplicationId(String applicationId) {
//        Object[] result = gldeptinRepository.findAreaCodeByApplicationId(applicationId);
//
//        if (result != null && result.length == 2) {
//            AreaCodeDTO dto = new AreaCodeDTO();
//
//            // Convert areaCode to String safely
//            if (result[0] != null) {
//                dto.setAreaCode(result[0].toString());
//            } else {
//                dto.setAreaCode(null);
//            }
//
//            // Convert deptCode to String safely
//            if (result[1] != null) {
//                dto.setDeptCode(result[1].toString());
//            } else {
//                dto.setDeptCode(null);
//            }
//
//            return dto;
//        }
//        return null;
//    }
//}
