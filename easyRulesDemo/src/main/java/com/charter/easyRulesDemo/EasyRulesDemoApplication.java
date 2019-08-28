package com.charter.easyRulesDemo;

import com.charter.easyRulesDemo.rules.WeatherRuleAnnotation;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyRulesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRulesDemoApplication.class, args);
		triggerType3();
	}

	/**
	 * Mvel rule
	 */
	private static void triggerType3() {
		Rule weatherRule = new MVELRule().name("weather rule").description("If it rains take an umbrella")
				.when("rain == true && cloud == false").then("System.out.println(\"If it rains take a big umbrella\");");
		
	//	Rule weatherRule = new MVELRule().name("weather rule").description("If it rains take an umbrella")
		//		.when("rain == true && cloud == false").then("throw new Exception(\"Yellow Bucket Exception\");");

		Facts facts = new Facts();
		facts.put("rain", true);
		facts.put("cloud", false);

		Rules rules = new Rules();
		rules.register(weatherRule);

		// fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}

	/**
	 * Using programmatic way
	 */
	private static void triggerType2() {

		Rule weatherRule = new RuleBuilder().name("weather rule")
				.description("If it rains, take an umbrealla").when(facts -> facts.get("rain").equals(true))
				.then(facts -> System.out.println("Take an umbrella")).build();

		Facts facts = new Facts();
		facts.put("rain", true);

		Rules rules = new Rules();
		rules.register(weatherRule);

		// fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}

	/**
	 * Using annotations
	 */
	private static void triggerType1() {
		Facts facts = new Facts();
		facts.put("rain", true);

		Rules rules = new Rules();
		rules.register(new WeatherRuleAnnotation());

		// fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
}
