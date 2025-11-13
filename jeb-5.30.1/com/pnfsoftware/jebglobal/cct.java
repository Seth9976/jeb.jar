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

public class cct extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cct.class);
   private static final Set RF = new HashSet();
   private static final Set xK = new HashSet();

   public cct() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      int var1 = 0;
      cct.eo var2 = new cct.eo();

      for (IDInstruction var4 : this.cfg.instructions()) {
         if (!var4.visitInstructionPostOrder(var2, true)) {
            var4.transformToNop();
            var1++;
         }
      }

      var1 += var2.q;
      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   static {
      RF.add("Ljava/lang/CharSequence;");
      RF.add("Ljava/lang/String;");
      RF.add("Ljava/lang/Boolean;");
      RF.add("Ljava/lang/Byte;");
      RF.add("Ljava/lang/Character;");
      RF.add("Ljava/lang/Short;");
      RF.add("Ljava/lang/Integer;");
      RF.add("Ljava/lang/Long;");
      RF.add("Ljava/lang/Float;");
      RF.add("Ljava/lang/Double;");
      RF.add("Ljava/lang/Math;");
      RF.add("Ljava/lang/StrictMath;");
      xK.add("Ljava/lang/Math;->random()D");
      xK.add("Ljava/lang/StrictMath;->random()D");
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            IDExpression var5 = this.q(var4);
            if (var5 != null) {
               if (var2 instanceof IDInstruction var6 && var6.isInvoke()) {
                  var3.interrupt(false);
               } else if (var2 != null && var2.replaceSubExpression(var1, var5)) {
                  this.q++;
               }
            }
         }
      }

      private IDExpression q(IDCallInfo var1) {
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
            } else if (cct.RF.contains(var5.csig) && !cct.xK.contains(var2)) {
               IDState var6 = cct.this.g.getEmulator();
               Watchdog var24 = var6.setWatchdog(cct.this.ctx.getWatchdog());
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
                                 var10 = cct.this.g.createStaticField(var14, true);
                                 break;
                              case "Ljava/lang/Byte;":
                                 byte var34 = (Byte)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;", Arrays.asList(cct.this.g.createByte(var34))
                                    );
                                 break;
                              case "Ljava/lang/Character;":
                                 char var33 = (Character)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC,
                                       "Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;",
                                       Arrays.asList(cct.this.g.createChar(var33))
                                    );
                                 break;
                              case "Ljava/lang/Short;":
                                 short var32 = (Short)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;", Arrays.asList(cct.this.g.createShort(var32))
                                    );
                                 break;
                              case "Ljava/lang/Integer;":
                                 int var31 = (Integer)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;", Arrays.asList(cct.this.g.createInt(var31))
                                    );
                                 break;
                              case "Ljava/lang/Long;":
                                 long var30 = (Long)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;", Arrays.asList(cct.this.g.createLong(var30))
                                    );
                                 break;
                              case "Ljava/lang/Float;":
                                 float var29 = (Float)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;", Arrays.asList(cct.this.g.createFloat(var29))
                                    );
                                 break;
                              case "Ljava/lang/Double;":
                                 double var28 = (Double)var6.getObject(var9);
                                 var10 = cct.this.g
                                    .createCallInfo(
                                       DInvokeType.STATIC, "Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;", Arrays.asList(cct.this.g.createDouble(var28))
                                    );
                                 break;
                              case "Ljava/lang/String;":
                                 String var13 = var6.getStringObject(var9);
                                 if (var13 != null) {
                                    var10 = cct.this.g.createString(var13);
                                 }
                           }
                        } else {
                           Object var11 = var6.getArrayObject(var9);
                           switch (var23) {
                              case "Z":
                                 var10 = cct.this.g.createBooleanArray((boolean[])var11);
                                 break;
                              case "B":
                                 var10 = cct.this.g.createByteArray((byte[])var11);
                                 break;
                              case "C":
                                 var10 = cct.this.g.createCharArray((char[])var11);
                                 break;
                              case "S":
                                 var10 = cct.this.g.createShortArray((short[])var11);
                                 break;
                              case "I":
                                 var10 = cct.this.g.createIntArray((int[])var11);
                                 break;
                              case "J":
                                 var10 = cct.this.g.createLongArray((long[])var11);
                                 break;
                              case "F":
                                 var10 = cct.this.g.createFloatArray((float[])var11);
                                 break;
                              case "D":
                                 var10 = cct.this.g.createDoubleArray((double[])var11);
                                 break;
                              case "Ljava/lang/String;":
                                 var10 = cct.this.g.createStringArray((String[])var11);
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
                  cct.q.catchingSilent(var19);
                  return null;
               } catch (Exception var20) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var20, cct.this.ctx.getMethodSignature());
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
