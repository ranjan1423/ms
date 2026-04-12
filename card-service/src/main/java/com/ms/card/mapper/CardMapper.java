package com.ms.card.mapper;

import com.ms.card.dto.CardDTO;
import com.ms.card.entity.Card;

public class CardMapper {
    public static CardDTO mapToCardDto(Card cards, CardDTO cardDTO) {
        cardDTO.setCardNumber(cards.getCardNumber());
        cardDTO.setCardType(cards.getCardType());
        cardDTO.setMobileNumber(cards.getMobileNumber());
        cardDTO.setTotalLimit(cards.getTotalLimit());
        cardDTO.setAvailableAmount(cards.getAvailableAmount());
        cardDTO.setAmountUsed(cards.getAmountUsed());
        return cardDTO;
    }

    public static Card mapToCard(CardDTO CardDTO, Card card) {
        card.setCardNumber(CardDTO.getCardNumber());
        card.setCardType(CardDTO.getCardType());
        card.setMobileNumber(CardDTO.getMobileNumber());
        card.setTotalLimit(CardDTO.getTotalLimit());
        card.setAvailableAmount(CardDTO.getAvailableAmount());
        card.setAmountUsed(CardDTO.getAmountUsed());
        return card;
    }
}
