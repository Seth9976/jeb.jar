package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ccp extends AbstractDOptimizer {
   private static final ILogger oT = GlobalLog.getLogger(ccp.class);
   static final String pC = S.L("Unreflected");
   static final String A = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         32,
         69,
         65,
         66,
         74,
         2,
         30,
         18,
         70,
         93,
         66,
         124,
         84,
         65,
         69,
         103,
         62,
         11,
         2,
         26,
         4,
         15,
         13,
         15,
         10,
         72,
         64,
         33,
         84,
         27,
         29,
         29,
         71,
         90,
         79,
         42,
         3,
         13,
         31,
         0,
         91,
         9,
         18,
         64,
         71,
         110,
         47,
         0,
         65,
         1,
         26,
         92
      },
      2,
      163
   );
   static final String kS = ckx.pC(
      new byte[]{
         -37,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         22,
         19,
         88,
         9,
         29,
         60,
         47,
         12,
         8,
         77,
         100,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         124,
         39,
         6,
         27,
         7,
         9,
         92,
         97,
         22,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         63,
         35,
         14,
         5,
         1,
         23,
         73,
         18,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72
      },
      1,
      151
   );
   static final String wS = ckx.pC(
      new byte[]{
         61,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         22,
         19,
         89,
         2,
         17,
         55,
         44,
         1,
         29,
         7,
         6,
         7,
         22,
         23,
         27,
         29,
         90,
         115,
         23,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         18,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         93,
         23,
         3,
         10,
         9,
         6,
         23,
         91,
         108,
         44,
         1,
         29,
         7,
         6,
         7,
         22,
         23,
         27,
         29,
         73
      },
      1,
      113
   );
   static final String UT = ckx.pC(
      new byte[]{
         -4,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         22,
         19,
         89,
         2,
         17,
         48,
         33,
         6,
         15,
         13,
         19,
         23,
         1,
         39,
         44,
         1,
         29,
         7,
         6,
         7,
         22,
         23,
         27,
         29,
         90,
         115,
         23,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         18,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         93,
         23,
         3,
         10,
         9,
         6,
         23,
         91,
         108,
         44,
         1,
         29,
         7,
         6,
         7,
         22,
         23,
         27,
         29,
         73
      },
      1,
      176
   );
   static final String E = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         32,
         69,
         65,
         66,
         74,
         2,
         30,
         18,
         71,
         87,
         68,
         127,
         80,
         88,
         72,
         32,
         22,
         73,
         47,
         6,
         4,
         86,
         0,
         65,
         8,
         78,
         1,
         21,
         15,
         58,
         0,
         1,
         73,
         15,
         1,
         93,
         50,
         32,
         3,
         0,
         2,
         4,
         92,
         66,
         65,
         47,
         11,
         67,
         99,
         30,
         8,
         20,
         27,
         79,
         90,
         108,
         24,
         4,
         5,
         4,
         93,
         26,
         4,
         10,
         73,
         108,
         29,
         21,
         31,
         30,
         12,
         4,
         28,
         91,
         109,
         77,
         23,
         65,
         79,
         85,
         2
      },
      2,
      190
   );
   static final String sY = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         32,
         69,
         65,
         66,
         74,
         2,
         30,
         18,
         71,
         87,
         68,
         118,
         80,
         79,
         76,
         46,
         0,
         4,
         7,
         33,
         0,
         84,
         9,
         1,
         0,
         7,
         35,
         24,
         65,
         31,
         21,
         92,
         76,
         0,
         8,
         1,
         70,
         63,
         29,
         19,
         29,
         11,
         20,
         21,
         123,
         13,
         6,
         13,
         86,
         19,
         70,
         11,
         9,
         26,
         20,
         15,
         49,
         9,
         18,
         22,
         1,
         77,
         76,
         40,
         68,
         34,
         25,
         17,
         86,
         30,
         8,
         9,
         15,
         91,
         82,
         77,
         5,
         69,
         69,
         82,
         77,
         22,
         126,
         73,
         84,
         90,
         95,
         86,
         14
      },
      2,
      34
   );
   static final String ys = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         32,
         69,
         65,
         66,
         74,
         2,
         30,
         18,
         71,
         87,
         68,
         116,
         92,
         73,
         76,
         43,
         90,
         45,
         9,
         13,
         19,
         65,
         78,
         2,
         5,
         65,
         8,
         93,
         115,
         29,
         6,
         26,
         78,
         6,
         93,
         79,
         37,
         6,
         8,
         23,
         21,
         74,
         31,
         79,
         78,
         38,
         67,
         30,
         69,
         20,
         5,
         2,
         11,
         0,
         92,
         102,
         27,
         0,
         31,
         1,
         73
      },
      2,
      121
   );
   static final String ld = ckx.pC(
      new byte[]{
         -39,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         22,
         19,
         89,
         2,
         17,
         48,
         33,
         6,
         15,
         13,
         19,
         23,
         1,
         34,
         47,
         12,
         9,
         8,
         76,
         100,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         124,
         39,
         6,
         27,
         7,
         9,
         92,
         18,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         93,
         23,
         3,
         10,
         9,
         6,
         23,
         91,
         105,
         47,
         12,
         9,
         8,
         95
      },
      1,
      149
   );
   static final Set gp = new HashSet();
   private static final String fI = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         17,
         76,
         70,
         93,
         92,
         90,
         71,
         3,
         109,
         87,
         68,
         90,
         90,
         72,
         27,
         98,
         76,
         8,
         13,
         26,
         10,
         75,
         4,
         70,
         40,
         69,
         14,
         4,
         65,
         70,
         24,
         18,
         78,
         6,
         73,
         41,
         11,
         6,
         12,
         2,
         0,
         94,
         40,
         98,
         74,
         32,
         26,
         13,
         15,
         30,
         8,
         9,
         15,
         91,
         60,
         66,
         24,
         0,
         16,
         17,
         73,
         95,
         41,
         14,
         79,
         53,
         14,
         95,
         21,
         19,
         7,
         0,
         71,
         59,
         66,
         66,
         6,
         74,
         84,
         10
      },
      2,
      118
   );
   private static final String WR = ckx.pC(
      new byte[]{
         107,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         93,
         23,
         3,
         10,
         9,
         6,
         23,
         91,
         108,
         44,
         1,
         29,
         7,
         6,
         7,
         22,
         23,
         27,
         29,
         73,
         22,
         19,
         80,
         11,
         18,
         62,
         39,
         29,
         7,
         21,
         15,
         13,
         6,
         77,
         115,
         23,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         96,
         45,
         8,
         15,
         6,
         23,
         79,
         18,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         96,
         45,
         8,
         15,
         6,
         23,
         79
      },
      1,
      39
   );
   private static final Map NS = new HashMap();
   private static final Map vP = new HashMap();
   private static final Map xC = new HashMap();
   private static final Set ED = new HashSet();
   private static Set Sc = new HashSet();
   private boolean ah = true;
   private static final Map eP = new HashMap();
   private static final Map UO = new HashMap();

   public ccp() {
      super(DOptimizerType.UNSAFE);
      this.getPluginInformation().setDescription(S.L("Code unreflection"));
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      int var1 = 0;
      ccp.Av var2 = new ccp.Av();

      for (BasicBlock var4 : this.cfg) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            var6.visitInstructionPostOrder(var2, true);
         }
      }

      var1 += var2.pC;
      HashSet var22 = new HashSet();
      ccp.Sv var23 = new ccp.Sv();

      for (BasicBlock var26 : this.cfg) {
         for (int var7 = 0; var7 < var26.size(); var7++) {
            IDInstruction var8 = (IDInstruction)var26.get(var7);
            var23.pC(var8);
            var8.visitInstruction(var23, true);
            if (var23.kS != null) {
               IDInstruction var9 = var23.kS;
               var9.copyBaseFields(var8);
               var26.set(var7, var9);
            }

            if (var23.A > 0) {
               var1 += var23.A;
               var22.add(var26);
            }
         }
      }

      int var25 = 0;

      for (BasicBlock var30 : this.cfg) {
         for (int var32 = 0; var32 < var30.size(); var32++) {
            IDInstruction var34 = (IDInstruction)var30.get(var32);
            if (var34.getOpcode() == DOpcodeType.IR_INVOKE && var34.getOperand2() instanceof IDCallInfo) {
               IDCallInfo var10 = (IDCallInfo)var34.getOperand2();
               if ("Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;".equals(var10.getMethodSignature())) {
                  IDExpression var11 = var10.getArgument(0);
                  if (var11 instanceof IDImm && ((IDImm)var11).isString()) {
                     String var12 = ((IDImm)var11).getStringValue(this.g);
                     if (var12.startsWith("java.")) {
                        var34.transformToNop();
                        var25++;
                     }
                  }
               }
            }
         }
      }

      try {
         if (this.ah && var1 > 0 && !this.ctx.getExceptionData().isEmpty()) {
            IDTryData var28 = this.ctx.getExceptionData();
            HashSet var31 = new HashSet();
            int var33 = 0;

            for (BasicBlock var37 : var22) {
               int var39 = (int)var37.getAddress();
               ArrayList var41 = new ArrayList(var28.getBlockHandlers(var39));
               if (var41.size() >= 1) {
                  IDExceptionHandler var13 = (IDExceptionHandler)var41.get(var41.size() - 1);
                  if (var13.isCatchAll(this.ctx)) {
                     int var14 = var13.getAddress();
                     BasicBlock var15 = this.cfg.getBlockAt((long)var14);
                     if (var15 != null && this.cfg.deleteIrregularEdge(var37, var15)) {
                        var31.add(var14);
                        var28.unprotectBlock(var39, var14, var13.getTypeIndex());
                        var33++;
                     }
                  }

                  for (IDExceptionHandler var46 : var41) {
                     String var16 = DUtil.getExceptionSignature(this.ctx, var46);
                     if (ED.contains(var16)) {
                        int var17 = var46.getAddress();
                        BasicBlock var18 = this.cfg.getBlockAt((long)var17);
                        if (var18 != null && this.cfg.deleteIrregularEdge(var37, var18)) {
                           var31.add(var17);
                           var28.unprotectBlock(var39, var17, var46.getTypeIndex());
                           var33++;
                        }
                     }
                  }
               }
            }

            for (BasicBlock var38 : this.cfg) {
               if (var38.irroutsize() > 0) {
                  int var40 = (int)var38.getAddress();
                  List var42 = var28.getBlockHandlers(var40);
                  if (var42.size() >= 1) {
                     IDExceptionHandler var43 = (IDExceptionHandler)var42.get(var42.size() - 1);
                     if (var43.isCatchAll(this.ctx)) {
                        int var45 = var43.getAddress();
                        if (var31.contains(var45) && this.cfg.deleteIrregularEdge(var38, this.cfg.getBlockAt((long)var45))) {
                           var28.unprotectBlock(var40, var45, var43.getTypeIndex());
                           var33++;
                        }
                     }
                  }
               }
            }

            if (var33 > 0) {
               this.cleanGraph();
            }
         }
      } catch (Exception var19) {
         oT.catchingSilent(var19);
      }

      var1 += var25;
      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
         if (((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.decomp).sY() >= 2) {
            String var29 = ckx.pC(
               new byte[]{
                  85, 60, 1, 26, 83, 77, 8, 17, 28, 7, 11, 68, 67, 12, 1, 26, 21, 8, 7, 29, 83, 85, 27, 28, 23, 3, 10, 9, 6, 23, 17, 1, 68, 67, 12, 11, 1
               },
               1,
               1
            );
            this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var29);
         }
      }

      return var1;
   }

   IDExpression pC(IDCallInfo var1, IDInstruction var2) {
      IDExpression var3 = var1.getArgument(0);
      IDExpression var4 = var1.getArgument(1);
      if (!(var3 instanceof IDCallInfo var5)) {
         return null;
      } else {
         String var6 = var5.getMethodSignature();
         int var7 = Arrays.asList(wS, UT).indexOf(var6);
         if (var7 < 0) {
            return null;
         } else {
            boolean var8 = var7 == 1;
            IDExpression var9 = var5.getArgument(0);
            IDExpression var10 = var5.getArgument(1);
            IJavaType var11 = this.A(var9, var2);
            if (var11 == null) {
               return null;
            } else {
               String var12 = var11.getSignature();
               String var13 = this.pC(var10, var2);
               if (var13 == null) {
                  return null;
               } else {
                  String[] var14 = new String[1];
                  String var15 = "<init>";
                  IJLSMethod var16 = this.g.getTypeInfoProvider().findMethod(var12, var15, var13, var8, var14);
                  if (var16 == null) {
                     String var23 = Strings.ff("%s->%s%sV", var12, var15, var13);
                     if (Sc.add(var23)) {
                        oT.trace("A candidate constructor for unreflection was not found: %s", var23);
                     }

                     return null;
                  } else {
                     ArrayList var17 = new ArrayList();
                     if (var4 instanceof IDNewArrayInfo) {
                        List var18 = ((IDNewArrayInfo)var4).getInitialValues();
                        var17.addAll(var18);
                     } else if (!(var4 instanceof IDImm) || !((IDImm)var4).isNullRef()) {
                        return null;
                     }

                     String var24 = var14[0] + "->" + var16.getSignature();
                     IDexMethod var19 = this.g.getDex().addMethod(var24);
                     IDNewInfo var20 = this.g.createNewInfo(var11, var11, this.g.createIndex(var19.getIndex()), var17, var24);
                     var20.transferMetadataFrom(var1);
                     var20.setOrigin(pC);
                     this.A(var20);
                     var20.upgradeMistypedArguments(null, this.g);
                     String var21 = DUtil.generateNativeAddress(this.ctx, var1);
                     DexDecompilerEvent.UnreflectedInvocation var22 = new DexDecompilerEvent.UnreflectedInvocation(var24, var21);
                     this.decomp.recordDecompilationEvent(var22);
                     this.dex.getCommentManager().addMetaComment(var21, new MetaComment(var22.format(true, false), 1), false);
                     this.dex.getReferenceManager().addMethodReference(var24, var21, DexReferenceType.INVOKE_REFLECTED);
                     return var20;
                  }
               }
            }
         }
      }
   }

   IDExpression A(IDCallInfo var1, IDInstruction var2) {
      IDExpression var3 = var1.getArgument(0);
      IDExpression var4 = var1.getArgument(1);
      IDExpression var5 = var1.getArgument(2);
      if (var3 instanceof IDVar var6) {
         this.analyzeChains();
         Long var7 = this.dfa.checkSingleDef(var2.getOffset(), var6.getId());
         if (var7 != null) {
            IDInstruction var8 = (IDInstruction)this.cfg.getInstruction(var7);
            if (var8 != null && var8.isAssignToVar(var6.getId())) {
               var3 = var8.getAssignSource();
            }
         }
      }

      if (!(var3 instanceof IDCallInfo var30)) {
         return null;
      } else {
         String var31 = var30.getMethodSignature();
         int var32 = Arrays.asList(E, sY).indexOf(var31);
         if (var32 < 0) {
            return null;
         } else {
            boolean var9 = var32 == 1;
            IDExpression var10 = var30.getArgument(0);
            IDExpression var11 = var30.getArgument(1);
            IJavaType var12 = this.A(var10, var2);
            if (var12 == null) {
               return null;
            } else {
               String var13 = var12.getSignature();
               String var14 = this.pC(var11);
               if (var14 == null) {
                  return null;
               } else {
                  String var15 = this.pC(var30.getArgument(2), var2);
                  if (var15 == null) {
                     return null;
                  } else {
                     String[] var16 = new String[1];
                     IJLSMethod var17 = this.g.getTypeInfoProvider().findMethod(var13, var14, var15, var9, var16);
                     if (var17 == null) {
                        String var33 = Strings.ff("%s->%s%s?", var13, var14, var15);
                        if (Sc.add(var33)) {
                           oT.trace("A candidate method for unreflection was not found: %s", var33);
                        }

                        return null;
                     } else {
                        boolean var18 = (var17.getAccessFlags() & 8) != 0;
                        String var19 = var16[0] + "->" + var17.getSignature();
                        int var20 = var19.indexOf(41);
                        String var21 = var19.substring(var20 + 1);
                        IJavaType var22 = this.tf.createType(var21);
                        ArrayList var23 = new ArrayList();
                        if (!var18) {
                           var23.add(var4);
                        }

                        if (var5 != null) {
                           if (var5 instanceof IDNewArrayInfo) {
                              List var24 = ((IDNewArrayInfo)var5).getInitialValues();
                              var23.addAll(var24);
                           } else if (!(var5 instanceof IDImm) || !((IDImm)var5).isNullRef()) {
                              return null;
                           }
                        }

                        JvmMethodSig var34 = JvmMethodSig.parse(var19);
                        if (!var18) {
                           var34.partypes.add(0, var34.csig);
                        }

                        if (var23.size() != var34.partypes.size()) {
                           com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("unexpected parameter count, will not unreflect"), var19);
                           return null;
                        } else {
                           IDexMethod var25 = this.g.getDex().addMethod(var19);
                           IDCallInfo var26 = this.g
                              .createCallInfo(this.g.createIndex(var25.getIndex()), var23, var22, var19, var18 ? DInvokeType.STATIC : DInvokeType.VIRTUAL);
                           var26.transferMetadataFrom(var1);
                           var26.setOrigin(pC);
                           this.A(var26);
                           var26.upgradeMistypedArguments(null, this.g);
                           IDExpression var27 = this.A(var26, var34.rettype);
                           ((bpp)var30).setCustomCanThrow(false);
                           ((bpp)var30).pC(false);
                           String var28 = DUtil.generateNativeAddress(this.ctx, var1);
                           DexDecompilerEvent.UnreflectedInvocation var29 = new DexDecompilerEvent.UnreflectedInvocation(var19, var28);
                           this.decomp.recordDecompilationEvent(var29);
                           this.dex.getCommentManager().addMetaComment(var28, new MetaComment(var29.format(true, false), 1), false);
                           this.dex.getReferenceManager().addMethodReference(var19, var28, DexReferenceType.INVOKE_REFLECTED);
                           return var27;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   IDExpression pC(IDCallInfo var1, boolean var2, IDInstruction var3) {
      String var4;
      if (var2) {
         var4 = (String)NS.get(var1.getMethodSignature());
      } else {
         var4 = (String)vP.get(var1.getMethodSignature());
      }

      Assert.a(var4 != null);
      if (var4.equals("L")) {
         var4 = null;
      }

      IDExpression var5 = var1.getArgument(0);
      if (var5 instanceof IDVar var6) {
         this.analyzeChains();
         Long var7 = this.dfa.checkSingleDef(var3.getOffset(), var6.getId());
         if (var7 != null) {
            IDInstruction var8 = (IDInstruction)this.cfg.getInstruction(var7);
            if (var8 != null && var8.isAssignToVar(var6.getId())) {
               var5 = var8.getAssignSource();
            }
         }
      }

      if (!(var5 instanceof IDCallInfo var25)) {
         return null;
      } else {
         String var26 = var25.getMethodSignature();
         int var27 = Arrays.asList(ys, ld).indexOf(var26);
         if (var27 < 0) {
            return null;
         } else {
            boolean var9 = var27 == 1;
            IDExpression var10 = var25.getArgument(0);
            IDExpression var11 = var25.getArgument(1);
            IJavaType var12 = this.A(var10, var3);
            if (var12 == null) {
               return null;
            } else {
               String var13 = this.pC(var11);
               if (var13 == null) {
                  return null;
               } else {
                  String[] var14 = new String[1];
                  String var15 = var12.getSignature();
                  IJLSField var16 = this.g.getTypeInfoProvider().findField(var15, var13, var9, var14);
                  if (var16 == null) {
                     String var28 = Strings.ff("%s->%s:?", var15, var13);
                     if (Sc.add(var28)) {
                        oT.trace("A candidate field for unreflection was not found: %s", var28);
                     }

                     return null;
                  } else {
                     boolean var17 = (var16.getAccessFlags() & 8) != 0;
                     String var18 = var14[0] + "->" + var16.getName() + ":" + var16.getDescriptor();
                     IDexField var19 = this.g.getDex().addField(var18);
                     String var20 = var16.getDescriptor();
                     IJavaType var21 = this.tf.createType(var20);
                     Object var22;
                     if (var17) {
                        var22 = this.g.createStaticField(this.g.createIndex(var19.getIndex()), var21, var12.getSignature(), var16.getName());
                     } else {
                        IDExpression var23 = var1.getArgument(1);
                        var22 = this.g.createInstanceField(var23, this.g.createIndex(var19.getIndex()), var21, var16.getName());
                     }

                     if (var2) {
                        if (var4 != null) {
                           if (!var20.equals(var4)) {
                              return null;
                           }
                        } else if (var21.isPrimitive()) {
                           String var29 = (String)eP.get(var20);
                           Assert.a(var29 != null);
                           var22 = this.g.createCallInfo(DInvokeType.STATIC, var29, Arrays.asList((IDExpression)var22));
                        }
                     } else {
                        IDExpression var30 = var1.getArgument(2);
                        if (var4 != null) {
                           if (!var20.equals(var4)) {
                              return null;
                           }

                           var22 = this.ctx.createAssign((IDExpression)var22, var30);
                        } else if (var21.isPrimitive()) {
                           var30 = this.pC(var30, var20);
                           var22 = this.ctx.createAssign((IDExpression)var22, var30);
                        } else {
                           var22 = this.ctx.createAssign((IDExpression)var22, var30);
                        }
                     }

                     ((IDExpression)var22).transferMetadataFrom(var1);
                     ((IDExpression)var22).setOrigin(pC);
                     ((bpp)var25).setCustomCanThrow(false);
                     ((bpp)var25).pC(false);
                     String var32 = DUtil.generateNativeAddress(this.ctx, var1);
                     Object var24 = var2
                        ? new DexDecompilerEvent.UnreflectedFieldRead(var18, var32)
                        : new DexDecompilerEvent.UnreflectedFieldWrite(var18, var32);
                     this.decomp.recordDecompilationEvent((DexDecompilerEvent)var24);
                     this.dex.getCommentManager().addMetaComment(var32, new MetaComment(((DexDecompilerEvent)var24).format(true, false), 1), false);
                     this.dex.getReferenceManager().addFieldReference(var18, var32, var2 ? DexReferenceType.GET_REFLECTED : DexReferenceType.SET_REFLECTED);
                     return (IDExpression)var22;
                  }
               }
            }
         }
      }
   }

   private String pC(IDExpression var1, IDInstruction var2) {
      if (var1 instanceof IDImm && ((IDImm)var1).isNullRef()) {
         return "()";
      } else if (var1 instanceof IDNewArrayInfo) {
         StringBuilder var3 = new StringBuilder("(");

         for (IDExpression var5 : ((IDNewArrayInfo)var1).getInitialValues()) {
            IJavaType var6 = this.A(var5, var2);
            if (var6 == null) {
               return null;
            }

            var3.append(var6.getSignature());
         }

         var3.append(")");
         return var3.toString();
      } else {
         return null;
      }
   }

   private IJavaType A(IDExpression var1, IDInstruction var2) {
      if (var1 instanceof IDStaticField) {
         String var7 = ((IDStaticField)var1).getClassSignature();
         String var8 = ((IDStaticField)var1).getFieldname();
         if (var8 == "class") {
            IDexType var10 = this.g.getDex().addType(var7);
            return this.tf.createType(var10.getSignature(true));
         } else {
            String var9 = (String)xC.get(var7);
            return this.tf.createType(var9);
         }
      } else if (var1 instanceof IDCallInfo) {
         return this.pC((IDCallInfo)var1);
      } else {
         if (var1 instanceof IDVar && var2 != null) {
            int var3 = ((IDVar)var1).getId();
            this.analyzeChains();
            Long var4 = this.dfa.checkSingleDef(var2.getOffset(), var3);
            if (var4 != null && var4 != -1L) {
               IDInstruction var5 = (IDInstruction)this.cfg.getInstruction(var4);
               if (var5 != null && var5.getOpcode() == DOpcodeType.IR_ASSIGN) {
                  var1 = (IDExpression)var5.getOperand2();
                  return this.A(var1, null);
               }
            }
         }

         return null;
      }
   }

   private IJavaType pC(IDCallInfo var1) {
      String var2 = var1.getMethodSignature();
      if (A.equals(var2)) {
         IDExpression var3 = var1.getArgument(0);
         if (var3 instanceof IDImm && ((IDImm)var3).isString()) {
            String var4 = ((IDImm)var3).getStringValue(this.g);
            String var5 = JavaUtil.toJvmName(var4);
            IDexType var6 = this.g.getDex().addType(var5);
            return this.tf.createType(var6.getSignature(true));
         }
      }

      return null;
   }

   private String pC(IDExpression var1) {
      return var1 instanceof IDImm && ((IDImm)var1).isString() ? ((IDImm)var1).getStringValue(this.g) : null;
   }

   private void A(IDCallInfo var1) {
      String var2 = var1.getMethodSignature();
      JvmMethodSig var3 = JvmMethodSig.parse(var2);
      if (var1.getInvokeType() != DInvokeType.STATIC && var1.getInvokeType() != DInvokeType.NEW) {
         var3.partypes.add(0, var3.csig);
      }

      int var4 = 0;

      for (String var6 : var3.partypes) {
         IDExpression var7 = var1.getArgument(var4);
         var1.setArgument(var4, this.pC(var7, var6));
         var4++;
      }
   }

   private IDExpression pC(IDExpression var1, String var2) {
      String var3 = (String)eP.get(var2);
      if (var3 == null) {
         return var1;
      } else {
         String var4 = var1.getType().getSignature();
         if (var4.equals(var2)) {
            return var1;
         } else if (var1 instanceof IDCallInfo var5 && var5.getMethodSignature().equals(var3)) {
            return var5.getArgument(0);
         } else {
            String var9 = var3.substring(var3.lastIndexOf(41) + 1);
            if (var4.equals(var9)) {
               String var6 = (String)UO.get(var2);
               Assert.a(var6 != null);
               IDexMethod var7 = this.g.getDex().addMethod(var6);
               IDCallInfo var8 = this.g
                  .createCallInfo(this.g.createIndex(var7.getIndex()), new IDExpression[]{var1}, this.tf.createType(var2), var6, DInvokeType.VIRTUAL);
               var8.transferMetadataFrom(var1);
               var8.setOrigin(pC);
               return var8;
            } else {
               return var1;
            }
         }
      }
   }

   private IDExpression A(IDExpression var1, String var2) {
      String var3 = (String)eP.get(var2);
      if (var3 == null) {
         return var1;
      } else {
         String var4 = JvmMethodSig.parse(var3).rettype;
         IDexMethod var5 = this.g.getDex().addMethod(var3);
         IDCallInfo var6 = this.g
            .createCallInfo(this.g.createIndex(var5.getIndex()), new IDExpression[]{var1}, this.tf.createType(var4), var3, DInvokeType.STATIC);
         var6.transferMetadataFrom(var1);
         var6.setOrigin(pC);
         return var6;
      }
   }

   static {
      gp.add(wS);
      gp.add(UT);
      gp.add(E);
      gp.add(sY);
      gp.add(ys);
      gp.add(ld);
      NS.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               68,
               41,
               74,
               0,
               24,
               5,
               0,
               3,
               19,
               78,
               14,
               91,
               60,
               66,
               11,
               3,
               5,
               29,
               87,
               64,
               45,
               30,
               4,
               5,
               79,
               15,
               45,
               13,
               2,
               71,
               93,
               38,
               5,
               2,
               17,
               16,
               84,
               73
            },
            2,
            59
         ),
         "L"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               98,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               54,
               45,
               0,
               3,
               9,
               4,
               15,
               70,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               115
            },
            1,
            46
         ),
         "Z"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               -48,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               54,
               59,
               13,
               17,
               77,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               107
            },
            1,
            156
         ),
         "B"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               47,
               13,
               65,
               19,
               70,
               40,
               69,
               14,
               4,
               65,
               70,
               24,
               18,
               78,
               6,
               73,
               41,
               11,
               6,
               12,
               2,
               0,
               94,
               90,
               109
            },
            2,
            36
         ),
         "C"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               63,
               13,
               79,
               19,
               26,
               76,
               99,
               5,
               19,
               86,
               8,
               91,
               31,
               65,
               15,
               1,
               73,
               38,
               14,
               3,
               4,
               23,
               17,
               72,
               7,
               115
            },
            2,
            212
         ),
         "S"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               -21,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               61,
               39,
               26,
               92,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               96
            },
            1,
            167
         ),
         "I"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               14,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               56,
               35,
               1,
               9,
               79,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               99
            },
            1,
            66
         ),
         "J"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               42,
               9,
               79,
               0,
               26,
               76,
               99,
               5,
               19,
               86,
               8,
               91,
               31,
               65,
               15,
               1,
               73,
               38,
               14,
               3,
               4,
               23,
               17,
               72,
               7,
               102
            },
            2,
            129
         ),
         "F"
      );
      NS.put(
         ckx.pC(
            new byte[]{
               29,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               48,
               43,
               26,
               23,
               14,
               9,
               77,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               109
            },
            1,
            81
         ),
         "D"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               68,
               41,
               74,
               0,
               24,
               5,
               0,
               3,
               19,
               78,
               14,
               91,
               60,
               66,
               11,
               3,
               5,
               29,
               87,
               37,
               11,
               21,
               19,
               18,
               1,
               76,
               32,
               2,
               11,
               15,
               61,
               11,
               13,
               13,
               23,
               7,
               27,
               91,
               51
            },
            2,
            53
         ),
         "L"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               114,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               54,
               45,
               0,
               3,
               9,
               4,
               15,
               70,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               97,
               115,
               127
            },
            1,
            62
         ),
         "Z"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               46,
               28,
               84,
               4,
               70,
               40,
               69,
               14,
               4,
               65,
               70,
               24,
               18,
               78,
               6,
               73,
               41,
               11,
               6,
               12,
               2,
               0,
               94,
               49,
               7,
               118
            },
            2,
            176
         ),
         "B"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               110,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               55,
               43,
               9,
               19,
               90,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               120,
               106,
               127
            },
            1,
            34
         ),
         "C"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               -122,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               39,
               59,
               7,
               29,
               6,
               92,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               104,
               122,
               127
            },
            1,
            202
         ),
         "S"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               37,
               11,
               84,
               73,
               34,
               14,
               78,
               25,
               19,
               15,
               5,
               21,
               29,
               71,
               78,
               41,
               4,
               3,
               9,
               10,
               21,
               79,
               44,
               90,
               120
            },
            2,
            248
         ),
         "I"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               32,
               10,
               78,
               6,
               70,
               40,
               69,
               14,
               4,
               65,
               70,
               24,
               18,
               78,
               6,
               73,
               41,
               11,
               6,
               12,
               2,
               0,
               94,
               57,
               7,
               118
            },
            2,
            46
         ),
         "J"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               42,
               9,
               79,
               0,
               26,
               76,
               99,
               5,
               19,
               86,
               8,
               91,
               31,
               65,
               15,
               1,
               73,
               38,
               14,
               3,
               4,
               23,
               17,
               72,
               104,
               9,
               23
            },
            2,
            113
         ),
         "F"
      );
      vP.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               40,
               10,
               85,
               3,
               2,
               1,
               7,
               35,
               24,
               65,
               31,
               21,
               92,
               76,
               0,
               8,
               1,
               70,
               35,
               11,
               11,
               17,
               6,
               7,
               21,
               100,
               104,
               58
            },
            2,
            108
         ),
         "D"
      );
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 70, 79, 93, 92, 88, 93, 23}, 2, 17), "Z");
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 80, 84, 84, 2}, 2, 234), "B");
      xC.put(ckx.pC(new byte[]{17, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 43, 9, 19, 19, 2, 23, 17, 23, 73}, 1, 93), "C");
      xC.put(ckx.pC(new byte[]{24, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 124, 59, 7, 29, 6, 79}, 1, 84), "S");
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 42, 71, 84, 84, 94, 92, 65, 23}, 2, 30), "I");
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 47, 70, 78, 86, 2}, 2, 161), "J");
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 37, 69, 79, 80, 77, 2}, 2, 29), "F");
      xC.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 39, 70, 85, 83, 85, 92, 8}, 2, 70), "D");
      ED.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               49,
               76,
               70,
               93,
               92,
               90,
               71,
               69,
               86,
               87,
               127,
               66,
               80,
               94,
               65,
               59,
               27,
               14,
               13,
               41,
               29,
               67,
               4,
               30,
               16,
               70,
               0,
               28,
               27
            },
            2,
            18
         )
      );
      ED.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 32, 69, 65, 66, 74, 119, 92, 88, 102, 93, 69, 92, 81, 105, 88, 44, 23, 17, 23, 5, 10, 78, 90},
            2,
            21
         )
      );
      ED.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 42, 69, 76, 84, 94, 88, 95, 109, 67, 81, 85, 65, 70, 105, 88, 44, 23, 17, 23, 5, 10, 78, 90},
            2,
            161
         )
      );
      ED.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 42, 71, 83, 69, 88, 87, 71, 69, 65, 70, 89, 93, 91, 105, 88, 44, 23, 17, 23, 5, 10, 78, 90},
            2,
            81
         )
      );
      ED.add(
         ckx.pC(new byte[]{-82, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 97, 33, 60, 38, 22, 11, 46, 47, 12, 9, 8, 33, 61, 27, 6, 21, 4, 29, 6, 1, 85}, 1, 226)
      );
      ED.add(
         ckx.pC(
            new byte[]{54, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 97, 33, 60, 38, 22, 11, 37, 40, 17, 28, 7, 11, 33, 61, 27, 6, 21, 4, 29, 6, 1, 85}, 1, 122
         )
      );
      ED.add(
         ckx.pC(
            new byte[]{
               0,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               102,
               39,
               24,
               25,
               12,
               2,
               21,
               29,
               6,
               1,
               58,
               53,
               19,
               21,
               2,
               17,
               49,
               61,
               27,
               6,
               21,
               4,
               29,
               6,
               1,
               85
            },
            1,
            76
         )
      );
      eP.put(
         "Z",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               33,
               70,
               79,
               93,
               92,
               88,
               93,
               23,
               13,
               12,
               70,
               83,
               89,
               89,
               69,
               0,
               20,
               73,
               57,
               69,
               41,
               74,
               0,
               24,
               5,
               0,
               3,
               19,
               78,
               14,
               91,
               49,
               79,
               14,
               10,
               3,
               8,
               2,
               82
            },
            2,
            69
         )
      );
      eP.put(
         "B",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               33,
               80,
               84,
               84,
               2,
               20,
               13,
               90,
               65,
               94,
               69,
               87,
               122,
               74,
               8,
               13,
               91,
               45,
               9,
               13,
               19,
               65,
               78,
               2,
               5,
               65,
               8,
               93,
               98,
               16,
               0,
               22,
               27
            },
            2,
            21
         )
      );
      eP.put(
         "C",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               32,
               65,
               65,
               67,
               88,
               90,
               71,
               73,
               82,
               9,
               29,
               12,
               67,
               77,
               76,
               58,
               23,
               46,
               5,
               68,
               38,
               9,
               45,
               4,
               5,
               89,
               14,
               93,
               76,
               8,
               26,
               20,
               15,
               34,
               14,
               7,
               27,
               13,
               10,
               21,
               17,
               23,
               72
            },
            2,
            128
         )
      );
      eP.put(
         "S",
         ckx.pC(
            new byte[]{
               95,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               59,
               7,
               29,
               6,
               79,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               123,
               122,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               59,
               7,
               29,
               6,
               79
            },
            1,
            19
         )
      );
      eP.put(
         "I",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               42,
               71,
               84,
               84,
               94,
               92,
               65,
               23,
               13,
               12,
               70,
               83,
               89,
               89,
               69,
               0,
               20,
               73,
               42,
               69,
               41,
               74,
               0,
               24,
               5,
               0,
               3,
               19,
               78,
               14,
               91,
               58,
               78,
               21,
               3,
               1,
               12,
               30,
               82
            },
            2,
            121
         )
      );
      eP.put(
         "J",
         ckx.pC(
            new byte[]{
               59,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               99,
               35,
               1,
               9,
               92,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               98,
               99,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               99,
               35,
               1,
               9,
               92
            },
            1,
            119
         )
      );
      eP.put(
         "F",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               37,
               69,
               79,
               80,
               77,
               2,
               30,
               18,
               86,
               83,
               92,
               71,
               80,
               99,
               70,
               103,
               52,
               72,
               47,
               6,
               4,
               86,
               0,
               65,
               8,
               78,
               1,
               21,
               15,
               47,
               24,
               28,
               65,
               21,
               93
            },
            2,
            99
         )
      );
      eP.put(
         "D",
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               39,
               70,
               85,
               83,
               85,
               92,
               8,
               1,
               30,
               68,
               81,
               94,
               64,
               73,
               111,
               41,
               90,
               37,
               74,
               32,
               15,
               65,
               23,
               15,
               75,
               67,
               14,
               28,
               71,
               70,
               48,
               28,
               85,
               3,
               10,
               3,
               82
            },
            2,
            236
         )
      );
      UO.put(
         "Z",
         ckx.pC(
            new byte[]{
               15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 70, 79, 93, 92, 88, 93, 23, 13, 12, 82, 93, 90, 64, 69, 46, 28, 55, 2, 0, 16, 69, 73, 71, 62
            },
            2,
            207
         )
      );
      UO.put(
         "B",
         ckx.pC(new byte[]{-28, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 109, 59, 13, 17, 94, 22, 19, 92, 27, 13, 17, 51, 55, 13, 25, 16, 77, 1, 107}, 1, 168)
      );
      UO.put(
         "C",
         ckx.pC(
            new byte[]{50, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 43, 9, 19, 19, 2, 23, 17, 23, 73, 22, 19, 93, 11, 9, 19, 36, 55, 13, 25, 16, 77, 1, 106},
            1,
            126
         )
      );
      UO.put(
         "S",
         ckx.pC(new byte[]{70, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 124, 59, 7, 29, 6, 79, 22, 19, 77, 27, 7, 29, 6, 34, 55, 13, 25, 16, 77, 1, 122}, 1, 10)
      );
      UO.put(
         "I",
         ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 42, 71, 84, 84, 94, 92, 65, 23, 13, 12, 89, 92, 65, 122, 65, 35, 7, 4, 75, 69, 44}, 2, 13)
      );
      UO.put(
         "J", ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 47, 70, 78, 86, 2, 20, 13, 64, 79, 92, 87, 100, 84, 64, 85, 42, 90, 72, 41}, 2, 171)
      );
      UO.put(
         "F",
         ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 37, 69, 79, 80, 77, 2, 30, 18, 70, 94, 95, 83, 65, 122, 65, 35, 7, 4, 75, 69, 35}, 2, 21)
      );
      UO.put(
         "D",
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 39, 70, 85, 83, 85, 92, 8, 1, 30, 86, 95, 71, 87, 64, 69, 25, 19, 13, 22, 9, 77, 9, 37}, 2, 2
         )
      );
   }

   private class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo && ((IDCallInfo)var1).getMethodSignature().equals(ccp.A) && ((IDCallInfo)var1).getArgument(0) instanceof IDImm) {
            IDImm var4 = (IDImm)((IDCallInfo)var1).getArgument(0);
            if (var4.isNullRef()) {
               return;
            }

            String var5 = var4.getStringValue(ccp.this.g);
            String var6 = JavaUtil.toJvmName(var5);
            String var8 = Strings.ltrim(var6, '[');
            boolean var7;
            if (!var8.startsWith("L")) {
               var7 = true;
            } else {
               IJLSType var9 = ccp.this.g.getTypeInfoProvider().findType(var8);
               var7 = var9 != null && (var9.getAccessFlags() & 1) != 0;
            }

            if (var7) {
               IDexType var12 = ccp.this.dex.getType(var6);
               if (var12 != null) {
                  var6 = var12.getSignature(false);
                  IDStaticField var10 = ccp.this.g.createClassObject(var6);
                  if (var2.replaceSubExpression(var1, var10)) {
                     this.pC++;
                  }
               }
            }
         }
      }
   }

   private class Sv implements IDVisitor {
      IDInstruction pC;
      int A;
      IDInstruction kS;

      void pC(IDInstruction var1) {
         this.pC = var1;
         this.A = 0;
         this.kS = null;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            String var5 = var4.getMethodSignature();

            try {
               IDExpression var6 = null;
               if (ccp.WR.equals(var5)) {
                  var6 = ccp.this.pC(var4, this.pC);
               } else if (ccp.fI.equals(var5)) {
                  var6 = ccp.this.A(var4, this.pC);
               } else if (ccp.NS.containsKey(var5)) {
                  var6 = ccp.this.pC(var4, true, this.pC);
               } else if (ccp.vP.containsKey(var5)) {
                  var6 = ccp.this.pC(var4, false, this.pC);
                  if (var6 != null && var2 instanceof IDInstruction) {
                     Assert.a(var6 instanceof IDInstruction);
                     this.kS = (IDInstruction)var6;
                     this.A++;
                     var3.interrupt(true);
                  }

                  return;
               }

               if (var6 != null && var2.replaceSubExpression(var1, var6)) {
                  var3.setReplacedNode(var6);
                  this.A++;
               }
            } catch (Exception var7) {
               ccp.oT.catchingSilent(var7);
            }
         }
      }
   }
}
