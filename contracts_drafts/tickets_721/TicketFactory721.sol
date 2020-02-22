pragma solidity ^0.5.11;

//import './zeppeline/token/ERC20/ERC20Mintable.sol';

import './Ticket721.sol';
import './TicketSale.sol';

contract TicketFactory721 {

//address Ticket;
//address TicketSale;

function createTicket(string memory name) public returns(address ticket_address) {

    // decimals = 0, cause you can't divide one ticket to parts
    uint8 dec = 0;
    // TODO: refactor to contract type
    ticket_address = address(new Ticket(name,dec));
    return ticket_address;
}

function createTicket721(string memory event_id) public returns (address ticket_address) {
   factory_address = address.this;
   ticket_address = address(new Ticket721(event_id,_factory_address));
   return ticket_address;
}

function createTicketSale(address payable orginizer, uint price, Ticket token, uint amount) public returns(address ticket_sale) {

    // calculate price
    uint256 cena = calculateRate(price);

    // TODO: refactor to contract type
    ticket_sale = address(new TicketSale(cena, orginizer, token));
    token.mint(ticket_sale,amount);


}

function calculateRate (uint256 price) internal returns (uint256 rate_p) {
    // rate = price * 1 eth
    rate_p = price * (1 ether);
    return rate_p;
}

}