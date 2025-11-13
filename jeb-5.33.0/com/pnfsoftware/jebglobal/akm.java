package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

@Ser
public abstract class akm extends ala implements IEStatement {
   private static final StructuredLogger pC = aco.pC(akm.class);
   private static final byte[] A = new byte[0];
   @SerId(1)
   protected IERoutineContext wS;
   @SerId(2)
   protected int UT;
   @SerId(3)
   protected LinkedHashSet E = new LinkedHashSet();
   @SerId(4)
   protected Integer sY;

   protected akm(IERoutineContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
         this.UT = 1;
      }
   }

   @Override
   public IERoutineContext getContext() {
      return this.wS;
   }

   @Override
   public IEStatement withLowerLevelAddress(long var1) {
      this.setLowerLevelAddress(var1);
      return this;
   }

   @Override
   public IEStatement withLowerLevelAddresses(Collection var1) {
      this.setLowerLevelAddresses(var1);
      return this;
   }

   @Override
   public void setLowerLevelAddresses(Collection var1) {
      this.E.clear();
      this.E.addAll(var1);
   }

   @Override
   public void setLowerLevelAddress(long var1) {
      this.E.clear();
      this.E.add(var1);
   }

   @Override
   public void addLowerLevelAddress(long var1) {
      this.E.add(var1);
   }

   @Override
   public void addLowerLevelAddresses(Collection var1) {
      this.E.addAll(var1);
   }

   @Override
   public void removeLowerLevelAddress(long var1) {
      this.E.remove(var1);
   }

   @Override
   public void resetLowerLevelAddresses() {
      this.E.clear();
   }

   @Override
   public void copyLowerLevelAddresses(IEStatement var1) {
      this.addLowerLevelAddresses(var1.getLowerLevelAddresses());
   }

   @Override
   public Collection getLowerLevelAddresses() {
      return Collections.unmodifiableCollection(this.E);
   }

   @Override
   public Long getPrimaryLowerLevelAddress() {
      return this.E.isEmpty() ? null : (Long)this.E.iterator().next();
   }

   @Override
   public void setPrimaryLowerLevelAddress(long var1) {
      if (this.E.isEmpty()) {
         this.E.add(var1);
      } else if ((Long)this.E.iterator().next() != var1) {
         LinkedHashSet var3 = new LinkedHashSet(this.E);
         this.E.clear();
         this.E.add(var1);
         this.E.addAll(var3);
      }
   }

   @Override
   public void setSPDelta(Integer var1) {
      this.sY = var1;
   }

   @Override
   public Integer getSPDelta() {
      return this.sY;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + this.UT;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true, true);
   }

   @Override
   public final boolean equalsEx(Object var1, boolean var2) {
      return this.equalsEx(var1, var2, true, true);
   }

   @Override
   public final boolean equalsEx(Object var1, boolean var2, boolean var3) {
      return this.equalsEx(var1, var2, var3, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         akm var5 = (akm)var1;
         if (var3) {
            if (this.wS == null) {
               if (var5.wS != null) {
                  return false;
               }
            } else if (this.wS != var5.wS) {
               return false;
            }
         }

         return !var4 || this.UT == var5.UT;
      }
   }

   @Override
   public int getProcessorMode() {
      return 0;
   }

   @Override
   public int getSize() {
      return this.UT;
   }

   @Override
   public void setSize(int var1) {
      if (var1 <= 0) {
         throw new RuntimeException("Illegal length: " + var1);
      } else {
         this.UT = var1;
      }
   }

   @Override
   public void adjustSize(int var1) {
      int var2 = this.UT + var1;
      if (var2 <= 0) {
         throw new RuntimeException("Illegal length: " + var2);
      } else {
         this.UT = var2;
      }
   }

   @Override
   public byte[] getCode() {
      return A;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public Collection getInstructionFlags() {
      return Collections.emptySet();
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      var1.clear();
      var2.clear();
      EDefUseInfo var4 = new EDefUseInfo(2, this.wS);
      this.getDefUse(var4);
      if (((aki)this.wS).E()) {
         var4.getDef().collectBits(var1);
         var4.getUse().collectBits(var2);
      } else {
         var4.getDef().collectVarIds(var1);
         var4.getUse().collectVarIds(var2);
      }
   }

   @Override
   public EDefUseInfo getDefUseInfo(long var1, int var3) {
      EDefUseInfo var4 = new EDefUseInfo(var3, this.wS);
      var4.setInstructionAddress(var1);
      this.getDefUse(var4);
      return var4;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.getDefUse(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      throw new RuntimeException("Do not call getDefinedOrUsedAsDestination() on IR statements");
   }

   @Override
   public boolean writesMemory() {
      return false;
   }

   @Override
   public int getPriority() {
      return 0;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return this.replaceVar(var1, var2, false);
   }

   protected final akm pC(akm var1) {
      var1.UT = this.UT;
      var1.addLowerLevelAddresses(this.E);
      var1.sY = this.sY;
      return (akm)super.pC(var1);
   }

   @Override
   public void copyProperties(IEStatement var1) {
      this.UT = var1.getSize();
      this.addLowerLevelAddresses(var1.getLowerLevelAddresses());
      this.sY = var1.getSPDelta();
      this.ld = var1.getFlags();
      this.gp = var1.getType();
   }

   @Override
   public void preUpdateTypes(ETypeInfo var1) {
   }

   @Override
   public void postUpdateTypes(ETypeInfo var1) {
   }

   @Override
   public abstract ICStatement generateC(IERoutineContext var1, ICMethod var2);

   protected ICElement pC(ICElement var1) {
      var1.addPhysicalOffsets(this.E);
      return var1;
   }

   @Override
   public String format(Object var1) {
      if (var1 == null) {
         var2 = new ajn();
      } else if (!(var1 instanceof ajn var2)) {
         var2 = new ajn();
      }

      this.pC(var2);
      return var2.toString();
   }

   @Override
   public void verify() throws IllegalIntermediateExpressionException {
      if (!this.visitDepthPre(new akn(this))) {
         throw new IllegalIntermediateExpressionException("Nested statement: " + this);
      }
   }
}
