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

public class i extends PA {
   private static final ILogger LK = GlobalLog.getLogger(i.class);
   INativeCodeAnalyzerExtension nf;
   long gP;
   long za;
   Set lm = new HashSet();
   Set zz = new HashSet();
   int JY = 20;
   int HF = this.JY + 1;

   public i(aae var1) {
      super(var1);
      this.nf = var1.Uv();
      this.q = "code-linear";
      this.JY = var1.Dw().xK(false);
      this.HF = this.JY + 1;
      MemoryRanges var2 = var1.q();
      if (var2 != null && var2.count() != 0) {
         this.oW = var2.asList();
      }
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      MemoryRanges var6 = this.RF.q();
      if (var6 != null && var6.contains(var1)) {
         Long var7 = (Long)this.nf.getPossiblePaddingSize(var1, var3).getResult();
         ICodePointer var8 = (ICodePointer)this.nf.getPrologueLooking(var1, var3).getResult();
         if (var7 == null || var7 == 0L || var8 != null && var8.getAddress() == var1) {
            if (!(Boolean)this.nf.verifyGapRoutineCandidate(var1).getResult()) {
               return null;
            } else {
               Object var9;
               if (var8 != null && var8.getMode() != 0) {
                  var9 = new ArrayList();
                  var9.add(new CodePointer(var8.getAddress(), var8.getMode()));
               } else {
                  var9 = (List)this.nf.getProbableEntryPoints(var1, var3).getResult();
                  if (var9.size() == 0) {
                     var9.add(new CodePointer(var1, 0));
                  }
               }

               if (var5) {
                  this.gP = var1;
               }

               return (List)var9;
            }
         } else {
            if (var5) {
               this.RF(this.Uv() + var7);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   @Override
   public void q(aaw.eo var1, Object var2) {
      if (var1 == aaw.eo.RF) {
         Long var3 = (Long)var2;
         if (var3 == this.gP) {
            if (this.lm.add(var3)) {
               this.za = this.Uv();
               this.RF(this.oW());
               this.JY--;
            }

            if (this.JY == 0) {
               this.RF.Dw().RF(true);
            }
         }
      } else if (var1 == aaw.eo.q) {
         Long var4 = (Long)var2;
         if (var4 == this.gP && this.zz.add(var4)) {
            if (this.lm.remove(var4)) {
               this.RF(this.za);
            }

            this.JY = this.JY == this.HF - 1 ? this.JY : this.JY + 1;
         }
      }
   }
}
