pragma solidity ^0.5.11;

import './TokenSale721.sol';
import './Ticket721.sol';

contract TicketSale721 is TokenSale721 {

using Counters for Counters.Counter;

Counters.Counter scanned_tickets;

constructor(uint256 rate, address payable wallet, Ticket721 token, uint sale_limit, string memory jid) TokenSale721(rate,wallet,token, sale_limit, jid) public {}


/**
*
*@dev overload buy token function to give allowance to reedem tickets
*
*
*/
function buyTicket(address buyer) public payable {
    super.buyTokens(buyer);
    //Ticket721 ticket_token = super.token();
   // super._token.approve(_wallet,tokens);

    // chek this one more time
    //ticket_token.setApprovalForEvent(buyer,);

}

// fallback
function() external payable {
        buyTicket(_msgSender());
    }

// deprecated
/*
function redeemTicket(address visitor, uint256 token_id) public {
    Ticket721 ticket_token = super.token();
    uint256 event_id = super.event_id();
    ticket_token.redeemTicket(visitor, token_id, event_id);
}
*/

function redeemTicket(address visitor, uint256 token_id) public {
    Ticket721 ticket_token = super.token();
    uint256 event_id = super.event_id();
    ticket_token.redeemTicket(visitor, token_id, event_id);
    scanned_tickets.increment();
}

function getScannedTicketsCount() public view  returns(uint)  {
    uint st = scanned_tickets.current();
    return st;
}


}