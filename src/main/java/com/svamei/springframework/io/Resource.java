package com.svamei.springframework.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName Resource
 * @Description
 * @Author Svamei
 * @Date 19:23 2023/3/6
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}