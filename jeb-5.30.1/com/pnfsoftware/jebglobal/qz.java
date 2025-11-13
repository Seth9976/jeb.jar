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
public class qz implements IBranchResolution {
   public static final qz q = new qz();
   @SerId(1)
   BranchTarget RF;
   @SerId(2)
   List xK;

   public qz() {
   }

   public qz(BranchTarget var1) {
      this.RF = var1;
   }

   @Override
   public boolean isEmpty() {
      return this.RF == null && (this.xK == null || this.xK.isEmpty());
   }

   @Override
   public List getTargets() {
      ArrayList var1 = new ArrayList();
      if (this.RF != null) {
         var1.add(this.RF);
      }

      if (this.xK != null) {
         var1.addAll(this.xK);
      }

      return var1;
   }

   @Override
   public boolean isResolved() {
      return this.RF != null;
   }

   public BranchTarget q() {
      return this.RF;
   }

   public boolean q(BranchTarget var1) {
      this.xK = null;
      if ((this.RF != null || var1 != null) && (this.RF == null || !this.RF.equals(var1))) {
         this.RF = var1;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public List getCandidates() {
      return this.xK == null ? Collections.emptyList() : Collections.unmodifiableList(this.xK);
   }

   public boolean RF(BranchTarget var1) {
      if (var1 == null) {
         return false;
      } else {
         if (this.xK == null) {
            this.xK = new ArrayList();
         }

         if (this.xK.contains(var1)) {
            return false;
         } else {
            for (BranchTarget var3 : this.xK) {
               if (var3.getInternalAddress() != null && var3.getInternalAddress().equals(var1.getInternalAddress())) {
                  if (var1.getClass().isInstance(var3)) {
                     return false;
                  }

                  if (!var3.getClass().isInstance(var1)) {
                     return false;
                  }

                  this.xK.remove(var3);
                  break;
               }
            }

            this.RF = null;
            this.xK.add(var1);
            return true;
         }
      }
   }

   public boolean q(IBranchTarget var1) {
      return var1 != null && this.xK != null ? this.xK.remove(var1) : false;
   }

   public void RF() {
      this.xK = null;
   }
}
