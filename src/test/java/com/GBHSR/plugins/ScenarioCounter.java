package com.GBHSR.plugins;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.util.HashSet;
import java.util.Set;

public class ScenarioCounter implements ConcurrentEventListener {
	private final Set<String> scenarioNames = new HashSet<>();

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		// Fires before any scenario starts
		publisher.registerHandlerFor(TestRunStarted.class, event -> {
			System.out.println("=== 🧮 Counting scenarios before run starts... ===");
		});

		// Each scenario discovered
		publisher.registerHandlerFor(TestCaseStarted.class, event -> {
			scenarioNames.add(event.getTestCase().getName());
		});

		// After all scenarios are loaded
		publisher.registerHandlerFor(TestRunFinished.class, event -> {
			System.out.println("=== ✅ Total scenarios in this run: " + scenarioNames.size() + " ===");
		});
	}

	public int getScenarioCount() {
		return scenarioNames.size();
	}
}
