package com.pnfsoftware.jeb.client.api;

public interface IOperable {
   boolean verifyOperation(OperationRequest var1);

   boolean doOperation(OperationRequest var1);
}
