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

package com.alibaba.cloud.ai.example.tongyi.service;

import java.util.Map;

import com.alibaba.cloud.ai.example.tongyi.models.ActorsFilms;
import com.alibaba.cloud.ai.example.tongyi.models.Completion;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.image.ImageResponse;

/**
 * @author fly
 * 致敬大师致敬未来的你
 */

public interface TongYiService {

	/**
	 * Hello World example.
	 *
	 * @param message conversation content question.
	 * @return AI answer.
	 */
	String completion(String message);

	/**
	 * Stream call.
	 *
	 * @param message conversation content question.
	 * @return AI answer.
	 */
	Map<String, String> streamCompletion(String message);

	/**
	 * Output parse object.
	 *
	 * @param actor actor name.
	 * @return Object.
	 */
	ActorsFilms genOutputParse(String actor);

	/**
	 * Prompt template.
	 *
	 * @param adjective params1.
	 * @param topic params2.
	 * @return AI answer.
	 */
	AssistantMessage genPromptTemplates(String adjective, String topic);

	/**
	 * AI role example.
	 *
	 * @param message question content,
	 * @param name params1.
	 * @param voice params2.
	 * @return AI answer.
	 */
	AssistantMessage genRole(String message, String name, String voice);

	/**
	 * Stuff and answer.
	 *
	 * @param message question.
	 * @param stuffit is stuff.
	 * @return Completion object.
	 */
	Completion stuffCompletion(String message, boolean stuffit);

	/**
	 * Gen images.
	 * @param imgPrompt prompt info.
	 * @return {@link ImageResponse}
	 */
	ImageResponse genImg(String imgPrompt);

	/**
	 * Gen audio.
	 * @param text prompt info.
	 * @return ByteBuffer object.
	 */
	String genAudio(String text);

}
