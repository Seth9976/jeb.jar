package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DEmuExternalPolicy;
import com.pnfsoftware.jeb.core.units.code.android.ir.DExecutionParameters;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalItercountExceededException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalStubException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalTimeoutExceededException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecNativeEvalFailedException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class bxp extends AbstractDOptimizer {
   private static final ILogger UT = GlobalLog.getLogger(bxp.class);
   public static final String pC = ckx.pC(
      new byte[]{10, 61, 47, 42, 38, 59, 46, 38, 51, 127, 108, 38, 106, 114, 104, 105, 109, 122, 99, 110, 109, 115, 126, 124, 98, 105, 27}, 2, 159
   );
   public static final String A = ckx.pC(new byte[]{93, 27, 13, 12, 7, 6, 27, 7, 9, 24, 27, 1, 6, 17, 11, 9, 4, 29, 6, 1, 17, 24, 2, 11, 11, 23, 27, 10}, 1, 20);
   public static final String kS = ckx.pC(
      new byte[]{10, 61, 47, 42, 38, 59, 46, 38, 51, 127, 108, 38, 106, 114, 104, 105, 109, 122, 99, 110, 109, 121, 124, 121, 101, 110, 10}, 2, 18
   );
   public static final String wS = ckx.pC(new byte[]{10, 61, 47, 42, 38, 59, 46, 38, 51, 127, 106, 54, 96, 108, 117, 112, 119, 116}, 2, 136);
   private static final String E = S.L("Result of execution:") + " ";
   private static final String sY = S.L("\nSome native JNI calls were emulated.");
   private static final String ys = ckx.pC(new byte[]{-122, 1, 6, 17, 11, 9, 4}, 1, 194);
   private boolean ld = true;
   private boolean gp = true;
   private boolean oT = true;
   private boolean fI = true;
   private int WR;
   private int NS;
   private static final String vP = ckx.pC(
      new byte[]{
         91,
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
         22,
         19,
         2,
         85,
         7,
         7,
         29,
         74,
         22,
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
         127
      },
      1,
      23
   );
   private boolean xC;
   private bxp.bO ED = new bxp.bO();
   private final String Sc = ckx.pC(new byte[]{34, 31, 0, 48, 28, 0, 19, 41, 0, 84, 77, 14, 89, 84, 84, 93}, 2, 235);

   public bxp() {
      this(1, 2);
   }

   public bxp(int var1, int var2) {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bpl.kS(this);
      this.WR = var1;
      this.NS = var2;
   }

   public void pC(int var1) {
      this.WR = var1;
   }

   public void A(int var1) {
      this.NS = var1;
   }

   @Override
   public int perform() {
      if (this.WR == 0) {
         return 0;
      } else {
         int var1 = 0;
         this.xC = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.g.getDecompiler()).E();
         if (this.ld) {
            IdentityHashMap var2 = new IdentityHashMap();
            ccw var3 = this.pC(pC);
            bxp.Av var4 = new bxp.Av(var2, var3);
            int var5 = var4.pC();
            var1 += var5;
            this.pC(var2);
            if (var5 > 0) {
               this.cfg.invalidateDataFlowAnalysis();
            }
         }

         if (this.gp && !bpl.pC(this.g)) {
            int var10 = 0;
            IdentityHashMap var14 = new IdentityHashMap();
            ccw var17 = this.pC(A);
            long var20 = 0L;

            label83:
            while (true) {
               Iterator var7 = this.cfg.instructions(var20).iterator();

               IDInstruction var8;
               bxp.DH var9;
               do {
                  if (!var7.hasNext()) {
                     var1 += var10;
                     this.pC(var14);
                     if (var10 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label83;
                  }

                  var8 = (IDInstruction)var7.next();
                  var9 = new bxp.DH(var8, var14, var17);
                  var8.visitInstructionPostOrder(var9, true);
                  var10 += var9.wS;
               } while (!var9.UT);

               var20 = var8.getOffset();
            }
         }

         if (this.oT) {
            int var11 = 0;
            IdentityHashMap var15 = new IdentityHashMap();
            ccw var18 = this.pC(kS);
            long var21 = 0L;

            label70:
            while (true) {
               Iterator var23 = this.cfg.instructions(var21).iterator();

               IDInstruction var25;
               bxp.rQ var27;
               do {
                  if (!var23.hasNext()) {
                     var1 += var11;
                     this.pC(var15);
                     if (var11 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label70;
                  }

                  var25 = (IDInstruction)var23.next();
                  var27 = new bxp.rQ(var25, var15, var18);
                  var25.visitInstructionPostOrder(var27, true);
                  var11 += var27.wS;
               } while (!var27.UT);

               var21 = var25.getOffset();
            }
         }

         if (this.fI) {
            int var12 = 0;
            IdentityHashMap var16 = new IdentityHashMap();
            ccw var19 = this.pC(wS);
            long var22 = 0L;

            label57:
            while (true) {
               Iterator var24 = this.cfg.instructions(var22).iterator();

               IDInstruction var26;
               bxp.cq var28;
               do {
                  if (!var24.hasNext()) {
                     var1 += var12;
                     this.pC(var16);
                     if (var12 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label57;
                  }

                  var26 = (IDInstruction)var24.next();
                  var28 = new bxp.cq(var26, var16, var19);
                  var26.visitInstructionPostOrder(var28, true);
                  var12 += var28.wS;
               } while (!var28.UT);

               var22 = var26.getOffset();
            }
         }

         if (var1 > 0 && ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.decomp).sY() >= 2) {
            String var13 = S.L("This method contains decrypted strings");
            this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var13);
         }

         return var1;
      }
   }

   private void pC(Map var1) {
      for (IDInstruction var3 : var1.keySet()) {
         IDExpression var4 = (IDExpression)var1.get(var3);
         if (var4 == null) {
            var3.transformToNop();
         } else {
            var3.morph(DOpcodeType.IR_INVOKE, null, var4);
         }
      }
   }

   private ccw pC(String var1) {
      return ccw.pC(this.g, var1);
   }

   private void pC(IDState var1) {
      if (!Boolean.TRUE.equals((Boolean)var1.getData(this.Sc))) {
         var1.setData(this.Sc, Boolean.TRUE);
         IApkUnit var2 = DexUtil.findParentApk(this.dex);
         if (var2 != null) {
            String var3 = var2.getPackageName();
            if (var3 != null) {
               String var4 = var2.getApplicationName();
               String var5 = var2.getMainActivity();
               if (var4 != null || var5 != null) {
                  int var6 = var1.registerSandboxHooks(new bxq(this, var3));

                  try {
                     if (var4 != null) {
                        String var7 = "L" + var4.replace('.', '/') + ";";
                        String var8 = var7 + ckx.pC(new byte[]{110, 81, 31, 23, 49, 27, 2, 9, 0, 69, 0, 74, 127}, 2, 248);
                        if (this.dex.getClass(var7) != null) {
                           try {
                              IDImm var9 = var1.createNewInstance(var7);
                              if (this.dex.getMethod(var8) != null) {
                                 ArrayList var10 = new ArrayList();
                                 var10.add(var9);
                                 var1.invokeMethod(var8, var10, DInvokeType.VIRTUAL);
                              }
                           } catch (Exception var21) {
                           }
                        }
                     }

                     if (var5 != null) {
                        String var25 = "L" + var5.replace('.', '/') + ";";
                        IDexClass var26 = this.dex.getClass(var25);
                        if (var26 != null) {
                           try {
                              var1.loadClass(var25);
                           } catch (Exception var20) {
                           }

                           var1.pushContext("gu");
                           IDImm var27 = null;

                           try {
                              var27 = var1.createNewInstance(var25);
                           } catch (Exception var19) {
                           }

                           try {
                              if (DexUtil.findInternalVirtualMethodTarget(
                                    this.dex,
                                    var26,
                                    ckx.pC(new byte[]{-103, 1, 45, 49, 23, 4, 21, 17}, 1, 246),
                                    ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 95, 93, 85, 86, 23}, 2, 51)
                                 )
                                 != null) {
                                 String var28 = var25
                                    + ckx.pC(
                                       new byte[]{
                                          -92,
                                          19,
                                          81,
                                          1,
                                          45,
                                          49,
                                          23,
                                          4,
                                          21,
                                          17,
                                          77,
                                          100,
                                          45,
                                          15,
                                          10,
                                          22,
                                          29,
                                          6,
                                          13,
                                          75,
                                          64,
                                          28,
                                          92,
                                          109,
                                          55,
                                          27,
                                          10,
                                          8,
                                          9,
                                          94,
                                          18,
                                          127
                                       },
                                       1,
                                       137
                                    );

                                 try {
                                    IDImm var11 = var1.createNewInstance(
                                       ckx.pC(new byte[]{127, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94}, 1, 51)
                                    );
                                    var1.invokeMethod(var28, Arrays.asList(var27, var11), DInvokeType.VIRTUAL);
                                 } catch (DexDecEvalStubException var22) {
                                    if (!JvmMethodSig.nameAndParams(var22.getStubMethodSignature())
                                       .equals(
                                          ckx.pC(
                                             new byte[]{
                                                44,
                                                1,
                                                51,
                                                11,
                                                23,
                                                8,
                                                19,
                                                13,
                                                92,
                                                108,
                                                73,
                                                13,
                                                77,
                                                82,
                                                94,
                                                80,
                                                93,
                                                28,
                                                67,
                                                83,
                                                29,
                                                114,
                                                71,
                                                91,
                                                72,
                                                76,
                                                42,
                                                73,
                                                72
                                             },
                                             2,
                                             94
                                          )
                                       )) {
                                       throw var22;
                                    }
                                 }
                              }

                              var1.deleteTopContext();
                           } catch (Exception var23) {
                           }
                        }
                     }
                  } finally {
                     var1.unregisterSandboxHooks(var6);
                  }
               }
            }
         }
      }
   }

   private boolean pC(btp var1, boolean var2, Object var3, IDExpression var4, IDExpression var5, IVisitResults var6, ccw.Av var7, Map var8) {
      String var9 = DUtil.generateNativeAddress(this.ctx, var4);
      String var10 = null;
      byte[] var11 = null;
      Object var12 = null;
      if (var3 instanceof String) {
         var10 = (String)var3;
         var12 = this.g.createString(var10);
      } else if (var3 instanceof byte[]) {
         var11 = (byte[])var3;
         var12 = this.g.createByteArray(var11);
      }

      if (var12 == null) {
         return false;
      } else {
         String var13 = E + var4;
         if (var2) {
            var13 = var13 + sY;
         }

         ((IDExpression)var12).setOrigin(var13);
         if (var5.replaceSubExpression(var4, (IDExpression)var12)) {
            var6.setReplacedNode(var12);
         } else {
            if (!(var5 instanceof IDInstruction var14) || var14.getOpcode() != DOpcodeType.IR_INVOKE || !(var4 instanceof IDCallInfo)) {
               return false;
            }

            if (var10 != null) {
               IDNewInfo var15 = this.g.createNewInfo(vP, (IDExpression)var12);
               var8.put(var14, var15);
            } else if (var11 != null) {
               var8.put(var14, var12);
            } else {
               var8.put(var14, null);
            }
         }

         String var17;
         Object var18;
         if (var10 != null) {
            var18 = new DexDecompilerEvent.DecryptedString(var10, var9);
            String var16 = S.L("Method %s: Decrypted string: \"%s\"");
            var17 = Strings.ff(var16, this.ctx.getMethodSignature(), Formatter.escapeString(var10));
         } else {
            if (var11 == null) {
               throw new RuntimeException();
            }

            var18 = new DexDecompilerEvent.DecryptedBytes(var11, var9);
            String var19 = S.L("Method %s: Decrypted bytes: \"%s\"");
            var17 = Strings.ff(var19, this.ctx.getMethodSignature(), Formatter.byteArrayToHexString(var11));
         }

         this.decomp.recordDecompilationEvent((DexDecompilerEvent)var18);
         this.dex.getCommentManager().addMetaComment(var9, new MetaComment(((DexDecompilerEvent)var18).format(true, false), 1), false);
         if (var10 != null) {
            this.dex.getReferenceManager().addStringReference(var10, var9, DexReferenceType.REFLECTED);
         }

         UT.info(var17);
         return true;
      }
   }

   public static void pC(Exception var0, String var1, IDState var2) {
      if (var0 instanceof DexDecEvaluationException) {
         if (var0 instanceof DexDecEvalItercountExceededException) {
            UT.warn(S.L("Not enough emulator resources (max emulation count exceeded) when optimizing method %s"), var1);
         } else if (var0 instanceof DexDecEvalTimeoutExceededException) {
            UT.warn(S.L("Not enough emulator resources (timeout exceeded) when optimizing method %s"), var1);
         } else if (!(var0 instanceof DexDecEvalStubException) && var0 instanceof DexDecNativeEvalFailedException && Licensing.isReleaseBuild()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
               new RuntimeException(ckx.pC(new byte[]{13, 14, 4, 16, 4, 12, 71, 11, 27, 68, 77, 67, 76, 86, 80, 85, 25, 85, 77, 73, 94, 85, 86}, 2, 179), var0),
               var1
            );
         }
      } else {
         if (Licensing.isReleaseBuild()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var0, var1);
         }

         UT.catchingSilent(var0);
      }
   }

   class Av {
      Map pC;
      ccw A;
      int kS;

      Av(Map var2, ccw var3) {
         this.pC = var2;
         this.A = var3;
      }

      int pC() {
         String var1 = bxp.this.ctx.getMethodSignature();
         JvmMethodSig var2 = JvmMethodSig.parse(var1);
         if (!var2.mname.equals("<clinit>")) {
            return 0;
         } else {
            String var3 = var2.csig;
            ccw.Av var4 = this.A.pC(var3, true);
            if (var4.wS() > 0) {
               return 0;
            } else {
               boolean var5 = false;

               for (IDInstruction var7 : bxp.this.ctx.getCfg().instructions()) {
                  if (var7.isAssign()) {
                     String var8 = var7.getAssignDestination().getType().getSignature();
                     if (var8.equals("Ljava/lang/String;") || var8.equals("[B")) {
                        var5 = true;
                        break;
                     }
                  }
               }

               if (!var5) {
                  return 0;
               } else {
                  btp var17 = (btp)bxp.this.g.getEmulator();
                  if (!var17.canRun()) {
                     return 0;
                  } else {
                     int var18 = var17.vP();
                     Watchdog var19 = var17.setWatchdog(bxp.this.ctx.getWatchdog());
                     var17.pushContext(bxp.ys + "{" + var1 + "}");

                     label101: {
                        byte var10;
                        try {
                           Object[] var10000 = new Object[]{var3};
                           var17.loadClass(var3);
                           break label101;
                        } catch (Exception var15) {
                           bxp.pC(var15, var1, var17);
                           var4.pC(DUtil.generateNativeCoordinates(bxp.this.ctx, null), null, var15);
                           var10 = 0;
                        } finally {
                           var17.UO();
                           var17.setWatchdog(var19);
                        }

                        return var10;
                     }

                     var4.pC(DUtil.generateNativeCoordinates(bxp.this.ctx, null), null);
                     boolean var9 = var17.vP() - var18 > 0;
                     if (var9 && !com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC()) {
                        String var21 = S.L("Method %s: Some code could be decrypted but the native code emulator is not enabled in this build");
                        String var22 = String.format(var21, bxp.this.ctx.getMethodSignature());
                        bxp.UT.info(var22);
                        return 0;
                     } else {
                        btj var20 = var17.UT(var3);

                        for (IDInstruction var12 : bxp.this.cfg.instructions()) {
                           var12.visitInstruction(new bxr(this, var20, var17, var9, var4), true);
                        }

                        btr.pC(var17).pC();
                        return this.kS;
                     }
                  }
               }
            }
         }
      }
   }

   class DH extends bxp.Sv {
      DH(IDInstruction var2, Map var3, ccw var4) {
         super(var2, var3, var4);
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         this.A(var1, var2, var3);
      }

      public boolean A(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var2 instanceof IDInstruction var4 && var4.getOpcode() == DOpcodeType.IR_INVOKE && var4.getInvokeData() instanceof IDCallInfo var6) {
            IDexMethod var7 = bxp.this.dex.getMethod(var6.getMethodSignature());
            if (var7 != null && var7.isInternal() && var7.getData().isSynthetic()) {
               return false;
            }
         }

         if (var1 instanceof IDCallInfo var42) {
            String var43 = var42.getMethodSignature();
            if (bqh.pC(var43, bxp.this.g).pC()) {
               return false;
            } else {
               Watchdog.verify(bxp.this.ctx.getWatchdog());
               ccw.Av var44 = this.kS.pC(var43);
               if (var44 != null && !var44.UT()) {
                  return false;
               } else {
                  if (var44 == null) {
                     var44 = this.kS.pC(var43, true);
                  }

                  IDexMethod var8 = bxp.this.dex.getMethod(var43);
                  if (var8 != null && var8.isInternal() && var8.getData().getCodeItem() != null) {
                     IDexType var9 = var8.getPrototype().getReturnType();
                     int var10 = -1;
                     if (!var9.isPrimitive()) {
                        var10 = Arrays.asList("Ljava/lang/String;", "[B").indexOf(var9.getSignature());
                        if (var10 < 0) {
                           var44.pC(false);
                           return false;
                        }
                     } else if (var42.hasSideEffects(bxp.this.ctx, false)) {
                        return false;
                     }

                     boolean var11 = var44.A() >= 1;
                     var44.pC(true);
                     String var12 = var8.getSignature(false);
                     boolean var13 = var8.getData().isStatic();
                     ArrayList var14 = new ArrayList(var42.getArguments());
                     if (!var13) {
                        var14.set(0, bxp.this.g.createNull());
                     }

                     for (int var15 = 0; var15 < var14.size(); var15++) {
                        IDExpression var16 = (IDExpression)var14.get(var15);
                        if (!(var16 instanceof IDImm) && !var16.getVarIds().isEmpty()) {
                           IDExpression var17 = null;
                           if (var16 instanceof IDVar) {
                              var17 = this.pC(var16, var11);
                           } else if (var11) {
                              var17 = this.pC(var16, true);
                           }

                           if (var17 != null) {
                              var14.set(var15, var17);
                              var16 = var17;
                           }

                           if (!var16.getVarIds().isEmpty()) {
                              return false;
                           }
                        }
                     }

                     btp var45 = (btp)bxp.this.g.getEmulator();
                     if (!var45.canRun()) {
                        return false;
                     } else {
                        String var47 = null;
                        byte[] var18 = null;
                        String var19 = DUtil.generateNativeAddress(bxp.this.ctx, var1);
                        ICodeCoordinates var20 = DUtil.generateNativeCoordinates(bxp.this.ctx, var1);
                        Integer var21 = null;
                        Integer var22 = null;
                        IDEmuContext var23 = var45.pushContext(bxp.ys + "{" + var8.getSignature() + "}");
                        var23.pushOriginInfo(bxp.this.ctx.getMethodSignature());
                        int var24 = var45.vP();
                        int var25 = var45.DQ();
                        Watchdog var26 = var45.setWatchdog(bxp.this.ctx.getWatchdog());

                        Object var46;
                        try {
                           boolean var27 = false;
                           IDImm var50 = null;
                           bxp.Ws var29 = null;
                           if (bxp.this.xC) {
                              ArrayList var30 = new ArrayList();

                              for (IDExpression var32 : var14.subList(var13 ? 0 : 1, var14.size())) {
                                 if (var32 instanceof IDImm var33) {
                                    Object var34 = var33.getImmediateAsJavaObject(bxp.this.g);
                                    if (var34 == null) {
                                       var30 = null;
                                       break;
                                    }

                                    var30.add(var34);
                                 }
                              }

                              if (var30 != null) {
                                 Object var63 = bxp.this.ED.pC(var12, var30);
                                 if (var63 instanceof String) {
                                    var50 = var45.pC("Ljava/lang/String;", var63);
                                 }
                              }
                           }

                           if (var50 == null) {
                              if (bxp.this.xC) {
                                 bxp.K var55 = bxp.this.new K(var45, var8);
                                 var21 = var45.registerEmulatorHooks(var55);
                              }

                              var29 = bxp.this.new Ws(var45);
                              var22 = var45.registerEmulatorHooks(var29);
                              if (var10 >= 0 && !var8.getData().isStatic()) {
                                 IDexType var56 = var8.getClassType();
                                 String var64 = var56.getSignature(false) + "-><init>()V";
                                 IDexMethod var68 = bxp.this.dex.getMethod(var64);
                                 if (var68 != null && var68.isInternal()) {
                                    try {
                                       IDImm var71 = var45.pC(var68.getSignature(false), Arrays.asList(), DInvokeType.NEW, var56.getSignature(false));
                                       var14.set(0, var71);
                                    } catch (DexDecEvaluationException var39) {
                                    }
                                 }
                              }

                              Object[] var10000 = new Object[]{var1};
                              var27 = true;
                              bxp.this.pC(var45);
                              var50 = var45.A(var8, var14);
                              if (bxp.this.WR == 2 && var45.DQ() - var25 > 0) {
                                 var50 = null;
                              }
                           }

                           if (var50 == null) {
                              return false;
                           }

                           if (var10 == -1) {
                              if (var50.isRef()) {
                                 return false;
                              }

                              var46 = var50.duplicate();
                              ((IDExpression)var46).setType(bxp.this.tf.createType(var9.getSignature()));
                           } else if (var10 == 0) {
                              var47 = var45.getStringObject(var50);
                              if (var47 == null) {
                                 return false;
                              }

                              var46 = bxp.this.g.createString(var47);
                           } else {
                              if (var10 != 1) {
                                 throw new RuntimeException();
                              }

                              var18 = (byte[])var45.getArrayObject(var50);
                              if (var18 == null) {
                                 return false;
                              }

                              var46 = bxp.this.g.createByteArray(var18);
                           }

                           if (var27) {
                              bpl.pC(bxp.this.g, var43);
                              if (var29 != null) {
                                 brw var59 = ((bpr)bxp.this.g).ys();

                                 for (String var69 : var29.A) {
                                    var59.pC(var69, var45);
                                 }
                              }
                           }

                           boolean var60 = var45.vP() - var24 > 0;
                           if (var60 && !com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC()) {
                              String var67 = S.L(
                                 "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                              );
                              String var70 = String.format(var67, bxp.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                              bxp.UT.info(var70);
                              return false;
                           }

                           String var66 = bxp.E + var1;
                           if (var60) {
                              var66 = var66 + bxp.sY;
                           }

                           ((IDExpression)var46).setOrigin(var66);
                        } catch (Exception var40) {
                           bxp.pC(var40, var43, var45);
                           var44.pC(var20, var1, var40);
                           return false;
                        } finally {
                           if (var21 != null) {
                              var45.unregisterEmulatorHooks(var21);
                           }

                           if (var22 != null) {
                              var45.unregisterEmulatorHooks(var22);
                           }

                           var45.UO();
                           var45.setWatchdog(var26);
                        }

                        if (!var2.replaceSubExpression(var1, (IDExpression)var46)) {
                           if (!(var2 instanceof IDInstruction var48) || var48.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                              var44.A(var20, var1);
                              return false;
                           }

                           if (var10 == 0) {
                              IDNewInfo var51 = bxp.this.g.createNewInfo(bxp.vP, (IDExpression)var46);
                              this.A.put(this.pC, var51);
                           } else if (var10 == 1) {
                              this.A.put(this.pC, var46);
                           } else {
                              this.A.put(this.pC, null);
                           }
                        }

                        btr.pC(var45).pC();
                        var44.pC(var20, var1);
                        Object var49 = null;
                        String var52 = null;
                        if (var47 != null) {
                           var49 = new DexDecompilerEvent.DecryptedString(var47, var19);
                           String var53 = S.L("Method %s: Decrypted string: \"%s\"");
                           var52 = Strings.ff(var53, bxp.this.ctx.getMethodSignature(), Formatter.escapeString(var47));
                        } else if (var18 != null) {
                           var49 = new DexDecompilerEvent.DecryptedBytes(var18, var19);
                           String var54 = S.L("Method %s: Decrypted bytes: \"%s\"");
                           var52 = Strings.ff(var54, bxp.this.ctx.getMethodSignature(), Formatter.byteArrayToHexString(var18));
                        }

                        if (var49 != null) {
                           bxp.this.decomp.recordDecompilationEvent((DexDecompilerEvent)var49);
                           bxp.this.dex.getCommentManager().addMetaComment(var19, new MetaComment(((DexDecompilerEvent)var49).format(true, false), 1), false);
                           if (var47 != null) {
                              bxp.this.dex.getReferenceManager().addStringReference(var47, var19, DexReferenceType.REFLECTED);
                           }
                        }

                        if (var52 != null) {
                           bxp.UT.info(var52);
                        }

                        this.wS++;
                        return true;
                     }
                  } else {
                     var44.pC(false);
                     return false;
                  }
               }
            }
         } else {
            return false;
         }
      }

      private IDExpression pC(IDExpression var1, boolean var2) {
         if (var1 instanceof IDVar var6) {
            return this.pC(var6, var2);
         } else {
            IDExpression var3 = null;
            AtomicBoolean var4 = new AtomicBoolean(true);

            for (IDExpression var5 = var1; var5.visitDepthPost(new bxs(this, var2, var4)); var5 = var3) {
               if (!var4.get()) {
                  return var3;
               }

               var4.set(false);
               var3 = var1.duplicate();
            }

            return null;
         }
      }

      private IDExpression pC(IDVar var1, boolean var2) {
         bxp.this.analyzeChains();
         IDInstruction[] var3 = new IDInstruction[1];
         Set var4 = var1.getVarIds();
         Map var5 = bxp.this.dfa.getUseDefChains(this.pC.getOffset());
         if (!bpl.pC(bxp.this.cfg, var5, var4, var3)) {
            IDImm[] var8 = new IDImm[1];
            return !bpl.pC(bxp.this.cfg, var5, var4, var8) ? null : var8[0];
         } else {
            IDInstruction var6 = var3[0];
            if (var6 != null && var6.isAssign()) {
               IDExpression var7 = var6.getAssignSource();
               if (!var2) {
                  if (!(var7 instanceof IDImm)) {
                     return null;
                  }
               } else if (!var7.getVarIds().isEmpty() || var6.hasUseSideEffects(false)) {
                  return null;
               }

               return var7;
            } else {
               return null;
            }
         }
      }
   }

   class K implements IDEmulatorHooks {
      btp pC;
      boolean A;
      String kS;
      Map wS = new HashMap();

      K(btp var2, IDexMethod var3) {
         this.pC = var2;
         this.A = var3.getData().isStatic();
         this.kS = var3.getSignature(false);
      }

      @Override
      public Wrapper invokeMethod(long var1, String var3, List var4) {
         try {
            if (var3.equals(this.kS)) {
               ArrayList var5 = new ArrayList();

               for (IDImm var7 : var4.subList(this.A ? 0 : 1, var4.size())) {
                  Object var8 = this.pC.getObject(var7);
                  var5.add(var8);
               }

               this.wS.put(var1, var5);
            }

            return null;
         } catch (DexDecEvaluationException var9) {
            return null;
         }
      }

      @Override
      public Wrapper examineMethodResult(long var1, IDImm var3) {
         try {
            List var4 = (List)this.wS.remove(var1);
            if (var4 != null) {
               Object var5 = this.pC.getObject(var3);
               if (var5 != null) {
                  bxp.this.ED.pC(this.kS, var4, var5);
               }
            }

            return null;
         } catch (DexDecEvaluationException var6) {
            return null;
         }
      }
   }

   abstract static class Sv implements IDVisitor {
      protected final IDInstruction pC;
      protected final Map A;
      protected final ccw kS;
      protected int wS;
      protected boolean UT;

      Sv(IDInstruction var1, Map var2, ccw var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   class Ws implements IDEmulatorHooks {
      btp pC;
      Set A = new HashSet();

      Ws(btp var2) {
         this.pC = var2;
      }

      @Override
      public Wrapper getField(long var1, String var3, IDImm var4) {
         if (var4 == null) {
            IDexField var5 = bxp.this.dex.getField(var3);
            if (var5 != null && var5.isInternal() && (var5.getData().getAccessFlags() & 8) == 8 && brw.A(var5.getFieldTypeSignature(false))) {
               this.A.add(var3);
            }
         }

         return null;
      }
   }

   static class bO {
      Map pC = new HashMap();

      void pC(String var1, List var2, Object var3) {
         bxp.bO.Av var4 = (bxp.bO.Av)this.pC.get(var1);
         if (var4 == null) {
            var4 = new bxp.bO.Av();
            this.pC.put(var1, var4);
         }

         var4.pC.put(var2, var3);
      }

      Object pC(String var1, List var2) {
         bxp.bO.Av var3 = (bxp.bO.Av)this.pC.get(var1);
         return var3 == null ? null : var3.pC.get(var2);
      }

      static class Av {
         Map pC = new HashMap();
      }
   }

   class cq extends bxp.Sv {
      cq(IDInstruction var2, Map var3, ccw var4) {
         super(var2, var3, var4);
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            String var5 = var4.getMethodSignature();
            if (var5 != null) {
               IDexMethod var6 = bxp.this.dex.getMethod(var5);
               if (var6 != null && !var6.isInternal()) {
                  if (var5.startsWith("Ljava/lang/String;->")) {
                     if (!var5.equals(bxp.vP)) {
                        if (var5.startsWith("Ljava/lang/String;-><init>(") || var5.endsWith(")Ljava/lang/String;")) {
                           Watchdog.verify(bxp.this.ctx.getWatchdog());
                           ccw.Av var8 = this.kS.pC(var5);
                           if (var8 == null || var8.UT()) {
                              if (var8 == null) {
                                 var8 = this.kS.pC(var5, true);
                              }

                              var8.pC(true);
                              ArrayList var9 = new ArrayList(var4.getArguments());

                              for (IDExpression var11 : var9) {
                                 if (!var11.getVarIds().isEmpty()) {
                                    return;
                                 }
                              }

                              btp var29 = (btp)bxp.this.g.getEmulator();
                              if (var29.canRun()) {
                                 String var13 = DUtil.generateNativeAddress(bxp.this.ctx, var1);
                                 ICodeCoordinates var14 = DUtil.generateNativeCoordinates(bxp.this.ctx, var1);
                                 IDEmuContext var15 = var29.pushContext(bxp.ys + "{" + var6.getSignature() + "}");
                                 var15.pushOriginInfo(bxp.this.ctx.getMethodSignature());
                                 int var16 = var29.vP();
                                 DEmuExternalPolicy var17 = var29.setExternalPolicy(new DEmuExternalPolicy(true, true, true));
                                 Watchdog var18 = var29.setWatchdog(bxp.this.ctx.getWatchdog());

                                 String var12;
                                 IDImm var30;
                                 try {
                                    Object[] var10000 = new Object[]{var1};
                                    DInvokeType var19 = var4.getInvokeType();
                                    IDImm var20 = var29.pC(var5, var9, var19, var19 == DInvokeType.NEW ? "Ljava/lang/String;" : null);
                                    if (var20 == null) {
                                       return;
                                    }

                                    var12 = var29.getStringObject(var20);
                                    if (var12 == null) {
                                       return;
                                    }

                                    boolean var21 = var29.vP() - var16 > 0;
                                    if (var21 && !com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC()) {
                                       String var34 = S.L(
                                          "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                                       );
                                       String var23 = String.format(var34, bxp.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                                       bxp.UT.info(var23);
                                       return;
                                    }

                                    var30 = bxp.this.g.createString(var12);
                                    String var22 = bxp.E + var1;
                                    if (var21) {
                                       var22 = var22 + bxp.sY;
                                    }

                                    var30.setOrigin(var22);
                                 } catch (Exception var27) {
                                    bxp.pC(var27, var5, var29);
                                    var8.pC(var14, var1, var27);
                                    return;
                                 } finally {
                                    var29.UO();
                                    var29.setExternalPolicy(var17);
                                    var29.setWatchdog(var18);
                                 }

                                 if (!var2.replaceSubExpression(var1, var30)) {
                                    if (!(var2 instanceof IDInstruction var31) || var31.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                                       var8.A(var14, var1);
                                       return;
                                    }

                                    IDNewInfo var33 = bxp.this.g.createNewInfo(bxp.vP, var30);
                                    this.A.put(this.pC, var33);
                                 }

                                 btr.pC(var29).pC();
                                 var8.pC(var14, var1);
                                 DexDecompilerEvent.BuiltString var32 = new DexDecompilerEvent.BuiltString(var12, var13);
                                 bxp.this.decomp.recordDecompilationEvent(var32);
                                 bxp.this.dex.getCommentManager().addMetaComment(var13, new MetaComment(var32.format(true, false), 1), false);
                                 this.wS++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   class rQ extends bxp.Sv {
      rQ(IDInstruction var2, Map var3, ccw var4) {
         super(var2, var3, var4);
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         this.A(var1, var2, var3);
      }

      private boolean A(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.isStringImm()) {
            return false;
         } else if (var2 != null && var1.getType() != null && var1.getType().isJavaLangString()) {
            if (var1 instanceof IDCallInfo var4) {
               String var5 = var4.getMethodSignature();
               if (var5.equals(bxp.vP)) {
                  return false;
               }
            }

            IDExpression var46 = var1;
            Watchdog.verify(bxp.this.ctx.getWatchdog());
            boolean[] var47 = new boolean[1];
            Long var6 = this.pC(this.pC.getOffset(), var1, var47);
            if (var6 == null) {
               return false;
            } else {
               boolean var7 = var47[0];
               IDInstruction var8 = (IDInstruction)bxp.this.cfg.getInstruction(var6);
               if (var8 != null && var8.isStoreException()) {
                  return false;
               } else {
                  String var9 = "@" + var6;
                  String var10 = ckx.pC(new byte[]{6, 33, 51, 42, 38, 59}, 2, 49) + "_" + var9;
                  ccw.Av var11 = this.kS.pC(var10);
                  if (var11 != null && !var11.UT()) {
                     return false;
                  } else {
                     if (var11 == null) {
                        var11 = this.kS.pC(var10, true);
                     }

                     var11.pC(true);
                     long var12 = var6;
                     long var14 = this.pC.getOffset();
                     btp var16 = (btp)bxp.this.g.getEmulator();
                     if (!var16.canRun()) {
                        return false;
                     } else {
                        String var19 = DUtil.generateNativeAddress(bxp.this.ctx, var1);
                        ICodeCoordinates var20 = DUtil.generateNativeCoordinates(bxp.this.ctx, var1);
                        IDEmuContext var21 = var16.pushContext(bxp.ys + "{INLINED}");
                        var21.pushOriginInfo(bxp.this.ctx.getMethodSignature());
                        int var22 = var16.vP();
                        int var23 = var16.DQ();
                        DEmuExternalPolicy var24 = var16.setExternalPolicy(new DEmuExternalPolicy(true, true, true));
                        bxp.Ws var25 = bxp.this.new Ws(var16);
                        int var26 = var16.registerEmulatorHooks(var25);
                        Watchdog var27 = var16.setWatchdog(bxp.this.ctx.getWatchdog());

                        String var17;
                        IDImm var18;
                        try {
                           Object[] var10000 = new Object[]{var46};
                           var16.pushFrame(bxp.this.ctx.getMethodSignature());
                           TreeMap var28 = new TreeMap();
                           bxp.this.cfg.getInstructions().forEach(var1x -> var28.put((int)var1x.getOffset(), var1x));
                           DExecutionParameters var49 = new DExecutionParameters(var28);
                           var49.pc = (int)var12;
                           var49.pcThresholdMin = var49.pc;
                           var49.pcThresholdMax = (int)var14;
                           var49.pcExpectedTermination = var49.pcThresholdMax;
                           var16.execute(var49);
                           IDImm var30 = var46.evaluate(var16);
                           if (var30 == null || var30.isNullRef()) {
                              return false;
                           }

                           var17 = var16.getStringObject(var30);
                           if (var17 == null) {
                              return false;
                           }

                           if (bxp.this.WR == 2 && var16.DQ() - var23 > 0) {
                              return false;
                           }

                           boolean var31 = var16.vP() - var22 > 0;
                           if (var31 && !com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC()) {
                              String var65 = S.L(
                                 "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                              );
                              String var69 = String.format(var65, bxp.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                              bxp.UT.info(var69);
                              return false;
                           }

                           var18 = bxp.this.g.createString(var17);
                           String var32 = bxp.E + var1;
                           if (var31) {
                              var32 = var32 + bxp.sY;
                           }

                           var18.setOrigin(var32);
                           if (var25 != null) {
                              brw var33 = ((bpr)bxp.this.g).ys();

                              for (String var35 : var25.A) {
                                 var33.pC(var35, var16);
                              }
                           }
                        } catch (Exception var44) {
                           bxp.pC(var44, bxp.this.ctx.getMethodSignature(), var16);
                           var11.pC(var20, var1, var44);
                           return false;
                        } finally {
                           var16.unregisterEmulatorHooks(var26);
                           var16.UO();
                           var16.setExternalPolicy(var24);
                           var16.setWatchdog(var27);
                        }

                        boolean var48 = false;
                        if (!var2.replaceSubExpression(var1, var18)) {
                           if (!(var2 instanceof IDInstruction var50) || var50.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                              var11.A(var20, var1);
                              return false;
                           }

                           var48 = true;
                           IDNewInfo var54 = bxp.this.g.createNewInfo(bxp.vP, var18);
                           this.A.put(this.pC, var54);
                        }

                        long var51 = var12;

                        while (var51 < var14) {
                           IDInstruction var57 = (IDInstruction)bxp.this.cfg.getInstruction(var51);
                           if (var57 == null) {
                              break;
                           }

                           if (var57.isInvoke() && var57.getInvokeData() instanceof IDCallInfo var63) {
                              String var67 = var63.getMethodSignature();
                              bpl.pC(bxp.this.g, var67);
                           }

                           var51 += var57.getSize();
                        }

                        if (!var48 && var12 < var14 && !var7) {
                           int[] var52 = new int[1];
                           Collection var55 = this.pC(var12, var14, var52);
                           if (var55 != null && !var55.isEmpty()) {
                              int var58 = var52[0];
                              if (var58 < 2 && (bxp.this.NS == 1 && var58 == 0 || bxp.this.NS == 2)) {
                                 Couple var64 = bxp.this.cfg.getInstructionLocation(var12);
                                 Couple var68 = bxp.this.cfg.getInstructionLocation(var14);
                                 BasicBlock var70 = (BasicBlock)var64.getFirst();
                                 int var72 = (Integer)var64.getSecond();
                                 BasicBlock var36 = (BasicBlock)var68.getFirst();
                                 int var37 = (Integer)var68.getSecond();
                                 if (var70.outsize() == 1) {
                                    IDInstruction var38 = (IDInstruction)var70.get(var72);
                                    BasicBlock var39;
                                    if (var37 > 0) {
                                       var39 = DUtil.splitBlock(bxp.this.ctx, var36, var37);
                                    } else {
                                       var39 = var36;
                                    }

                                    BasicBlock var40;
                                    if (var72 < var70.size() - 1) {
                                       var40 = DUtil.splitBlock(bxp.this.ctx, var70, var72 + 1);
                                    } else {
                                       var40 = var70.getOutputBlock(0);
                                    }

                                    IDInstruction var41 = bxp.this.ctx.createJump((int)var14);
                                    var41.copyBaseFields(var38);
                                    var70.set(var72, var41);
                                    bxp.this.cfg.reconnectEdge(var70, var40, var39);
                                    DUtil.cleanGraph(bxp.this.ctx);
                                    this.UT = true;
                                    var3.interrupt(true);
                                 }
                              }
                           }
                        }

                        btr.pC(var16).pC();
                        var11.pC(var20, var1);
                        DexDecompilerEvent.DecryptedString var53 = new DexDecompilerEvent.DecryptedString(var17, var19);
                        bxp.this.decomp.recordDecompilationEvent(var53);
                        bxp.this.dex.getCommentManager().addMetaComment(var19, new MetaComment(var53.format(true, false), 1), false);
                        bxp.this.dex.getReferenceManager().addStringReference(var17, var19, DexReferenceType.REFLECTED);
                        String var56 = S.L("Method %s: Decrypted string: \"%s\"");
                        String var59 = String.format(var56, bxp.this.ctx.getMethodSignature(), Formatter.escapeString(var17));
                        bxp.UT.info(var59);
                        this.wS++;
                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      }

      Long pC(long var1, IDExpression var3, boolean[] var4) {
         bxp.this.analyzeChains();
         ArrayList var5 = new ArrayList();
         HashSet var6 = new HashSet();
         var3.collectVarIds(var6);
         var6.forEach(var3x -> var5.add(new Couple(var1, var3x)));
         long var7 = var1;
         ArrayList var9 = new ArrayList();
         ArrayList var10 = new ArrayList();

         while (!var5.isEmpty()) {
            long var11 = var7;

            for (Couple var14 : var5) {
               for (long var17 : bxp.this.dfa.getUseDefs((Long)var14.getFirst(), (Integer)var14.getSecond())) {
                  if (var17 < 0L || var17 >= var1) {
                     return null;
                  }

                  if (var17 < var11) {
                     var11 = var17;
                  }
               }
            }

            var5.clear();
            long var21 = var11;

            while (var21 < var7) {
               IDInstruction var24 = (IDInstruction)bxp.this.cfg.getInstruction(var21);
               var24.getDefUse(var9, var10);

               for (int var28 : var10) {
                  var5.add(new Couple(var21, var28));
               }

               var21 += var24.getSize();
            }

            var7 = var11;
         }

         long var20 = var7;

         while (var20 < var1) {
            IDInstruction var22 = (IDInstruction)bxp.this.cfg.getInstruction(var20);
            var22.getDefUse(var9, var10);

            for (int var25 : var9) {
               for (long var18 : bxp.this.dfa.getDefUses(var20, var25)) {
                  if (var18 > var1) {
                     var4[0] = true;
                     return !this.pC(var7, var1) ? null : var7;
                  }
               }
            }

            var20 += var22.getSize();
         }

         return !this.pC(var7, var1) ? null : var7;
      }

      boolean pC(long var1, long var3) {
         BasicBlock var5 = bxp.this.cfg.getBlockContaining(var1);
         BasicBlock var6 = bxp.this.cfg.getBlockContaining(var3);
         if (var5 == var6) {
            return true;
         } else {
            HashSet var7 = new HashSet();
            HashSet var8 = new HashSet();
            ArrayDeque var9 = new ArrayDeque();
            var9.add(var5.getBase());

            while (!var9.isEmpty()) {
               long var10 = (Long)var9.remove();
               if (var7.add(var10)) {
                  BasicBlock var12 = bxp.this.cfg.getBlockAt(var10);
                  if (var12 != var6) {
                     for (long var14 : var12.getOutputOffsets()) {
                        if (var14 > var3 || var14 < var5.getBase()) {
                           return false;
                        }

                        var9.add(var14);
                     }
                  }

                  if (var12 != var5) {
                     for (long var17 : var12.getInputOffsets()) {
                        if (var17 >= var3 || var17 < var5.getBase()) {
                           return false;
                        }

                        var8.add(var17);
                     }
                  }
               }
            }

            return var7.containsAll(var8);
         }
      }

      Collection pC(long var1, long var3, int[] var5) {
         HashSet var6 = new HashSet();
         BasicBlock var7 = bxp.this.cfg.getBlockContaining(var1);
         BasicBlock var8 = bxp.this.cfg.getBlockContaining(var3);
         if (var7 == var8) {
            var6.add(var7.getBase());
         } else {
            HashSet var9 = new HashSet();
            ArrayDeque var10 = new ArrayDeque();
            var10.add(var7.getBase());

            while (!var10.isEmpty()) {
               long var11 = (Long)var10.remove();
               if (var6.add(var11)) {
                  BasicBlock var13 = bxp.this.cfg.getBlockAt(var11);
                  if (var13 != var8) {
                     for (long var15 : var13.getOutputOffsets()) {
                        if (var15 > var3 || var15 < var7.getBase()) {
                           return null;
                        }

                        var10.add(var15);
                     }
                  }

                  if (var13 != var7) {
                     for (long var29 : var13.getInputOffsets()) {
                        if (var29 >= var3 || var29 < var7.getBase()) {
                           return null;
                        }

                        var9.add(var29);
                     }
                  }
               }
            }

            if (!var6.containsAll(var9)) {
               return null;
            }
         }

         IDFA var24 = bxp.this.cfg.doDataFlowAnalysis(true);
         byte var25 = 0;
         ArrayList var26 = new ArrayList();

         for (long var27 : var6) {
            BasicBlock var30 = bxp.this.cfg.getBlockAt(var27);
            int var16 = 0;
            if (var30 == var7) {
               var16 = var30.getIndexOfInstruction(var1);
            }

            int var17 = var30.size();
            if (var30 == var8) {
               var17 = var30.getIndexOfInstruction(var3);
            }

            for (int var18 = var16; var18 < var17; var18++) {
               IDInstruction var19 = (IDInstruction)var30.get(var18);
               if (var25 == 0 && var19.hasUseSideEffects(false)) {
                  var25 = 1;
               }

               if (var19.isAssignToVar()) {
                  IDVar var20 = var19.getAssignDestination().asVar();

                  for (long var22 : var24.getDefUses(var19.getOffset(), var20.getId())) {
                     if (var22 < var1 || var22 >= var3) {
                        return null;
                     }
                  }
               }

               var26.add(var19.getOffset());
            }
         }

         var5[0] = var25;
         return var26;
      }
   }
}
