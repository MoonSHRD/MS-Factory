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
public class TicketSale721 extends Contract {
    public static final String BINARY = "0x608060405260016002556000600455600560095534801561001f57600080fd5b5060405161103f38038061103f833981810160405260c081101561004257600080fd5b815160208301516040808501516060860151608087018051935195979496929591949193928201928464010000000082111561007d57600080fd5b90830190602082018581111561009257600080fd5b82516401000000008111828201881017156100ac57600080fd5b82525081516020918201929091019080838360005b838110156100d95781810151838201526020016100c1565b50505050905090810190601f1680156101065780820380516001836020036101000a031916815260200191505b50604052602001516000805460ff191660011790559150869050858585858585610177576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b0385166101bc5760405162461bcd60e51b815260040180806020018281038252602581526020018061101a6025913960400191505060405180910390fd5b6001600160a01b0384166102015760405162461bcd60e51b8152600401808060200182810382526024815260200180610ff66024913960400191505060405180910390fd5b6007869055600580546001600160a01b03199081166001600160a01b038881169190911792839055600680549092168482161790915560008054610100600160a81b031916610100888416810291909117808355670de0b6b3a7640000880260035560408051631440364760e31b81529585166004870181815260248801928352895160448901528951949093049095169563a201b2389594899460649092019160208601918190849084905b838110156102c65781810151838201526020016102ae565b50505050905090810190601f1680156102f35780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561031357600080fd5b505af1158015610327573d6000803e3d6000fd5b505050506040513d602081101561033d57600080fd5b5051600155505050505050505050505050610c998061035d6000396000f3fe6080604052600436106101145760003560e01c8063a8253cac116100a0578063f00a93d311610064578063f00a93d3146102c0578063f9a4bb1e146102d5578063f9cfe26b146102ea578063fbbdc465146102ff578063fc0c546a1461031457610114565b8063a8253cac14610246578063e27920a81461025b578063e61d959414610270578063ec8ac4d814610285578063ecd0c0c3146102ab57610114565b8063610757e4116100e7578063610757e4146101a85780636c1e81e3146101bd5780636ed08ea0146101f657806391039dee1461020b578063a5f8cdbb1461022057610114565b806323e6f340146101265780632c4e722e1461014d5780634042b66f14610162578063521eb27314610177575b61012461011f610329565b61032d565b005b34801561013257600080fd5b5061013b610339565b60408051918252519081900360200190f35b34801561015957600080fd5b5061013b61033f565b34801561016e57600080fd5b5061013b610345565b34801561018357600080fd5b5061018c61034b565b604080516001600160a01b039092168252519081900360200190f35b3480156101b457600080fd5b5061018c61035a565b3480156101c957600080fd5b50610124600480360360408110156101e057600080fd5b506001600160a01b038135169060200135610369565b34801561020257600080fd5b5061013b610411565b34801561021757600080fd5b5061013b610417565b6101246004803603602081101561023657600080fd5b50356001600160a01b031661032d565b34801561025257600080fd5b5061013b61041d565b34801561026757600080fd5b5061013b610430565b34801561027c57600080fd5b5061013b610436565b6101246004803603602081101561029b57600080fd5b50356001600160a01b031661043c565b3480156102b757600080fd5b5061018c610573565b3480156102cc57600080fd5b5061013b610587565b3480156102e157600080fd5b5061018c61058d565b3480156102f657600080fd5b5061013b61059c565b34801561030b57600080fd5b5061013b6105a2565b34801561032057600080fd5b5061018c6105a8565b3390565b6103368161043c565b50565b60025481565b60075490565b60085490565b6005546001600160a01b031690565b6005546001600160a01b031681565b60006103736105a8565b9050600061037f610417565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b1580156103e957600080fd5b505af11580156103fd573d6000803e3d6000fd5b5050505061040b600a6105bc565b50505050565b60015481565b60015490565b60008061042a600a6105c5565b91505090565b60095481565b60045490565b60005460ff16610493576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906104a8826105c9565b90506104b583838361063c565b6008546104c8908363ffffffff61072f16565b6008556004546104de908263ffffffff61072f16565b6004556104eb8382610792565b826001600160a01b03166104fd610329565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a361054f838361079c565b6105576107a0565b610561838361079c565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60035490565b6006546001600160a01b031681565b60045481565b60025490565b60005461010090046001600160a01b031690565b80546001019055565b5490565b600060075482101561060c5760405162461bcd60e51b815260040180806020018281038252602c815260200180610c39602c913960400191505060405180910390fd5b6000670de0b6b3a76400006007548161062157fe5b04670de0b6b3a764000084048161063457fe5b049392505050565b6001600160a01b0383166106815760405162461bcd60e51b815260040180806020018281038252602a815260200180610c0f602a913960400191505060405180910390fd5b816106d3576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600454600354670de0b6b3a764000090910290828201908111156107285760405162461bcd60e51b815260040180806020018281038252602a815260200180610bc4602a913960400191505060405180910390fd5b5050505050565b600082820183811015610789576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b90505b92915050565b61079c8282610874565b5050565b34606460006107af83836108fb565b60055460405194829003949192506001600160a01b03169084156108fc029085906000818181858888f193505050501580156107ef573d6000803e3d6000fd5b506006546040516001600160a01b039091169082156108fc029083906000818181858888f1935050505015801561082a573d6000803e3d6000fd5b50604080518481526020810183905282850381830181905291517f8f4705a6d8c5af200050d845b50d0f90f838a179c62b9365daa314992598fc1b9181900360600190a150505050565b60008054600154600254604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b1580156108df57600080fd5b505af11580156108f3573d6000803e3d6000fd5b505050505050565b60008061090884846109e2565b905060006109168585610a24565b90506000610926600954866109e2565b9050600061093660095487610a24565b9050600061094d6109478685610a66565b88610a66565b9050600061095b8684610a66565b905060006109698686610a66565b905060006109778786610a66565b905060006109868b60026109e2565b90506000610994838361072f565b905060006109a2828e6109e2565b905060006109b0888861072f565b905060006109be828861072f565b905060006109cc828561072f565b9e50505050505050505050505050505092915050565b600061078983836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250610abf565b600061078983836040518060400160405280601881526020017f536166654d6174683a206d6f64756c6f206279207a65726f0000000000000000815250610b61565b600082610a755750600061078c565b82820282848281610a8257fe5b04146107895760405162461bcd60e51b8152600401808060200182810382526021815260200180610bee6021913960400191505060405180910390fd5b60008183610b4b5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610b10578181015183820152602001610af8565b50505050905090810190601f168015610b3d5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b506000838581610b5757fe5b0495945050505050565b60008183610bb05760405162461bcd60e51b8152602060048201818152835160248401528351909283926044909101919085019080838360008315610b10578181015183820152602001610af8565b50828481610bba57fe5b0694935050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820b504a859a25256791984fde206f32fabe784df405bc1f05322924c1d469c036264736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f2061646472657373";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TICKET_TYPE = "_ticket_type";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_PERCENT_FEE = "percent_fee";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_SALE_LIMIT = "sale_limit";

