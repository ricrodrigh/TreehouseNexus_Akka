package com.hadron.treehousenexus.eao.config;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.ExtensionIdProvider;

public class AppSettings extends AbstractExtensionId<SettingsImpl> implements ExtensionIdProvider {

	public final static AppSettings SettingsProvider = new AppSettings();

	private AppSettings() {
	}

	public AppSettings lookup() {
		return AppSettings.SettingsProvider;
	}

	public SettingsImpl createExtension(ExtendedActorSystem system) {
		return new SettingsImpl(system.settings().config());
	}
}
