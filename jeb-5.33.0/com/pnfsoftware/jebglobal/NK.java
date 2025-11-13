package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import java.io.IOException;

public abstract class NK {
   protected aI UT;
   protected qa E;
   protected XE sY;
   protected OL ys;
   protected RegisterLayoutBridge ld;

   public NK(aI var1) {
      this.UT = var1;
      this.E = var1.eP();
      this.sY = var1.rl();
      this.ys = var1.Ek();
   }

   public abstract LD pC(Tl var1);

   public abstract IInstruction A(long var1) throws IOException;

   public IInstruction A(LD var1) throws IOException {
      long var2 = var1.getProgramCounter();
      return this.A(var2);
   }

   public abstract boolean kS(long var1, int var3);

   public abstract byte[] pC(long var1, int var3);

   public abstract boolean A(long var1, int var3) throws IOException;

   public boolean kS(long var1) throws IOException {
      return this.A(var1, 0);
   }

   public boolean pC() {
      return false;
   }

   public boolean pC(boolean var1) throws IOException {
      return false;
   }

   public boolean pC(LD var1, boolean var2) throws IOException {
      return false;
   }

   public abstract IProcessor A();
}
