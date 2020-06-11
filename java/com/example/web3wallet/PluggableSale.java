package com.example.web3wallet;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.15.
 */
@SuppressWarnings("rawtypes")
public class PluggableSale extends Contract {
    public static final String BINARY = "0x608060405260006005556005600a5534801561001a57600080fd5b50604051610ea9380380610ea98339818101604052608081101561003d57600080fd5b508051602082015160408301516060909301516000805460ff1916600117905591929091836100b3576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b03831661010e576040805162461bcd60e51b815260206004820152601d60248201527f6f726967696e2063616e6e6f74206265207a65726f2061646472657373000000604482015290519081900360640190fd5b600180546001600160a01b0319166001600160a01b0385811691909117918290556040805163521eb27360e01b81529051929091169163521eb27391600480820192602092909190829003018186803b15801561016a57600080fd5b505afa15801561017e573d6000803e3d6000fd5b505050506040513d602081101561019457600080fd5b5051600680546001600160a01b03199081166001600160a01b0393841617909155600780549091168383161790556008859055670de0b6b3a76400008302600490815560015460408051637e062a3560e11b81529051919093169263fc0c546a9281810192602092909190829003018186803b15801561021357600080fd5b505afa158015610227573d6000803e3d6000fd5b505050506040513d602081101561023d57600080fd5b505160008054610100600160a81b0319166101006001600160a01b039384160217905560015460408051634881cef760e11b8152905191909216916391039dee916004808301926020929190829003018186803b15801561029d57600080fd5b505afa1580156102b1573d6000803e3d6000fd5b505050506040513d60208110156102c757600080fd5b505160028190556000805460065460408051630645c0fb60e41b815260048101959095526001600160a01b03918216602486015251610100909204169263645c0fb09260448083019360209390929083900390910190829087803b15801561032e57600080fd5b505af1158015610342573d6000803e3d6000fd5b505050506040513d602081101561035857600080fd5b505160035550505050610b39806103706000396000f3fe6080604052600436106100f35760003560e01c8063e61d95941161008a578063f9a4bb1e11610059578063f9a4bb1e14610240578063f9cfe26b14610255578063fbbdc4651461026a578063fc0c546a1461027f576100f3565b8063e61d9594146101db578063ec8ac4d8146101f0578063ecd0c0c314610216578063f00a93d31461022b576100f3565b8063610757e4116100c6578063610757e4146101875780636ed08ea01461019c57806391039dee146101b1578063e27920a8146101c6576100f3565b80632c4e722e146101055780634042b66f1461012c57806341035a4114610141578063521eb27314610172575b6101036100fe610294565b610298565b005b34801561011157600080fd5b5061011a6103cf565b60408051918252519081900360200190f35b34801561013857600080fd5b5061011a6103d5565b34801561014d57600080fd5b506101566103db565b604080516001600160a01b039092168252519081900360200190f35b34801561017e57600080fd5b506101566103ea565b34801561019357600080fd5b506101566103f9565b3480156101a857600080fd5b5061011a610408565b3480156101bd57600080fd5b5061011a61040e565b3480156101d257600080fd5b5061011a610414565b3480156101e757600080fd5b5061011a61041a565b6101036004803603602081101561020657600080fd5b50356001600160a01b0316610298565b34801561022257600080fd5b50610156610420565b34801561023757600080fd5b5061011a610434565b34801561024c57600080fd5b5061015661043a565b34801561026157600080fd5b5061011a610449565b34801561027657600080fd5b5061011a61044f565b34801561028b57600080fd5b50610156610455565b3390565b60005460ff166102ef576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff19168155349061030482610469565b90506103118383836104dc565b600954610324908363ffffffff6105cf16565b60095560055461033a908263ffffffff6105cf16565b6005556103478382610632565b826001600160a01b0316610359610294565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36103ab838361063c565b6103b3610640565b6103bd838361063c565b50506000805460ff1916600117905550565b60085490565b60095490565b6001546001600160a01b031681565b6006546001600160a01b031690565b6006546001600160a01b031681565b60025481565b60025490565b600a5481565b60055490565b60005461010090046001600160a01b031681565b60045490565b6007546001600160a01b031681565b60055481565b60035490565b60005461010090046001600160a01b031690565b60006008548210156104ac5760405162461bcd60e51b815260040180806020018281038252602c815260200180610ad9602c913960400191505060405180910390fd5b6000670de0b6b3a7640000600854816104c157fe5b04670de0b6b3a76400008404816104d457fe5b049392505050565b6001600160a01b0383166105215760405162461bcd60e51b815260040180806020018281038252602a815260200180610aaf602a913960400191505060405180910390fd5b81610573576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600554600454670de0b6b3a764000090910290828201908111156105c85760405162461bcd60e51b815260040180806020018281038252602a815260200180610a64602a913960400191505060405180910390fd5b5050505050565b600082820183811015610629576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b90505b92915050565b61063c8282610714565b5050565b346064600061064f838361079b565b60065460405194829003949192506001600160a01b03169084156108fc029085906000818181858888f1935050505015801561068f573d6000803e3d6000fd5b506007546040516001600160a01b039091169082156108fc029083906000818181858888f193505050501580156106ca573d6000803e3d6000fd5b50604080518481526020810183905282850381830181905291517f8f4705a6d8c5af200050d845b50d0f90f838a179c62b9365daa314992598fc1b9181900360600190a150505050565b60008054600254600354604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561077f57600080fd5b505af1158015610793573d6000803e3d6000fd5b505050505050565b6000806107a88484610882565b905060006107b685856108c4565b905060006107c6600a5486610882565b905060006107d6600a54876108c4565b905060006107ed6107e78685610906565b88610906565b905060006107fb8684610906565b905060006108098686610906565b905060006108178786610906565b905060006108268b6002610882565b9050600061083483836105cf565b90506000610842828e610882565b9050600061085088886105cf565b9050600061085e82886105cf565b9050600061086c82856105cf565b9e50505050505050505050505050505092915050565b600061062983836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f00000000000081525061095f565b600061062983836040518060400160405280601881526020017f536166654d6174683a206d6f64756c6f206279207a65726f0000000000000000815250610a01565b6000826109155750600061062c565b8282028284828161092257fe5b04146106295760405162461bcd60e51b8152600401808060200182810382526021815260200180610a8e6021913960400191505060405180910390fd5b600081836109eb5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b838110156109b0578181015183820152602001610998565b50505050905090810190601f1680156109dd5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5060008385816109f757fe5b0495945050505050565b60008183610a505760405162461bcd60e51b81526020600482018181528351602484015283519092839260449091019190850190808383600083156109b0578181015183820152602001610998565b50828481610a5a57fe5b0694935050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a723158201421b34d8733e0cb390855ae86bfe63c07373fa69abcc5fb3635cac0076500f964736f6c63430005110032";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_ORIGIN_SALE = "origin_sale";

