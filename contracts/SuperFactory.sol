pragma solidity ^0.6.1;

import './gnosis/MultiSigWalletFactory.sol';
import './gnosis/MultiSigWalletWithDailyLimitFactory.sol';
import './KNS.sol';


contract SuperFactory is MultiSigWalletWithDailyLimitFactory {

address registry;

constructor(address registry_deployed) {
    registry = registry_deployed;
}

function createWallet(address[] _owners, uint _required, uint _dailyLimit, string Jid, string tel) public {

    address wallet = create(_owners,_required, _dailyLimit);

    address prime_owner = _owners[0];
    registry.Register(prime_owner,wallet,Jid,tel);
}

}