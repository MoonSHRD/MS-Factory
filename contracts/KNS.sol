pragma solidity ^0.6.1;

contract KNS {

/*
    Events
*/

event LostedKey(string indexed tel, string indexed Jid, address indexed new_wallet);

/*
    Constants
*/

/*
    Storage
*/

//Registry for JID => personal_info
mapping (string => Info) public Registry;

struct Info {
        address owner;
        address wallet;
        string tel;
    }

/*
    Modidifiers

*/



/*
    Public functions
*/

function Register(address owner, address wallet, string memory Jid, string memory tel) public {

    Info memory info;
    info.owner = owner;
    info.wallet = wallet;
    info.tel = tel;

    Registry[Jid] = info;

}

function LostKey(string memory Jid, address new_wallet) public {

    Info memory info;
    info = Registry[Jid];
    emit LostedKey(info.tel,Jid,new_wallet);

}




}