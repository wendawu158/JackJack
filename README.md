# JackJack
**by Wenda Wu, Copyright 2025. All Rights Reserved**

# Key Skills Learned
This was my first big project using Java. By working on this, I learned:
* Probability analysis using Java
* Defining custom Java classes and methods
* ArrayList and HashMap manipulation, including 2D-ArrayLists
* Debugging with Intellij

# What is Blackjack?
Blackjack is a card game where you, the player, play against the dealer.

## Game Start

The game begins with you putting down your "stake".

The dealer then deals two cards to you, and two cards to themself. One of the dealer cards is dealt face down, all others are dealt face up.

A hand's total is calculated by adding up the values on the card. Face cards count as ten.

Aces count as 11 if the other cards add up to 10 or less, and 1 otherwise. If a hand has an Ace that counts as an 11, it is a "soft" hand.

The goal is to have a higher hand total than the dealer, while having your hand total being 21 or lower.

## Your Turn

On your turn, you may either "hit", "stand", "double", "split", or "surrender"[^1].

If you hit, the dealer gives you another card, and that card's value is added to your hand's total.

If you stand, your turn is over, and it becomes the next player's turn.

If you double, you must double your stake, you are dealt one more card, and your turn is over.

If you have a pair of cards with the same number/letter, you may split your hand into two hands, and you must place an additional stake of the same value as your original stake on the second hand.

You are then dealt another card on both hands, and you now play two turns, one for each hand.

These two hands win/lose independently of each other, and you play those hands as if you started off with two hands.

If you split aces, your turn ends after your second card on each hand is dealt

If you surrender, you may reclaim half your stake. The other half is lost.

If there are no more players to have a turn, it becomes the dealer's turn.

## Blackjack and Insurance

If you are dealt an ace and a ten/face card on your turn, you have blackjack, and win 3:2 of your original stake unless the dealer also has blackjack.

If the dealer is showing an ace as their face-up card, players may choose to take insurance at the beginning of their turn.

Insurance may be any amount up to half the stake

The dealer then checks their face down card. If it is a ten, all players lose their stake, unless they also have a blackjack, and players with insurance are paid out double their insurance.

If the dealer is showing a ten/face card as their face-up card, they check if they have an ace. If they do, all players lose their stake, unless they also have a blackjack.

If both a player and a dealer has a Blackjack, the player's stake is returned to them

## The dealer's turn

On the dealer's turn, they keep drawing cards until they reach a hand total of 18 or above.

Some casinos will also have the dealer stop drawing at 17, and others will have the dealer draw if it is a soft 17 (aka there is an ace counted as 11)

The dealer draws independently to what the player's hands are.

## Showdown

If you go over 21 or the dealer gets a higher total and stays under 21, you lose, and your stake is forfeited.

If you have the same hand total, you draw, and your stake is returned to you.

Otherwise, you win, and your stake is doubled.

[^1]:Surrendering is not allowed in some casinos

