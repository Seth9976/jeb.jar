package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.HashMap;
import java.util.Map;

public class cbo extends AbstractDOptimizer {
   private static Map q = new HashMap();
   private static Map RF = new HashMap();

   @Override
   public int perform() {
      cbo.eo var1 = new cbo.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstructionPostOrder(var1, true);
      }

      return var1.q;
   }

   private IDExpression q(IDCallInfo var1) {
      String var2 = var1.getMethodSignature();
      if (var2 != null && !var2.isEmpty() && var2.substring(0, var2.length() - 1).endsWith("Value()")) {
         String[] var3 = (String[])q.get(var2);
         if (var3 == null) {
            var2 = (String)RF.get(var2);
            if (var2 == null) {
               return null;
            }

            var3 = (String[])q.get(var2);
            if (var3 == null) {
               return null;
            }
         }

         IDExpression var4 = var1.getArgument(0);
         if (var4 instanceof IDOperation var5 && var5.isCast(this.tf.createType(var3[0]))) {
            IDExpression var11 = var5.getRight();
            if (var11.getType() == this.tf.createType(var3[1])) {
               return var11;
            }
         } else {
            if (var4 instanceof IDCallInfo var6 && var6.getMethodSignature().equals(var3[2])) {
               return var6.getArgument(0);
            }

            if (var4 instanceof IDStaticField var7) {
               String var8 = var7.getClassSignature();
               if (var8.equals(var3[0])) {
                  String var9 = var7.getFieldname();
                  if (var8.equals("Ljava/lang/Boolean;")) {
                     if (var9.equals("TRUE")) {
                        return this.g.createBoolean(true);
                     }

                     if (var9.equals("FALSE")) {
                        return this.g.createBoolean(false);
                     }
                  } else if (var8.equals("Ljava/lang/Byte;")) {
                     if (var9.equals("MAX_VALUE")) {
                        return this.g.createByte((byte)127);
                     }

                     if (var9.equals("MIN_VALUE")) {
                        return this.g.createByte((byte)-128);
                     }
                  } else if (var8.equals("Ljava/lang/Short;")) {
                     if (var9.equals("MAX_VALUE")) {
                        return this.g.createShort((short)32767);
                     }

                     if (var9.equals("MIN_VALUE")) {
                        return this.g.createShort((short)-32768);
                     }
                  } else if (var8.equals("Ljava/lang/Integer;")) {
                     if (var9.equals("MAX_VALUE")) {
                        return this.g.createInt(Integer.MAX_VALUE);
                     }

                     if (var9.equals("MIN_VALUE")) {
                        return this.g.createInt(Integer.MIN_VALUE);
                     }
                  } else if (var8.equals("Ljava/lang/Long;")) {
                     if (var9.equals("MAX_VALUE")) {
                        return this.g.createLong(Long.MAX_VALUE);
                     }

                     if (var9.equals("MIN_VALUE")) {
                        return this.g.createLong(Long.MIN_VALUE);
                     }
                  }
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private int q(IDInstruction var1) {
      IDVar var2 = (IDVar)var1.getAssignDestination();
      IJavaType var3 = var2.getType();
      if (var3 != null && var3.isPrimitive()) {
         String var8 = var3.getName();
         long var4;
         long var6;
         switch (var8) {
            case "B":
               var4 = 127L;
               var6 = -128L;
               break;
            case "C":
               var4 = 65535L;
               var6 = 0L;
               break;
            case "S":
               var4 = 32767L;
               var6 = -32768L;
               break;
            case "I":
               var4 = 2147483647L;
               var6 = -2147483648L;
               break;
            default:
               return 0;
         }

         int var15 = 0;
         if (var1.getAssignSource() instanceof IDOperation var10 && var10.isCast() && var10.getRight() instanceof IDImm var11) {
            IJavaType var17 = var10.getOperator().getCastType();
            if (var17 == var3) {
               long var13 = var11.getRawValue();
               if (var13 >= var6 && var13 <= var4) {
                  var1.setAssignSource(var11);
                  var15++;
               }
            }
         }

         return var15;
      } else {
         return 0;
      }
   }

   private int q(IDNewArrayInfo var1) {
      if (var1.getInitialValues().isEmpty()) {
         return 0;
      } else if (var1.getType() != null && var1.getType().isArray() && var1.getType().getDimensions() <= 1) {
         IJavaType var2 = var1.getType().getArrayElementType();
         if (!var2.isPrimitive()) {
            return 0;
         } else {
            String var7 = var2.getName();
            long var3;
            long var5;
            switch (var7) {
               case "B":
                  var3 = 127L;
                  var5 = -128L;
                  break;
               case "C":
                  var3 = 65535L;
                  var5 = 0L;
                  break;
               case "S":
                  var3 = 32767L;
                  var5 = -32768L;
                  break;
               case "I":
                  var3 = 2147483647L;
                  var5 = -2147483648L;
                  break;
               default:
                  return 0;
            }

            int var16 = 0;
            int var17 = 0;

            for (IDExpression var10 : var1.getInitialValues()) {
               if (var10 instanceof IDOperation var11 && var11.isCast() && var11.getRight() instanceof IDImm var12) {
                  IJavaType var18 = var11.getOperator().getCastType();
                  if (var18 == var2) {
                     long var14 = var12.getRawValue();
                     if (var14 >= var5 && var14 <= var3) {
                        var1.setInitialValue(var17, var12);
                        var16++;
                     }
                  }
               }

               var17++;
            }

            return var16;
         }
      } else {
         return 0;
      }
   }

   static {
      q.put("Ljava/lang/Boolean;->booleanValue()Z", new String[]{"Ljava/lang/Boolean;", "Z", "Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;"});
      q.put("Ljava/lang/Character;->charValue()C", new String[]{"Ljava/lang/Character;", "C", "Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;"});
      q.put("Ljava/lang/Byte;->byteValue()B", new String[]{"Ljava/lang/Byte;", "B", "Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;"});
      q.put("Ljava/lang/Short;->shortValue()S", new String[]{"Ljava/lang/Short;", "S", "Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;"});
      q.put("Ljava/lang/Integer;->intValue()I", new String[]{"Ljava/lang/Integer;", "I", "Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;"});
      q.put("Ljava/lang/Long;->longValue()J", new String[]{"Ljava/lang/Long;", "J", "Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;"});
      q.put("Ljava/lang/Float;->floatValue()F", new String[]{"Ljava/lang/Float;", "F", "Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;"});
      q.put("Ljava/lang/Double;->doubleValue()D", new String[]{"Ljava/lang/Double;", "D", "Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;"});
      RF.put("Ljava/lang/Number;->byteValue()B", "Ljava/lang/Byte;->byteValue()B");
      RF.put("Ljava/lang/Number;->shortValue()S", "Ljava/lang/Short;->shortValue()S");
      RF.put("Ljava/lang/Number;->intValue()I", "Ljava/lang/Integer;->intValue()I");
      RF.put("Ljava/lang/Number;->longValue()J", "Ljava/lang/Long;->longValue()J");
      RF.put("Ljava/lang/Number;->floatValue()F", "Ljava/lang/Float;->floatValue()F");
      RF.put("Ljava/lang/Number;->doubleValue()D", "Ljava/lang/Double;->doubleValue()D");
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4 && var4.isCast()) {
            IJavaType var12 = var4.getOperator().getCastType();
            IDExpression var9 = var4.getRight();
            IJavaType var10 = var9.getType();
            if (var9 instanceof IDImm var11 && var11.isNullRef()) {
               if (var2.replaceSubExpression(var1, var9)) {
                  this.q++;
               }
            } else if (var12.equals(var10)) {
               if (var2.replaceSubExpression(var1, var9)) {
                  this.q++;
               }
            } else if (!var10.isVoid()
               && cbo.this.g.getTypeInfoProvider().isCompatible(var10.getSignature(), var12.getSignature())
               && var2.replaceSubExpression(var1, var9)) {
               this.q++;
            }
         } else if (var1 instanceof IDNewArrayInfo var5) {
            if (cbo.this.q(var5) > 0) {
               this.q++;
            }
         } else if (var1 instanceof IDInstruction var6 && var6.isAssignToVar()) {
            if (cbo.this.q(var6) > 0) {
               this.q++;
            }
         } else if (var1 instanceof IDCallInfo var7) {
            IDExpression var8 = cbo.this.q(var7);
            if (var8 != null && var2.replaceSubExpression(var1, var8)) {
               this.q++;
            }
         }
      }
   }
}
