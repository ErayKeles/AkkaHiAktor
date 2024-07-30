package org.example;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Actor1 extends AbstractBehavior<Actor1.Greet> {

    private static final Logger logger = LogManager.getLogger(Actor1.class);

    public static class Greet {
        public final String message;
        public Greet(String message) {
            this.message = message;
        }
    }

    public static Behavior<Greet> create() {
        return Behaviors.setup(Actor1::new);
    }

    private Actor1(ActorContext<Greet> context) {
        super(context);
    }

    @Override
    public Receive<Greet> createReceive() {
        return newReceiveBuilder()
                .onMessage(Greet.class, this::onGreet)
                .build();
    }

    private Behavior<Greet> onGreet(Greet message) {
        logger.info("Actor1 received message: {}", message.message);
        return this;
    }
}
