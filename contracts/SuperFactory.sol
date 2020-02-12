pragma solidity ^0.5.11;

import './gnosis/MultiSigWalletFactory.sol';
import './gnosis/MultiSigWalletWithDailyLimitFactory.sol';
import './KNS.sol';


contract SuperFactory is MultiSigWalletWithDailyLimitFactory {

KNS registry;

constructor(address registry_deployed) public {
    registry = KNS(registry_deployed);
}

function createWallet(address[] memory _owners, uint _required, uint _dailyLimit, string memory Jid, string memory tel) public returns(address _wallet) {

    address wallet = super.create(_owners,_required, _dailyLimit);

    address prime_owner = _owners[0];
    registry.Register(prime_owner,wallet,Jid,tel);
    return wallet;
}


// TODO: add function to create wallet with pre-defined 2fa/replacer key
/*
function createSimpleWallet(address memory _owner, uint _required, uint _dailyLimit, string memory Jid, string memory tel) public {

    address[] memory _owners;
    address wallet = create(_owners,_required, _dailyLimit);

    address prime_owner = _owners[0];
    registry.Register(prime_owner,wallet,Jid,tel);
}
*/



}