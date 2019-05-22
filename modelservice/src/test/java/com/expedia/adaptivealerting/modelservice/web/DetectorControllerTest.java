package com.expedia.adaptivealerting.modelservice.web;

import com.expedia.adaptivealerting.modelservice.entity.ElasticsearchDetector;
import com.expedia.adaptivealerting.modelservice.service.DetectorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class DetectorControllerTest {

    @InjectMocks
    private DetectorController controller;

    @Mock
    private DetectorService detectorService;

    @Mock
    private List<ElasticsearchDetector> detectors;

    @Before
    public void setUp() {
        this.controller = new DetectorController();
        MockitoAnnotations.initMocks(this);
        when(detectorService.getLastUpdatedDetectors(anyInt())).thenReturn(detectors);
    }

    @Test
    public void testFindByUuid() {
        List<ElasticsearchDetector> actualDetectors = controller.findByUuid("uuid");
        assertNotNull(actualDetectors);
    }

    @Test
    public void testFindByCreatedBy() {
        List<ElasticsearchDetector> actualDetectors = controller.findByCreatedBy("kashah");
        assertNotNull(actualDetectors);
    }

    @Test
    public void testToggleDetector() {
        controller.toggleDetector("uuid", true);
    }

    @Test
    public void testGetLastUpdatedDetectors() {
        int interval = 5;
        List<ElasticsearchDetector> actualDetectors = controller.getLastUpdatedDetectors(interval);
        assertNotNull(actualDetectors);
        assertSame(detectors, actualDetectors);
    }

}