pragma solidity ^0.5.11;

//import './zeppeline/token/ERC20/ERC20Mintable.sol';

import './Ticket.sol';
import './TicketSale.sol';

contract TicketFactory {

//address Ticket;
//address TicketSale;

function createTicket(string memory name) public returns(address ticket_address) {

    // decimals = 0, cause you can't divide one ticket to parts
    uint8 dec = 0;
    // TODO: refactor to contract type
    ticket_address = address(new Ticket(name,dec));
    return ticket_address;
}

function createTicketSale(address payable orginizer, uint price, Ticket token, uint amount) public returns(address ticket_sale) {

    // calculate price
    uint256 cena = price;

    // TODO: refactor to contract type
    ticket_sale = address(new TicketSale(cena, orginizer, token));
    token.mint(ticket_sale,amount);


}



}