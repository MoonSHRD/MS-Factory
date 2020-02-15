
pragma solidity ^0.5.11;

import './zeppeline/ownership/Ownable.sol';

contract Deposit is Ownable {

/*
Events
*/

// this event invoke when someone want to cashout funds to plastic card
// this event should been catched by 'cashier' service and procced tx out at Fiat payment processer
// amount is in Wei, so cashier probably need to convert it
event cashOutRequestEvent(string indexed destination, address indexed user, uint amount);

event cashInRequestEvent(string indexed from, address indexed user, uint amount, string indexed uuid);


/*
Constants
*/


// fiat uuid = request
mapping (string => IRequest) public InRequest;

//mapping (string => bool) public Executed

struct IRequest {

    string fiat_uuid;
    uint amount;
    address payable user;
    string fiat_address;
    address submited_by;
    bool executed;
    // tx.data -- can be added for getting fiat tx.data and check it. 
    // but it's not secure..
}


// cash out

function cashOutRequest(string memory destination, address user) public payable {
    uint amount = msg.value;
    emit cashOutRequestEvent(destination, user, amount);

}

// cash in
// cashier submit request for cash in while getting events from Fiat payment processor
function cashInRequest(string memory from, address payable user, string memory uuid, uint amount) public onlyOwner {

    emit cashInRequestEvent(from,user,amount,uuid);

    IRequest memory irq;
    irq.fiat_uuid = uuid;
    irq.user = user;
    irq.fiat_address = from;
    irq.submited_by = msg.sender;
    irq.amount = amount;
    irq.executed = false;

    InRequest[uuid] = irq;


}

// TODO Validator key can be added here to prove (submit) transaction from FIAT processor. 
// It 's not neccerily, as we could just use the blockchain validation itself, but do it in more transcendent way
// FIXME : change onlyOwner to onlyValidator
function cashInSubmit(string memory uuid) public onlyOwner {
    IRequest memory irq;
    irq = InRequest[uuid];
    require(irq.executed = false, "transaction is already executed! (reentrancy guard)");
    address payable _user = irq.user;
    uint amount = irq.amount;
    // Do some conversion for amount (FIXME)
    // ...

    // FIXME : use internal proceedTransaction instead
    _user.transfer(amount);
    irq.executed = true;
    InRequest[uuid] = irq;


}

function proceedTransaction(IRequest memory tx) internal {
    address payable _user = tx.user;
    uint amount = tx.amount;
    _user.transfer(amount);
}

// fallback
function() external payable {
  //  cashOutRequest()
  revert();
}


}