package com.galaxy.diddao.test;


import cn.hutool.json.JSONUtil;
import com.galaxy.diddao.utils.HttpUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Bytes32;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author leyangjie
 * @Date 2022/9/20 10:23
 * @Description:
 */
public class TestEtcCall {
    public static void main(String[] args) {
        String reqUrl = "https://ethereum-ropsten-rpc.allthatnode.com";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("jsonrpc", "2.0");
        paramMap.put("method", "eth_call");

        List<Object> paramList = Lists.newArrayList();
        Map<String, Object> secondParamMap = Maps.newHashMap();
        secondParamMap.put("to", "0x8b3a11Bf55c1b73Dbce6686540de94eD5E789509");
        secondParamMap.put("data", "0x6ab87f2375774ec3a979d857b1fb14d04907aa30e515f6f75124bde3a8e3ef9aefbf07cb");
        paramList.add(secondParamMap);
        paramList.add("latest");

        paramMap.put("params", paramList);
        paramMap.put("id", "1");

        final String resultStr = HttpUtils.postJson(reqUrl, JSONUtil.toJsonStr(paramMap));
        System.out.println(JSONUtil.toJsonStr(paramMap));
        System.out.println(resultStr);


    }

    public static void testEncode() {
        Function function2 =
                new Function(
                        "gasBalance",
                        Arrays.asList(
                                new Bytes32(hexToByteData("75774ec3a979d857b1fb14d04907aa30e515f6f75124bde3a8e3ef9aefbf07cb"))
                        ),
                        Collections.emptyList()
                );

        System.out.println(FunctionEncoder.encode(function2));
    }

    public static byte[] hexToByteData(String hex) {
        byte[] convertedByteArray = new byte[hex.length() / 2];
        int count = 0;

        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output;
            output = hex.substring(i, (i + 2));
            int decimal = (int) (Integer.parseInt(output, 16));
            convertedByteArray[count] = (byte) (decimal & 0xFF);
            count++;
        }
        return convertedByteArray;
    }
}


