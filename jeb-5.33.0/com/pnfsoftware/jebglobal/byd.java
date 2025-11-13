package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class byd extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(byd.class);
   private static final Set A = new HashSet();
   private static final Set kS = new HashSet();

   public byd() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      int var1 = 0;
      byd.Av var2 = new byd.Av();

      for (IDInstruction var4 : this.cfg.instructions()) {
         if (!var4.visitInstructionPostOrder(var2, true)) {
            var4.transformToNop();
            var1++;
         }
      }

      var1 += var2.pC;
      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   static {
      A.add("Ljava/lang/CharSequence;");
      A.add("Ljava/lang/String;");
      A.add("Ljava/lang/Boolean;");
      A.add("Ljava/lang/Byte;");
      A.add("Ljava/lang/Character;");
      A.add("Ljava/lang/Short;");
      A.add("Ljava/lang/Integer;");
      A.add("Ljava/lang/Long;");
      A.add("Ljava/lang/Float;");
      A.add("Ljava/lang/Double;");
      A.add("Ljava/lang/Math;");
      A.add("Ljava/lang/StrictMath;");
      kS.add("Ljava/lang/Math;->random()D");
      kS.add("Ljava/lang/StrictMath;->random()D");
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            IDExpression var5 = this.pC(var4);
            if (var5 != null) {
               if (var2 instanceof IDInstruction var6 && var6.isInvoke()) {
                  var3.interrupt(false);
               } else if (var2 != null && var2.replaceSubExpression(var1, var5)) {
                  this.pC++;
               }
            }
         }
      }

      private IDExpression pC(IDCallInfo var1) {
         String var2 = var1.getMethodSignature();
         if (var2 == null) {
            return null;
         } else {
            for (IDExpression var4 : var1.getArguments()) {
               if (!var4.isConstantImm()) {
                  return null;
               }
            }

            JvmMethodSig var5 = JvmMethodSig.parse(var2);
            boolean var22;
            String var23;
            if (var5.rettype.startsWith("[")) {
               var22 = true;
               var23 = var5.rettype.substring(1);
            } else {
               var22 = false;
               var23 = var5.rettype;
            }

            switch (var23) {
               case "Z":
               case "B":
               case "C":
               case "S":
               case "I":
               case "J":
               case "F":
               case "D":
               case "Ljava/lang/String;":
                  break;
               case "Ljava/lang/Boolean;":
                  if (var22 || var2.equals("Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Byte;":
                  if (var22 || var2.equals("Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Character;":
                  if (var22 || var2.equals("Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Short;":
                  if (var22 || var2.equals("Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Integer;":
                  if (var22 || var2.equals("Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Long;":
                  if (var22 || var2.equals("Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Float;":
                  if (var22 || var2.equals("Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;")) {
                     return null;
                  }
                  break;
               case "Ljava/lang/Double;":
                  if (var22 || var2.equals("Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;")) {
                     return null;
                  }
                  break;
               default:
                  return null;
            }

            if (var5.csig.equals("Ljava/lang/String;") && (var5.mname.equals("getBytes") || var5.mname.equals("toCharArray"))) {
               return null;
            } else if (byd.A.contains(var5.csig) && !byd.kS.contains(var2)) {
               IDState var6 = byd.this.g.getEmulator();
               Watchdog var24 = var6.setWatchdog(byd.this.ctx.getWatchdog());
               boolean var8 = var6.setExceptionHandlingEnabled(false);

               try {
                  IDImm var9 = var1.evaluate(var6);
                  if (var9 == null) {
                     return null;
                  } else {
                     Object var10 = null;
                     if (var9.isRef()) {
                        if (var9.isNullRef()) {
                           return null;
                        }

                        if (!var22) {
                           switch (var23) {
                              case "Ljava/lang/Boolean;":
                                 boolean var35 = (Boolean)var6.getObject(var9);
                                 String var14 = "Ljava/lang/Boolean;->" + (var35 ? "TRUE" : "FALSE") + ":Ljava/lang/Boolean;";
                                 var10 = byd.this.g.createStaticField(var14, true);
                                 break;
                              case "Ljava/lang/Byte;":
                                 byte var34 = (Byte)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;", Arrays.asList(byd.this.g.createByte(var34))
                                    );
                                 break;
                              case "Ljava/lang/Character;":
                                 char var33 = (Character)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC,
                                       "Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;",
                                       Arrays.asList(byd.this.g.createChar(var33))
                                    );
                                 break;
                              case "Ljava/lang/Short;":
                                 short var32 = (Short)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;", Arrays.asList(byd.this.g.createShort(var32))
                                    );
                                 break;
                              case "Ljava/lang/Integer;":
                                 int var31 = (Integer)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;", Arrays.asList(byd.this.g.createInt(var31))
                                    );
                                 break;
                              case "Ljava/lang/Long;":
                                 long var30 = (Long)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;", Arrays.asList(byd.this.g.createLong(var30))
                                    );
                                 break;
                              case "Ljava/lang/Float;":
                                 float var29 = (Float)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;", Arrays.asList(byd.this.g.createFloat(var29))
                                    );
                                 break;
                              case "Ljava/lang/Double;":
                                 double var28 = (Double)var6.getObject(var9);
                                 var10 = byd.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;", Arrays.asList(byd.this.g.createDouble(var28))
                                    );
                                 break;
                              case "Ljava/lang/String;":
                                 String var13 = var6.getStringObject(var9);
                                 if (var13 != null) {
                                    var10 = byd.this.g.createString(var13);
                                 }
                           }
                        } else {
                           Object var11 = var6.getArrayObject(var9);
                           switch (var23) {
                              case "Z":
                                 var10 = byd.this.g.createBooleanArray((boolean[])var11);
                                 break;
                              case "B":
                                 var10 = byd.this.g.createByteArray((byte[])var11);
                                 break;
                              case "C":
                                 var10 = byd.this.g.createCharArray((char[])var11);
                                 break;
                              case "S":
                                 var10 = byd.this.g.createShortArray((short[])var11);
                                 break;
                              case "I":
                                 var10 = byd.this.g.createIntArray((int[])var11);
                                 break;
                              case "J":
                                 var10 = byd.this.g.createLongArray((long[])var11);
                                 break;
                              case "F":
                                 var10 = byd.this.g.createFloatArray((float[])var11);
                                 break;
                              case "D":
                                 var10 = byd.this.g.createDoubleArray((double[])var11);
                                 break;
                              case "Ljava/lang/String;":
                                 var10 = byd.this.g.createStringArray((String[])var11);
                           }
                        }
                     } else {
                        if (var22) {
                           return null;
                        }

                        var10 = var9.duplicate();
                     }

                     return (IDExpression)var10;
                  }
               } catch (DexDecEvaluationException var19) {
                  byd.pC.catchingSilent(var19);
                  return null;
               } catch (Exception var20) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var20, byd.this.ctx.getMethodSignature());
                  return null;
               } finally {
                  var6.setExceptionHandlingEnabled(var8);
                  var6.setWatchdog(var24);
               }
            } else {
               return null;
            }
         }
      }
   }
}
