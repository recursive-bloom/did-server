package com.galaxy.diddao.test;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

/**
 * @Author Ant
 * @Date 2022/9/23
 * @Description:
 */
public class Web3jTest {


    public static void main(String[] args) throws Exception {
        String reqUrl = "https://eth-goerli.g.alchemy.com/v2/-s1zkDpkEmnjF4wIk8pLsiJBuxWelYV0";
        final Web3j web3j = Web3j.build(new HttpService(reqUrl));
        web3j.transactionFlowable().subscribe(tx -> {
            System.out.println(tx.getHash());
            System.out.println("------------------");
        });


//        testClientVersion(reqUrl);

    }

    /**
     * 测试客户端版本
     *
     * @param web3j
     * @throws java.io.IOException
     */
    private static void testClientVersion(String reqUrl) throws java.io.IOException {
        final Web3j web3j = Web3j.build(new HttpService(reqUrl));
        final Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
        System.out.println(clientVersion.getWeb3ClientVersion());
    }


}
