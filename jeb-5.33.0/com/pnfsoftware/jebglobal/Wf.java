package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wf extends kx {
   private static final ILogger vP = GlobalLog.getLogger(Wf.class);
   INativeCodeAnalyzerExtension ys;
   long ld;
   long gp;
   Set oT = new HashSet();
   Set fI = new HashSet();
   int WR = 20;
   int NS = this.WR + 1;

   public Wf(a var1) {
      super(var1);
      this.ys = var1.UT();
      this.pC = "code-linear";
      this.WR = var1.wS().A(false);
      this.NS = this.WR + 1;
      MemoryRanges var2 = var1.pC();
      if (var2 != null && var2.count() != 0) {
         this.E = var2.asList();
      }
   }

   @Override
   protected List A(long var1, long var3, boolean var5) {
      MemoryRanges var6 = this.A.pC();
      if (var6 != null && var6.contains(var1)) {
         Long var7 = (Long)this.ys.getPossiblePaddingSize(var1, var3).getResult();
         ICodePointer var8 = (ICodePointer)this.ys.getPrologueLooking(var1, var3).getResult();
         if (var7 == null || var7 == 0L || var8 != null && var8.getAddress() == var1) {
            if (!(Boolean)this.ys.verifyGapRoutineCandidate(var1).getResult()) {
               return null;
            } else {
               Object var9;
               if (var8 != null && var8.getMode() != 0) {
                  var9 = new ArrayList();
                  var9.add(new CodePointer(var8.getAddress(), var8.getMode()));
               } else {
                  var9 = (List)this.ys.getProbableEntryPoints(var1, var3).getResult();
                  if (var9.size() == 0) {
                     var9.add(new CodePointer(var1, 0));
                  }
               }

               if (var5) {
                  this.ld = var1;
               }

               return (List)var9;
            }
         } else {
            if (var5) {
               this.A(this.UT() + var7);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   @Override
   public void pC(Vr.Av var1, Object var2) {
      if (var1 == Vr.Av.A) {
         Long var3 = (Long)var2;
         if (var3 == this.ld) {
            if (this.oT.add(var3)) {
               this.gp = this.UT();
               this.A(this.E());
               this.WR--;
            }

            if (this.WR == 0) {
               this.A.wS().pC(true);
            }
         }
      } else if (var1 == Vr.Av.pC) {
         Long var4 = (Long)var2;
         if (var4 == this.ld && this.fI.add(var4)) {
            if (this.oT.remove(var4)) {
               this.A(this.gp);
            }

            this.WR = this.WR == this.NS - 1 ? this.WR : this.WR + 1;
         }
      }
   }
}
