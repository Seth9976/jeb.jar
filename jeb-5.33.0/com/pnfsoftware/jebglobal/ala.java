package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;

@Ser
public abstract class ala implements IEGeneric {
   private static final StructuredLogger pC = aco.pC(ala.class);
   static AtomicBoolean ys = new AtomicBoolean();
   @SerId(1)
   protected int ld;
   @SerId(2)
   protected IWildcardType gp;

   @Override
   public int getFlags() {
      return this.ld;
   }

   @Override
   public boolean hasFlags(int var1) {
      return (this.ld & var1) == var1;
   }

   @Override
   public boolean addFlags(int var1) {
      int var2 = this.ld | var1;
      if (var2 == this.ld) {
         return false;
      } else {
         this.ld = var2;
         return true;
      }
   }

   @Override
   public void removeFlags(int var1) {
      this.ld &= ~var1;
   }

   @Override
   public void setFlags(int var1) {
      this.ld = var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.gp == null ? 0 : this.gp.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof ala var3)) {
         return false;
      } else {
         if (var2) {
            if (this.gp == null) {
               if (var3.gp != null) {
                  return false;
               }
            } else if (!this.gp.equals(var3.gp)) {
               return false;
            }
         }

         return true;
      }
   }

   public abstract void pC(akz var1);

   @Override
   public String format(IInstruction var1, long var2) {
      ajn var4 = new ajn();
      this.pC(var4);
      return var4.toString();
   }

   @Override
   public String toString() {
      return this.format(null, 0L);
   }

   @Override
   public abstract int getBitsize();

   @Override
   public abstract int getPriority();

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      this.getUsed(var1);
   }

   @Override
   public long evaluateUnsignedLong(EState var1) {
      IEImm var2 = this.evaluate(var1);
      if (var2 == null) {
         throw new EvaluationException("Evaluation failed");
      } else {
         return var2.getValueAsUnsignedLong();
      }
   }

   @Override
   public long evaluateAddress(EState var1) {
      IEImm var2 = this.evaluate(var1);
      if (var2 == null) {
         throw new EvaluationException("Evaluation failed");
      } else {
         return var2.getValueAsAddress();
      }
   }

   @Override
   public boolean setType(IWildcardType var1) {
      return this.setType(var1, null);
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return this.setType(var1, var2, false);
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2, boolean var3) {
      if ((var1 != null || this.gp != null) && (var1 == null || !var1.equals(this.gp))) {
         if (var1 != null && var1.isVoid()) {
            if (var2 != null) {
               var2.recordConflict("Refusing to apply type `void`", this, this.gp, var1);
            }

            return false;
         } else {
            String var4 = "Generic conflict";
            if (this.gp == null || var3 || this.gp.isLessSpecializedThan(var1)) {
               boolean var5 = false;
               if (var1 != null && var1.getMaximumBitsize() > this.getBitsize()) {
                  var4 = "Bitsize incompatibility";
                  var5 = true;
               }

               if (!var5) {
                  this.gp = var1;
                  if (var2 != null) {
                     var2.recordChanged();
                  }

                  return true;
               }
            }

            if (var2 != null) {
               var2.recordConflict(var4, this, this.gp, var1);
            }

            return false;
         }
      } else {
         if (var2 != null) {
            var2.recordUnchanged();
         }

         return true;
      }
   }

   @Override
   public IWildcardType getType() {
      return this.gp;
   }

   @Override
   public boolean visitDepthPre(IEVisitor var1) {
      return this.visitDepthPre(var1, null, null);
   }

   @Override
   public boolean visitDepthPre(IEVisitor var1, IEGeneric var2) {
      return this.visitDepthPre(var1, var2, null);
   }

   @Override
   public boolean visitDepthPre(IEVisitor var1, IEGeneric var2, EVisitResults var3) {
      if (var3 == null) {
         var3 = new EVisitResults();
      }

      pC(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void pC(IEVisitor var0, IEGeneric var1, IEGeneric var2, EVisitResults var3) {
      var3.currentNode = var1;
      boolean var4 = false;
      if (var3.skipAssignmentDestination && var2 instanceof IEStatement) {
         ArrayList var5 = new ArrayList();
         ((IEStatement)var2).collectUsedExpressions(var5);
         var4 = true;

         for (Couple var7 : var5) {
            if (var7.getFirst() == var2 && var7.getSecond() == var1) {
               var4 = false;
               break;
            }
         }
      }

      if (!var4) {
         var0.process(var1, var2, var3);
      }

      if (!var3.isInterruptedVisit()) {
         if (var3.skipVisitingChildren) {
            var3.skipVisitingChildren = false;
         } else {
            var1 = (IEGeneric)var3.currentNode;
            var3.pushParent(var1);
            int var9 = 0;

            for (IEGeneric var11 : EUtil.getSubExpressions(var1)) {
               var3.visitedChildPosition = var9;
               pC(var0, var11, var1, var3);
               if (var3.isInterruptedVisit()) {
                  return;
               }

               var9++;
            }

            var3.popParent();
         }
      }
   }

   @Override
   public boolean visitDepthPost(IEVisitor var1) {
      return this.visitDepthPost(var1, null, null);
   }

   @Override
   public boolean visitDepthPost(IEVisitor var1, IEGeneric var2) {
      return this.visitDepthPost(var1, var2, null);
   }

   @Override
   public boolean visitDepthPost(IEVisitor var1, IEGeneric var2, EVisitResults var3) {
      if (var3 == null) {
         var3 = new EVisitResults();
      }

      A(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void A(IEVisitor var0, IEGeneric var1, IEGeneric var2, EVisitResults var3) {
      var3.pushParent(var1);

      for (IEGeneric var5 : EUtil.getSubExpressions(var1)) {
         if (var3.parents == null || !var3.parents.contains(var5)) {
            A(var0, var5, var1, var3);
            if (var3.isInterruptedVisit()) {
               return;
            }
         }
      }

      var3.popParent();
      var3.setReplacedNode(var1);
      var3.visitedChildPosition = -1;
      boolean var8 = false;
      if (var3.skipAssignmentDestination && var2 instanceof IEStatement) {
         ArrayList var9 = new ArrayList();
         ((IEStatement)var2).collectUsedExpressions(var9);
         var8 = true;

         for (Couple var7 : var9) {
            if (var7.getFirst() == var2 && var7.getSecond() == var1) {
               var8 = false;
               break;
            }
         }
      }

      if (!var8) {
         var0.process(var1, var2, var3);
      }
   }

   @Override
   public void verify() throws IllegalIntermediateExpressionException {
   }

   public abstract ala A();

   public ala pC(ala var1) {
      var1.ld = this.ld;
      var1.gp = this.gp;
      return var1;
   }

   @Override
   public void copyProperties(IEGeneric var1) {
      this.ld = var1.getFlags();
      this.gp = var1.getType();
   }

   protected static void pC(IEGeneric... var0) throws IllegalIntermediateExpressionException {
      for (IEGeneric var4 : var0) {
         if (var4 == null) {
            throw new IllegalIntermediateExpressionException("Illegal null IR expression");
         }
      }
   }

   protected static void A(IEGeneric var0, IEGeneric var1) throws IllegalIntermediateExpressionException {
      if (var0.getBitsize() != var1.getBitsize()) {
         throw new IllegalIntermediateExpressionException("Unexpected bitsize: actual=%d expected=%d", var1.getBitsize(), var0.getBitsize());
      }
   }

   protected static List pC(List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());

         for (IEGeneric var3 : var0) {
            var1.add(var3.duplicate());
         }

         return var1;
      }
   }

   @Override
   public IEGeneric part(int var1) {
      return this.slice(0, var1);
   }

   @Override
   public IEGeneric bit(int var1) {
      return this.slice(var1, var1 + 1);
   }

   @Override
   public IEGeneric slice(int var1) {
      return this.slice(var1, this.getBitsize());
   }

   @Override
   public IEGeneric slice(int var1, int var2) {
      int var3 = this.getBitsize();
      if (var1 < 0 || var1 >= var2 || var2 > var3) {
         throw new RuntimeException("Illegal Slice with begin:" + var1 + ", end:" + var2);
      } else if (var1 == 0 && var2 == var3) {
         return this;
      } else if (this instanceof IESlice) {
         int var10 = ((IESlice)this).getBitStart();
         return ((IESlice)this).getWholeExpression().slice(var10 + var1, var10 + var2);
      } else if (this instanceof IEImm) {
         return ((IEImm)this)._shr(var1).truncate(var2 - var1);
      } else if (this instanceof IECompose && var1 == 0) {
         ArrayList var4 = new ArrayList();
         int var5 = 0;

         for (IEGeneric var9 : ((ajl)this).pC) {
            if (var5 >= var2) {
               break;
            }

            var4.add(var9);
            var5 += var9.getBitsize();
         }

         return (IEGeneric)(var4.size() == 1 ? ((IEGeneric)var4.get(0)).slice(var1, var2) : new akk(new ajl(var4), var1, var2));
      } else {
         return new akk(this, var1, var2);
      }
   }

   @Override
   public IEGeneric slice(IERange var1) {
      return this.slice(var1.getBegin(), var1.getEnd());
   }

   @Override
   public IEGeneric msb() {
      int var1 = this.getBitsize();
      return this.slice(var1 - 1, var1);
   }

   @Override
   public IEGeneric lsb() {
      return this.slice(0, 1);
   }

   @Override
   public IEGeneric half() {
      int var1 = this.getBitsize() / 2;
      if ((this.getBitsize() & 1) != 0) {
         var1++;
      }

      return this.part(var1);
   }

   @Override
   public IEGeneric topHalf() {
      int var1 = this.getBitsize() / 2;
      if ((this.getBitsize() & 1) != 0) {
         var1++;
      }

      return this.slice(var1);
   }

   @Override
   public IEGeneric zeroExtend(int var1) {
      int var2 = this.getBitsize();
      if (var1 < var2) {
         throw new RuntimeException();
      } else if (var1 == var2) {
         return this;
      } else {
         ajr var3 = ajr.pC(0L, var1);
         return new ajl(this, var3.slice(var2, var1));
      }
   }

   @Override
   public IEGeneric signExtend(int var1) {
      int var2 = this.getBitsize();
      if (var1 < var2) {
         throw new RuntimeException();
      } else if (var1 == var2) {
         return this;
      } else {
         ajr var3 = ajr.pC(0L, var1 - var2);
         ajr var4 = ajr.pC(-1L, var1 - var2);
         return new ajl(this.A(), new ajm(this.msb(), var4, var3));
      }
   }

   @Override
   public IEGeneric leftShift(int var1, int var2) {
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : new ajl(ajr.pC(0L, var1), this.slice(0, var2 - var1)));
   }

   @Override
   public IEGeneric leftShift(int var1) {
      return this.leftShift(var1, this.getBitsize());
   }

   @Override
   public IEGeneric rightShift(int var1, int var2) {
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : new ajl(this.slice(var1, var2), ajr.pC(0L, var1)));
   }

   @Override
   public IEGeneric rightShift(int var1) {
      return this.rightShift(var1, this.getBitsize());
   }

   @Override
   public IECond countSuccessiveBits(boolean var1, boolean var2, int var3) {
      ajr var4 = ajr.pC(this.getBitsize(), var3);
      ajr var5 = ajr.pC(this.getBitsize() - 1, var3);
      int var6 = var2 ? 0 : this.getBitsize() - 1;
      ajm var7 = new ajm(this.A().slice(var6, var6 + 1), var1 ? var4 : var5, var1 ? var5 : var4);

      for (int var8 = 1; var8 < this.getBitsize(); var8++) {
         var6 = var2 ? var8 : this.getBitsize() - var8 - 1;
         ajr var9 = ajr.pC(this.getBitsize() - var8 - 1, var3);
         var7 = new ajm(this.A().slice(var6, var6 + 1), (IEGeneric)(var1 ? var7 : var9), (IEGeneric)(var1 ? var9 : var7));
      }

      return var7;
   }

   @Override
   public IWildcardType safelyType(IWildcardTypeManager var1) {
      return this.pC(var1, true);
   }

   @Override
   public IWildcardType getSafeType(IWildcardTypeManager var1) {
      return this.pC(var1, false);
   }

   public IWildcardType pC(IWildcardTypeManager var1, boolean var2) {
      if (this.getType() == null) {
         IWildcardType var3 = var1.createWithMaximumBitsize(this.getBitsize());
         if (!var2) {
            return var3;
         }

         if (!this.setType(var3, null)) {
            Object[] var10000 = new Object[]{var3, this};
            return var3;
         }
      }

      return this.getType();
   }

   protected static boolean pC(IEGeneric var0, IEGeneric var1, boolean var2) {
      if (var0 == var1) {
         return true;
      } else {
         return var0 != null && var1 != null ? var0.equalsEx(var1, var2) : false;
      }
   }

   protected static boolean pC(IEGeneric[] var0, IEGeneric[] var1, boolean var2) {
      if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null) {
         int var3 = var0.length;
         if (var1.length != var3) {
            return false;
         } else {
            for (int var4 = 0; var4 < var3; var4++) {
               IEGeneric var5 = var0[var4];
               IEGeneric var6 = var1[var4];
               if (var5 == null ? var6 != null : !var5.equalsEx(var6, var2)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected static boolean pC(List var0, List var1, boolean var2) {
      if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null) {
         if (var0.size() != var1.size()) {
            return false;
         } else {
            ListIterator var3 = var0.listIterator();
            ListIterator var4 = var1.listIterator();

            while (var3.hasNext() && var4.hasNext()) {
               IEGeneric var5 = (IEGeneric)var3.next();
               IEGeneric var6 = (IEGeneric)var4.next();
               if (var5 == null ? var6 != null : !var5.equalsEx(var6, var2)) {
                  return false;
               }
            }

            return !var3.hasNext() && !var4.hasNext();
         }
      } else {
         return false;
      }
   }

   @Override
   public IEGeneric findParent(IEGeneric var1) {
      return this.findParent(var1, 0);
   }

   @Override
   public IEGeneric findParent(IEGeneric var1, int var2) {
      Couple var3 = this.find(var1, var2, 0, null);
      return var3 == null ? null : (IEGeneric)var3.getFirst();
   }

   @Override
   public Couple find(IEGeneric var1, int var2, int var3, IEGeneric var4) {
      Couple[] var5 = new Couple[1];
      if (!this.visitDepthPre(new alb(this, var3, var1, var2, var5))) {
         return null;
      } else {
         Couple var6 = var5[0];
         if (var6 != null && var6.getFirst() == null) {
            var6.setFirst(var4);
         }

         return var6;
      }
   }

   @Override
   public IEGeneric findByType(Class var1) {
      return this.findByType(var1, 0);
   }

   @Override
   public IEGeneric findByType(Class var1, int var2) {
      IEGeneric[] var3 = new IEGeneric[1];
      return !this.visitDepthPre(new alc(this, var1, var2, var3)) ? null : var3[0];
   }

   protected static void pC(IEGeneric var0, IEGeneric var1, Collection var2) {
      Assert.a(var0 != null);
      if (var1 != null) {
         var2.add(new Couple(var0, var1));
      }
   }

   protected static void A(IEGeneric var0, IEGeneric var1, Collection var2) {
      Assert.a(var0 != null);
      if (var1 != null) {
         if (var1 instanceof IEMem var3) {
            var2.add(new Couple(var3, var3.getReference()));
         }
      }
   }
}