    public static final String FUNC_SOLD_COUNT = "sold_count";

    public static final String FUNC_TICKET_TYPE = "ticket_type";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_TREASURE_FUND = "treasure_fund";

    public static final String FUNC_WALLET = "wallet";

    public static final String FUNC_WEIRAISED = "weiRaised";

    public static final String FUNC_BUYTICKET = "buyTicket";

    public static final String FUNC_REDEEMTICKET = "redeemTicket";

    public static final String FUNC_GETSCANNEDTICKETSCOUNT = "getScannedTicketsCount";

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
    protected TicketSale721(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketSale721(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketSale721(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketSale721(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<BigInteger> _ticket_type() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TICKET_TYPE, 
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

    public RemoteFunctionCall<TransactionReceipt> buyTokens(String beneficiary, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EVENT_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> percent_fee() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PERCENT_FEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> rate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RATE, 
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

    public RemoteFunctionCall<BigInteger> sold_count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SOLD_COUNT, 
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

    public RemoteFunctionCall<String> token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> treasure_fund() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TREASURE_FUND, 
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

    public RemoteFunctionCall<BigInteger> weiRaised() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WEIRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyTicket(String buyer, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(buyer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> redeemTicket(String visitor, BigInteger token_id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEMTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(visitor), 
                new org.web3j.abi.datatypes.generated.Uint256(token_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getScannedTicketsCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSCANNEDTICKETSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static TicketSale721 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSale721(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketSale721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSale721(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketSale721 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketSale721(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketSale721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketSale721(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketSale721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketSale721.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
