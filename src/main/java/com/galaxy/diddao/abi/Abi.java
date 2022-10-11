package com.galaxy.diddao.abi;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Abi extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_BASE_COST = "base_cost";

    public static final String FUNC_GASBALANCE = "gasBalance";

    public static final String FUNC_GETCOST = "getCost";

    public static final String FUNC_MIN_COST = "min_cost";

    public static final String FUNC_NODELEFTTOADDRESS = "nodeLeftToAddress";

    public static final String FUNC_NODETOADDRESS = "nodeToAddress";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_SETNODE = "setNode";

    public static final String FUNC_SETREVERSE = "setReverse";

    public static final Event FOO_EVENT = new Event("Foo",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }, new TypeReference<Utf8String>(true) {
            }, new TypeReference<Uint256>() {
            }));
    ;

    public static final Event NODEINFOUPDATED_EVENT = new Event("NodeInfoUpdated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {
            }, new TypeReference<Bytes32>() {
            }, new TypeReference<Address>() {
            }, new TypeReference<Uint64>() {
            }, new TypeReference<Uint64>() {
            }, new TypeReference<Uint64>() {
            }, new TypeReference<Utf8String>() {
            }));
    ;

    public static final Event REVERSERECORDSET_EVENT = new Event("ReverseRecordSet",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Bytes32>(true) {
            }));
    ;

    public static final Event PLATFORMINCOME_EVENT = new Event("PlatformIncome",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Uint256>() {
            }));
    ;

    public static final Event USERDIVIDENT_EVENT = new Event("UserDivident",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {
            }, new TypeReference<Address>(true) {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Uint256>() {
            }));
    ;


    @Deprecated
    protected Abi(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Abi(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Abi(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Abi(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<FooEventResponse> getFooEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(FOO_EVENT, transactionReceipt);
        ArrayList<FooEventResponse> responses = new ArrayList<FooEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FooEventResponse typedResponse = new FooEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msgSender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.name = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FooEventResponse> fooEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FooEventResponse>() {
            @Override
            public FooEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FOO_EVENT, log);
                FooEventResponse typedResponse = new FooEventResponse();
                typedResponse.log = log;
                typedResponse.msgSender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.owner = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.name = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FooEventResponse> fooEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FOO_EVENT));
        return fooEventFlowable(filter);
    }

    public List<NodeInfoUpdatedEventResponse> getNodeInfoUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NODEINFOUPDATED_EVENT, transactionReceipt);
        ArrayList<NodeInfoUpdatedEventResponse> responses = new ArrayList<NodeInfoUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeInfoUpdatedEventResponse typedResponse = new NodeInfoUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.parent = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.expire = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.ttl = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.transfer = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NodeInfoUpdatedEventResponse> nodeInfoUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NodeInfoUpdatedEventResponse>() {
            @Override
            public NodeInfoUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NODEINFOUPDATED_EVENT, log);
                NodeInfoUpdatedEventResponse typedResponse = new NodeInfoUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.parent = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.expire = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.ttl = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.transfer = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NodeInfoUpdatedEventResponse> nodeInfoUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEINFOUPDATED_EVENT));
        return nodeInfoUpdatedEventFlowable(filter);
    }

    public List<ReverseRecordSetEventResponse> getReverseRecordSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REVERSERECORDSET_EVENT, transactionReceipt);
        ArrayList<ReverseRecordSetEventResponse> responses = new ArrayList<ReverseRecordSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ReverseRecordSetEventResponse typedResponse = new ReverseRecordSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.main_address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReverseRecordSetEventResponse> reverseRecordSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReverseRecordSetEventResponse>() {
            @Override
            public ReverseRecordSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REVERSERECORDSET_EVENT, log);
                ReverseRecordSetEventResponse typedResponse = new ReverseRecordSetEventResponse();
                typedResponse.log = log;
                typedResponse.main_address = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.node = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ReverseRecordSetEventResponse> reverseRecordSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVERSERECORDSET_EVENT));
        return reverseRecordSetEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> base_cost() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BASE_COST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> gasBalance(byte[] node) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GASBALANCE, 
                Arrays.<Type>asList(new Bytes32(node)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCost(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOST, 
                Arrays.<Type>asList(new Utf8String(name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> min_cost() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MIN_COST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> nodeLeftToAddress(byte[] node) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NODELEFTTOADDRESS, 
                Arrays.<Type>asList(new Bytes32(node)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> nodeToAddress(byte[] node) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NODETOADDRESS, 
                Arrays.<Type>asList(new Bytes32(node)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> register(String owner, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Address(160, owner),
                        new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNode(byte[] node, byte[] parent, String owner, BigInteger expire, BigInteger ttl, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETNODE, 
                Arrays.<Type>asList(new Bytes32(node),
                        new Bytes32(parent),
                        new Address(160, owner),
                        new Uint64(expire),
                        new Uint64(ttl),
                        new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setReverse(String main_address, byte[] node) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETREVERSE, 
                Arrays.<Type>asList(new Address(160, main_address),
                        new Bytes32(node)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Abi load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Abi(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Abi load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Abi(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Abi load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Abi(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Abi load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Abi(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class FooEventResponse extends BaseEventResponse {
        public String msgSender;

        public String owner;

        public byte[] name;

        public BigInteger value;
    }

    public static class NodeInfoUpdatedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] parent;

        public String owner;

        public BigInteger expire;

        public BigInteger ttl;

        public BigInteger transfer;

        public String name;
    }

    public static class ReverseRecordSetEventResponse extends BaseEventResponse {
        public String main_address;

        public byte[] node;
    }
}
