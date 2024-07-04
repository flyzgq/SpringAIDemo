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

package com.alibaba.cloud.ai.example.tongyi.service.impl.prompttemplate;

import java.util.Map;

import com.alibaba.cloud.ai.example.tongyi.service.AbstractTongYiServiceImpl;
import com.alibaba.cloud.ai.example.tongyi.service.TongYiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * The TongYiPromptTemplateServiceImpl shows how to use the StringTemplate Engine and the Spring AI PromptTemplate class.
 * In the resources\prompts directory is the file joke-prompt.
 *
 * @author fly
 * 致敬大师致敬未来的你
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TongYiPromptTemplateServiceImpl extends AbstractTongYiServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);

    private final ChatClient chatClient;

    @Value("classpath:/prompts/assistant-message.st")
    private Resource jokeResource;


    @Override
    public AssistantMessage genPromptTemplates(String adjective, String topic) {

        PromptTemplate promptTemplate = new PromptTemplate(jokeResource);

        Prompt prompt = promptTemplate.create(Map.of("adjective", adjective, "topic", topic));
        return chatClient.call(prompt).getResult().getOutput();
    }
}
