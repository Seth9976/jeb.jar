package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SerDisabled
public abstract class bph implements IDExpression {
   private static final ILogger A = GlobalLog.getLogger(bph.class, Integer.MAX_VALUE);
   protected IJavaType pC;
   private int kS = -1;
   private int wS = -1;
   private Map UT;

   public bph(IJavaType var1) {
      if (var1 == null && !(this instanceof IDInstruction)) {
         throw new RuntimeException("Missing type for non-instruction IR!");
      } else {
         this.pC = var1;
      }
   }

   @Override
   public int hashCode() {
      return 1;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, false);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         if (var2) {
            bph var3 = (bph)var1;
            if (this.pC == null) {
               if (var3.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var3.pC)) {
               return false;
            }

            if (this.kS != var3.kS) {
               return false;
            }

            if (this.wS != var3.wS) {
               return false;
            }

            if ((this.UT != null || var3.UT != null) && (this.UT == null || !this.UT.equals(var3.UT))) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public int getPhysicalMethodIndex() {
      return this.kS;
   }

   @Override
   public void setPhysicalMethodIndex(int var1) {
      this.kS = var1;
   }

   @Override
   public void collectAllPhysicalMethodIndices(Collection var1) {
      if (this.kS >= 0) {
         var1.add(this.kS);
      }

      for (IDExpression var3 : this.getSubExpressions()) {
         var3.collectAllPhysicalMethodIndices(var1);
      }
   }

   @Override
   public final void updateAllPhysicalMethodIndices(int var1) {
      this.kS = var1;

      for (IDExpression var3 : this.getSubExpressions()) {
         var3.updateAllPhysicalMethodIndices(var1);
      }
   }

   @Override
   public int getPhysicalOffset() {
      return this.wS;
   }

   @Override
   public void setPhysicalOffset(int var1) {
      this.wS = var1;
   }

   @Override
   public void collectAllPhysicalOffsets(Collection var1) {
      if (this.wS >= 0) {
         var1.add(this.wS);
      }

      for (IDExpression var3 : this.getSubExpressions()) {
         var3.collectAllPhysicalOffsets(var1);
      }
   }

   @Override
   public final void updateAllPhysicalOffsets(int var1) {
      this.wS = var1;

      for (IDExpression var3 : this.getSubExpressions()) {
         var3.updateAllPhysicalOffsets(var1);
      }
   }

   @Override
   public Object setData(String var1, Object var2) {
      if (this.UT == null) {
         this.UT = new LinkedHashMap();
      }

      return this.UT.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.UT == null ? null : this.UT.get(var1);
   }

   @Override
   public void removeData(String var1) {
      if (this.UT != null) {
         this.UT.remove(var1);
         if (this.UT.isEmpty()) {
            this.UT = null;
         }
      }
   }

   @Override
   public void setOrigin(String var1) {
      this.setData("origin", var1);
   }

   @Override
   public String getOrigin() {
      return (String)this.getData("origin");
   }

   protected static IDExpression pC(IDExpression var0, int var1) {
      if (var0 == null) {
         return null;
      } else {
         return var1 == 0 ? var0 : var0.duplicate();
      }
   }

   protected static IDExpression pC(IDExpression var0, DCopyOptions var1) {
      return var0 == null ? null : var0.copy(var1);
   }

   protected static List pC(List var0, DCopyOptions var1) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList(var0.size());

         for (IDExpression var4 : var0) {
            var2.add(pC(var4, var1));
         }

         return var2;
      }
   }

   protected static IDExpression[] pC(IDExpression[] var0, DCopyOptions var1) {
      if (var0 == null) {
         return null;
      } else {
         IDExpression[] var2 = new IDExpression[var0.length];

         for (int var3 = 0; var3 < var0.length; var3++) {
            var2[var3] = pC(var0[var3], var1);
         }

         return var2;
      }
   }

   protected void pC(bph var1) {
      var1.kS = this.kS;
      var1.wS = this.wS;
      var1.UT = this.UT == null ? null : new LinkedHashMap(this.UT);
   }

   protected void A(bph var1) {
      var1.pC = this.pC;
      var1.kS = this.kS;
      var1.wS = this.wS;
      var1.UT = this.UT == null ? null : new LinkedHashMap(this.UT);
   }

   @Override
   public IJavaType getType() {
      return this.pC;
   }

   @Override
   public boolean checkType(IJavaType var1) {
      return this.pC == var1;
   }

   @Override
   public final boolean setType(IJavaType var1) {
      return this.setType(var1, null);
   }

   @Override
   public final boolean setType(IJavaType var1, DTypeInfo var2) {
      return this.setType(var1, var2, false);
   }

   @Override
   public boolean setType(IJavaType var1, DTypeInfo var2, boolean var3) {
      if ((var1 != null || this.pC != null) && (var1 == null || !var1.equals(this.pC))) {
         if (var3) {
            this.pC = var1;
            if (var2 != null) {
               var2.recordChanged();
            }

            return true;
         } else {
            if (this.pC == null) {
               if (var1.isLegal()) {
                  this.pC = var1;
                  if (var2 != null) {
                     var2.recordChanged();
                  }

                  return true;
               }
            } else {
               if (this.pC.canSetType(var1, true)) {
                  this.pC = var1;
                  if (var2 != null) {
                     var2.recordChanged();
                  }

                  return true;
               }

               if (this.pC.isObject() && var1.isObject()) {
                  if (var2 != null) {
                     var2.recordConflict("Cannot update type from some object type to another object type", this, this.pC, var1);
                  }

                  return false;
               }

               if (var1.isSmallIntegerWildcard() && this.pC.isSmallInt()) {
                  if (var2 != null) {
                     var2.recordConflict("Cannot update type from wildcard SMALLINT to defined SMALLINT", this, this.pC, var1);
                  }

                  return false;
               }
            }

            if (var2 != null) {
               var2.recordConflict("Cannot update type", this, this.pC, var1);
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

   public abstract boolean pC();

   @Override
   public void setCustomCanThrow(Boolean var1) {
      if (var1 == null) {
         this.removeData("customCanThrow");
      } else {
         this.setData("customCanThrow", var1);
      }
   }

   @Override
   public Boolean getCustomCanThrow() {
      return (Boolean)this.getData("customCanThrow");
   }

   public void pC(Boolean var1) {
      if (var1 == null) {
         this.removeData("customHasSideEffects");
      } else {
         this.setData("customHasSideEffects", var1);
      }
   }

   public Boolean A() {
      return (Boolean)this.getData("customHasSideEffects");
   }

   public void pC(IJavaType var1, IJavaType var2) {
      if (this.pC.equals(var1)) {
         this.pC = var2;
      }
   }

   @Override
   public final IDImm evaluate(IDMethodContext var1) throws DexDecEvaluationException {
      btp var2 = new btp((bpr)var1.getGlobalContext());

      IDImm var3;
      try {
         var3 = this.evaluate(var2);
      } finally {
         var2.dispose();
      }

      return var3;
   }

   @Override
   public final IDImm evaluate(IDGlobalContext var1, Map var2) throws DexDecEvaluationException {
      if (this instanceof IDInstruction) {
         throw new IllegalArgumentException("This method cannot be used to evaluate an instruction");
      } else if (this instanceof IDImm) {
         return (IDImm)this;
      } else {
         IDState var3 = var1.createState();

         IDImm var5;
         try {
            var3.setSubRoutineInvocationPolicy(0);
            var3.setFieldAccessPolicy(0);
            var3.enableEmulator(true);
            var3.enableSandbox(false);
            var3.setMaxIterationCount(1);
            var3.pushContext("context");
            IDEmuFrame var4 = var3.pushFrame("frame");
            var4.getVarMap().putAll(var2);
            var5 = this.evaluate(var3);
         } finally {
            var3.dispose();
         }

         return var5;
      }
   }

   @Override
   public IDImm spawn(long var1) {
      return new bps(var1, this.pC);
   }

   protected IJavaElement pC(IJavaElement var1) {
      ((bim)var1).pC(this.kS, this.wS);
      return var1;
   }

   @Override
   public String toString(IDMethodContext var1) {
      DFormattingContext var2 = new DFormattingContext(var1);
      this.format(var2);
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.toString(null);
   }

   @Override
   public final String format(IInstruction var1, long var2) {
      return this.toString();
   }

   @Override
   public boolean isInstruction() {
      return this instanceof bpv;
   }

   public void verify() {
   }

   @Override
   public boolean visitDepthPre(IDVisitor var1) {
      return this.visitDepthPre(var1, null, null);
   }

   @Override
   public boolean visitDepthPre(IDVisitor var1, IDExpression var2) {
      return this.visitDepthPre(var1, var2, null);
   }

   @Override
   public boolean visitDepthPre(IDVisitor var1, IDExpression var2, DVisitResults var3) {
      if (var3 == null) {
         var3 = new DVisitResults();
      }

      pC(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void pC(IDVisitor var0, IDExpression var1, IDExpression var2, DVisitResults var3) {
      var3.currentNode = var1;
      boolean var4 = false;
      if (var3.skipAssignmentDestination && var2 instanceof IDInstruction) {
         DOpcodeType var5 = ((IDInstruction)var2).getOpcode();
         if (var5 == DOpcodeType.IR_ASSIGN || var5 == DOpcodeType.IR_STORE_EXCEPTION) {
            IDExpression var6 = (IDExpression)var2.asInstruction().getOperand1();
            if (var6 == var1) {
               var4 = true;
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
            var1 = (IDExpression)var3.currentNode;
            var3.pushParent(var1);

            for (IDExpression var9 : var1.getSubExpressions()) {
               pC(var0, var9, var1, var3);
               if (var3.isInterruptedVisit()) {
                  return;
               }
            }

            var3.popParent();
         }
      }
   }

   @Override
   public boolean visitDepthPost(IDVisitor var1) {
      return this.visitDepthPost(var1, null, null);
   }

   @Override
   public boolean visitDepthPost(IDVisitor var1, IDExpression var2) {
      return this.visitDepthPost(var1, var2, null);
   }

   @Override
   public boolean visitDepthPost(IDVisitor var1, IDExpression var2, DVisitResults var3) {
      if (var3 == null) {
         var3 = new DVisitResults(1);
      }

      A(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void A(IDVisitor var0, IDExpression var1, IDExpression var2, DVisitResults var3) {
      var3.pushParent(var1);
      List var4 = var1.getSubExpressions();

      for (int var5 = 0; var5 < var4.size(); var5++) {
         IDExpression var6 = (IDExpression)var4.get(var5);
         if (var3.parents == null || !var3.parents.contains(var6)) {
            A(var0, var6, var1, var3);
            if (var3.isInterruptedVisit()) {
               return;
            }

            if (var6 instanceof IDVar) {
               var4 = var1.getSubExpressions();
            }
         }
      }

      var3.popParent();
      var3.setReplacedNode(var1);
      boolean var8 = false;
      if (var3.skipAssignmentDestination && var2 instanceof bpv) {
         DOpcodeType var9 = ((bpv)var2).getOpcode();
         if (var9 == DOpcodeType.IR_ASSIGN || var9 == DOpcodeType.IR_STORE_EXCEPTION) {
            IDExpression var7 = (IDExpression)var2.asInstruction().getOperand1();
            if (var7 == var1) {
               var8 = true;
            }
         }
      }

      if (!var8) {
         var0.process((bph)var1, (bph)var2, var3);
      }
   }

   @Override
   public IDExpression findParent(IDExpression var1) {
      return this.findParent(var1, 0);
   }

   @Override
   public IDExpression findParent(IDExpression var1, int var2) {
      Couple var3 = this.find(var1, var2, 0, null);
      return var3 == null ? null : (IDExpression)var3.getFirst();
   }

   @Override
   public Couple find(IDExpression var1, int var2, int var3, IDExpression var4) {
      Couple[] var5 = new Couple[1];
      if (!this.visitDepthPre(new bpi(this, var3, var1, var2, var5))) {
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
   public IDExpression findByType(Class var1) {
      return this.findByType(var1, 0);
   }

   @Override
   public IDExpression findByType(Class var1, int var2) {
      IDExpression[] var3 = new IDExpression[1];
      return !this.visitDepthPre(new bpj(this, var1, var2, var3)) ? null : var3[0];
   }

   protected static boolean pC(IDExpression[] var0, IDExpression[] var1, boolean var2) {
      if (var0.length != var1.length) {
         return false;
      } else {
         for (int var3 = 0; var3 < var0.length; var3++) {
            IDExpression var4 = var0[var3];
            IDExpression var5 = var1[var3];
            if ((var4 != null || var5 != null) && (var4 == null || !var4.equalsEx(var5, var2))) {
               return false;
            }
         }

         return true;
      }
   }

   protected static boolean pC(Collection var0, Collection var1, boolean var2) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         Iterator var3 = var0.iterator();
         Iterator var4 = var1.iterator();

         while (var3.hasNext() && var4.hasNext()) {
            IDExpression var5 = (IDExpression)var3.next();
            IDExpression var6 = (IDExpression)var4.next();
            if ((var5 != null || var6 != null) && (var5 == null || !var5.equalsEx(var6, var2))) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void transferMetadataFrom(IDExpression var1) {
      this.kS = ((bph)var1).kS;
      this.wS = ((bph)var1).wS;
      this.UT = ((bph)var1).UT;
   }
}
