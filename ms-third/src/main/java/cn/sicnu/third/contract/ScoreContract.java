package cn.sicnu.third.contract;

import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class ScoreContract extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506110016000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a6040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845285818151815260200191508051906020019080838360005b8381101561013957808201518184015260208101905061011e565b50505050905090810190601f1680156101665780820380516001836020036101000a031916815260200191505b50848103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001848103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001945050505050602060405180830381600087803b1580156101ef57600080fd5b505af1158015610203573d6000803e3d6000fd5b505050506040513d602081101561021957600080fd5b810190808051906020019092919050505050611a298061023a6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063160202731461005c5780638f1b406d146100d9578063fa052fac14610156575b600080fd5b34801561006857600080fd5b506100c3600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101dd565b6040518082815260200191505060405180910390f35b3480156100e557600080fd5b50610140600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610795565b6040518082815260200191505060405180910390f35b34801561016257600080fd5b506101c7600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610f95565b6040518082815260200191505060405180910390f35b60008060008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156102c35780820151818401526020810190506102a8565b50505050905090810190601f1680156102f05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561030f57600080fd5b505af1158015610323573d6000803e3d6000fd5b505050506040513d602081101561033957600080fd5b810190808051906020019092919050505093508373ffffffffffffffffffffffffffffffffffffffff1663e8434e39878673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156103cd57600080fd5b505af11580156103e1573d6000803e3d6000fd5b505050506040513d60208110156103f757600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156104a557808201518184015260208101905061048a565b50505050905090810190601f1680156104d25780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156104f257600080fd5b505af1158015610506573d6000803e3d6000fd5b505050506040513d602081101561051c57600080fd5b8101908080519060200190929190505050925060008373ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561059557600080fd5b505af11580156105a9573d6000803e3d6000fd5b505050506040513d60208110156105bf57600080fd5b810190808051906020019092919050505014156105fe577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff945061078c565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561066e57600080fd5b505af1158015610682573d6000803e3d6000fd5b505050506040513d602081101561069857600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561074b57600080fd5b505af115801561075f573d6000803e3d6000fd5b505050506040513d602081101561077557600080fd5b810190808051906020019092919050505090508094505b50505050919050565b6000806000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561087c578082015181840152602081019050610861565b50505050905090810190601f1680156108a95780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156108c857600080fd5b505af11580156108dc573d6000803e3d6000fd5b505050506040513d60208110156108f257600080fd5b810190808051906020019092919050505094508473ffffffffffffffffffffffffffffffffffffffff1663e8434e39888773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561098657600080fd5b505af115801561099a573d6000803e3d6000fd5b505050506040513d60208110156109b057600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610a5e578082015181840152602081019050610a43565b50505050905090810190601f168015610a8b5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610aab57600080fd5b505af1158015610abf573d6000803e3d6000fd5b505050506040513d6020811015610ad557600080fd5b8101908080519060200190929190505050935060008473ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b4e57600080fd5b505af1158015610b62573d6000803e3d6000fd5b505050506040513d6020811015610b7857600080fd5b8101908080519060200190929190505050141515610bb8577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff9550610f8b565b8473ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610c1c57600080fd5b505af1158015610c30573d6000803e3d6000fd5b505050506040513d6020811015610c4657600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610d19578082015181840152602081019050610cfe565b50505050905090810190601f168015610d465780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610d6657600080fd5b505af1158015610d7a573d6000803e3d6000fd5b50505050600091508273ffffffffffffffffffffffffffffffffffffffff16632ef8ba74836040518263ffffffff167c010000000000000000000000000000000000000000000000000000","00000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610e2a57600080fd5b505af1158015610e3e573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff166331afac3688856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610efd578082015181840152602081019050610ee2565b50505050905090810190601f168015610f2a5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610f4a57600080fd5b505af1158015610f5e573d6000803e3d6000fd5b505050506040513d6020811015610f7457600080fd5b810190808051906020019092919050505090508095505b5050505050919050565b60008060008060008060008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015611081578082015181840152602081019050611066565b50505050905090810190601f1680156110ae5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156110cd57600080fd5b505af11580156110e1573d6000803e3d6000fd5b505050506040513d60208110156110f757600080fd5b810190808051906020019092919050505097508773ffffffffffffffffffffffffffffffffffffffff1663e8434e398c8a73ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561118b57600080fd5b505af115801561119f573d6000803e3d6000fd5b505050506040513d60208110156111b557600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015611263578082015181840152602081019050611248565b50505050905090810190601f1680156112905780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156112b057600080fd5b505af11580156112c4573d6000803e3d6000fd5b505050506040513d60208110156112da57600080fd5b8101908080519060200190929190505050965060008773ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561135357600080fd5b505af1158015611367573d6000803e3d6000fd5b505050506040513d602081101561137d57600080fd5b810190808051906020019092919050505014156113bc577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98506119ef565b8673ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561142c57600080fd5b505af1158015611440573d6000803e3d6000fd5b505050506040513d602081101561145657600080fd5b810190808051906020019092919050505095508573ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561150957600080fd5b505af115801561151d573d6000803e3d6000fd5b505050506040513d602081101561153357600080fd5b81019080805190602001909291905050509450848a0193508773ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156115af57600080fd5b505af11580156115c3573d6000803e3d6000fd5b505050506040513d60208110156115d957600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff16632ef8ba74856040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561169457600080fd5b505af11580156116a8573d6000803e3d6000fd5b505050508773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561171057600080fd5b505af1158015611724573d6000803e3d6000fd5b505050506040513d602081101561173a57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561180d5780820151818401526020810190506117f2565b50505050905090810190601f16801561183a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561185a57600080fd5b505af115801561186e573d6000803e3d6000fd5b505050508773ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18c85856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015611960578082015181840152602081019050611945565b50505050905090810190601f16801561198d5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b1580156119ae57600080fd5b505af11580156119c2573d6000803e3d6000fd5b505050506040513d60208110156119d857600080fd5b810190808051906020019092919050505090508098505b5050505050505050929150505600a165627a7a72305820a00d690d89e246cce9a78234a8327c15cd3c204ed958f266c658735a1393d3660029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b506110016000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c92a78016040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845285818151815260200191508051906020019080838360005b8381101561013957808201518184015260208101905061011e565b50505050905090810190601f1680156101665780820380516001836020036101000a031916815260200191505b50848103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001848103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001945050505050602060405180830381600087803b1580156101ef57600080fd5b505af1158015610203573d6000803e3d6000fd5b505050506040513d602081101561021957600080fd5b810190808051906020019092919050505050611a298061023a6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630592c9b61461005c5780633a463798146100d9578063b2d9564f14610156575b600080fd5b34801561006857600080fd5b506100c3600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101dd565b6040518082815260200191505060405180910390f35b3480156100e557600080fd5b50610140600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610795565b6040518082815260200191505060405180910390f35b34801561016257600080fd5b506101c7600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610f95565b6040518082815260200191505060405180910390f35b60008060008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166359a48b656040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156102c35780820151818401526020810190506102a8565b50505050905090810190601f1680156102f05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561030f57600080fd5b505af1158015610323573d6000803e3d6000fd5b505050506040513d602081101561033957600080fd5b810190808051906020019092919050505093508373ffffffffffffffffffffffffffffffffffffffff1663d8ac5957878673ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156103cd57600080fd5b505af11580156103e1573d6000803e3d6000fd5b505050506040513d60208110156103f757600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156104a557808201518184015260208101905061048a565b50505050905090810190601f1680156104d25780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156104f257600080fd5b505af1158015610506573d6000803e3d6000fd5b505050506040513d602081101561051c57600080fd5b8101908080519060200190929190505050925060008373ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561059557600080fd5b505af11580156105a9573d6000803e3d6000fd5b505050506040513d60208110156105bf57600080fd5b810190808051906020019092919050505014156105fe577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff945061078c565b8273ffffffffffffffffffffffffffffffffffffffff16633dd2b61460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561066e57600080fd5b505af1158015610682573d6000803e3d6000fd5b505050506040513d602081101561069857600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff16634900862e6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561074b57600080fd5b505af115801561075f573d6000803e3d6000fd5b505050506040513d602081101561077557600080fd5b810190808051906020019092919050505090508094505b50505050919050565b6000806000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166359a48b656040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561087c578082015181840152602081019050610861565b50505050905090810190601f1680156108a95780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156108c857600080fd5b505af11580156108dc573d6000803e3d6000fd5b505050506040513d60208110156108f257600080fd5b810190808051906020019092919050505094508473ffffffffffffffffffffffffffffffffffffffff1663d8ac5957888773ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561098657600080fd5b505af115801561099a573d6000803e3d6000fd5b505050506040513d60208110156109b057600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610a5e578082015181840152602081019050610a43565b50505050905090810190601f168015610a8b5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610aab57600080fd5b505af1158015610abf573d6000803e3d6000fd5b505050506040513d6020811015610ad557600080fd5b8101908080519060200190929190505050935060008473ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b4e57600080fd5b505af1158015610b62573d6000803e3d6000fd5b505050506040513d6020811015610b7857600080fd5b8101908080519060200190929190505050141515610bb8577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff9550610f8b565b8473ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610c1c57600080fd5b505af1158015610c30573d6000803e3d6000fd5b505050506040513d6020811015610c4657600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff16631a391cb4886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610d19578082015181840152602081019050610cfe565b50505050905090810190601f168015610d465780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610d6657600080fd5b505af1158015610d7a573d6000803e3d6000fd5b50505050600091508273ffffffffffffffffffffffffffffffffffffffff1663def42698836040518263ffffffff167c010000000000000000000000000000000000000000000000000000","00000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610e2a57600080fd5b505af1158015610e3e573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff16634c6f30c088856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610efd578082015181840152602081019050610ee2565b50505050905090810190601f168015610f2a5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610f4a57600080fd5b505af1158015610f5e573d6000803e3d6000fd5b505050506040513d6020811015610f7457600080fd5b810190808051906020019092919050505090508095505b5050505050919050565b60008060008060008060008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166359a48b656040805190810160405280600781526020017f745f73636f7265000000000000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015611081578082015181840152602081019050611066565b50505050905090810190601f1680156110ae5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156110cd57600080fd5b505af11580156110e1573d6000803e3d6000fd5b505050506040513d60208110156110f757600080fd5b810190808051906020019092919050505097508773ffffffffffffffffffffffffffffffffffffffff1663d8ac59578c8a73ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561118b57600080fd5b505af115801561119f573d6000803e3d6000fd5b505050506040513d60208110156111b557600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015611263578082015181840152602081019050611248565b50505050905090810190601f1680156112905780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156112b057600080fd5b505af11580156112c4573d6000803e3d6000fd5b505050506040513d60208110156112da57600080fd5b8101908080519060200190929190505050965060008773ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561135357600080fd5b505af1158015611367573d6000803e3d6000fd5b505050506040513d602081101561137d57600080fd5b810190808051906020019092919050505014156113bc577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98506119ef565b8673ffffffffffffffffffffffffffffffffffffffff16633dd2b61460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561142c57600080fd5b505af1158015611440573d6000803e3d6000fd5b505050506040513d602081101561145657600080fd5b810190808051906020019092919050505095508573ffffffffffffffffffffffffffffffffffffffff16634900862e6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561150957600080fd5b505af115801561151d573d6000803e3d6000fd5b505050506040513d602081101561153357600080fd5b81019080805190602001909291905050509450848a0193508773ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156115af57600080fd5b505af11580156115c3573d6000803e3d6000fd5b505050506040513d60208110156115d957600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663def42698856040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561169457600080fd5b505af11580156116a8573d6000803e3d6000fd5b505050508773ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561171057600080fd5b505af1158015611724573d6000803e3d6000fd5b505050506040513d602081101561173a57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663ae763db58c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260068152602001807f6964436172640000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561180d5780820151818401526020810190506117f2565b50505050905090810190601f16801561183a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561185a57600080fd5b505af115801561186e573d6000803e3d6000fd5b505050508773ffffffffffffffffffffffffffffffffffffffff1663664b37d68c85856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015611960578082015181840152602081019050611945565b50505050905090810190601f16801561198d5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b1580156119ae57600080fd5b505af11580156119c2573d6000803e3d6000fd5b505050506040513d60208110156119d857600080fd5b810190808051906020019092919050505090508098505b5050505050505050929150505600a165627a7a72305820b0d5753ccf4310255116f4825a2de86e405c2ecd50e154a4f76de30f2d009d880029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"idCard\",\"type\":\"string\"}],\"name\":\"getScore\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"idCard\",\"type\":\"string\"}],\"name\":\"signAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"idCard\",\"type\":\"string\"},{\"name\":\"score\",\"type\":\"int256\"}],\"name\":\"addScore\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETSCORE = "getScore";

    public static final String FUNC_SIGNACCOUNT = "signAccount";

    public static final String FUNC_ADDSCORE = "addScore";

    protected ScoreContract(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public BigInteger getScore(String idCard) throws ContractException {
        final Function function = new Function(FUNC_GETSCORE, 
                Arrays.<Type>asList(new Utf8String(idCard)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt signAccount(String idCard) {
        final Function function = new Function(
                FUNC_SIGNACCOUNT, 
                Arrays.<Type>asList(new Utf8String(idCard)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void signAccount(String idCard, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SIGNACCOUNT, 
                Arrays.<Type>asList(new Utf8String(idCard)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSignAccount(String idCard) {
        final Function function = new Function(
                FUNC_SIGNACCOUNT, 
                Arrays.<Type>asList(new Utf8String(idCard)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSignAccountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SIGNACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<BigInteger> getSignAccountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SIGNACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt addScore(String idCard, BigInteger score) {
        final Function function = new Function(
                FUNC_ADDSCORE, 
                Arrays.<Type>asList(new Utf8String(idCard),
                new Int256(score)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void addScore(String idCard, BigInteger score, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDSCORE, 
                Arrays.<Type>asList(new Utf8String(idCard),
                new Int256(score)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddScore(String idCard, BigInteger score) {
        final Function function = new Function(
                FUNC_ADDSCORE, 
                Arrays.<Type>asList(new Utf8String(idCard),
                new Int256(score)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getAddScoreInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDSCORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getAddScoreOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDSCORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public static ScoreContract load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new ScoreContract(contractAddress, client, credential);
    }

    public static ScoreContract deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(ScoreContract.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
