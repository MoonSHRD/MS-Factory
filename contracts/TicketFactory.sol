pragma solidity ^0.6.1;

//import './zeppeline/token/ERC20/ERC20Mintable.sol';

import './Ticket.sol';
import './TicketSale.sol';

contract TicketFactory {

//address Ticket;
//address TicketSale;

function createTicket(string memory name) public returns(address ticket_address) {

    // decimals = 0, cause you can't divide one ticket to parts
    uint8 dec = 0;
    ticket_address = new Ticket(name,dec);
    return ticket_address;
}

function createTicketSale(address orginizer, uint price, Ticket token, uint amount) public returns(address ticket_sale) {

    // calculate price
    uint cena = price;

    ticket_sale = new TicketSale(cena, orginizer, token);
    token.mint(ticket_sale,amount);


}



}