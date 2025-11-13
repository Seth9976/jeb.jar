package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
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
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bqi {
   private IDGlobalContext A;
   private IJavaTypeFactory kS;
   private IDMethodContext wS;
   private static final Map UT = new HashMap();
   private static final Map E = new HashMap();
   private static final Map sY = new HashMap();
   public static final Map pC = new TreeMap();

   public bqi(IDGlobalContext var1) {
      this(var1, null);
   }

   public bqi(IDGlobalContext var1, IDMethodContext var2) {
      this.A = var1;
      this.kS = var1.getTypeFactory();
      this.wS = var2;
   }

   public IDImm pC(IDCallInfo var1, IDInstruction var2) {
      return this.pC(var1.getMethodSignature(), var1.getArguments(), var2);
   }

   public IDImm pC(String var1, List var2, IDInstruction var3) {
      IDImm var4 = null;
      Long var5 = (Long)pC.get(var1);
      if (var5 != null) {
         String var6 = this.pC(var1, var2);
         if (var6 != null) {
            IJavaType var7 = this.kS.createType(var6);
            var4 = this.A.createImm(var5, var7);
         }
      }

      if (var4 == null && var1.equals("Ljava/lang/Long;->compare(JJ)I")) {
         if (var2.get(1) instanceof IDImm var13) {
            long var18 = var13.getRawValue();
            if (var18 <= 1L) {
               IDExpression var10 = (IDExpression)var2.get(0);
               if (var10 instanceof IDCallInfo var11) {
                  String var12 = var11.getMethodSignature();
                  if (E.containsKey(var12)) {
                     var4 = this.A.createInt(1);
                  }
               }
            }
         } else if (var2.get(0) instanceof IDImm var15) {
            long var20 = var15.getRawValue();
            if (var20 <= 1L) {
               IDExpression var22 = (IDExpression)var2.get(1);
               if (var22 instanceof IDCallInfo var23) {
                  String var24 = var23.getMethodSignature();
                  if (E.containsKey(var24)) {
                     var4 = this.A.createInt(-1);
                  }
               }
            }
         }
      }

      if (var4 == null && UT.containsKey(var1) && var2.get(0) instanceof IDImm var14 && var14.isString()) {
         String var17 = var14.getStringValue(this.A);
         if ("".equals(var17)) {
            int var21 = (Integer)UT.get(var1);
            var4 = this.A.createInt(var21);
         }
      }

      return var4;
   }

   public IDImm pC(IDOperation var1, IDInstruction var2) {
      IDImm var3 = null;
      if (var1.getOperator().isAnyOf(JavaOperatorType.SHR, JavaOperatorType.USHR) && var1.getRight() instanceof IDImm var4) {
         if (var1.getLeft() instanceof IDCallInfo var19) {
            String var20 = var19.getMethodSignature();
            Integer var11 = (Integer)sY.get(var20);
            if (var11 != null && (int)var4.getRawValue() == var11) {
               var3 = this.A.createInt(0);
            }
         } else if (var1.getLeft() instanceof IDVar var6 && var2 != null && this.wS != null && var2.getContext() == this.wS) {
            CFG var26 = this.wS.getCfg();
            IDFA var27 = var26.doDataFlowAnalysis();
            Collection var12 = var27.getBlockUseDefs(var2.getOffset(), var6.getId());
            if (var12.size() == 1) {
               long var13 = (Long)var12.iterator().next();
               if (var13 >= 0L) {
                  IDInstruction var15 = (IDInstruction)var26.getInstruction(var13);
                  if (var15 != null && var15.isAssignToVar(var6.getId()) && var15.getAssignSource() instanceof IDCallInfo var16) {
                     String var28 = var16.getMethodSignature();
                     Integer var18 = (Integer)sY.get(var28);
                     if (var18 != null && (int)var4.getRawValue() == var18) {
                        var3 = this.A.createInt(0);
                     }
                  }
               }
            }
         } else if (var1.getLeft() instanceof IDOperation var7
            && var7.getOperator().is(JavaOperatorType.ADD)
            && var7.getRight() instanceof IDImm var8
            && var7.getLeft() instanceof IDCallInfo var9) {
            String var25 = var9.getMethodSignature();
            if ("Landroid/os/Process;->getThreadPriority(I)I".equals(var25) && (int)var4.getRawValue() == 6 && (int)var8.getRawValue() == 20) {
               var3 = this.A.createInt(0);
            }
         }
      }

      return var3;
   }

   String pC(String var1, Collection var2) {
      JvmMethodSig var3 = JvmMethodSig.parse(var1);
      int var4 = 0;

      for (IDExpression var6 : var2) {
         if (!(var6 instanceof IDImm var7)) {
            return null;
         }

         String var8 = (String)var3.partypes.get(var4);
         switch (var8) {
            case "Z":
            case "B":
            case "S":
            case "I":
            case "J":
            case "F":
            case "D":
               if (var7.getRawValue() != 0L) {
                  return null;
               }
               break;
            case "C":
               if ((char)var7.getRawValue() != '0') {
                  return null;
               }
               break;
            case "Ljava/lang/String;":
            case "Ljava/lang/CharSequence;":
               if (!var7.isString() || !"".equals(var7.getStringValue(this.A))) {
                  return null;
               }
               break;
            default:
               return null;
         }

         var4++;
      }

      return var3.rettype;
   }

   static {
      UT.put("Landroid/text/TextUtils;->indexOf(Ljava/lang/CharSequence;C)I", -1);
      UT.put("Landroid/text/TextUtils;->indexOf(Ljava/lang/CharSequence;CI)I", -1);
      UT.put("Landroid/text/TextUtils;->indexOf(Ljava/lang/CharSequence;CII)I", -1);
      UT.put("Landroid/text/TextUtils;->lastIndexOf(Ljava/lang/CharSequence;C)I", -1);
      UT.put("Landroid/text/TextUtils;->lastIndexOf(Ljava/lang/CharSequence;CI)I", -1);
      UT.put("Landroid/text/TextUtils;->lastIndexOf(Ljava/lang/CharSequence;CII)I", -1);
      UT.put("Landroid/text/TextUtils;->getTrimmedLength(Ljava/lang/CharSequence;)I", 0);
      E.put("Ljava/lang/System;->currentTimeMillis()J", 0);
      E.put("Ljava/lang/System;->nanoTime()J", 0);
      E.put("Landroid/os/Process;->getElapsedCpuTime()J", 0);
      E.put("Landroid/os/SystemClock;->elapsedRealtime()J", 0);
      E.put("Landroid/os/SystemClock;->elapsedRealtimeNanos()J", 0);
      E.put("Landroid/os/SystemClock;->currentThreadTimeMillis()J", 0);
      E.put("Landroid/os/SystemClock;->uptimeMillis()J", 22);
      sY.put("Landroid/view/ViewConfiguration;->getLongPressTimeout()I", 16);
      sY.put("Landroid/view/ViewConfiguration;->getMultiPressTimeout()I", 16);
      sY.put("Landroid/view/ViewConfiguration;->getKeyRepeatTimeout()I", 16);
      sY.put("Landroid/view/ViewConfiguration;->getKeyRepeatDelay()I", 16);
      sY.put("Landroid/os/Process;->myPid()I", 22);
      sY.put("Landroid/os/Process;->myTid()I", 22);

      try {
         byte[] var0 = AssetManager.getAssetBytes(ckx.pC(new byte[]{34, 9, 7, 9, 27, 71, 5, 1, 26}, 2, 26));
         if (var0 != null) {
            String var1 = ckx.pC(new byte[]{-127, 1, 3, 1, 7, 107, 106, 3, 1, 15, 1}, 1, 177);
            ckv.pC(Strings.encodeASCII(var1), var0);
            String var2 = Strings.decodeASCII(var0);

            for (String var6 : Strings.splitLines(var2)) {
               var6 = var6.trim();
               if (!var6.isEmpty() && !var6.startsWith("#")) {
                  String[] var7 = var6.split(",");
                  if (var7.length == 2) {
                     String var8 = var7[0];
                     Long var9 = Long.parseLong(var7[1]);
                     pC.put(var8, var9);
                  }
               }
            }
         }
      } catch (Exception var10) {
         pC.clear();
      }

      pC.put("Landroid/view/KeyEvent;->keyCodeFromString(Ljava/lang/String;)I", 0L);
      pC.put("Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I", 0L);
      pC.put("Landroid/view/View$MeasureSpec;->makeSafeMeasureSpec(II)I", 0L);
      pC.put("Lorg/apache/http/conn/ssl/AbstractVerifier;->acceptableCountryWildcard(Ljava/lang/String;)Z", 1L);
      pC.put("Landroid/telephony/cdma/CdmaCellLocation;->convertQuartSecToDecDegrees(I)D", 0L);
      pC.put("Landroid/text/AndroidCharacter;->getMirror(C)C", 48L);
      pC.put("Landroid/os/Process;->getUidForName(Ljava/lang/String;)I", -1L);
      pC.put("Landroid/os/Process;->getGidForName(Ljava/lang/String;)I", -1L);
   }
}
