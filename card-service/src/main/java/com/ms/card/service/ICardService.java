package com.ms.card.service;

import com.ms.card.dto.CardDTO;

public interface ICardService {

    void createCard(String mobileNumber);

    CardDTO fetchCard(String mobileNumber);

    boolean updateCard(CardDTO cardsDto);

    boolean deleteCard(String mobileNumber);
}
