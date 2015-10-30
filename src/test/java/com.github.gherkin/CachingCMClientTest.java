package com.github.gherkin;

import com.github.gherkin.content.Content;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CachingCMClientTest {

    @Mock
    private Cache cache;
    @InjectMocks private CachingCMClient cmClient;

//    @Before
//    public void init() {
//        cmClient = new CachingCMClient();
//    }

    @Test
    public void testGetContent() throws Exception {
        Content content = cmClient.getContent("3");
        for(String value : content.values()) {
            System.out.println(value);
        }
        org.junit.Assert.assertNotNull("content should not be null", content);
    }
}