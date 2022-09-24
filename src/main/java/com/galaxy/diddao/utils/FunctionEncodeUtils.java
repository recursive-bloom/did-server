package com.galaxy.diddao.utils;

import cn.hutool.core.lang.Assert;
import com.galaxy.diddao.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Bytes32;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author Ant
 * @Date 2022/9/23
 * @Description:
 */
@Slf4j
public class FunctionEncodeUtils {

    /**
     * @param node
     * @return
     */
    public static String getGasBalanceEncodeNoPrefix(String node) {
        Assert.notBlank(node, "node is null");
        node = StringUtils.remove(node, CommonConstants.ADDRESS_PREFIX);
        return getGasBalanceEncode(node);
    }

    /**
     * 获取gasBalance的方法编码
     *
     * @param node
     * @return
     */
    public static String getGasBalanceEncode(String node) {
        Function function =
                new Function(
                        "gasBalance",
                        Arrays.asList(
                                new Bytes32(hexToByteData(node))
                        ),
                        Collections.emptyList()
                );
        final String encode = FunctionEncoder.encode(function);
        System.out.println(encode);
        return encode;
    }


    public static byte[] hexToByteData(String hex) {
        byte[] convertedByteArray = new byte[0];
        try {
            convertedByteArray = new byte[hex.length() / 2];
            int count = 0;

            for (int i = 0; i < hex.length() - 1; i += 2) {
                String output;
                output = hex.substring(i, (i + 2));
                int decimal = (int) (Integer.parseInt(output, 16));
                convertedByteArray[count] = (byte) (decimal & 0xFF);
                count++;
            }
        } catch (NumberFormatException e) {
            log.error("出错了。。。", e);
        }
        return convertedByteArray;
    }
}
