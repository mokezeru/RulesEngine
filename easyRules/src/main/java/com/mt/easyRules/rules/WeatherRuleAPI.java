package com.mt.easyRules.rules;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

public class WeatherRuleAPI {

	Rule weatherRule = new RuleBuilder()
			.name("weather rule")
			.description("If it rains, take an umbrealla")
			.when(facts -> facts.get("rain").equals(true))
			.then(facts -> System.out.println("Take an umbrella"))
			.build();
}
