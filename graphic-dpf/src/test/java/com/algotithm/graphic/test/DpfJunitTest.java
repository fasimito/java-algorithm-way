package com.algotithm.graphic.test;

import com.algorithm.graphic.dpf.AlgorithmGraphicDpf;
import com.algorithm.graphic.dpf.Configure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringJUnitConfig(classes = Configure.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DpfJunitTest {

    @Autowired
    private AlgorithmGraphicDpf algorithmGraphicDpf;

    @Test
    public void testTransfer(){
        algorithmGraphicDpf.testTransfer();
    }

}
