package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import java.util.ArrayDeque;
import java.util.Deque;

public class LA {
   private Deque q = new ArrayDeque();

   public Integer q() {
      return (Integer)this.q.peek();
   }

   public Integer RF() {
      return (Integer)this.q.removeFirst();
   }

   public boolean xK() {
      return !this.q.isEmpty();
   }

   public void q(int var1) {
      this.q.add(var1);
   }

   public void Dw() {
      this.q.clear();
   }

   public int Uv() {
      return this.q.size();
   }

   public void q(BytesBlock var1) throws ProcessorException {
      int var2 = Lf.q(var1.getBECode());
      int var3 = var2 % 2 == 0 ? var2 + 1 : var2 - 1;
      if (this.xK()) {
         throw new ProcessorException("Illegal IT instruction in IT Block");
      } else {
         Deque var4 = Lf.RF(var1.getBECode());
         this.q(var2);

         while (!var4.isEmpty()) {
            boolean var5 = (Boolean)var4.pop();
            this.q(var5 ? var2 : var3);
         }
      }
   }
}
