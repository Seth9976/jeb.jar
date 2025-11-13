package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class Me implements IBranchResolution {
   public static final Me pC = new Me();
   @SerId(1)
   BranchTarget A;
   @SerId(2)
   List kS;

   @Override
   public boolean isEmpty() {
      return this.A == null && (this.kS == null || this.kS.isEmpty());
   }

   @Override
   public List getTargets() {
      ArrayList var1 = new ArrayList();
      if (this.A != null) {
         var1.add(this.A);
      }

      if (this.kS != null) {
         var1.addAll(this.kS);
      }

      return var1;
   }

   @Override
   public boolean isResolved() {
      return this.A != null;
   }

   public BranchTarget pC() {
      return this.A;
   }

   public boolean pC(BranchTarget var1) {
      this.kS = null;
      if ((this.A != null || var1 != null) && (this.A == null || !this.A.equals(var1))) {
         this.A = var1;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public List getCandidates() {
      return this.kS == null ? Collections.emptyList() : Collections.unmodifiableList(this.kS);
   }

   public boolean A(BranchTarget var1) {
      if (var1 == null) {
         return false;
      } else {
         if (this.kS == null) {
            this.kS = new ArrayList();
         }

         if (this.kS.contains(var1)) {
            return false;
         } else {
            for (BranchTarget var3 : this.kS) {
               if (var3.getInternalAddress() != null && var3.getInternalAddress().equals(var1.getInternalAddress())) {
                  if (var1.getClass().isInstance(var3)) {
                     return false;
                  }

                  if (!var3.getClass().isInstance(var1)) {
                     return false;
                  }

                  this.kS.remove(var3);
                  break;
               }
            }

            this.A = null;
            this.kS.add(var1);
            return true;
         }
      }
   }

   public boolean pC(IBranchTarget var1) {
      return var1 != null && this.kS != null ? this.kS.remove(var1) : false;
   }

   public void A() {
      this.kS = null;
   }
}
