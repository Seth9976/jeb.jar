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

public class CU implements IGlobalAnalyzer {
   private static final ILogger Dw = GlobalLog.getLogger(CU.class);
   INativeCodeUnit q;
   INativeDecompilerUnit RF;
   PY xK;
   private ITypeManager Uv;
   private List oW;
   private Map gO = new LinkedHashMap();
   private INativeClassItem nf;
   private List gP = new ArrayList();
   private List za = new ArrayList();

   public CU(INativeDecompilerUnit var1) {
      this.RF = var1;
      this.q = (INativeCodeUnit)var1.getParent();
      this.xK = (PY)this.q.getParent();
      this.Uv = this.q.getTypeManager();
   }

   private List RF() {
      ArrayList var1 = new ArrayList();

      for (INativeMethodItem var3 : this.q.getInternalMethodsLeafFirst()) {
         var1.add(var3.getData());
      }

      return var1;
   }

   public INativeClassItem q() {
      return this.nf;
   }

   @Override
   public boolean perform() {
      try {
         boolean var2;
         try (ACLock var1 = this.q.getLock().a()) {
            this.xK();
            var2 = true;
         }

         return var2;
      } catch (Exception var6) {
         Dw.catching(var6);
         return false;
      }
   }

   private boolean xK() {
      boolean var15;
      try (ACLock var1 = this.q.getLock().a()) {
         this.oW = this.RF();

         for (INativeMethodDataItem var3 : this.oW) {
            long var4 = var3.getMemoryAddress();
            INativeMethodItem var6 = var3.getRoutine();
            if (var6 != null) {
               this.gO.put(var4, var6);
            }
         }

         if (this.gO.isEmpty()) {
            return false;
         }

         for (INativeMethodItem var12 : this.gO.values()) {
            new CU.eo(var12).q();
         }

         Pl var10 = new Pl(this.q, this.RF);

         for (INativeMethodItem var16 : this.gO.values()) {
            var10.q(var16);
         }

         var10.q();

         for (INativeContinuousItem var17 : var10.q) {
            this.gP.add((INativeDataItem)var17);
         }

         this.Dw();
         var15 = true;
      }

      return var15;
   }

   private void Dw() {
      for (INativeMethodItem var2 : this.gO.values()) {
         this.q(var2, null);
      }

      String var10 = "DecompiledContract";
      IClassType var11 = this.Uv.createClassType(var10, 1, 0);
      INativeClassItem var3 = this.q.getClassManager().createClass(var11);
      this.nf = var3;
      ArrayList var4 = new ArrayList();

      for (String var6 : this.xK.gO()) {
         var4.add(this.Uv.createClassType(var6));
      }

      this.Uv.setClassSuperTypes(var11, var4, null);
      this.Uv.completeClassTypeInitialization(var11);
      ArrayList var12 = new ArrayList();
      PY var13 = (PY)this.q.getParent();

      for (qx var8 : var13.oW()) {
         if (this.gO.containsKey((long)var8.Dw())) {
            var12.add((INativeMethodItem)this.gO.get((long)var8.Dw()));
         }
      }

      for (INativeMethodItem var18 : var12) {
         this.q.getClassManager().addNonVirtualMethod(var3, var18);
      }

      for (INativeDataItem var19 : this.gP) {
         INativeFieldItem var9 = this.q.getFieldManager().createField(var3, var19);
         this.za.add(var9);
      }

      this.q.getClassManager().completeClassInitialization(var3);

      for (INativeMethodItem var20 : this.gO.values()) {
         this.q.getPackageManager().moveToClass(var20, this.nf);
      }

      for (INativeFieldItem var21 : this.za) {
         this.q.getPackageManager().moveToClass(var21, this.nf);
      }
   }

   private IDecompiledMethod q(INativeMethodItem var1) {
      return this.q(var1, NativeDecompilationStage.LIFTING);
   }

   private IDecompiledMethod q(INativeMethodItem var1, NativeDecompilationStage var2) {
      try {
         DecompilationContext var3 = new DecompilationContext(64);
         return this.RF.decompileMethodEx(var1, var3, var2);
      } catch (Exception var4) {
         Dw.catchingSilent(var4);
         return null;
      }
   }

   class eo {
      INativeMethodItem q;
      IDecompiledMethod RF;
      tl xK;
      IERoutineContext Dw;
      CFG Uv;

      eo(INativeMethodItem var2) {
         this.q = var2;
      }

      boolean q() {
         this.RF = CU.this.q(this.q);
         if (this.RF != null && !this.RF.isFailure()) {
            this.xK = (tl)this.RF.getConverter();
            this.Dw = this.RF.getIRContext();
            this.Uv = this.Dw.getCfg();
            qx var1 = CU.this.xK.xK(this.q.getMemoryAddress());
            if (var1 != null) {
               boolean var2 = this.RF();
               var1.q(var2);
            }

            return true;
         } else {
            return false;
         }
      }

      private boolean RF() {
         this.Uv.doDataFlowAnalysis();
         int var1 = 0;
         IEStatement var2 = (IEStatement)this.Uv.getInstruction(var1);
         if (EUtil.isUnconditionalJump(var2)) {
            var1 = ((IEJump)var2).getBranchAddress();
            var2 = (IEStatement)this.Uv.getInstruction(var1);
         }

         if (EUtil.isConditionalJump(var2) && var2.getSize() == 1) {
            IEJump var3 = (IEJump)var2;
            int var4 = var3.getBranchAddress();
            if (var4 != var1 + 2) {
               return false;
            } else {
               IEStatement var5 = (IEStatement)this.Uv.getInstruction(var1 + 1);
               if (!tl.q(var5) && !tl.xK(var5) && !tl.Dw(var5)) {
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
                        Integer var11 = this.Dw.getUnderlyingRegisterId(var10);
                        if (var11 != null && var11 == this.xK.wF.getId()) {
                           var6 = true;
                        }
                     }
                  }

                  if (!var6) {
                     return false;
                  } else {
                     var3.setCondition(this.xK.Dz);
                     this.Uv.invalidateDataFlowAnalysis();
                     this.RF.getIROptimizer().perform();
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
