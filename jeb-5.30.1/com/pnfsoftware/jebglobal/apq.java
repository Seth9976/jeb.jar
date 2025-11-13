package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class apq {
   IERoutineContext q;
   ICConstantFactory RF;
   List xK = new ArrayList();

   public apq(IERoutineContext var1, ICGlobalContext var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.RF = var2.getConstantFactory();
      }
   }

   public List q() {
      return this.xK;
   }

   public int RF() {
      CFG var1 = this.q.getCfg();

      for (AddressableInstruction var3 : var1.addressableInstructions()) {
         IEStatement var4 = (IEStatement)var3.getInstruction();
         EVisitResults var5 = new EVisitResults(1);
         var4.visitDepthPost(new apr(this, var3), null, var5);
      }

      return this.xK.size();
   }

   private boolean q(IEImm var1, IEGeneric var2, IVisitResults var3, Iterator var4) {
      if (var2 instanceof IECond) {
         if (((IECond)var2).getExpressionTrue() == var1 || ((IECond)var2).getExpressionFalse() == var1) {
            if (var4 == null) {
               var4 = var3.parentsIterator();
               var4.next();
            }

            if (var4.hasNext()) {
               return this.q(var1, (IEGeneric)var4.next(), var3, var4);
            }
         }

         return false;
      } else {
         return var2 instanceof IEAssign || var2 instanceof IECall || var2 instanceof IEReturn;
      }
   }

   public static class eo {
      int q;
      long RF;
      String xK;

      eo(int var1, long var2, String var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var4;
      }

      public int q() {
         return this.q;
      }

      public long RF() {
         return this.RF;
      }

      public String xK() {
         return this.xK;
      }

      @Override
      public String toString() {
         return Strings.ff("@%X - 0x%X=\"%s\"", this.q, this.RF, this.xK);
      }
   }
}
