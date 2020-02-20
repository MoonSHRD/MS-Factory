pragma solidity ^0.5.11;

import './zeppeline/crowdsale/Crowdsale.sol';

contract TicketSale is Crowdsale {



constructor(uint256 rate, address payable wallet, IERC20 token) Crowdsale(rate,wallet,token) public {}


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
    uint256 tokens = super._getTokenAmount(weiAmount);
    IERC20 ticket_token = super.token();
   // super._token.approve(_wallet,tokens);
   ticket_token.approve(_wallet,tokens);
}

// fallback
function() external payable {
        buyTicket(_msgSender());
    }


/*
    TODO: add redeem ticket
*/
function redeemTicket(address visitor) public {
    IERC20 ticket_token = super.token();
    ticket_token.transferFrom(visitor,_wallet,1);
}


}