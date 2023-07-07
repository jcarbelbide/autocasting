package com.autocasting;

import com.google.inject.Inject;
import java.util.Objects;
import javax.inject.Singleton;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.*;
import net.runelite.client.ui.overlay.components.ComponentOrientation;

import java.awt.*;
import java.awt.image.BufferedImage;

@Singleton
class AutocastingOverlay extends OverlayPanel
{
	private int counter = 0;
	private final int GAP_SIZE = 4;

	private final AutocastingPlugin plugin;
	private final AutocastingState state;
	private final AutocastingConfig config;

	@Inject
	AutocastingOverlay(AutocastingPlugin plugin, AutocastingConfig config, AutocastingState state)
	{
		super(plugin);
		this.plugin = plugin;
		this.config = config;
		this.state = state;
		super.setDynamicFont(true);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!state.isEquippedWeaponMagic())
		{
			return null;
		}
		if (!config.showOverlay())
		{
			return null;
		}
		if (!config.showOverlayOutsideCombat() && !state.isConsideredInCombat())
		{
			return null;
		}

		int casts = state.getCastsRemaining();
		boolean displayCasts = config.showCastsRemaining()
			&& casts <= config.displayCastLimit()
			&& state.hasActiveAutocast();

		String overrideText = getOverrideText(casts);

		TitleComponent textComponent = null;
		String textPart = "";
		if (config.showSpellName() || displayCasts || overrideText != null)
		{
			if (config.showSpellName() && displayCasts)
			{
				textPart = String.format("%s (%s)", getCurrentSpellName(), casts);
			}
			else
			{  // Exactly 1 of the fields needs to be shown
				textPart = displayCasts ? Integer.toString(casts) : getCurrentSpellName();
			}

			if (overrideText != null)
			{
				textPart = overrideText;
			}

			textComponent = TitleComponent.builder()
				.text(textPart)
				.build();
		}

		ImageComponent imageComponent = null;
		if (config.showSpellIcon())
		{
			imageComponent = new ImageComponent(getCurrentSpellImage());
		}

		if (textComponent == null && imageComponent == null)
		{
			return null;
		}

		LayoutableRenderableEntity component;
		if (textComponent != null && imageComponent != null)
		{
			component = SplitComponent.builder()
				.first(textComponent)
				.second(imageComponent)
				.orientation(ComponentOrientation.HORIZONTAL)
				.gap(new Point(GAP_SIZE, 0))
				.build();
		}
		else
		{ // Exactly 1 is nonnull
			component = (textComponent != null) ? textComponent : imageComponent;
		}
		panelComponent.getChildren().add(component);
		panelComponent.setPreferredSize(new Dimension(
			graphics.getFontMetrics().stringWidth(textPart) + 10,
			0
		));

		configureBackground(casts);
		return super.render(graphics);
	}

	private String getOverrideText(int casts)
	{
		if (!config.enableOverrideText())
		{
			return null;
		}

		if (state.isMagicLevelTooLowForSpell())
		{
			String override = config.lowMagicLevelText().strip();
			if (!override.equals(""))
			{
				return override;
			}
		}

		if (casts == 0 && state.hasActiveAutocast())
		{
			String override = config.noRunesText().strip();
			if (!override.equals(""))
			{
				return override;
			}
		}
		return null;
	}

	private void configureBackground(int casts)
	{
		boolean shouldAlert = state.isMagicLevelTooLowForSpell() || (casts == 0 && state.hasActiveAutocast());
		if (config.overlayAlertStyle() == AutocastingConstants.OverlayNotificationType.FLASH)
		{
			flashBackground(shouldAlert);
		}
		else
		{
			solidBackground(shouldAlert);
		}
	}

	private void flashBackground(boolean alert)
	{
		if (alert && (++counter % config.getFlashPeriod() > config.getFlashPeriod() / 2))
		{
			panelComponent.setBackgroundColor(config.overlayAlertColor());
		}
		else
		{
			panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		}
	}

	private void solidBackground(boolean alert)
	{
		if (alert && config.overlayAlertStyle() == AutocastingConstants.OverlayNotificationType.SOLID)
		{
			panelComponent.setBackgroundColor(config.overlayAlertColor());
		}
		else
		{
			panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		}
	}

	private BufferedImage getCurrentSpellImage()
	{
		return state.getImage(state.getCurrentAutocastSpell().getSpriteID());
	}

	private String getCurrentSpellName()
	{
		return state.getCurrentAutocastSpell().getName();
	}

}