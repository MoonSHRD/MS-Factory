pragma solidity ^0.6.1;

import './zeppeline/crowdsale/Crowdsale.sol';

contract TicketSale is Crowdsale {


/** 
*
* @dev overload buy token function to give allowance to reedem tickets 
*
*
*/
function buyTicket(address buyer) public payable {
    super.buyTokens(_msgSender());
    // @TODO add allowance request to orginizer
    uint256 weiAmount = msg.value;
    // calculate token amount to be created
    uint256 tokens = super._getTokenAmount(weiAmount);
    super._token.approve(_wallet,tokens);
}

fallback() external payable {
        buyTicket(_msgSender());
    }


/*
    TODO: add redeem ticket
*/


 

}