package com.luoying.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {
    public static String genSign(String body, String secretKey) {
        Digester SHA256 = new Digester(DigestAlgorithm.SHA256);
        String content = body + ".secretKey" + secretKey;
        return SHA256.digestHex(content.getBytes());
    }
}
