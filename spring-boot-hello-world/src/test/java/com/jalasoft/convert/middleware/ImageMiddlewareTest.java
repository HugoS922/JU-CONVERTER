/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

package com.jalasoft.convert.middleware;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * It is responsible to test the ImageController Class, as it is being used Filters, it is necessary to mock the funcionality
 * of request, response and filterchain, Spring offers the "Mocks" of these three classes
 * @author Mauricio Aliendre
 * @version 1.0
 */
public class ImageMiddlewareTest {
    /**
     * This Test Case checks if the middleware and controller works correctly inlcuding the token
     */
    @Test (expected = RuntimeException.class)
    public void testPositiveImageControllerMiddleware() throws IOException, ServletException, NullPointerException{
        ImageControllerMiddleware imageControllerMiddleware = new ImageControllerMiddleware();
        File newFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\jalasoft\\convert\\middleware\\prooffiles\\image.jpg");
        byte[] bFile = new byte[(int) newFile.length()];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", bFile);
        MockMultipartHttpServletRequest req = new  MockMultipartHttpServletRequest();
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        MockFilterChain mockChain = new MockFilterChain();
        req.setMethod("POST");
        req.setContentType("multipart/form-data; boundary=<calculated when request is sent>");
        req.addHeader("Authorization", "Bearer 1667345126530-7cd1287d-e176-4a49-877d-039a5d51b545");
        req.addFile(mockMultipartFile);
        req.addParameter("process", "convert");
        req.addParameter("tool", "-colorspace");
        req.addParameter("width_black", "10");
        req.addParameter("height_white", "10");
        req.addParameter("color", "");
        req.addParameter("fileout", "ok");
        req.addParameter("format", "jpg");
        imageControllerMiddleware.doFilter(req, rsp, mockChain);

        assertEquals(req.getParameter("process"), "convert");
        assertEquals(req.getParameter("tool"), "-colorspace");
        assertEquals(req.getParameter("width_black"), "10");
        assertEquals(req.getParameter("height_white"), "10");
        assertEquals(req.getParameter("color"), "");
        assertEquals(req.getParameter("fileout"), "ok");
        assertNotEquals(req.getParameter("format"), "png");
        assertThat(rsp.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * This Test Case checkS if the middleware and controller works correctly with a incorrect token number
     */
    @Test (expected = RuntimeException.class)
    public void testNegativeImageControllerMiddleware() throws IOException, ServletException, NullPointerException{
        ImageControllerMiddleware imageControllerMiddleware = new ImageControllerMiddleware();
        File newFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\jalasoft\\convert\\middleware\\prooffiles\\image.jpg");
        byte[] bFile = new byte[(int) newFile.length()];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", bFile);
        MockMultipartHttpServletRequest req = new  MockMultipartHttpServletRequest();
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        MockFilterChain mockChain = new MockFilterChain();
        req.setMethod("POST");
        req.setContentType("multipart/form-data; boundary=<calculated when request is sent>");
        req.addHeader("Authorization", "Bearer 1667345126530-7cd1287d-e176-4a49-877d-039a5d51b5f6");
        req.addFile(mockMultipartFile);
        req.addParameter("process", "convert");
        req.addParameter("tool", "-colorspace");
        req.addParameter("width_black", "10");
        req.addParameter("height_white", "10");
        req.addParameter("color", "");
        req.addParameter("fileout", "ok");
        req.addParameter("format", "jpg");
        imageControllerMiddleware.doFilter(req, rsp, mockChain);

        assertEquals(req.getParameter("process"), "convert");
        assertEquals(req.getParameter("tool"), "-colorspace");
        assertEquals(req.getParameter("width_black"), "10");
        assertEquals(req.getParameter("height_white"), "10");
        assertEquals(req.getParameter("color"), "");
        assertEquals(req.getParameter("fileout"), "ok");
        assertNotEquals(req.getParameter("format"), "png");
        assertThat(rsp.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    /**
     * This Test Case checks if the middleware and controller works correctly with a empty field on parameters sended
     */
    @Test (expected = RuntimeException.class)
    public void testNegativeImageControllerMiddleware2() throws IOException, ServletException, NullPointerException{
        ImageControllerMiddleware imageControllerMiddleware = new ImageControllerMiddleware();
        File newFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\jalasoft\\convert\\middleware\\prooffiles\\image.jpg");
        byte[] bFile = new byte[(int) newFile.length()];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", bFile);
        MockMultipartHttpServletRequest req = new  MockMultipartHttpServletRequest();
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        MockFilterChain mockChain = new MockFilterChain();
        req.setMethod("POST");
        req.setContentType("multipart/form-data; boundary=<calculated when request is sent>");
        req.addHeader("Authorization", "Bearer 1667345126530-7cd1287d-e176-4a49-877d-039a5d51b545");
        req.addFile(mockMultipartFile);
        req.addParameter("", "convert");
        req.addParameter("tool", "-colorspace");
        req.addParameter("width_black", "10");
        req.addParameter("height_white", "10");
        req.addParameter("color", "");
        req.addParameter("fileout", "ok");
        req.addParameter("format", "jpg");
        imageControllerMiddleware.doFilter(req, rsp, mockChain);

        assertNull(req.getParameter("process"));
        assertEquals(req.getParameter("tool"), "-colorspace");
        assertEquals(req.getParameter("width_black"), "10");
        assertEquals(req.getParameter("height_white"), "10");
        assertEquals(req.getParameter("color"), "");
        assertEquals(req.getParameter("fileout"), "ok");
        assertNotEquals(req.getParameter("format"), "png");
        assertThat(rsp.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
