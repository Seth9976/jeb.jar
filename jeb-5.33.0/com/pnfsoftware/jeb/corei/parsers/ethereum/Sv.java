package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sv implements IGlobalAnalyzer {
   private static final ILogger wS = GlobalLog.getLogger(Sv.class);
   INativeCodeUnit pC;
   INativeDecompilerUnit A;
   yt kS;
   private ITypeManager UT;
   private List E;
   private Map sY = new LinkedHashMap();
   private INativeClassItem ys;
   private List ld = new ArrayList();
   private List gp = new ArrayList();

   public Sv(INativeDecompilerUnit var1) {
      this.A = var1;
      this.pC = (INativeCodeUnit)var1.getParent();
      this.kS = (yt)this.pC.getParent();
      this.UT = this.pC.getTypeManager();
   }

   private List pC() {
      ArrayList var1 = new ArrayList();

      for (INativeMethodItem var3 : this.pC.getInternalMethodsLeafFirst()) {
         var1.add(var3.getData());
      }

      return var1;
   }

   @Override
   public boolean perform() {
      try {
         boolean var2;
         try (ACLock var1 = this.pC.getLock().a()) {
            this.A();
            var2 = true;
         }

         return var2;
      } catch (Exception var6) {
         wS.catching(var6);
         return false;
      }
   }

   private boolean A() {
      boolean var15;
      try (ACLock var1 = this.pC.getLock().a()) {
         this.E = this.pC();

         for (INativeMethodDataItem var3 : this.E) {
            long var4 = var3.getMemoryAddress();
            INativeMethodItem var6 = var3.getRoutine();
            if (var6 != null) {
               this.sY.put(var4, var6);
            }
         }

         if (this.sY.isEmpty()) {
            return false;
         }

         for (INativeMethodItem var12 : this.sY.values()) {
            new Sv.Av(var12).pC();
         }

         dE var10 = new dE(this.pC, this.A);

         for (INativeMethodItem var16 : this.sY.values()) {
            var10.pC(var16);
         }

         var10.pC();

         for (INativeContinuousItem var17 : var10.pC) {
            this.ld.add((INativeDataItem)var17);
         }

         this.kS();
         var15 = true;
      }

      return var15;
   }

   private void kS() {
      for (INativeMethodItem var2 : this.sY.values()) {
         this.pC(var2, null);
      }

      String var10 = "DecompiledContract";
      IClassType var11 = this.UT.createClassType(var10, 1, 0);
      INativeClassItem var3 = this.pC.getClassManager().createClass(var11);
      this.ys = var3;
      ArrayList var4 = new ArrayList();

      for (String var6 : this.kS.UT()) {
         var4.add(this.UT.createClassType(var6));
      }

      this.UT.setClassSuperTypes(var11, var4, null);
      this.UT.completeClassTypeInitialization(var11);
      ArrayList var12 = new ArrayList();
      yt var13 = (yt)this.pC.getParent();

      for (eW var8 : var13.wS()) {
         if (this.sY.containsKey((long)var8.A())) {
            var12.add((INativeMethodItem)this.sY.get((long)var8.A()));
         }
      }

      for (INativeMethodItem var18 : var12) {
         this.pC.getClassManager().addNonVirtualMethod(var3, var18);
      }

      for (INativeDataItem var19 : this.ld) {
         INativeFieldItem var9 = this.pC.getFieldManager().createField(var3, var19);
         this.gp.add(var9);
      }

      this.pC.getClassManager().completeClassInitialization(var3);

      for (INativeMethodItem var20 : this.sY.values()) {
         this.pC.getPackageManager().moveToClass(var20, this.ys);
      }

      for (INativeFieldItem var21 : this.gp) {
         this.pC.getPackageManager().moveToClass(var21, this.ys);
      }
   }

   private IDecompiledMethod pC(INativeMethodItem var1) {
      return this.pC(var1, NativeDecompilationStage.LIFTING);
   }

   private IDecompiledMethod pC(INativeMethodItem var1, NativeDecompilationStage var2) {
      try {
         DecompilationContext var3 = new DecompilationContext(64);
         return this.A.decompileMethodEx(var1, var3, var2);
      } catch (Exception var4) {
         wS.catchingSilent(var4);
         return null;
      }
   }

   class Av {
      INativeMethodItem pC;
      IDecompiledMethod A;
      oP kS;
      IERoutineContext wS;
      CFG UT;

      Av(INativeMethodItem var2) {
         this.pC = var2;
      }

      boolean pC() {
         this.A = Sv.this.pC(this.pC);
         if (this.A != null && !this.A.isFailure()) {
            this.kS = (oP)this.A.getConverter();
            this.wS = this.A.getIRContext();
            this.UT = this.wS.getCfg();
            eW var1 = Sv.this.kS.kS(this.pC.getMemoryAddress());
            if (var1 != null) {
               boolean var2 = this.A();
               var1.pC(var2);
            }

            return true;
         } else {
            return false;
         }
      }

      private boolean A() {
         this.UT.doDataFlowAnalysis();
         int var1 = 0;
         IEStatement var2 = (IEStatement)this.UT.getInstruction(var1);
         if (EUtil.isUnconditionalJump(var2)) {
            var1 = ((IEJump)var2).getBranchAddress();
            var2 = (IEStatement)this.UT.getInstruction(var1);
         }

         if (EUtil.isConditionalJump(var2) && var2.getSize() == 1) {
            IEJump var3 = (IEJump)var2;
            int var4 = var3.getBranchAddress();
            if (var4 != var1 + 2) {
               return false;
            } else {
               IEStatement var5 = (IEStatement)this.UT.getInstruction(var1 + 1);
               if (!oP.pC(var5) && !oP.kS(var5) && !oP.wS(var5)) {
                  return false;
               } else {
                  boolean var6 = false;
                  IEGeneric var7 = var3.getCondition();
                  OperationType var8 = EUtil.getOperation(var7, OperationType.LOG_EQ, OperationType.LOG_NOT);
                  if (var8 != null) {
                     IEGeneric var9 = null;
                     if (var8 == OperationType.LOG_EQ) {
                        if (EUtil.isZero(((IEOperation)var7).getOperand2())) {
                           var9 = ((IEOperation)var7).getOperand1();
                        }
                     } else if (EUtil.isOperation(var7, OperationType.LOG_NOT)) {
                        var9 = ((IEOperation)var7).getOperand1();
                     }

                     if (var9 instanceof IEVar) {
                        int var10 = ((IEVar)var9).getId();
                        Integer var11 = this.wS.getUnderlyingRegisterId(var10);
                        if (var11 != null && var11 == this.kS.Ek.getId()) {
                           var6 = true;
                        }
                     }
                  }

                  if (!var6) {
                     return false;
                  } else {
                     var3.setCondition(this.kS.Er);
                     this.UT.invalidateDataFlowAnalysis();
                     this.A.getIROptimizer().perform();
                     return true;
                  }
               }
            }
         } else {
            return false;
         }
      }
   }
}
