package com.mt.easyRules;

import java.util.HashMap;
import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.support.ActivationRuleGroup;
import org.jeasy.rules.support.ConditionalRuleGroup;
import org.jeasy.rules.support.UnitRuleGroup;
import org.mvel2.MVEL;
import org.mvel2.Unit;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mt.easyRules.model.CustomerRequest;
import com.mt.easyRules.rules.WeatherRuleAnnotation;

/**
 * Easy Rules API!
 *
 */
@SpringBootApplication
public class EasyRulesApp {
	public static void main(String[] args) {
		SpringApplication.run(EasyRulesApp.class, args);

//    	triggerType1();

//		triggerType2();

//		triggerType3();
		
//		triggerType4();
		
		triggerUnitGroup();

//		triggerActivationGroup();

//		triggerConditionalGroup();
	}
	
	private static void triggerUnitGroup() {
		Rule yellowBucketRule = new MVELRule()
				.name("Yellow Bucket rule")
				.description("Yellow Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 150 && request.fraudScore < 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Yellow range\");");
		Rule greenBucketRule = new MVELRule()
				.name("Green Bucket rule")
				.description("Green Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Green range\");");
		Rule redBucketRule = new MVELRule()
				.name("Red Bucket rule")
				.description("Red Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore < 150)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Red range\");");
		
		//Create a composite rule from two primitive rules
		UnitRuleGroup myUnitRuleGroup = new UnitRuleGroup("myUnitRuleGroup", "unit of my yellow, green and red rules");
		myUnitRuleGroup.addRule(yellowBucketRule);
		myUnitRuleGroup.addRule(greenBucketRule);
		myUnitRuleGroup.addRule(redBucketRule);
		
		//Create facts
		CustomerRequest custRequest = new CustomerRequest("D2C", 450, 1200, 1);
		Facts facts = new Facts();
		facts.put("request", custRequest);

		//Register the composite rule as a regular rule
		Rules rules = new Rules();
		rules.register(myUnitRuleGroup);

		//Fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
	private static void triggerActivationGroup() {
		Rule yellowBucketRule = new MVELRule()
				.name("Yellow Bucket rule")
				.description("Yellow Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 150 && request.fraudScore < 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Yellow range\");");
		Rule greenBucketRule = new MVELRule()
				.name("Green Bucket rule")
				.description("Green Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Green range\");");
		Rule redBucketRule = new MVELRule()
				.name("Red Bucket rule")
				.description("Red Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore < 150)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Red range\");");
		
		//Create a composite rule from three primitive rules
		ActivationRuleGroup myActivationRuleGroup = new ActivationRuleGroup("myActivationRuleGroup", "activation of my yellow, green and red rules");
		myActivationRuleGroup.addRule(yellowBucketRule);
		myActivationRuleGroup.addRule(greenBucketRule);
		myActivationRuleGroup.addRule(redBucketRule);
		
		//Create facts
		CustomerRequest custRequest = new CustomerRequest("D2C", 450, 1200, 1);
		Facts facts = new Facts();
		facts.put("request", custRequest);
		
		//Register the composite rule as a regular rule
		Rules rules = new Rules();
		rules.register(myActivationRuleGroup);

		//Fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
	private static void triggerConditionalGroup() {
		Rule yellowBucketRule = new MVELRule()
				.name("Yellow Bucket rule")
				.description("Yellow Rule")
				.priority(2)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 150 && request.fraudScore < 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Yellow range\");");
		Rule greenBucketRule = new MVELRule()
				.name("Green Bucket rule")
				.description("Green Rule")
				.priority(1)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore > 1000)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Green range\");");
		Rule redBucketRule = new MVELRule()
				.name("Red Bucket rule")
				.description("Red Rule")
				.priority(2)
				.when("(request.channel == 'D2C' "
						+ "&& (request.eipAmount > 1 && request.eipAmount < 800) "
						+ "&& (request.fraudScore < 150)) || (request.invalidPayments > 0)")
				.then("System.out.println(\"It falls in the Red range\");");
		
		//Create a composite rule from three primitive rules
		ConditionalRuleGroup myConditionalRuleGroup = new ConditionalRuleGroup("myConditionalRuleGroup", "condition of my yellow, green and red rules");
		myConditionalRuleGroup.addRule(yellowBucketRule);
		myConditionalRuleGroup.addRule(greenBucketRule);
		myConditionalRuleGroup.addRule(redBucketRule);
		
		//Create facts
		CustomerRequest custRequest = new CustomerRequest("D2C", 450, 1200, 1);
		Facts facts = new Facts();
		facts.put("request", custRequest);
				
		
		//Register the composite rule as a regular rule
		Rules rules = new Rules();
		rules.register(myConditionalRuleGroup);

