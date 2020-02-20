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
    // @TODO add allowance request to orginizer
    uint256 weiAmount = msg.value;
    // calculate token amount to be created
    uint256 tokenAmount = super._getTokenAmount(weiAmount);
    Ticket721 ticket_token = super.token();
   // super._token.approve(_wallet,tokens);

    ticket_token.setApprovalForAllFactory(buyer);

/*
    Deprecate that, cause we approve factory for every ticket
    FIXME: approve orginizer of event throught factory?


    for (uint256 i = 0; i < tokenAmount; i++ ){
            uint256 ticket_token_id = ticket_token.tokenOfOwnerByIndex(buyer,i);
            ticket_token.approve(_wallet,ticket_token_id);
        }
*/
  // ticket_token.approve(_wallet,tokens);
}

// fallback
function() external payable {
        buyTicket(_msgSender());
    }


/*
    TODO: add redeem ticket
*/
function redeemTicket(address visitor) public {
    Ticket721 ticket_token = super.token();
    ticket_token._transferFromTicket(visitor,_wallet,1);
}


}