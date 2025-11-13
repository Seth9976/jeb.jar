package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.DefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EDefUseInfo extends DefUseInfo {
   public IERoutineContext ctx;
   private EDefUseInfo.PotFilter potTester = new EDefUseInfo.PotFilter();

   public EDefUseInfo() {
   }

   public EDefUseInfo(int var1, IERoutineContext var2) {
      super(var1);
      if (var2 == null) {
         throw new IllegalArgumentException("Please provide an routine context or use the other constructor");
      } else {
         this.ctx = var2;
      }
   }

   public IERoutineContext getContext() {
      return this.ctx;
   }

   @Override
   public boolean shouldCollectPotentials() {
      return this.ctx != null && super.shouldCollectPotentials();
   }

   @Override
   public boolean shouldCollectSpoiled() {
      return this.ctx != null && super.shouldCollectSpoiled();
   }

   public void addDefined(IEVar var1) {
      this.def.add(var1);
   }

   public void addDefined(Collection var1) {
      this.def.addAll(var1);
   }

   public void addUsed(IEVar var1) {
      this.use.add(var1);
   }

   public void addUsed(Collection var1) {
      this.use.addAll(var1);
   }

   public void add(IEVar var1, boolean var2) {
      if (var2) {
         this.addDefined(var1);
      } else {
         this.addUsed(var1);
      }
   }

   public void add(Collection var1, boolean var2) {
      if (var2) {
         this.addDefined(var1);
      } else {
         this.addUsed(var1);
      }
   }

   public void addPotentialDefined(IEVar var1) {
      if (this.potTester.test(var1) && !this.def.containsVarFull(var1.getId())) {
         this.defpot.add(var1);
      }
   }

   public void addPotentialDefined(Collection var1) {
      var1 = (Collection)var1.stream().filter(this.potTester).collect(Collectors.toList());
      this.defpot.addAll(var1, this.def);
   }

   public void addPotentialUsed(IEVar var1) {
      if (this.potTester.test(var1) && !this.use.containsVarFull(var1.getId())) {
         this.usepot.add(var1);
      }
   }

   public void addPotentialUsed(Collection var1) {
      var1 = (Collection)var1.stream().filter(this.potTester).collect(Collectors.toList());
      this.usepot.addAll(var1, this.use);
   }

   public void addPotential(IEVar var1, boolean var2) {
      if (var2) {
         this.addPotentialDefined(var1);
      } else {
         this.addPotentialUsed(var1);
      }
   }

   public void addPotential(Collection var1, boolean var2) {
      if (var2) {
         this.addPotentialDefined(var1);
      } else {
         this.addPotentialUsed(var1);
      }
   }

   public void addSpoiled(IEVar var1) {
      if (!this.def.containsVarFull(var1.getId())) {
         this.spoiled.add(var1);
      }
   }

   public void addSpoiled(Collection var1) {
      this.spoiled.addAll(var1, this.def);
   }

   private static class PotFilter implements Predicate {
      public boolean test(IEVar var1) {
         return (var1.getFlags() & 96) == 0;
      }
   }
}
