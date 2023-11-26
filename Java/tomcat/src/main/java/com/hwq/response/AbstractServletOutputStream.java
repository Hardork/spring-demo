package com.hwq.response;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * @Author:HWQ
 * @DateTime:2023/11/22 17:35
 * @Description:
 **/
public class AbstractServletOutputStream extends ServletOutputStream {
    // 暂时存储response的响应信息
    private byte[] bytes = new byte[1024];
    private int pos = 0;

    @Override
    public void write(int b) throws IOException {
        bytes[pos] = (byte) b;
        pos++;
        // 暂时不考虑扩容问题
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getPos() {
        return pos;
    }
}
