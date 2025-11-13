package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cft extends cfq {
   public cft(cfq var1, String var2) {
      super(var1.getCodeBlock(), var2, (cfj[])var1.getOperands(), var1.A(), var1.E(), var1.getProcessorMode());
   }
}
