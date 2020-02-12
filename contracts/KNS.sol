pragma solidity ^0.5.11;

contract KNS {

/*
    Events
*/

event LostedKey(string indexed tel, string indexed Jid, address indexed new_wallet);
event Registred(address prime_owner, address wallet, string Jid, string tel);

/*
    Constants
*/

/*
    Storage
*/

//Registry for JID => personal_info
mapping (string => Info) public Registry;

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

function Register(address prime_owner, address wallet, string memory Jid, string memory tel) public {

    Info memory info;
    info.prime_owner = prime_owner;
    info.wallet = wallet;
    info.tel = tel;

    Registry[Jid] = info;
    emit Registred(prime_owner,wallet,Jid,tel);

}

function GetOwnerByJid(string memory Jid) public view returns(address owner) {

    Info memory info;
    info = Registry[Jid];
    address _owner = info.prime_owner;
    return _owner;
}

function GetWalletByJid(string memory Jid) public view returns(address wallet) {

    Info memory info;
    info = Registry[Jid];
    address _wallet = info.wallet;
    return _wallet;
}

function LostKey(string memory Jid, address new_wallet) public {

    Info memory info;
    info = Registry[Jid];
    emit LostedKey(info.tel,Jid,new_wallet);

}




}