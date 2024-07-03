/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.example.tongyi.service.impl.stuff;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.cloud.ai.example.tongyi.models.Completion;
import com.alibaba.cloud.ai.example.tongyi.service.AbstractTongYiServiceImpl;
import com.alibaba.cloud.ai.example.tongyi.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Train the model using pre-found data to enhance the AI model to achieve the desired results.
 *
 * @author fly
 * 致敬大师致敬未来的你
 */

@Slf4j
@Service
public class TongYiStuffServiceImpl extends AbstractTongYiServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);

	private final ChatClient chatClient;

	public TongYiStuffServiceImpl(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Value("classpath:/docs/wikipedia-curling.md")
	private Resource docsToStuffResource;

	@Value("classpath:/prompts/qa-prompt.st")
	private Resource qaPromptResource;

	// TongYi model: Range of input length should be [1, 6000]
	@Override
	public Completion stuffCompletion(String message, boolean stuffit) {

		PromptTemplate promptTemplate = new PromptTemplate(qaPromptResource);
		Map<String, Object> map = new HashMap<>();
		map.put("question", message);

		if (stuffit) {
			map.put("context", docsToStuffResource);
		}
		else {
			map.put("context", "");
		}

		Prompt prompt = promptTemplate.create(map);
		Generation generation = chatClient.call(prompt).getResult();
		return new Completion(generation.getOutput().getContent());
	}
}