    public static final String FUNC_PERCENT_FEE = "percent_fee";

    public static final String FUNC_TREASURE_FUND = "treasure_fund";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_WALLET = "wallet";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_WEIRAISED = "weiRaised";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_SALE_LIMIT = "sale_limit";

    public static final String FUNC_TICKET_TYPE = "ticket_type";

    public static final String FUNC_SOLD_COUNT = "sold_count";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final Event CALCULATEDFEES_EVENT = new Event("CalculatedFees", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOKENSPURCHASED_EVENT = new Event("TokensPurchased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected PluggableSale(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PluggableSale(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PluggableSale(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PluggableSale(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CalculatedFeesEventResponse> getCalculatedFeesEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CALCULATEDFEES_EVENT, transactionReceipt);
        ArrayList<CalculatedFeesEventResponse> responses = new ArrayList<CalculatedFeesEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CalculatedFeesEventResponse typedResponse = new CalculatedFeesEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.initial_value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.fees = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.transfered_amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CalculatedFeesEventResponse> calculatedFeesEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CalculatedFeesEventResponse>() {
            @Override
            public CalculatedFeesEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CALCULATEDFEES_EVENT, log);
                CalculatedFeesEventResponse typedResponse = new CalculatedFeesEventResponse();
                typedResponse.log = log;
                typedResponse.initial_value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.fees = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.transfered_amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CalculatedFeesEventResponse> calculatedFeesEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CALCULATEDFEES_EVENT));
        return calculatedFeesEventFlowable(filter);
    }

    public List<TokensPurchasedEventResponse> getTokensPurchasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENSPURCHASED_EVENT, transactionReceipt);
        ArrayList<TokensPurchasedEventResponse> responses = new ArrayList<TokensPurchasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensPurchasedEventResponse typedResponse = new TokensPurchasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.purchaser = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokensPurchasedEventResponse> tokensPurchasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokensPurchasedEventResponse>() {
            @Override
            public TokensPurchasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENSPURCHASED_EVENT, log);
                TokensPurchasedEventResponse typedResponse = new TokensPurchasedEventResponse();
                typedResponse.log = log;
                typedResponse.purchaser = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokensPurchasedEventResponse> tokensPurchasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSPURCHASED_EVENT));
        return tokensPurchasedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__EVENT_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> _sold_count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__SOLD_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> _token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> _wallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__WALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> origin_sale() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORIGIN_SALE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> percent_fee() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PERCENT_FEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> treasure_fund() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TREASURE_FUND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> wallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> rate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> weiRaised() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WEIRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EVENT_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> sale_limit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SALE_LIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> ticket_type() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKET_TYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> sold_count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SOLD_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyTokens(String beneficiary, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static PluggableSale load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PluggableSale(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PluggableSale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PluggableSale(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PluggableSale load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PluggableSale(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PluggableSale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PluggableSale(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PluggableSale> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String origin, BigInteger sale_limit, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(PluggableSale.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PluggableSale> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String origin, BigInteger sale_limit, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(PluggableSale.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PluggableSale> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String origin, BigInteger sale_limit, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(PluggableSale.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PluggableSale> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String origin, BigInteger sale_limit, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(PluggableSale.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class CalculatedFeesEventResponse extends BaseEventResponse {
        public BigInteger initial_value;

        public BigInteger fees;

        public BigInteger transfered_amount;
    }

    public static class TokensPurchasedEventResponse extends BaseEventResponse {
        public String purchaser;

        public String beneficiary;

        public BigInteger value;

        public BigInteger amount;
    }
}
