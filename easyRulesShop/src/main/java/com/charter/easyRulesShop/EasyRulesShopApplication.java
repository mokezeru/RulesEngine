package com.charter.easyRulesShop;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.charter.easyRulesShop.model.Person;

@SpringBootApplication
public class EasyRulesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRulesShopApplication.class, args);
		triggerType1();
	}

	private static void triggerType1() {
		
		Rule ageRule = new MVELRule()
	        .name("age rule")
	        .description("Check if person's age is > 18 and marks the person as adult")
	        .priority(1)
	        .when("person.age > 18")
	        .then("person.setAdult(true);");
	
		Rule alcoholRule1 = new MVELRule()
				.name("alcohol rule1")
				.description("Children are not allowed to buy alcohol")
				.priority(2)
				.when("person.isAdult() == false")
				.then("System.out.println(\"Shop: Sorry, you are not allowed to buy alcohol\");");
					
		Rule alcoholRule2 = new MVELRule()
				.name("alcohol rule2")
				.description("Adults are allowed to buy alcohol")
				.priority(2)
				.when("person.isAdult() == true")
				.then("System.out.println(\"Shop: Congrats, you are old enough to buy alcohol\");");
		
		//Create facts
        Person tom = new Person("Tom", 14);
        Facts facts = new Facts();
        facts.put("person", tom);
        
        //Register rules
        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(alcoholRule1);
        rules.register(alcoholRule2);

        //Create a default rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();

        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
	}
	
}
