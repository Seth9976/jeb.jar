package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Ser
public class FlowInformation implements IFlowInformation {
   public static final int FLAG_COMPUTE_FT = 1;
   public static final int FLAG_NO_FT = 2;
   public static final FlowInformation NONE = new FlowInformation(true, 0, 0);
   public static final FlowInformation EMPTY = new FlowInformation(false, 0, 0);
   public static final FlowInformation EMPTY_NO_FT = new FlowInformation(false, 0, 2);
   @SerId(1)
   private List targets;
   @SerId(value = 2, deprecated = true)
   private boolean computeFallThrough;
   @SerId(3)
   private int delaySlotCount;
   @SerId(4)
   private int flags;

   @SerCustomInitPostGraph
   private void init() {
      if (this.computeFallThrough) {
         this.flags |= 1;
         this.computeFallThrough = false;
      }
   }

   private FlowInformation(boolean var1, int var2, int var3) {
      if (!var1) {
         this.targets = new ArrayList();
      }

      this.delaySlotCount = var2;
      this.flags = var3;
   }

   public FlowInformation() {
      this(false, 0, 0);
   }

   public FlowInformation(boolean var1, int var2) {
      this.targets = new ArrayList();
      this.setComputeFallThrough(var1);
      this.setDelaySlotCount(var2);
   }

   public void addTarget(ICodePointer var1) {
      if (this.targets == null) {
         throw new IllegalArgumentException("NONE flow information can not contain targets");
      } else {
         this.targets.add(var1);
      }
   }

   public void addTargets(Collection var1) {
      if (this.targets == null) {
         throw new IllegalArgumentException("NONE flow information can not contain targets");
      } else {
         this.targets.addAll(var1);
      }
   }

   @Override
   public boolean isBroken() {
      return this.targets != null;
   }

   @Override
   public boolean isBrokenUnknown() {
      return this.targets != null && (this.targets.isEmpty() || this.areAllTargetsUnknown());
   }

   @Override
   public boolean isBrokenKnown() {
      return this.targets != null && !this.targets.isEmpty() && !this.hasUnknownTargets();
   }

   private boolean hasUnknownTargets() {
      for (ICodePointer var2 : this.targets) {
         if (var2.isUnknownAddress()) {
            return true;
         }
      }

      return false;
   }

   private boolean areAllTargetsUnknown() {
      for (ICodePointer var2 : this.targets) {
         if (!var2.isUnknownAddress()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public List getTargets() {
      return this.targets;
   }

   public void setComputeFallThrough(boolean var1) {
      if (var1) {
         this.flags |= 1;
      } else {
         this.flags &= -2;
      }
   }

   @Override
   public boolean mustComputeFallThrough() {
      return (this.flags & 1) != 0;
   }

   public void setNoFallThrough(boolean var1) {
      if (var1) {
         this.flags |= 2;
      } else {
         this.flags &= -3;
      }
   }

   @Override
   public boolean noFallThrough() {
      return (this.flags & 2) != 0;
   }

   public void setDelaySlotCount(int var1) {
      if (var1 >= 0 && var1 <= 255) {
         this.delaySlotCount = var1;
      } else {
         throw new RuntimeException("Delay slot must be in [0, 255]");
      }
   }

   @Override
   public int getDelaySlotCount() {
      return this.delaySlotCount;
   }

   @Override
   public String toString() {
      if (this.targets == null) {
         return "NON-BREAKING";
      } else {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "targets=%s", this.targets);
         if (this.mustComputeFallThrough()) {
            Strings.ff(var1, ",computeFallThrough=true");
         }

         if (this.getDelaySlotCount() != 0) {
            Strings.ff(var1, ",delaySlotCount=%d", this.getDelaySlotCount());
         }

         return var1.toString();
      }
   }

   public static boolean isAddressInTargets(IFlowInformation var0, long var1) {
      for (ICodePointer var4 : var0.getTargets()) {
         if (var4.getAddress() == var1) {
            return true;
         }
      }

      return false;
   }
}
