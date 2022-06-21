package com.tez.todoservice.filters;

import com.tez.todoservice.feignclient.InvalidPayloadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Component
@Slf4j
public class RequestResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MyCustomHttpRequestMapper requestWrapper = new MyCustomHttpRequestMapper((HttpServletRequest) request);
        String uri = requestWrapper.getRequestURI();
        String requestData = new String(requestWrapper.getByteArray());

        log.info("Requeust URI: {}", uri);
        log.info("Requeust Method: {}", requestWrapper.getMethod());
        log.info("Request Body {}: " + requestData);

        MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse) response);
        chain.doFilter(requestWrapper, responseWrapper);
        String responseData = new String(responseWrapper.getBaos().toByteArray());
        log.info("Response status: {}" + responseWrapper.getStatus());
        log.info("Response status: {}" + responseData);
    }

}

class MyCustomHttpRequestMapper extends HttpServletRequestWrapper {

    private byte[] byteArray;

    public MyCustomHttpRequestMapper(HttpServletRequest request) {
        super(request);
        try {
            byteArray = IOUtils.toByteArray(request.getInputStream());
        } catch (IOException e) {
            throw new InvalidPayloadException("Issue while reading request stream");
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
    }

    public byte[] getByteArray() {
        return byteArray;
    }
}

class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private PrintStream printStream = new PrintStream(baos);

    public ByteArrayOutputStream getBaos() {
        return baos;
    }

    public MyCustomHttpResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));
    }

}
