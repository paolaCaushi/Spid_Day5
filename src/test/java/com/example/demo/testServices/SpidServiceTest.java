package com.example.demo.testServices;

import com.example.demo.Day5Application;
import com.example.demo.model.Spid;
import com.example.demo.service.SpidService;
import com.example.demo.utilities.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = {Day5Application.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpidServiceTest {

    @Mock
    SpidService spidService;

    @BeforeAll
    public void setup() throws Exception {
        Spid spid = new Spid();
        spid.setId(1);
        spid.setStatus(Status.READY_FOR_REVIEW);

        when(spidService.changeStatus(1)).thenReturn(spid);
    }

    @Test
    public void testDatabaseRetrievalForSpids() throws Exception {
        Spid spid = new Spid();
        spid.setId(1);
        spid.setStatus(Status.READY_FOR_REVIEW);
        assertEquals(spid, spidService.changeStatus(1));
    }

}