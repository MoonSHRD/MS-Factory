pragma solidity ^0.5.11;

contract KNS {

/*
    Events
*/

event LostedKey(string indexed tel, string indexed Jid, address indexed new_wallet);
event Registred(address prime_owner, address wallet, string Jid, string indexed tel);

/*
    Constants
*/

/*
    Storage
*/

//Registry for JID => personal_info
mapping (string => Info) public RegistryJ;

// map from telephone => info
mapping (string => Info) public RegistryT;

struct Info {
       // address[] owners;
        address prime_owner;
        address wallet;
        string tel;
    }

/*
    Modidifiers

*/



/*
    Public functions
*/

// FIXME: add check for existence
function Register(address prime_owner, address wallet, string memory Jid, string memory tel) public {

    Info memory info;
    info.prime_owner = prime_owner;
    info.wallet = wallet;
    info.tel = tel;

    RegistryJ[Jid] = info;
    RegistryT[tel] = info;
    emit Registred(prime_owner,wallet,Jid,tel);

}

function GetOwnerByJid(string memory Jid) public view returns(address owner) {

    Info memory info;
    info = RegistryJ[Jid];
    address _owner = info.prime_owner;
    return _owner;
}

function GetWalletByJid(string memory Jid) public view returns(address wallet) {

    Info memory info;
    info = RegistryJ[Jid];
    address _wallet = info.wallet;
    return _wallet;
}

function GetWalletByTel(string memory tel) public view returns (address wallet) {

    Info memory info;
    info = RegistryT[tel];
    address _wallet = info.wallet;
    return _wallet;

}

function LostKey(string memory Jid, address new_wallet) public {

    Info memory info;
    info = RegistryJ[Jid];
    emit LostedKey(info.tel,Jid,new_wallet);

}




}