package com.facecto.code.base.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.0.0
 */
public class CodeFileUtils {
    /**
     * Read files from the resource directory as byte arrays
     *
     * @param path
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getBytes(String path) throws IOException {
        ClassPathResource classResource = new ClassPathResource(path);
        InputStream stream = classResource.getInputStream();
        byte[] bytes = IOUtils.toByteArray(stream);
        stream.read(bytes);
        stream.close();
        return bytes;
    }

    /**
     * Read a file from a resource directory as a file stream
     *
     * @param path
     * @return stream
     * @throws IOException
     */
    public static InputStream getStream(String path) throws IOException {
        return new ByteArrayInputStream(getBytes(path));
    }
}
