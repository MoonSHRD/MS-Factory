pragma solidity ^0.5.11;

import './TokenSale721.sol';
import './Ticket721.sol';

contract TicketSale721 is TokenSale721 {



constructor(uint256 rate, address payable wallet, Ticket721 token) TokenSale721(rate,wallet,token) public {}


/**
*
* @dev overload buy token function to give allowance to reedem tickets 
*
*
*/
function buyTicket(address buyer) public payable {
    super.buyTokens(buyer);
    Ticket721 ticket_token = super.token();
   // super._token.approve(_wallet,tokens);

    // chek this one more time
    ticket_token.setApprovalForAllFactory(buyer);

}

// fallback
function() external payable {
        buyTicket(_msgSender());
    }


/*
 *   TODO: add redeem ticket
*/
function redeemTicket(address visitor) public {
    Ticket721 ticket_token = super.token();
    ticket_token._transferFromTicket(visitor,_wallet,1);
}


}