package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.SortedMap;

public class brz {
   private static final ILogger pC = GlobalLog.getLogger(brz.class);
   private IDMethodContext A;
   private boolean kS;

   public brz(IDMethodContext var1) {
      this.A = var1;
   }

   public void pC(boolean var1) {
      this.kS = var1;
   }

   public void pC() {
      IDGlobalContext var1 = this.A.getGlobalContext();
      IJavaTypeFactory var2 = this.A.getTypeFactory();
      CFG var3 = this.A.getCfg();
      DTypeInfo var4 = new DTypeInfo(this.A);
      Object[] var10000 = new Object[0];
      var4.reset();
      SortedMap var5 = this.A.getParametersTypeMap();

      for (IDVar var7 : this.A.getParameterVariables()) {
         int var8 = var7.getId();
         int var9 = this.A.retrievePhysicalRegisterId(var8);
         IJavaType var10 = (IJavaType)var5.get(var9);
         if (var10 != null) {
            var7.setType(var10, var4, true);
            var10000 = new Object[]{var7};
         }
      }

      var10000 = new Object[0];
      var4.reset();

      for (IDInstruction var17 : var3.instructions()) {
         var17.visitDepthPost(new bsa(this, var4, var1));
      }

      var4.reset();
      IJavaType var16 = (IJavaType)var5.get(-1);
      if (!var16.isVoid()) {
         var10000 = new Object[0];
         if (var16 == var2.getInt()) {
            var16 = var2.getSmallIntWildcard();
         } else if (var16.isClassOrInterface()) {
            var16 = var2.createWildcardType(var16.getName(), true);
         }

         for (BasicBlock var19 : var3) {
            IDInstruction var21 = (IDInstruction)var19.getLast();
            if (var21.isReturn()) {
               IDExpression var28 = var21.getReturnExpression();
               if (var28 != null) {
                  var28.setType(var16, var4, false);
               }
            }
         }
      }

      var10000 = new Object[0];

      int var20;
      for (var20 = 0; var20 < 50; var20++) {
         var4.reset();
         if (!this.kS) {
            for (IDInstruction var29 : var3.instructions()) {
               var29.updateTypes(var4);
            }
         } else {
            HashSet var23 = new HashSet();
            ArrayDeque var30 = new ArrayDeque();
            var30.add(var3.getEntryBlock());

            while (!var30.isEmpty()) {
               BasicBlock var11 = (BasicBlock)var30.remove();
               if (var23.add(var11.getBase())) {
                  for (IDInstruction var13 : var11) {
                     var13.updateTypes(var4);
                  }

                  var30.addAll(var11.getOutputs());
                  var30.addAll(var11.getIrregularOutputs());
               }
            }
         }

         if (var4.getChangedCounter() == 0) {
            break;
         }
      }

      if (var20 >= 50) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("IR typing not terminating"), this.A.getMethodSignature());
      }

      var10000 = new Object[0];
      var4.reset();

      for (IDInstruction var31 : var3.instructions()) {
         var31.visitDepthPost(new bsb(this, var4, var1));
      }

      if (!var16.isVoid()) {
         var10000 = new Object[0];

         for (BasicBlock var32 : var3) {
            IDInstruction var35 = (IDInstruction)var32.getLast();
            if (var35.isReturn()) {
               IDExpression var38 = var35.getReturnExpression();
               if (var38 != null) {
                  IJavaType var41 = var38.getType();
                  if (var16.isBoolean() && var41.isSmallInt()) {
                     IDOperation var14 = var1.createPredicate(JavaOperatorType.NE, var38, var1.createImm(0L, var38.getType()));
                     var35.setReturnExpression(var14);
                  }
               }
            }
         }
      }

      var10000 = new Object[0];

      for (IDInstruction var33 : var3.instructions()) {
         if (var33.isAssign() && var33.getAssignDestination().isVar() && var33.getAssignSource() instanceof IDImm var36 && !var36.isString()) {
            IDVar var40 = (IDVar)var33.getAssignDestination();
            if (var36.getType() != var40.getType()) {
               var36.setType(var40.getType(), var4, true);
            }
         }
      }

      var10000 = new Object[0];

      for (IDInstruction var34 : var3.instructions()) {
         bpv var37 = (bpv)var34;
         var37.pC(var2.getSmallIntWildcard(), var2.getInt());
         var37.pC(var2.getSingleSlotWildcard(), var2.getInt());
         var37.pC(var2.getDoubleSlotWildcard(), var2.getLong());
         var37.pC(var2.getGenericObjectWildcard(), var2.getJavaLangObject());
         var34.visitDepthPost(new bsc(this, var2, var4));
      }

      var10000 = new Object[0];
   }
}
