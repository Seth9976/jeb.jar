package com.pnfsoftware.jeb.client.mcp.llm;

import java.util.List;

public interface ILlmConversation {
   void close();

   LlmConversationBuilder.Information getInformation();

   List listModels() throws Exception;

   List send(String var1) throws Exception;

   List send(List var1) throws Exception;

   List getMessages();

   void clearMessages();
}
