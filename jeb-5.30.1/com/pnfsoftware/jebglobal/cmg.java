package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cmg extends cmd {
   public cmg(cmd var1, String var2, clv[] var3, BytesBlock var4) {
      super(var4, var2, var3, var1.RF(), var1.oW(), var1.getProcessorMode());
   }

   public cmg(cmd var1, String var2, clv[] var3) {
      super(var1.getCodeBlock(), var2, var3, var1.RF(), var1.oW(), var1.getProcessorMode());
   }

   public cmg(cmd var1, String var2) {
      super(var1.getCodeBlock(), var2, (clv[])var1.getOperands(), var1.RF(), var1.oW(), var1.getProcessorMode());
   }
}
