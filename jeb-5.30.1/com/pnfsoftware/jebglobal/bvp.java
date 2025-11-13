package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class bvp {
   private static final ILogger q = GlobalLog.getLogger(bvp.class);
   private static final boolean RF = false;
   private static Random xK = new Random(0L);
   private int Dw = 5000;
   private int Uv = 5;
   private int oW = 4;
   private int gO = 2;
   private int nf = 100;
   private IDMethodContext gP;
   private CFG za;
   private IDGlobalContext lm;
   private Set zz;
   private boolean JY;
   private IDExpression HF;
   private int LK;
   private int io;
   private Set qa;
   private int Hk;
   private Map Me;
   private Set PV;
   private int oQ = 1;
   private static final Set xW = new HashSet();

   private static void q(String var0, Object... var1) {
   }

   public bvp(IDMethodContext var1) {
      this.gP = var1;
      this.za = var1.getCfg();
      this.lm = var1.getGlobalContext();
   }

   public void q(Set var1) {
      this.zz = var1;
   }

   public void q(boolean var1) {
      this.JY = var1;
   }

   public IDExpression q(IDExpression var1) {
      return this.q(var1, null, -1);
   }

   public IDExpression q(IDExpression var1, BasicBlock var2, int var3) {
      JavaOperatorType var4;
      if (var1 instanceof IDOperation var5
         && (var4 = var5.getOperatorType()).isAnyOf(JavaOperatorType.MUL, JavaOperatorType.DIV, JavaOperatorType.REM, JavaOperatorType.SHL)) {
         IDExpression var6 = var5.getOperand1();
         IDExpression var7 = var5.getOperand2();
         byte var8 = 0;
         if (RF(var6)) {
            var8 |= 1;
         }

         if (RF(var7)) {
            var8 |= 2;
         }

         if (var8 == 3) {
            return null;
         }

         if (var8 == 1) {
            IDExpression var10 = this.RF(var7, var2, var3);
            if (var10 == null) {
               return null;
            }

            return this.gP.createOperation(null, var4, var6, var10);
         }

         if (var8 == 2) {
            IDExpression var9 = this.RF(var6, var2, var3);
            if (var9 == null) {
               return null;
            }

            return this.gP.createOperation(null, var4, var9, var7);
         }

         Assert.a(var8 == 0);
      }

      return this.RF(var1, var2, var3);
   }

   private static boolean RF(IDExpression var0) {
      return var0 instanceof IDImm || var0 instanceof IDVar || var0 instanceof IDField || var0 instanceof IDInvokeInfo;
   }

   private IDExpression RF(IDExpression var1, BasicBlock var2, int var3) {
      this.HF = var1;
      this.qa = new TreeSet();
      this.Hk = 0;
      this.Me = new HashMap();
      this.PV = new HashSet();
      IDExpression var4 = var1;
      int var5 = var3 - 1;
      HashSet var6 = new HashSet();
      HashMap var7 = new HashMap();

      label96:
      while (true) {
         this.io = 0;
         this.LK = 0;
         this.qa.clear();
         DCopyOptions var8 = new DCopyOptions();
         if (!var4.visitDepthPre(new bvq(this, var7, var8))) {
            return null;
         }

         if (var8.hasOptions()) {
            var4 = var4.copy(var8);
            this.io = 0;
            this.LK = 0;
            this.qa.clear();
            var4.visitDepthPre(new bvr(this));
         }

         int var9 = DUtil.calculateComplexity(var4);
         if (var9 >= this.Dw) {
            Object[] var22 = new Object[]{var9, this.Dw};
            return null;
         }

         String var10 = var4.toString();
         if (this.zz == null || !this.zz.contains(var10)) {
            IDExpression var11 = this.xK(var4);
            if (var11 != null) {
               return var11;
            }

            if (this.zz != null) {
               this.zz.add(var10);
            }
         }

         if (var2 == null) {
            return null;
         }

         while (var5 >= 0) {
            IDInstruction var19 = (IDInstruction)var2.get(var5--);
            if (var19.isAssignToVar()) {
               IDVar var12 = var19.getAssignDestination().asVar();
               if (this.qa.contains(var12.getId())) {
                  IDExpression var13 = var19.getAssignSource();
                  if (!var13.hasSideEffects(this.gP, false) && !CollectionUtil.hasIntersection(DUtil.collectUniqueVarIds(var19), var6)) {
                     int var14 = 0;

                     while (var14++ < 5) {
                        IDCallInfo var15 = (IDCallInfo)var13.findByType(IDCallInfo.class);
                        if (var15 == null) {
                           break;
                        }

                        IJavaType var16 = var15.getType();
                        if (var16.isSmallInt() || var16.isBoolean() || var16.isLong()) {
                           Object var17 = null;
                           if (this.JY) {
                              var17 = bto.q(this.gP, var15, 30, 5, true);
                           }

                           if (var17 == null || !bto.q((IDExpression)var17, null, Integer.MAX_VALUE)) {
                              IDVar var18 = ((bud)this.gP).retrieveTemporaryVariable(var16, this.Hk++);
                              this.Me.put(var18, var15);
                              var17 = var18;
                           }

                           DCopyOptions var21 = new DCopyOptions();
                           var21.replmap_id.put(var15, var17);
                           var13 = var13.copy(var21);
                        }
                     }

                     this.qa.remove(var12.getId());
                     var13.collectVarIds(this.qa);
                     if (var4 == var1) {
                        var4 = var4.duplicate();
                     }

                     var4.replaceVariable(var12, var13);
                     continue label96;
                  }
               }
            }

            IDVar var20 = var19.getDefinedVariable();
            if (var20 != null) {
               var6.add(var20.getId());
            }
         }

         return null;
      }
   }

   private IDExpression xK(IDExpression param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 000: aload 0
      // 001: getfield com/pnfsoftware/jebglobal/bvp.qa Ljava/util/Set;
      // 004: invokeinterface java/util/Set.size ()I 1
      // 009: istore 2
      // 00a: iload 2
      // 00b: ifeq 016
      // 00e: iload 2
      // 00f: aload 0
      // 010: getfield com/pnfsoftware/jebglobal/bvp.Uv I
      // 013: if_icmple 018
      // 016: aconst_null
      // 017: areturn
      // 018: aload 0
      // 019: getfield com/pnfsoftware/jebglobal/bvp.LK I
      // 01c: aload 0
      // 01d: getfield com/pnfsoftware/jebglobal/bvp.oW I
      // 020: if_icmpge 025
      // 023: aconst_null
      // 024: areturn
      // 025: aload 0
      // 026: getfield com/pnfsoftware/jebglobal/bvp.io I
      // 029: aload 0
      // 02a: getfield com/pnfsoftware/jebglobal/bvp.gO I
      // 02d: if_icmpge 032
      // 030: aconst_null
      // 031: areturn
      // 032: aload 1
      // 033: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.getType ()Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType; 1
      // 038: invokeinterface com/pnfsoftware/jeb/core/units/code/java/IJavaType.isBoolean ()Z 1
      // 03d: ifeq 05f
      // 040: invokestatic com/pnfsoftware/jebglobal/bvy.q ()Z
      // 043: ifeq 05d
      // 046: new com/pnfsoftware/jebglobal/bvy
      // 049: dup
      // 04a: aload 0
      // 04b: getfield com/pnfsoftware/jebglobal/bvp.gP Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext;
      // 04e: invokespecial com/pnfsoftware/jebglobal/bvy.<init> (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext;)V
      // 051: aload 1
      // 052: aconst_null
      // 053: invokevirtual com/pnfsoftware/jebglobal/bvy.q (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDInstruction;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;
      // 056: astore 3
      // 057: aload 3
      // 058: ifnull 05d
      // 05b: aload 3
      // 05c: areturn
      // 05d: aconst_null
      // 05e: areturn
      // 05f: aload 0
      // 060: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 063: bipush 1
      // 064: if_icmplt 06b
      // 067: bipush 1
      // 068: goto 06c
      // 06b: bipush 0
      // 06c: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 06f: aload 0
      // 070: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 073: bipush 1
      // 074: if_icmpne 07c
      // 077: aload 1
      // 078: astore 3
      // 079: goto 09f
      // 07c: aload 0
      // 07d: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 080: aconst_null
      // 081: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.MUL Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 084: aload 0
      // 085: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 088: aload 0
      // 089: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 08c: i2l
      // 08d: aload 1
      // 08e: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.getType ()Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType; 1
      // 093: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createImm (JLcom/pnfsoftware/jeb/core/units/code/java/IJavaType;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 4
      // 098: aload 1
      // 099: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createOperation (Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType;Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDOperation; 5
      // 09e: astore 3
      // 09f: aload 0
      // 0a0: getfield com/pnfsoftware/jebglobal/bvp.qa Ljava/util/Set;
      // 0a3: invokeinterface java/util/Set.size ()I 1
      // 0a8: bipush 1
      // 0a9: iadd
      // 0aa: istore 4
      // 0ac: new java/util/ArrayList
      // 0af: dup
      // 0b0: iload 4
      // 0b2: invokespecial java/util/ArrayList.<init> (I)V
      // 0b5: astore 5
      // 0b7: bipush -1
      // 0b8: istore 6
      // 0ba: bipush 0
      // 0bb: istore 7
      // 0bd: iload 7
      // 0bf: iload 4
      // 0c1: if_icmpge 0e8
      // 0c4: iload 4
      // 0c6: newarray 10
      // 0c8: astore 8
      // 0ca: iload 6
      // 0cc: iflt 0d5
      // 0cf: aload 8
      // 0d1: iload 6
      // 0d3: bipush 1
      // 0d4: iastore
      // 0d5: aload 5
      // 0d7: aload 8
      // 0d9: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 0de: pop
      // 0df: iinc 6 1
      // 0e2: iinc 7 1
      // 0e5: goto 0bd
      // 0e8: aload 0
      // 0e9: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 0ec: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createState ()Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDState; 1
      // 0f1: astore 8
      // 0f3: aload 8
      // 0f5: bipush 0
      // 0f6: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.setSubRoutineInvocationPolicy (I)I 2
      // 0fb: pop
      // 0fc: aload 8
      // 0fe: bipush 0
      // 0ff: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.setFieldAccessPolicy (I)I 2
      // 104: pop
      // 105: aload 8
      // 107: bipush 1
      // 108: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.enableEmulator (Z)V 2
      // 10d: aload 8
      // 10f: bipush 0
      // 110: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.enableSandbox (Z)Z 2
      // 115: pop
      // 116: aload 8
      // 118: bipush 10
      // 11a: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.setMaxIterationCount (I)I 2
      // 11f: pop
      // 120: aload 8
      // 122: ldc "context"
      // 124: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.pushContext (Ljava/lang/String;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDEmuContext; 2
      // 129: pop
      // 12a: aload 8
      // 12c: ldc "frame"
      // 12e: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.pushFrame (Ljava/lang/String;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDEmuFrame; 2
      // 133: astore 9
      // 135: bipush 0
      // 136: istore 10
      // 138: iload 10
      // 13a: iload 4
      // 13c: if_icmpge 1d4
      // 13f: aload 5
      // 141: iload 10
      // 143: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 148: checkcast [I
      // 14b: astore 11
      // 14d: bipush 0
      // 14e: istore 12
      // 150: aload 0
      // 151: getfield com/pnfsoftware/jebglobal/bvp.qa Ljava/util/Set;
      // 154: invokeinterface java/util/Set.iterator ()Ljava/util/Iterator; 1
      // 159: astore 13
      // 15b: aload 13
      // 15d: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 162: ifeq 1a2
      // 165: aload 13
      // 167: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 16c: checkcast java/lang/Integer
      // 16f: invokevirtual java/lang/Integer.intValue ()I
      // 172: istore 14
      // 174: aload 9
      // 176: iload 14
      // 178: aload 0
      // 179: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 17c: aload 11
      // 17e: iload 12
      // 180: iaload
      // 181: i2l
      // 182: aload 0
      // 183: getfield com/pnfsoftware/jebglobal/bvp.gP Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext;
      // 186: iload 14
      // 188: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext.getVar (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDVar; 2
      // 18d: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDVar.getType ()Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType; 1
      // 192: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createImm (JLcom/pnfsoftware/jeb/core/units/code/java/IJavaType;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 4
      // 197: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDEmuFrame.setVariable (ILcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;)V 3
      // 19c: iinc 12 1
      // 19f: goto 15b
      // 1a2: aload 3
      // 1a3: aload 8
      // 1a5: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.evaluate (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDState;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 1aa: astore 13
      // 1ac: aload 13
      // 1ae: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDImm.getValueAsLong ()J 1
      // 1b3: l2i
      // 1b4: istore 14
      // 1b6: aload 11
      // 1b8: iload 12
      // 1ba: iload 14
      // 1bc: iastore
      // 1bd: goto 1ce
      // 1c0: pop
      // 1c1: aconst_null
      // 1c2: astore 13
      // 1c4: aload 8
      // 1c6: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 1cb: aload 13
      // 1cd: areturn
      // 1ce: iinc 10 1
      // 1d1: goto 138
      // 1d4: iload 4
      // 1d6: newarray 10
      // 1d8: astore 10
      // 1da: aload 5
      // 1dc: bipush 0
      // 1dd: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 1e2: checkcast [I
      // 1e5: iload 4
      // 1e7: bipush 1
      // 1e8: isub
      // 1e9: iaload
      // 1ea: istore 11
      // 1ec: bipush 1
      // 1ed: istore 12
      // 1ef: iload 12
      // 1f1: aload 5
      // 1f3: invokeinterface java/util/List.size ()I 1
      // 1f8: if_icmpge 220
      // 1fb: aload 5
      // 1fd: iload 12
      // 1ff: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 204: checkcast [I
      // 207: iload 4
      // 209: bipush 1
      // 20a: isub
      // 20b: iaload
      // 20c: iload 11
      // 20e: isub
      // 20f: istore 13
      // 211: aload 10
      // 213: iload 12
      // 215: bipush 1
      // 216: isub
      // 217: iload 13
      // 219: iastore
      // 21a: iinc 12 1
      // 21d: goto 1ef
      // 220: aload 10
      // 222: aload 5
      // 224: invokeinterface java/util/List.size ()I 1
      // 229: bipush 1
      // 22a: isub
      // 22b: iload 11
      // 22d: iastore
      // 22e: bipush 1
      // 22f: anewarray 36
      // 232: bipush 0
      // 233: aload 10
      // 235: invokestatic java/util/Arrays.toString ([I)Ljava/lang/String;
      // 238: aastore
      // 239: iload 4
      // 23b: newarray 10
      // 23d: astore 12
      // 23f: bipush 0
      // 240: istore 13
      // 242: iload 13
      // 244: aload 0
      // 245: getfield com/pnfsoftware/jebglobal/bvp.nf I
      // 248: if_icmpge 334
      // 24b: bipush 0
      // 24c: istore 14
      // 24e: aload 0
      // 24f: getfield com/pnfsoftware/jebglobal/bvp.qa Ljava/util/Set;
      // 252: invokeinterface java/util/Set.iterator ()Ljava/util/Iterator; 1
      // 257: astore 15
      // 259: aload 15
      // 25b: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 260: ifeq 2ea
      // 263: aload 15
      // 265: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 26a: checkcast java/lang/Integer
      // 26d: invokevirtual java/lang/Integer.intValue ()I
      // 270: istore 16
      // 272: getstatic com/pnfsoftware/jebglobal/bvp.xK Ljava/util/Random;
      // 275: invokevirtual java/util/Random.nextInt ()I
      // 278: istore 17
      // 27a: aload 0
      // 27b: getfield com/pnfsoftware/jebglobal/bvp.gP Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext;
      // 27e: iload 16
      // 280: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext.getVar (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDVar; 2
      // 285: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDVar.getType ()Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType; 1
      // 28a: astore 18
      // 28c: aload 18
      // 28e: invokeinterface com/pnfsoftware/jeb/core/units/code/java/IJavaType.isByte ()Z 1
      // 293: ifeq 29e
      // 296: iload 17
      // 298: i2b
      // 299: istore 19
      // 29b: goto 2c6
      // 29e: aload 18
      // 2a0: invokeinterface com/pnfsoftware/jeb/core/units/code/java/IJavaType.isChar ()Z 1
      // 2a5: ifeq 2b0
      // 2a8: iload 17
      // 2aa: i2c
      // 2ab: istore 19
      // 2ad: goto 2c6
      // 2b0: aload 18
      // 2b2: invokeinterface com/pnfsoftware/jeb/core/units/code/java/IJavaType.isShort ()Z 1
      // 2b7: ifeq 2c2
      // 2ba: iload 17
      // 2bc: i2s
      // 2bd: istore 19
      // 2bf: goto 2c6
      // 2c2: iload 17
      // 2c4: istore 19
      // 2c6: aload 9
      // 2c8: iload 16
      // 2ca: aload 0
      // 2cb: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 2ce: iload 19
      // 2d0: i2l
      // 2d1: aload 18
      // 2d3: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createImm (JLcom/pnfsoftware/jeb/core/units/code/java/IJavaType;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 4
      // 2d8: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDEmuFrame.setVariable (ILcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;)V 3
      // 2dd: aload 12
      // 2df: iload 14
      // 2e1: iload 19
      // 2e3: iastore
      // 2e4: iinc 14 1
      // 2e7: goto 259
      // 2ea: aload 3
      // 2eb: aload 8
      // 2ed: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.evaluate (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDState;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 2f2: astore 15
      // 2f4: aload 15
      // 2f6: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDImm.getValueAsLong ()J 1
      // 2fb: l2i
      // 2fc: istore 16
      // 2fe: aload 12
      // 300: iload 14
      // 302: iload 16
      // 304: iastore
      // 305: aload 0
      // 306: aload 12
      // 308: aload 10
      // 30a: invokevirtual com/pnfsoftware/jebglobal/bvp.q ([I[I)Z
      // 30d: ifne 31d
      // 310: aconst_null
      // 311: astore 17
      // 313: aload 8
      // 315: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 31a: aload 17
      // 31c: areturn
      // 31d: goto 32e
      // 320: pop
      // 321: aconst_null
      // 322: astore 15
      // 324: aload 8
      // 326: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 32b: aload 15
      // 32d: areturn
      // 32e: iinc 13 1
      // 331: goto 242
      // 334: new java/util/ArrayList
      // 337: dup
      // 338: aload 0
      // 339: getfield com/pnfsoftware/jebglobal/bvp.qa Ljava/util/Set;
      // 33c: invokespecial java/util/ArrayList.<init> (Ljava/util/Collection;)V
      // 33f: astore 13
      // 341: bipush 0
      // 342: istore 14
      // 344: aload 10
      // 346: astore 15
      // 348: aload 15
      // 34a: arraylength
      // 34b: istore 16
      // 34d: bipush 0
      // 34e: istore 17
      // 350: iload 17
      // 352: iload 16
      // 354: if_icmpge 374
      // 357: aload 15
      // 359: iload 17
      // 35b: iaload
      // 35c: istore 18
      // 35e: iload 18
      // 360: aload 0
      // 361: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 364: irem
      // 365: ifeq 36e
      // 368: bipush 1
      // 369: istore 14
      // 36b: goto 374
      // 36e: iinc 17 1
      // 371: goto 350
      // 374: iload 14
      // 376: ifne 396
      // 379: bipush 0
      // 37a: istore 15
      // 37c: iload 15
      // 37e: aload 10
      // 380: arraylength
      // 381: if_icmpge 396
      // 384: aload 10
      // 386: iload 15
      // 388: dup2
      // 389: iaload
      // 38a: aload 0
      // 38b: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 38e: idiv
      // 38f: iastore
      // 390: iinc 15 1
      // 393: goto 37c
      // 396: aload 0
      // 397: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 39a: aload 10
      // 39c: aload 10
      // 39e: arraylength
      // 39f: bipush 1
      // 3a0: isub
      // 3a1: iaload
      // 3a2: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createInt (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 3a7: astore 15
      // 3a9: bipush 0
      // 3aa: istore 16
      // 3ac: iload 16
      // 3ae: aload 10
      // 3b0: arraylength
      // 3b1: bipush 1
      // 3b2: isub
      // 3b3: if_icmpge 41b
      // 3b6: aload 10
      // 3b8: iload 16
      // 3ba: iaload
      // 3bb: istore 17
      // 3bd: iload 17
      // 3bf: ifeq 415
      // 3c2: aload 0
      // 3c3: getfield com/pnfsoftware/jebglobal/bvp.gP Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext;
      // 3c6: aload 13
      // 3c8: iload 16
      // 3ca: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 3cf: checkcast java/lang/Integer
      // 3d2: invokevirtual java/lang/Integer.intValue ()I
      // 3d5: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDMethodContext.getVar (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDVar; 2
      // 3da: astore 18
      // 3dc: aload 18
      // 3de: astore 19
      // 3e0: iload 17
      // 3e2: bipush 1
      // 3e3: if_icmpeq 402
      // 3e6: aload 0
      // 3e7: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 3ea: aconst_null
      // 3eb: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.MUL Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 3ee: aload 0
      // 3ef: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 3f2: iload 17
      // 3f4: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createInt (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 3f9: aload 18
      // 3fb: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createOperation (Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType;Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDOperation; 5
      // 400: astore 19
      // 402: aload 0
      // 403: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 406: aconst_null
      // 407: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.ADD Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 40a: aload 15
      // 40c: aload 19
      // 40e: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createOperation (Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType;Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDOperation; 5
      // 413: astore 15
      // 415: iinc 16 1
      // 418: goto 3ac
      // 41b: iload 14
      // 41d: ifeq 43e
      // 420: aload 0
      // 421: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 424: aconst_null
      // 425: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.DIV Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 428: aload 15
      // 42a: aload 0
      // 42b: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 42e: aload 0
      // 42f: getfield com/pnfsoftware/jebglobal/bvp.oQ I
      // 432: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createInt (I)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 437: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createOperation (Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType;Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDOperation; 5
      // 43c: astore 15
      // 43e: bipush 1
      // 43f: anewarray 36
      // 442: bipush 0
      // 443: aload 15
      // 445: aastore
      // 446: aload 0
      // 447: getfield com/pnfsoftware/jebglobal/bvp.Me Ljava/util/Map;
      // 44a: invokeinterface java/util/Map.isEmpty ()Z 1
      // 44f: ifne 474
      // 452: new com/pnfsoftware/jeb/core/units/code/android/ir/DCopyOptions
      // 455: dup
      // 456: invokespecial com/pnfsoftware/jeb/core/units/code/android/ir/DCopyOptions.<init> ()V
      // 459: astore 16
      // 45b: aload 16
      // 45d: getfield com/pnfsoftware/jeb/core/units/code/android/ir/DCopyOptions.replmap_eq Ljava/util/Map;
      // 460: aload 0
      // 461: getfield com/pnfsoftware/jebglobal/bvp.Me Ljava/util/Map;
      // 464: invokeinterface java/util/Map.putAll (Ljava/util/Map;)V 2
      // 469: aload 15
      // 46b: aload 16
      // 46d: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.copy (Lcom/pnfsoftware/jeb/core/units/code/android/ir/DCopyOptions;)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression; 2
      // 472: astore 15
      // 474: aload 15
      // 476: aload 0
      // 477: getfield com/pnfsoftware/jebglobal/bvp.HF Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;
      // 47a: bipush 0
      // 47b: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.equalsEx (Ljava/lang/Object;Z)Z 3
      // 480: ifne 48f
      // 483: aload 15
      // 485: aload 1
      // 486: bipush 0
      // 487: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.equalsEx (Ljava/lang/Object;Z)Z 3
      // 48c: ifeq 49c
      // 48f: aconst_null
      // 490: astore 16
      // 492: aload 8
      // 494: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 499: aload 16
      // 49b: areturn
      // 49c: aload 15
      // 49e: invokestatic com/pnfsoftware/jeb/core/units/code/android/ir/DUtil.calculateComplexity (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)I
      // 4a1: aload 1
      // 4a2: invokestatic com/pnfsoftware/jeb/core/units/code/android/ir/DUtil.calculateComplexity (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression;)I
      // 4a5: if_icmplt 4b5
      // 4a8: aconst_null
      // 4a9: astore 16
      // 4ab: aload 8
      // 4ad: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 4b2: aload 16
      // 4b4: areturn
      // 4b5: aload 1
      // 4b6: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.getType ()Lcom/pnfsoftware/jeb/core/units/code/java/IJavaType; 1
      // 4bb: invokeinterface com/pnfsoftware/jeb/core/units/code/java/IJavaType.isBoolean ()Z 1
      // 4c0: ifeq 50f
      // 4c3: aload 15
      // 4c5: instanceof com/pnfsoftware/jeb/core/units/code/android/ir/IDImm
      // 4c8: ifeq 502
      // 4cb: aload 15
      // 4cd: checkcast com/pnfsoftware/jeb/core/units/code/android/ir/IDImm
      // 4d0: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDImm.getRawValue ()J 1
      // 4d5: l2i
      // 4d6: istore 16
      // 4d8: iload 16
      // 4da: ifeq 4e3
      // 4dd: iload 16
      // 4df: bipush 1
      // 4e0: if_icmpne 502
      // 4e3: aload 0
      // 4e4: getfield com/pnfsoftware/jebglobal/bvp.lm Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext;
      // 4e7: iload 16
      // 4e9: ifne 4f0
      // 4ec: bipush 0
      // 4ed: goto 4f1
      // 4f0: bipush 1
      // 4f1: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDGlobalContext.createBoolean (Z)Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm; 2
      // 4f6: astore 17
      // 4f8: aload 8
      // 4fa: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 4ff: aload 17
      // 501: areturn
      // 502: aconst_null
      // 503: astore 16
      // 505: aload 8
      // 507: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 50c: aload 16
      // 50e: areturn
      // 50f: bipush 1
      // 510: anewarray 36
      // 513: bipush 0
      // 514: aload 15
      // 516: aastore
      // 517: aload 15
      // 519: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDExpression.duplicate ()Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDExpression; 1
      // 51e: astore 7
      // 520: aload 7
      // 522: astore 16
      // 524: aload 8
      // 526: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 52b: aload 16
      // 52d: areturn
      // 52e: astore 20
      // 530: aload 8
      // 532: invokeinterface com/pnfsoftware/jeb/core/units/code/android/ir/IDState.dispose ()V 1
      // 537: aload 20
      // 539: athrow
   }

   private boolean q(int[] var1, int[] var2) {
      Assert.a(var1.length == var2.length);
      int var3 = var2.length - 1;
      int var4 = var2[var3];

      for (int var5 = 0; var5 < var3; var5++) {
         var4 += var2[var5] * var1[var5];
      }

      return var4 == var1[var3];
   }

   private static boolean Dw(IDExpression var0) {
      if (var0 instanceof IDImm) {
         IDImm var3 = var0.asImm();
         return !var3.isZero();
      } else if (var0 instanceof IDOperation var1 && var1.getOperatorType() == JavaOperatorType.OR && var1.getOperand2() instanceof IDImm) {
         IDImm var2 = var1.getOperand2().asImm();
         return !var2.isZero();
      } else {
         return false;
      }
   }

   private static boolean q(String var0) {
      return xW.contains(var0);
   }

   static {
      xW.add(
         cvm.q(
            new byte[]{
               -94,
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
               42,
               10,
               7,
               17,
               8,
               86,
               22,
               19,
               87,
               13,
               1,
               11,
               26,
               29,
               29,
               13,
               49,
               41,
               18,
               27,
               43,
               44,
               11,
               1,
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
               96
            },
            1,
            238
         )
      );
      xW.add(
         cvm.q(
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
               124,
               42,
               10,
               7,
               17,
               8,
               86,
               22,
               19,
               93,
               22,
               7,
               0,
               23,
               11,
               26,
               32,
               61,
               4,
               8,
               40,
               36,
               5,
               0,
               5,
               26,
               91,
               1,
               101
            },
            1,
            202
         )
      );
   }
}
