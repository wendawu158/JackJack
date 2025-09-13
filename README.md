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

The dealer then deals two cards to you, and two cards to themself from the shoe[^1]. One of the dealer cards is dealt face down, all others are dealt face up.

A hand's total is calculated by adding up the values on the card. Face cards count as ten.

Aces count as 11 if the other cards add up to 10 or less, and 1 otherwise. If a hand has an Ace that counts as an 11, it is a "soft" hand.

The goal is to have a higher hand total than the dealer, while having your hand total being 21 or lower.

## Your Turn

On your turn, you may either "hit", "stand", "double", "split", or "surrender"[^2].

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

## Assumptions

We can always double, no matter what the cards on the table are.

Splitting is always allowed for pairs

Late surrender, i.e. surrender is given after dealer checks for blackjack.

Dealer hits on soft 17

1:1 payout for wins

3:2 payout for blackjack

Push for both blackjack

# Strategy

So, given this information, it should be pretty easy to create a strategy where we have an edge.
 
Why? Because we know exactly where each card is in the game. If we start at the beginning of a shoe and play either heads-up[^3] or single-player, we know exactly which cards are discarded, in play, or in the shoe.

Therefore, using this information, we can calculate the expected values for hitting, standing, doubling, splitting, or surrendering.

## Standing

The expected value of standing is very simple to calculate. Assuming the dealer does not already have blackjack (or ignoring blackjack as a possiblity entirely), we can calculate the probability of any run happening.

Consider, the dealer must:
* Draw to 17+, and draw if they have a soft 17
* Stand on 18 or over

There are three possible outcomes to this
1. The dealer loses because they go bust/have a worse hand than us
2. The dealer draws with us because they have an equally good hand to us
3. The dealer wins because they have a better hand than us.

Therefore, for any given situation, we know exactly what the expected value of standing is.

## Doubling

Given that when we double, we only get one more card, and then our turn is over, it is trivial to simply run the same algorithm for standing on each possible card that we could draw, and simply running the standing EV function for every possible hand we could get. Obviously, we would have to weigh the hands that we get from doubling, but it should still be trivial to figure out.

## Surrendering

The easiest one to figure out. Always returns -0.5 EV

## Hitting

This is a bit trickier. In order to figure out if hitting is "worth it", we need to take into account that the player may hit multiple times. 

This could be simplified by forcing the bot to hit in all positions where we could not go bust. However, it still leaves the difficult position of determining when it is worth it to risk busting.

If we listen to basic strategy, we should hit on any hand lower than a 17 whenever the dealer shows a 7 or higher, and stand otherwise. However, this may not always be the best move.

For example, consider a deck which has an extremely low count would favour potentially hitting on hands that are 17 or larger, and a deck with an extremely high count would favour not hitting on hands between 12 and 16, defending on how high the count is.

Therefore, how should we value the possible outcomes?

What we could do is calculate the branching probabilities of hitting. Every time we hit above 21, if we do not bust, we have improved our hand and increased our total. However, any hand that is below 17 is treated the same when it comes to the showdown between the player and the dealer.

In order to save on memory, we could also do the same thing that we do for calculating the EV of standing, by ignoring hands once we see that they have busted.

What we could do is run a similar algorithm, keeping track of all the hands, but this time we'll only stop if they bust.

[^1]:The thing holding the cards.
[^2]:Surrendering is not allowed in some casinos.
[^3]:All cards held by players being laid face-up. Most casinos play heads-up.

