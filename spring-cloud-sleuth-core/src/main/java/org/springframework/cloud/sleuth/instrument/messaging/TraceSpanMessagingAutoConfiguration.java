/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.instrument.messaging;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.SpanExtractor;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * AutoConfiguration containing Span extractor and injector for messaging. Will be reused
 * by Messaging and WebSockets
 *
 * @author Marcin Grzejszczak
 *
 * @since 1.0.0
 */
@Configuration
@ConditionalOnBean({ TraceKeys.class, Random.class })
public class TraceSpanMessagingAutoConfiguration {

	@Bean
	public SpanExtractor<Message<?>> messagingSpanExtractor(Random random) {
		return new MessagingSpanExtractor(random);
	}

	@Bean
	public SpanInjector<MessageBuilder<?>> messagingSpanInjector(TraceKeys traceKeys) {
		return new MessagingSpanInjector(traceKeys);
	}
}
