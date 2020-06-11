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
import org.web3j.abi.datatypes.Utf8String;
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
public class TicketFactory721 extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5060405161267c38038061267c8339818101604052604081101561003357600080fd5b508051602090910151600080546001600160a01b039384166001600160a01b031991821617909155600180549390921692169190911790556126028061007a6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632168d20e1461005157806351c187821461009f578063722b58171461017f578063b992389314610187575b600080fd5b6100836004803603606081101561006757600080fd5b506001600160a01b03813516906020810135906040013561023f565b604080516001600160a01b039092168252519081900360200190f35b61015c600480360360808110156100b557600080fd5b6001600160a01b03823516916020810135918101906060810160408201356401000000008111156100e557600080fd5b8201836020820111156100f757600080fd5b8035906020019184600183028401116401000000008311171561011957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061040c915050565b604080516001600160a01b03909316835260208301919091528051918290030190f35b610083610787565b61022d6004803603602081101561019d57600080fd5b8101906020810181356401000000008111156101b857600080fd5b8201836020820111156101ca57600080fd5b803590602001918460018302840111640100000000831117156101ec57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610796945050505050565b60408051918252519081900360200190f35b60008061024b846107fe565b905080858460405161025c9061092d565b9283526001600160a01b0390911660208301526040808301919091525190819003606001906000f080158015610296573d6000803e3d6000fd5b50915060008290506000816001600160a01b03166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b1580156102d957600080fd5b505afa1580156102ed573d6000803e3d6000fd5b505050506040513d602081101561030357600080fd5b50516040805163fbbdc46560e01b815290519192506000916001600160a01b0385169163fbbdc465916004808301926020929190829003018186803b15801561034b57600080fd5b505afa15801561035f573d6000803e3d6000fd5b505050506040513d602081101561037557600080fd5b505160405190915082906001600160a01b038a169033907f80ea680f0864b200472457d5e9db6c7ab4390228ca763e8159260f99ff4c07e490600090a4604080513381526001600160a01b038a1660208201528082018490526060810183905290517f842f53ee085ffb3e94c2cdd4376dd879b170ba013b0f22dcc95e9ba6596fbbce9181900360800190a1505050509392505050565b60008054604051845183926001600160a01b031691600291879190819060208401908083835b602083106104515780518252601f199092019160209182019101610432565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220541591506104bd90505760405162461bcd60e51b81526004018080602001828103825260268152602001806125a86026913960400191505060405180910390fd5b806104cb888883888a61080b565b93506000849050806001600160a01b03166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b15801561050b57600080fd5b505afa15801561051f573d6000803e3d6000fd5b505050506040513d602081101561053557600080fd5b50516040805163fbbdc46560e01b815290519195506000916001600160a01b0384169163fbbdc465916004808301926020929190829003018186803b15801561057d57600080fd5b505afa158015610591573d6000803e3d6000fd5b505050506040513d60208110156105a757600080fd5b5051604051895191925086916002918b91819060208401908083835b602083106105e25780518252601f1990920191602091820191016105c3565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842094909455505089518a928291908401908083835b602083106106405780518252601f199092019160209182019101610621565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020858b6001600160a01b03167f87836d9d795936ee8b4b6ffb2c7b7fba245eb618db8294c11e9cf03565afbb408c6040518082815260200191505060405180910390a47f8cf17295cea07172ef627805a194fc701945ac99cb803ae5930490fe2afb45098a8a878b8560405180866001600160a01b03166001600160a01b0316815260200185815260200184815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561073c578181015183820152602001610724565b50505050905090810190601f1680156107695780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a15050505094509492505050565b6000546001600160a01b031690565b60006002826040518082805190602001908083835b602083106107ca5780518252601f1990920191602091820191016107ab565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b670de0b6b3a76400000290565b600080610817866107fe565b90508087868686600160009054906101000a90046001600160a01b03166040516108409061093a565b80878152602001866001600160a01b03166001600160a01b03168152602001856001600160a01b03166001600160a01b0316815260200184815260200180602001836001600160a01b03166001600160a01b03168152602001828103825284818151815260200191508051906020019080838360005b838110156108ce5781810151838201526020016108b6565b50505050905090810190601f1680156108fb5780820380516001836020036101000a031916815260200191505b50975050505050505050604051809103906000f080158015610921573d6000803e3d6000fd5b50979650505050505050565b610c218061094883390190565b61103f806115698339019056fe6080604052600060055534801561001557600080fd5b50604051610c21380380610c218339818101604052606081101561003857600080fd5b50805160208201516040909201516000805460ff19166001179055909190828282826100ab576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b038216610106576040805162461bcd60e51b815260206004820152601d60248201527f6f726967696e2063616e6e6f74206265207a65726f2061646472657373000000604482015290519081900360640190fd5b600180546001600160a01b0319166001600160a01b0384811691909117918290556040805163521eb27360e01b81529051929091169163521eb27391600480820192602092909190829003018186803b15801561016257600080fd5b505afa158015610176573d6000803e3d6000fd5b505050506040513d602081101561018c57600080fd5b5051600680546001600160a01b0319166001600160a01b039283161790556007849055670de0b6b3a76400008202600490815560015460408051637e062a3560e11b81529051919093169263fc0c546a9281810192602092909190829003018186803b1580156101fb57600080fd5b505afa15801561020f573d6000803e3d6000fd5b505050506040513d602081101561022557600080fd5b505160008054610100600160a81b0319166101006001600160a01b039384160217905560015460408051634881cef760e11b8152905191909216916391039dee916004808301926020929190829003018186803b15801561028557600080fd5b505afa158015610299573d6000803e3d6000fd5b505050506040513d60208110156102af57600080fd5b505160028190556000805460065460408051630645c0fb60e41b815260048101959095526001600160a01b03918216602486015251610100909204169263645c0fb09260448083019360209390929083900390910190829087803b15801561031657600080fd5b505af115801561032a573d6000803e3d6000fd5b505050506040513d602081101561034057600080fd5b50516003555050505050506108c78061035a6000396000f3fe6080604052600436106100fe5760003560e01c8063a5f8cdbb11610095578063ecd0c0c311610064578063ecd0c0c314610280578063f00a93d314610295578063f9cfe26b146102aa578063fbbdc465146102bf578063fc0c546a146102d4576100fe565b8063a5f8cdbb1461020a578063a8253cac14610230578063e61d959414610245578063ec8ac4d81461025a576100fe565b8063610757e4116100d1578063610757e4146101925780636c1e81e3146101a75780636ed08ea0146101e057806391039dee146101f5576100fe565b80632c4e722e146101105780634042b66f1461013757806341035a411461014c578063521eb2731461017d575b61010e6101096102e9565b6102ed565b005b34801561011c57600080fd5b506101256102f9565b60408051918252519081900360200190f35b34801561014357600080fd5b506101256102ff565b34801561015857600080fd5b50610161610305565b604080516001600160a01b039092168252519081900360200190f35b34801561018957600080fd5b50610161610314565b34801561019e57600080fd5b50610161610323565b3480156101b357600080fd5b5061010e600480360360408110156101ca57600080fd5b506001600160a01b038135169060200135610332565b3480156101ec57600080fd5b506101256103da565b34801561020157600080fd5b506101256103e0565b61010e6004803603602081101561022057600080fd5b50356001600160a01b03166102ed565b34801561023c57600080fd5b506101256103e6565b34801561025157600080fd5b506101256103f9565b61010e6004803603602081101561027057600080fd5b50356001600160a01b03166103ff565b34801561028c57600080fd5b50610161610536565b3480156102a157600080fd5b5061012561054a565b3480156102b657600080fd5b50610125610550565b3480156102cb57600080fd5b50610125610556565b3480156102e057600080fd5b5061016161055c565b3390565b6102f6816103ff565b50565b60075490565b60085490565b6001546001600160a01b031681565b6006546001600160a01b031690565b6006546001600160a01b031681565b600061033c61055c565b905060006103486103e0565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b1580156103b257600080fd5b505af11580156103c6573d6000803e3d6000fd5b505050506103d46009610570565b50505050565b60025481565b60025490565b6000806103f36009610579565b91505090565b60055490565b60005460ff16610456576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff19168155349061046b8261057d565b90506104788383836105f0565b60085461048b908363ffffffff6106e316565b6008556005546104a1908263ffffffff6106e316565b6005556104ae8382610744565b826001600160a01b03166104c06102e9565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a3610512838361074e565b61051a610752565b610524838361074e565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60045490565b60055481565b60035490565b60005461010090046001600160a01b031690565b80546001019055565b5490565b60006007548210156105c05760405162461bcd60e51b815260040180806020018281038252602c815260200180610867602c913960400191505060405180910390fd5b6000670de0b6b3a7640000600754816105d557fe5b04670de0b6b3a76400008404816105e857fe5b049392505050565b6001600160a01b0383166106355760405162461bcd60e51b815260040180806020018281038252602a81526020018061083d602a913960400191505060405180910390fd5b81610687576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600554600454670de0b6b3a764000090910290828201908111156106dc5760405162461bcd60e51b815260040180806020018281038252602a815260200180610813602a913960400191505060405180910390fd5b5050505050565b60008282018381101561073d576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b61074e828261078b565b5050565b6006546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156102f6573d6000803e3d6000fd5b60008054600254600354604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b1580156107f657600080fd5b505af115801561080a573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820255ff74fa3edd2c3de10dca392238f024d2a1b02e146a149a23a1ecd8b0d3af464736f6c63430005110032608060405260016002556000600455600560095534801561001f57600080fd5b5060405161103f38038061103f833981810160405260c081101561004257600080fd5b815160208301516040808501516060860151608087018051935195979496929591949193928201928464010000000082111561007d57600080fd5b90830190602082018581111561009257600080fd5b82516401000000008111828201881017156100ac57600080fd5b82525081516020918201929091019080838360005b838110156100d95781810151838201526020016100c1565b50505050905090810190601f1680156101065780820380516001836020036101000a031916815260200191505b50604052602001516000805460ff191660011790559150869050858585858585610177576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b0385166101bc5760405162461bcd60e51b815260040180806020018281038252602581526020018061101a6025913960400191505060405180910390fd5b6001600160a01b0384166102015760405162461bcd60e51b8152600401808060200182810382526024815260200180610ff66024913960400191505060405180910390fd5b6007869055600580546001600160a01b03199081166001600160a01b038881169190911792839055600680549092168482161790915560008054610100600160a81b031916610100888416810291909117808355670de0b6b3a7640000880260035560408051631440364760e31b81529585166004870181815260248801928352895160448901528951949093049095169563a201b2389594899460649092019160208601918190849084905b838110156102c65781810151838201526020016102ae565b50505050905090810190601f1680156102f35780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561031357600080fd5b505af1158015610327573d6000803e3d6000fd5b505050506040513d602081101561033d57600080fd5b5051600155505050505050505050505050610c998061035d6000396000f3fe6080604052600436106101145760003560e01c8063a8253cac116100a0578063f00a93d311610064578063f00a93d3146102c0578063f9a4bb1e146102d5578063f9cfe26b146102ea578063fbbdc465146102ff578063fc0c546a1461031457610114565b8063a8253cac14610246578063e27920a81461025b578063e61d959414610270578063ec8ac4d814610285578063ecd0c0c3146102ab57610114565b8063610757e4116100e7578063610757e4146101a85780636c1e81e3146101bd5780636ed08ea0146101f657806391039dee1461020b578063a5f8cdbb1461022057610114565b806323e6f340146101265780632c4e722e1461014d5780634042b66f14610162578063521eb27314610177575b61012461011f610329565b61032d565b005b34801561013257600080fd5b5061013b610339565b60408051918252519081900360200190f35b34801561015957600080fd5b5061013b61033f565b34801561016e57600080fd5b5061013b610345565b34801561018357600080fd5b5061018c61034b565b604080516001600160a01b039092168252519081900360200190f35b3480156101b457600080fd5b5061018c61035a565b3480156101c957600080fd5b50610124600480360360408110156101e057600080fd5b506001600160a01b038135169060200135610369565b34801561020257600080fd5b5061013b610411565b34801561021757600080fd5b5061013b610417565b6101246004803603602081101561023657600080fd5b50356001600160a01b031661032d565b34801561025257600080fd5b5061013b61041d565b34801561026757600080fd5b5061013b610430565b34801561027c57600080fd5b5061013b610436565b6101246004803603602081101561029b57600080fd5b50356001600160a01b031661043c565b3480156102b757600080fd5b5061018c610573565b3480156102cc57600080fd5b5061013b610587565b3480156102e157600080fd5b5061018c61058d565b3480156102f657600080fd5b5061013b61059c565b34801561030b57600080fd5b5061013b6105a2565b34801561032057600080fd5b5061018c6105a8565b3390565b6103368161043c565b50565b60025481565b60075490565b60085490565b6005546001600160a01b031690565b6005546001600160a01b031681565b60006103736105a8565b9050600061037f610417565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b1580156103e957600080fd5b505af11580156103fd573d6000803e3d6000fd5b5050505061040b600a6105bc565b50505050565b60015481565b60015490565b60008061042a600a6105c5565b91505090565b60095481565b60045490565b60005460ff16610493576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906104a8826105c9565b90506104b583838361063c565b6008546104c8908363ffffffff61072f16565b6008556004546104de908263ffffffff61072f16565b6004556104eb8382610792565b826001600160a01b03166104fd610329565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a361054f838361079c565b6105576107a0565b610561838361079c565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60035490565b6006546001600160a01b031681565b60045481565b60025490565b60005461010090046001600160a01b031690565b80546001019055565b5490565b600060075482101561060c5760405162461bcd60e51b815260040180806020018281038252602c815260200180610c39602c913960400191505060405180910390fd5b6000670de0b6b3a76400006007548161062157fe5b04670de0b6b3a764000084048161063457fe5b049392505050565b6001600160a01b0383166106815760405162461bcd60e51b815260040180806020018281038252602a815260200180610c0f602a913960400191505060405180910390fd5b816106d3576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600454600354670de0b6b3a764000090910290828201908111156107285760405162461bcd60e51b815260040180806020018281038252602a815260200180610bc4602a913960400191505060405180910390fd5b5050505050565b600082820183811015610789576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b90505b92915050565b61079c8282610874565b5050565b34606460006107af83836108fb565b60055460405194829003949192506001600160a01b03169084156108fc029085906000818181858888f193505050501580156107ef573d6000803e3d6000fd5b506006546040516001600160a01b039091169082156108fc029083906000818181858888f1935050505015801561082a573d6000803e3d6000fd5b50604080518481526020810183905282850381830181905291517f8f4705a6d8c5af200050d845b50d0f90f838a179c62b9365daa314992598fc1b9181900360600190a150505050565b60008054600154600254604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b1580156108df57600080fd5b505af11580156108f3573d6000803e3d6000fd5b505050505050565b60008061090884846109e2565b905060006109168585610a24565b90506000610926600954866109e2565b9050600061093660095487610a24565b9050600061094d6109478685610a66565b88610a66565b9050600061095b8684610a66565b905060006109698686610a66565b905060006109778786610a66565b905060006109868b60026109e2565b90506000610994838361072f565b905060006109a2828e6109e2565b905060006109b0888861072f565b905060006109be828861072f565b905060006109cc828561072f565b9e50505050505050505050505050505092915050565b600061078983836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250610abf565b600061078983836040518060400160405280601881526020017f536166654d6174683a206d6f64756c6f206279207a65726f0000000000000000815250610b61565b600082610a755750600061078c565b82820282848281610a8257fe5b04146107895760405162461bcd60e51b8152600401808060200182810382526021815260200180610bee6021913960400191505060405180910390fd5b60008183610b4b5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610b10578181015183820152602001610af8565b50505050905090810190601f168015610b3d5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b506000838581610b5757fe5b0495945050505050565b60008183610bb05760405162461bcd60e51b8152602060048201818152835160248401528351909283926044909101919085019080838360008315610b10578181015183820152602001610af8565b50828481610bba57fe5b0694935050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820b504a859a25256791984fde206f32fabe784df405bc1f05322924c1d469c036264736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f206164647265737373616c6520776974682074686973204a494420697320616c7265616479206372656174656421a265627a7a7231582025b482d281abf8a5a53222320fd87d80236217fd7efa8d20dfcd6b8ac8d90d6a64736f6c63430005110032";

    public static final String FUNC_CREATETICKETSALE = "createTicketSale";

    public static final String FUNC_PLUGINTICKETSALE = "PlugInTicketSale";

    public static final String FUNC_GETEVENTIDBYJID = "getEventIdByJid";

    public static final String FUNC_GETTICKETTEMPLATEADDRESS = "getTicketTemplateAddress";

    public static final Event PLUGGEDSALE_EVENT = new Event("PluggedSale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PLUGGEDSALEHUMAN_EVENT = new Event("PluggedSaleHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SALECREATED_EVENT = new Event("SaleCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event SALECREATEDHUMAN_EVENT = new Event("SaleCreatedHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("8995", "0xDDAB6A8cf4d392F6d2A7F59Fc3daE637EF26d2ee");
    }

    @Deprecated
    protected TicketFactory721(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketFactory721(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketFactory721(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketFactory721(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<PluggedSaleEventResponse> getPluggedSaleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLUGGEDSALE_EVENT, transactionReceipt);
        ArrayList<PluggedSaleEventResponse> responses = new ArrayList<PluggedSaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PluggedSaleEventResponse typedResponse = new PluggedSaleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orginal_sale = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PluggedSaleEventResponse> pluggedSaleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PluggedSaleEventResponse>() {
            @Override
            public PluggedSaleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLUGGEDSALE_EVENT, log);
                PluggedSaleEventResponse typedResponse = new PluggedSaleEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.orginal_sale = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PluggedSaleEventResponse> pluggedSaleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLUGGEDSALE_EVENT));
        return pluggedSaleEventFlowable(filter);
    }

    public List<PluggedSaleHumanEventResponse> getPluggedSaleHumanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLUGGEDSALEHUMAN_EVENT, transactionReceipt);
        ArrayList<PluggedSaleHumanEventResponse> responses = new ArrayList<PluggedSaleHumanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PluggedSaleHumanEventResponse typedResponse = new PluggedSaleHumanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.original_sale = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PluggedSaleHumanEventResponse> pluggedSaleHumanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PluggedSaleHumanEventResponse>() {
            @Override
            public PluggedSaleHumanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLUGGEDSALEHUMAN_EVENT, log);
                PluggedSaleHumanEventResponse typedResponse = new PluggedSaleHumanEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.original_sale = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PluggedSaleHumanEventResponse> pluggedSaleHumanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLUGGEDSALEHUMAN_EVENT));
        return pluggedSaleHumanEventFlowable(filter);
    }

    public List<SaleCreatedEventResponse> getSaleCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SALECREATED_EVENT, transactionReceipt);
        ArrayList<SaleCreatedEventResponse> responses = new ArrayList<SaleCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleCreatedEventResponse typedResponse = new SaleCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.event_JID = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SaleCreatedEventResponse> saleCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SaleCreatedEventResponse>() {
            @Override
            public SaleCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALECREATED_EVENT, log);
                SaleCreatedEventResponse typedResponse = new SaleCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.event_JID = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SaleCreatedEventResponse> saleCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALECREATED_EVENT));
        return saleCreatedEventFlowable(filter);
    }

    public List<SaleCreatedHumanEventResponse> getSaleCreatedHumanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SALECREATEDHUMAN_EVENT, transactionReceipt);
        ArrayList<SaleCreatedHumanEventResponse> responses = new ArrayList<SaleCreatedHumanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleCreatedHumanEventResponse typedResponse = new SaleCreatedHumanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.event_JID = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SaleCreatedHumanEventResponse> saleCreatedHumanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SaleCreatedHumanEventResponse>() {
            @Override
            public SaleCreatedHumanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALECREATEDHUMAN_EVENT, log);
                SaleCreatedHumanEventResponse typedResponse = new SaleCreatedHumanEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.event_JID = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SaleCreatedHumanEventResponse> saleCreatedHumanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALECREATEDHUMAN_EVENT));
        return saleCreatedHumanEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createTicketSale(String organizer, BigInteger price, String event_JID, BigInteger sale_limit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATETICKETSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(organizer), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.Utf8String(event_JID), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> PlugInTicketSale(String origin_sale, BigInteger price, BigInteger _sale_limit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PLUGINTICKETSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(origin_sale), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.generated.Uint256(_sale_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getEventIdByJid(String JID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEVENTIDBYJID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(JID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getTicketTemplateAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTICKETTEMPLATEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TicketFactory721 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketFactory721(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketFactory721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketFactory721(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketFactory721 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketFactory721(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketFactory721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketFactory721(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _ticket, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketFactory721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _ticket, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketFactory721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _ticket, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketFactory721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _ticket, String _treasure_fund) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket), 
                new org.web3j.abi.datatypes.Address(_treasure_fund)));
        return deployRemoteCall(TicketFactory721.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class PluggedSaleEventResponse extends BaseEventResponse {
        public String organizer;

        public String orginal_sale;

        public BigInteger event_id;
    }

    public static class PluggedSaleHumanEventResponse extends BaseEventResponse {
        public String organizer;

        public String original_sale;

        public BigInteger event_id;

        public BigInteger ticket_type;
    }

    public static class SaleCreatedEventResponse extends BaseEventResponse {
        public String organizer;

        public BigInteger event_id;

        public byte[] event_JID;

        public BigInteger price;
    }

    public static class SaleCreatedHumanEventResponse extends BaseEventResponse {
        public String organizer;

        public BigInteger price;

        public BigInteger event_id;

        public String event_JID;

        public BigInteger ticket_type;
    }
}
