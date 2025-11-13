package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import java.util.ArrayDeque;
import java.util.Deque;

public class sC {
   private Deque pC = new ArrayDeque();

   public Integer pC() {
      return (Integer)this.pC.peek();
   }

   public Integer A() {
      return (Integer)this.pC.removeFirst();
   }

   public boolean kS() {
      return !this.pC.isEmpty();
   }

   public void pC(int var1) {
      this.pC.add(var1);
   }

   public void wS() {
      this.pC.clear();
   }

   public int UT() {
      return this.pC.size();
   }

   public void pC(BytesBlock var1) throws ProcessorException {
      int var2 = uV.pC(var1.getBECode());
      int var3 = var2 % 2 == 0 ? var2 + 1 : var2 - 1;
      if (this.kS()) {
         throw new ProcessorException("Illegal IT instruction in IT Block");
      } else {
         Deque var4 = uV.A(var1.getBECode());
         this.pC(var2);

         while (!var4.isEmpty()) {
            boolean var5 = (Boolean)var4.pop();
            this.pC(var5 ? var2 : var3);
         }
      }
   }
}