		//Fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
	private static void triggerType4() {
		Map<String, Object> inputMap = new HashMap<String, Object>();
		
		String expression = "(channel == 'D2C') "
				+ "&& (eipAmount > 1 && eipAmount < 800)"
				+ "&& ((fraudScore > 150 && fraudScore < 1000) || (invalidPayments > 0))";
		
		VariableResolverFactory factory = new MapVariableResolverFactory(inputMap);

		inputMap.put("channel", "D2C");
		inputMap.put("eipAmount", 450);
		inputMap.put("fraudScore", 100);
		inputMap.put("invalidPayments", 0);

		Boolean result = (Boolean)MVEL.eval(expression, factory);
		
		if(result) {
			System.out.println("Rule passed and result [ " + result + " ]");
		}
		else {
			System.out.println("Rule Failed and result [ " + result + " ]");
		}
	}

	/**
	 * Mvel rule
	 */
	private static void triggerType3() {
//		Rule weatherRule = new MVELRule().name("weather rule").description("If it rains take an umbrella")
//				.when("rain == true && cloud == false").then("System.out.println(\"If it rains take a big umbrella\");");
		
		/*
		  Rule yellowBucketRule = new MVELRule() .name("Yellow Bucket rule")
		 		.description("throws Yellow Bucket Exception, if rule fails")
		 		.when("channel == 'D2C' " 
		 				+ "&& (eipAmount > 1 && eipAmount < 800) " 
		 				+ "&& ((fraudScore > 150 && fraudScore < 1000) || (invalidPayments > 0))")
		 		.then("System.out.println(\"Order falls in Yellow Bucket !!\");");
		 */
		int num = 0;
		String myExpression = "request.eipAmount > 1 && request.eipAmount < 800";
		Rule yellowBucketRule = new MVELRule()
				.name("Yellow Bucket rule")
				.description("Yellow Rule")
				.priority(1)
				.when("request.channel == 'D2C' "
						+ "&& "+ myExpression +" "
						+ "&& ((request.fraudScore > 150 && request.fraudScore < 1000) && (request.invalidPayments > 0))")
				.then("request.setRisk(\"yellow\");");
		Rule greenBucketRule = new MVELRule()
				.name("Green Bucket rule")
				.description("Green Rule")
				.priority(1)
				.when("request.channel == 'D2C' "
						+ "&& "+ myExpression +" "
						+ "&& ((request.fraudScore > 1000) && (request.invalidPayments < 0))")
				.then("request.setRisk(\"green\");");
		Rule redBucketRule = new MVELRule()
				.name("Red Bucket rule")
				.description("Red Rule")
				.priority(1)
				.when("request.channel == 'D2C' "
						+ "&& "+ myExpression +" "
						+ "&& ((request.fraudScore < 150) && (request.invalidPayments > "+num+"))")
				.then("request.setRisk(\"red\");");
			//	.then("System.out.println(\"Red: you have no bonus\");")
			//	.then("System.out.println(\"Calculate Bonus\");");
		
		Rule bonusYellowRule = new MVELRule()
				.name("Bonus Yellow rule")
				.description("Bonus Yellow Rule")
				.priority(2)
				.when("request.getRisk() == 'yellow' ")
				.then("System.out.println(\"Yellow: Congrats, you got 15% bonus\");");
		Rule bonusGreenRule = new MVELRule()
				.name("Bonus Green rule")
				.description("Bonus Green Rule")
				.priority(2)
				.when("request.getRisk() == 'green' ")
				.then("System.out.println(\"Green: Congrats, you got 25% bonus\");");
		Rule bonusRedRule = new MVELRule()
				.name("Bonus Red rule")
				.description("Bonus Red Rule")
				.priority(2)
				.when("request.getRisk() == 'red' ")
				.then("System.out.println(\"Red: you have no bonus\");"); 

		//Create facts
		//CustomerRequest custRequest = new CustomerRequest("D2C", 450, 1200, -1);
		//CustomerRequest custRequest = new CustomerRequest("D2C", 450, 200, 1);
		CustomerRequest custRequest = new CustomerRequest("D2C", 450, 100, 1);
		Facts facts = new Facts();
		facts.put("request", custRequest);
		//facts.put("channel", "D2C");
		//facts.put("eipAmount", 450);
		//facts.put("fraudScore", 100);
		//facts.put("invalidPayments", 1);
		
		
		//Register rules
		Rules rules = new Rules();
		rules.register(yellowBucketRule);
		rules.register(greenBucketRule);
		rules.register(redBucketRule);
		rules.register(bonusYellowRule);
		rules.register(bonusGreenRule);
		rules.register(bonusRedRule);

		//Fire rules on known facts
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
