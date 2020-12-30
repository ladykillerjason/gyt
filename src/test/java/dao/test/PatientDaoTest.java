/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package dao.test;

import org.gyt.dao.PatientDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:spring-context.xml")
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Test
    public void getBookByIdTest() {
        Map<String, Object> param = new HashMap();
        param.put("patient_name", "杨森");
        List<Map> ret = patientDao.findPatients(param);
        System.out.println();
        Assert.assertEquals(ret.size(), 1);
    }

}