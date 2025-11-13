package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import java.io.IOException;

public abstract class yi {
   protected oH Uv;
   protected sr oW;
   protected yw gO;
   protected UI nf;
   protected RegisterLayoutBridge gP;

   public yi(oH var1) {
      this.Uv = var1;
      this.oW = var1.oQ();
      this.gO = var1.Gf();
      this.nf = var1.cC();
   }

   public long xK(long var1) {
      return var1;
   }

   public abstract Ht q(kW var1);

   public abstract IInstruction RF(long var1) throws IOException;

   public IInstruction RF(Ht var1) throws IOException {
      long var2 = var1.getProgramCounter();
      return this.RF(var2);
   }

   public abstract boolean xK(long var1, int var3);

   public abstract byte[] q(long var1, int var3);

   public abstract boolean RF(long var1, int var3) throws IOException;

   public boolean Dw(long var1) throws IOException {
      return this.RF(var1, 0);
   }

   public boolean q() {
      return false;
   }

   public boolean q(boolean var1) throws IOException {
      return false;
   }

   public boolean q(Ht var1, boolean var2) throws IOException {
      return false;
   }

   public abstract IProcessor RF();
}
