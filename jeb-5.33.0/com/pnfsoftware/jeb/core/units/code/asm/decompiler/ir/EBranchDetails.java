package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class EBranchDetails implements IEBranchDetails {
   @SerId(1)
   private List def;
   @SerId(2)
   private List use;
   @SerId(3)
   private List spoiled;
   @SerId(4)
   private List dynamicTargetCandidates;
   @SerId(5)
   private boolean includeUnknownTarget;
   @SerId(6)
   private SPDDeterminer spdd = new SPDDeterminer();
   @SerId(7)
   private IPrototypeItem nativePrototypeHint;
   @SerId(8)
   private PreRoutineInvocationDetails preInvocationDetails;

   public EBranchDetails(List var1, List var2, List var3, int var4, IPrototypeItem var5, List var6) {
      if (var1 == null) {
         this.def = new ArrayList();
      } else {
         this.def = new ArrayList(var1);
      }

      if (var2 == null) {
         this.use = new ArrayList();
      } else {
         this.use = new ArrayList(var2);
      }

      if (var3 == null) {
         this.spoiled = new ArrayList();
      } else {
         this.spoiled = new ArrayList(var3);
      }

      this.spdd.add(var4, 0, 0);
      this.nativePrototypeHint = var5;
      if (var6 == null) {
         this.dynamicTargetCandidates = new ArrayList();
      } else {
         this.dynamicTargetCandidates = new ArrayList(var6);
      }
   }

   public EBranchDetails() {
      this(null, null, null, 0, null, null);
   }

   public EBranchDetails(IEBranchDetails var1) {
      this.def = new ArrayList(var1.getDef());
      this.use = new ArrayList(var1.getUse());
      this.spoiled = new ArrayList(var1.getSpoiled());
      this.dynamicTargetCandidates = new ArrayList(var1.getDynamicTargetCandidates());
      this.includeUnknownTarget = var1.isIncludeUnknownTarget();
      this.nativePrototypeHint = var1.getNativePrototypeHint();
      this.spdd = var1.getStackPointerDeltaDeterminer().clone();
   }

   @Override
   public List getDef() {
      return Collections.unmodifiableList(this.def);
   }

   @Override
   public List getUse() {
      return Collections.unmodifiableList(this.use);
   }

   @Override
   public List getSpoiled() {
      return Collections.unmodifiableList(this.spoiled);
   }

   @Override
   public SPDDeterminer getStackPointerDeltaDeterminer() {
      return this.spdd;
   }

   @Override
   public SPDC getStackPointerDelta() {
      return this.spdd.getBestCandidate();
   }

   @Override
   public int getStackPointerDeltaValue() {
      return this.spdd.getBestValue();
   }

   @Override
   public List getDynamicTargetCandidates() {
      return Collections.unmodifiableList(this.dynamicTargetCandidates);
   }

   @Override
   public boolean addCandidate(IBranchTarget var1) {
      return this.addCandidate(this.dynamicTargetCandidates.size(), var1);
   }

   @Override
   public boolean addCandidate(int var1, IBranchTarget var2) {
      if (var1 < 0) {
         var1 += this.dynamicTargetCandidates.size() + 1;
      }

      if (this.dynamicTargetCandidates.contains(var2)) {
         return false;
      } else {
         if (var2.isInternal()) {
            ICodePointer var3 = var2.getInternalAddress();

            for (int var4 = 0; var4 < this.dynamicTargetCandidates.size(); var4++) {
               IBranchTarget var5 = (IBranchTarget)this.dynamicTargetCandidates.get(var4);
               if (var5.isInternal()) {
                  ICodePointer var6 = var5.getInternalAddress();
                  if (var6.getAddress() == var3.getAddress()) {
                     if (var3.getMode() == 0) {
                        return false;
                     }

                     if (var6.getMode() == 0) {
                        this.dynamicTargetCandidates.set(var4, var2);
                        return true;
                     }

                     throw new RuntimeException(
                        Strings.ff(
                           "Invalid candidate has conflict processor modes (%d vs %d) with existing target %xh",
                           var3.getMode(),
                           var6.getMode(),
                           var6.getAddress()
                        )
                     );
                  }
               }
            }
         }

         this.dynamicTargetCandidates.add(var1, var2);
         return true;
      }
   }

   @Override
   public boolean addCandidates(List var1) {
      int var2 = 0;

      for (IBranchTarget var4 : var1) {
         if (this.addCandidate(var4)) {
            var2++;
         }
      }

      return var2 > 0;
   }

   @Override
   public boolean setIncludeUnknownTarget(boolean var1) {
      if (this.includeUnknownTarget == var1) {
         return false;
      } else {
         this.includeUnknownTarget = var1;
         return true;
      }
   }

   @Override
   public boolean isIncludeUnknownTarget() {
      return this.includeUnknownTarget;
   }

   @Override
   public IPrototypeItem getNativePrototypeHint() {
      return this.nativePrototypeHint;
   }

   @Override
   public boolean setNativePrototypeHint(IPrototypeItem var1) {
      if (this.nativePrototypeHint == var1) {
         return false;
      } else {
         this.nativePrototypeHint = var1;
         return true;
      }
   }

   @Override
   public PreRoutineInvocationDetails getPreInvocationDetails() {
      return this.preInvocationDetails;
   }

   @Override
   public boolean setPreInvocationDetails(PreRoutineInvocationDetails var1) {
      if (this.preInvocationDetails == var1) {
         return false;
      } else {
         this.preInvocationDetails = var1;
         return true;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.use == null ? 0 : this.use.hashCode());
      var1 = 31 * var1 + (this.def == null ? 0 : this.def.hashCode());
      var1 = 31 * var1 + (this.spoiled == null ? 0 : this.spoiled.hashCode());
      var1 = 31 * var1 + (this.dynamicTargetCandidates == null ? 0 : this.dynamicTargetCandidates.hashCode());
      var1 = 31 * var1 + (this.nativePrototypeHint == null ? 0 : this.nativePrototypeHint.hashCode());
      return 31 * var1 + (this.spdd == null ? 0 : this.spdd.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         EBranchDetails var2 = (EBranchDetails)var1;
         if (this.use == null) {
            if (var2.use != null) {
               return false;
            }
         } else if (!this.use.equals(var2.use)) {
            return false;
         }

         if (this.def == null) {
            if (var2.def != null) {
               return false;
            }
         } else if (!this.def.equals(var2.def)) {
            return false;
         }

         if (this.spoiled == null) {
            if (var2.spoiled != null) {
               return false;
            }
         } else if (!this.spoiled.equals(var2.spoiled)) {
            return false;
         }

         if (this.dynamicTargetCandidates == null) {
            if (var2.dynamicTargetCandidates != null) {
               return false;
            }
         } else if (!this.dynamicTargetCandidates.equals(var2.dynamicTargetCandidates)) {
            return false;
         }

         if (this.nativePrototypeHint == null) {
            if (var2.nativePrototypeHint != null) {
               return false;
            }
         } else if (!this.nativePrototypeHint.equals(var2.nativePrototypeHint)) {
            return false;
         }

         if (this.spdd == null) {
            if (var2.spdd != null) {
               return false;
            }
         } else if (!this.spdd.equals(var2.spdd)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "def=%s,use=%s,spoiled=%s", this.def, this.use, this.spoiled);
      Strings.ff(var1, ",spdd=%s", this.spdd);
      if (!this.dynamicTargetCandidates.isEmpty()) {
         Strings.ff(var1, ",targets=%s", this.dynamicTargetCandidates);
      }

      if (this.nativePrototypeHint != null) {
         Strings.ff(var1, ",protoHint=%s", this.nativePrototypeHint);
      }

      return var1.toString();
   }
}
