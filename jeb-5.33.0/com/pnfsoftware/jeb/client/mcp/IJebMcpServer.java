package com.pnfsoftware.jeb.client.mcp;

public interface IJebMcpServer {
   String getName();

   String getVersion();

   int getPort();

   String getEndpoint();

   void serve();
}
