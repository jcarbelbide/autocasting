package com.autocasting;

import net.runelite.api.ChatMessageType;
import net.runelite.client.Notifier;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AutocastingNotifications
{
	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private Notifier notifier;

	@Inject
	private AutocastingConfig config;

	@Inject
	private AutocastingState state;

	public void notifyStatDrain()
	{
		String autocastName = state.getCurrentAutocastSpell().getName();
		String message = String.format(AutocastingConstants.STAT_DRAIN_FORMAT, autocastName);
		sendNotification(message, config.notifyOnStatDrain());
	}

	public void handleCastsUpdated(int previous, int current)
	{
		if (current == 0)
		{
			if (previous != 0)
			{
				notifyNoCasts();
			}
		}
		else
		{
			int threshold = config.lowCastNotificationThreshold();
			if (previous > threshold && current <= threshold)
			{
				notifyLowCasts(current);
			}
		}
	}

	private void notifyNoCasts()
	{
		String autocastName = state.getCurrentAutocastSpell().getName();
		String message = String.format(AutocastingConstants.NO_CASTS_FORMAT, autocastName);
		sendNotification(message, config.notifyOnNoCasts());
	}

	private void notifyLowCasts(int amount)
	{
		String autocastName = state.getCurrentAutocastSpell().getName();
		String message = String.format(AutocastingConstants.LOW_CASTS_FORMAT, amount, autocastName);
		sendNotification(message, config.notifyOnLowCasts());
	}

	private void sendNotification(String message, AutocastingConstants.ChatNotificationType notificationSetting)
	{
		boolean shouldSendMessage = state.isConsideredInCombat() || config.notifyOutOfCombat();
		if (!shouldSendMessage)
		{
			return;
		}

		switch (notificationSetting)
		{
			case GAME:
				final String chatMessage = new ChatMessageBuilder()
					.append(ChatColorType.HIGHLIGHT)
					.append(message)
					.build();
				chatMessageManager.queue(QueuedMessage.builder()
					.type(ChatMessageType.CONSOLE)
					.runeLiteFormattedMessage(chatMessage)
					.build()
				);
				break;
			case BOTH:
				notifier.notify(message);
				break;
		}
	}
}
