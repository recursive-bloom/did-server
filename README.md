# web3j-server

https://github.com/hyperledger/besu

https://docs.web3j.io/4.8.7/command_line_tools

https://ethereum.org/en/developers/docs/apis/json-rpc/#eth_blocknumber

https://ethereum.org/en/developers/docs/apis/json-rpc/#eth_gettransactionreceipt

https://ethereum.org/en/developers/docs/apis/json-rpc/#eth_call

https://docs.alchemy.com/reference/batch-requests

https://ethereum.org/en/developers/docs/evm

https://ethereum.org/en/developers/docs/evm/opcodes

https://ethereum.org/en/developers/docs/gas

https://ethereum.org/en/developers/docs/ethereum-stack

https://ethereum.org/en/developers/docs/storage



```text

web3j generate solidity -a abi.json -o web3j-test -p com

web3j generate solidity -b code.bin -a abi.json -o web3j-test -p com
 
目标地址（比如5合约地址）

[startBlockNumber]

[latestBlockNumber] 


距离 128 安全块

合约地址 0x11a2fa9d918c7c7c0130e8a4cb2e4df7ec695e8f
部署是的区块高度：12985323
开始扫描区块：getBlockByNumber
筛选 目标为合约地址的 交易哈希 TransactionHash
获取交易详细信息与事件

curl https://ethereum-ropsten-rpc.allthatnode.com --request POST --header "Content-Type: application/json" --data '{"jsonrpc":"2.0","method":"eth_getBlockByNumber","params":["latest", true],"id":1}'

curl https://ethereum-ropsten-rpc.allthatnode.com --request POST --header "Content-Type: application/json" --data '{"jsonrpc":"2.0","method":"eth_getTransactionReceipt","params":["0x9dbd8f031998949f0be79b436e1e8fbf6f6f9af4b1a84b31fe1d96a802abbdb7"],"id":1}'

```

```json

{"transactionHash":"0x10ad9a691e4d4184702ca2dbb7879ddb0efb523beb9ae96a0954e795614c662d","transactionIndex":36,"blockHash":"0x9936a0ea41310a93ce694d81cf0be4d69a5dfe8284453a5c40362e9d6fa7b426","blockNumber":13015440,"cumulativeGasUsed":7077716,"gasUsed":30058,"status":"0x1","from":"0x6dcd82959e68f41b3a365c40f57b9a54cdd230e1","to":"0xc677b58ba633aef3610661120ac68dbaddb77dd3","logs":[{"removed":false,"logIndex":126,"transactionIndex":36,"transactionHash":"0x10ad9a691e4d4184702ca2dbb7879ddb0efb523beb9ae96a0954e795614c662d","blockHash":"0x9936a0ea41310a93ce694d81cf0be4d69a5dfe8284453a5c40362e9d6fa7b426","blockNumber":13015440,"address":"0xc677b58ba633aef3610661120ac68dbaddb77dd3","data":"0x000000000000000000000000000000000000000000000000000000000000002093cdeb708b7545dc668eb9280176169d1c33cfd8ed6f04690a0bcc88a93fc4ae0000000000000000000000006dcd82959e68f41b3a365c40f57b9a54cdd230e100000000000000000000000000000000000000000000000000000000750b2d52000000000000000000000000000000000000000000000000000000000000012c0000000000000000000000000000000000000000000000000000000063298a8800000000000000000000000000000000000000000000000000000000000000c0000000000000000000000000000000000000000000000000000000000000000762756666616c6f00000000000000000000000000000000000000000000000000","topics":["0x2b20fac2c51c3569e7dfdb4d005ad4ca19bdf1fb2624cb6cb40b4c6314aca71e","0x4e772f62035453f381d9e8495f65ea4d5c90168c61878c755d8b2c9170e259d2"]}],"logsBloom":"0x00000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000400800000100000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000800000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000"}

```




