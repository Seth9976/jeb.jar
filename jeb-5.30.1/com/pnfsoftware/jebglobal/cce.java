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
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
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

public class cce extends AbstractDOptimizer {
   private static final ILogger Uv = GlobalLog.getLogger(cce.class);
   public static final String q = cvm.q(new byte[]{78, 27, 13, 12, 7, 6, 27, 7, 9, 24, 27, 1, 6, 17, 11, 9, 4, 29, 6, 1, 17, 28, 15, 5, 7, 7, 29}, 1, 7);
   public static final String RF = cvm.q(
      new byte[]{-30, 27, 13, 12, 7, 6, 27, 7, 9, 24, 27, 1, 6, 17, 11, 9, 4, 29, 6, 1, 17, 24, 2, 11, 11, 23, 27, 10}, 1, 171
   );
   public static final String xK = cvm.q(
      new byte[]{10, 61, 47, 42, 38, 59, 46, 38, 51, 127, 108, 38, 106, 114, 104, 105, 109, 122, 99, 110, 109, 121, 124, 120, 101, 110, 10}, 2, 118
   );
   public static final String Dw = cvm.q(new byte[]{-105, 27, 13, 12, 7, 6, 27, 7, 9, 24, 29, 23, 28, 5, 8, 13, 7, 9}, 1, 222);
   private static final String oW = S.L("Result of execution:") + " ";
   private static final String gO = S.L("\nSome native JNI calls were emulated.");
   private static final String nf = cvm.q(new byte[]{-40, 1, 6, 17, 11, 9, 4}, 1, 156);
   private boolean gP = true;
   private boolean za = true;
   private boolean lm = true;
   private boolean zz = true;
   private int JY;
   private int HF;
   private static final String LK = cvm.q(
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
         48,
         93,
         82,
         88,
         87,
         94,
         8,
         1,
         30,
         14,
         89,
         92,
         93,
         88,
         30,
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
         48
      },
      2,
      17
   );
   private boolean io;
   private cce.oM qa = new cce.oM();
   private final String Hk = cvm.q(new byte[]{34, 31, 0, 48, 28, 0, 19, 41, 0, 84, 77, 14, 89, 84, 84, 93}, 2, 109);

   public cce() {
      this(1, 2);
   }

   public cce(int var1, int var2) {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bto.Dw(this);
      this.JY = var1;
      this.HF = var2;
   }

   public void q(int var1) {
      this.JY = var1;
   }

   public int q() {
      return this.JY;
   }

   public void RF(int var1) {
      this.HF = var1;
   }

   public int RF() {
      return this.HF;
   }

   public boolean xK() {
      return this.gP;
   }

   public void q(boolean var1) {
      this.gP = var1;
   }

   public boolean Dw() {
      return this.za;
   }

   public void RF(boolean var1) {
      this.za = var1;
   }

   public boolean Uv() {
      return this.lm;
   }

   public void xK(boolean var1) {
      this.lm = var1;
   }

   public boolean oW() {
      return this.zz;
   }

   public void Dw(boolean var1) {
      this.zz = var1;
   }

   @Override
   public int perform() {
      if (this.JY == 0) {
         return 0;
      } else {
         int var1 = 0;
         this.io = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.g.getDecompiler()).Me();
         if (this.gP) {
            IdentityHashMap var2 = new IdentityHashMap();
            chs var3 = this.q(q);
            cce.eo var4 = new cce.eo(var2, var3);
            int var5 = var4.q();
            var1 += var5;
            this.q(var2);
            if (var5 > 0) {
               this.cfg.invalidateDataFlowAnalysis();
            }
         }

         if (this.za && !bto.q(this.g)) {
            int var10 = 0;
            IdentityHashMap var14 = new IdentityHashMap();
            chs var17 = this.q(RF);
            long var20 = 0L;

            label83:
            while (true) {
               Iterator var7 = this.cfg.instructions(var20).iterator();

               IDInstruction var8;
               cce.iZ var9;
               do {
                  if (!var7.hasNext()) {
                     var1 += var10;
                     this.q(var14);
                     if (var10 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label83;
                  }

                  var8 = (IDInstruction)var7.next();
                  var9 = new cce.iZ(var8, var14, var17);
                  var8.visitInstructionPostOrder(var9, true);
                  var10 += var9.Dw;
               } while (!var9.Uv);

               var20 = var8.getOffset();
            }
         }

         if (this.lm) {
            int var11 = 0;
            IdentityHashMap var15 = new IdentityHashMap();
            chs var18 = this.q(xK);
            long var21 = 0L;

            label70:
            while (true) {
               Iterator var23 = this.cfg.instructions(var21).iterator();

               IDInstruction var25;
               cce.tw var27;
               do {
                  if (!var23.hasNext()) {
                     var1 += var11;
                     this.q(var15);
                     if (var11 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label70;
                  }

                  var25 = (IDInstruction)var23.next();
                  var27 = new cce.tw(var25, var15, var18);
                  var25.visitInstructionPostOrder(var27, true);
                  var11 += var27.Dw;
               } while (!var27.Uv);

               var21 = var25.getOffset();
            }
         }

         if (this.zz) {
            int var12 = 0;
            IdentityHashMap var16 = new IdentityHashMap();
            chs var19 = this.q(Dw);
            long var22 = 0L;

            label57:
            while (true) {
               Iterator var24 = this.cfg.instructions(var22).iterator();

               IDInstruction var26;
               cce.Nt var28;
               do {
                  if (!var24.hasNext()) {
                     var1 += var12;
                     this.q(var16);
                     if (var12 > 0) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                     break label57;
                  }

                  var26 = (IDInstruction)var24.next();
                  var28 = new cce.Nt(var26, var16, var19);
                  var26.visitInstructionPostOrder(var28, true);
                  var12 += var28.Dw;
               } while (!var28.Uv);

               var22 = var26.getOffset();
            }
         }

         if (var1 > 0 && ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.decomp).PV() >= 2) {
            String var13 = S.L("This method contains decrypted strings");
            this.decomp.addSpecialComment(this.ctx.getMethodSignature(), var13);
         }

         return var1;
      }
   }

   private void q(Map var1) {
      for (IDInstruction var3 : var1.keySet()) {
         IDExpression var4 = (IDExpression)var1.get(var3);
         if (var4 == null) {
            var3.transformToNop();
         } else {
            var3.morph(DOpcodeType.IR_INVOKE, null, var4);
         }
      }
   }

   private chs q(String var1) {
      return chs.q(this.g, var1);
   }

   private void q(IDState var1) {
      if (!Boolean.TRUE.equals((Boolean)var1.getData(this.Hk))) {
         var1.setData(this.Hk, Boolean.TRUE);
         IApkUnit var2 = DexUtil.findParentApk(this.dex);
         if (var2 != null) {
            String var3 = var2.getPackageName();
            if (var3 != null) {
               String var4 = var2.getApplicationName();
               String var5 = var2.getMainActivity();
               if (var4 != null || var5 != null) {
                  int var6 = var1.registerSandboxHooks(new ccf(this, var3));

                  try {
                     if (var4 != null) {
                        String var7 = "L" + var4.replace('.', '/') + ";";
                        String var8 = var7 + cvm.q(new byte[]{-46, 19, 81, 1, 45, 49, 23, 4, 21, 17, 77, 1, 127}, 1, 255);
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
                                    cvm.q(new byte[]{123, 1, 45, 49, 23, 4, 21, 17}, 1, 20),
                                    cvm.q(new byte[]{-62, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 109, 55, 27, 10, 8, 9, 94}, 1, 142)
                                 )
                                 != null) {
                                 String var28 = var25
                                    + cvm.q(
                                       new byte[]{
                                          105,
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
                                       68
                                    );

                                 try {
                                    IDImm var11 = var1.createNewInstance(
                                       cvm.q(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 95, 93, 85, 86, 23}, 2, 199)
                                    );
                                    var1.invokeMethod(var28, Arrays.asList(var27, var11), DInvokeType.VIRTUAL);
                                 } catch (DexDecEvalStubException var22) {
                                    if (!JvmMethodSig.nameAndParams(var22.getStubMethodSignature())
                                       .equals(
                                          cvm.q(
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
                                                90,
                                                72,
                                                76,
                                                42,
                                                73,
                                                72
                                             },
                                             2,
                                             90
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

   private boolean q(bye var1, boolean var2, Object var3, IDExpression var4, IDExpression var5, IVisitResults var6, chs.eo var7, Map var8) {
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
         String var13 = oW + var4;
         if (var2) {
            var13 = var13 + gO;
         }

         ((IDExpression)var12).setOrigin(var13);
         if (var5.replaceSubExpression(var4, (IDExpression)var12)) {
            var6.setReplacedNode(var12);
         } else {
            if (!(var5 instanceof IDInstruction var14) || var14.getOpcode() != DOpcodeType.IR_INVOKE || !(var4 instanceof IDCallInfo)) {
               return false;
            }

            if (var10 != null) {
               IDNewInfo var15 = this.g.createNewInfo(LK, (IDExpression)var12);
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

         Uv.info(var17);
         return true;
      }
   }

   public static void q(Exception var0, String var1, IDState var2) {
      if (var0 instanceof DexDecEvaluationException) {
         if (var0 instanceof DexDecEvalItercountExceededException) {
            Uv.warn(S.L("Not enough emulator resources (max emulation count exceeded) when optimizing method %s"), var1);
         } else if (var0 instanceof DexDecEvalTimeoutExceededException) {
            Uv.warn(S.L("Not enough emulator resources (timeout exceeded) when optimizing method %s"), var1);
         } else if (!(var0 instanceof DexDecEvalStubException) && var0 instanceof DexDecNativeEvalFailedException && Licensing.isReleaseBuild()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
               new RuntimeException(cvm.q(new byte[]{13, 14, 4, 16, 4, 12, 71, 11, 27, 68, 77, 67, 76, 86, 80, 85, 25, 85, 77, 73, 94, 85, 86}, 2, 190), var0),
               var1
            );
         }
      } else {
         if (Licensing.isReleaseBuild()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var0, var1);
         }

         Uv.catchingSilent(var0);
      }
   }

   private static void q(IDMethodContext var0) {
   }

   abstract static class CU implements IDVisitor {
      protected final IDInstruction q;
      protected final Map RF;
      protected final chs xK;
      protected int Dw;
      protected boolean Uv;

      CU(IDInstruction var1, Map var2, chs var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }

   class Nt extends cce.CU {
      private static final String gO = "Ljava/lang/String;";

      Nt(IDInstruction var2, Map var3, chs var4) {
         super(var2, var3, var4);
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            String var5 = var4.getMethodSignature();
            if (var5 != null) {
               IDexMethod var6 = cce.this.dex.getMethod(var5);
               if (var6 != null && !var6.isInternal()) {
                  if (var5.startsWith("Ljava/lang/String;->")) {
                     if (!var5.equals(cce.LK)) {
                        if (var5.startsWith("Ljava/lang/String;-><init>(") || var5.endsWith(")Ljava/lang/String;")) {
                           Watchdog.verify(cce.this.ctx.getWatchdog());
                           chs.eo var8 = this.xK.q(var5);
                           if (var8 == null || var8.gO()) {
                              if (var8 == null) {
                                 var8 = this.xK.q(var5, true);
                              }

                              var8.q(true);
                              ArrayList var9 = new ArrayList(var4.getArguments());

                              for (IDExpression var11 : var9) {
                                 if (!var11.getVarIds().isEmpty()) {
                                    return;
                                 }
                              }

                              bye var29 = (bye)cce.this.g.getEmulator();
                              if (var29.canRun()) {
                                 String var13 = DUtil.generateNativeAddress(cce.this.ctx, var1);
                                 ICodeCoordinates var14 = DUtil.generateNativeCoordinates(cce.this.ctx, var1);
                                 IDEmuContext var15 = var29.pushContext(cce.nf + "{" + var6.getSignature() + "}");
                                 var15.pushOriginInfo(cce.this.ctx.getMethodSignature());
                                 int var16 = var29.LK();
                                 DEmuExternalPolicy var17 = var29.setExternalPolicy(new DEmuExternalPolicy(true, true, true));
                                 Watchdog var18 = var29.setWatchdog(cce.this.ctx.getWatchdog());

                                 String var12;
                                 IDImm var30;
                                 try {
                                    Object[] var10000 = new Object[]{var1};
                                    DInvokeType var19 = var4.getInvokeType();
                                    IDImm var20 = var29.q(var5, var9, var19, var19 == DInvokeType.NEW ? "Ljava/lang/String;" : null);
                                    if (var20 == null) {
                                       return;
                                    }

                                    var12 = var29.getStringObject(var20);
                                    if (var12 == null) {
                                       return;
                                    }

                                    boolean var21 = var29.LK() - var16 > 0;
                                    if (var21 && !com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q()) {
                                       String var34 = S.L(
                                          "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                                       );
                                       String var23 = String.format(var34, cce.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                                       cce.Uv.info(var23);
                                       return;
                                    }

                                    var30 = cce.this.g.createString(var12);
                                    String var22 = cce.oW + var1;
                                    if (var21) {
                                       var22 = var22 + cce.gO;
                                    }

                                    var30.setOrigin(var22);
                                 } catch (Exception var27) {
                                    cce.q(var27, var5, var29);
                                    var8.q(var14, var1, var27);
                                    return;
                                 } finally {
                                    var29.oQ();
                                    var29.setExternalPolicy(var17);
                                    var29.setWatchdog(var18);
                                 }

                                 if (!var2.replaceSubExpression(var1, var30)) {
                                    if (!(var2 instanceof IDInstruction var31) || var31.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                                       var8.RF(var14, var1);
                                       return;
                                    }

                                    IDNewInfo var33 = cce.this.g.createNewInfo(cce.LK, var30);
                                    this.RF.put(this.q, var33);
                                 }

                                 byg.q(var29).q();
                                 var8.q(var14, var1);
                                 DexDecompilerEvent.BuiltString var32 = new DexDecompilerEvent.BuiltString(var12, var13);
                                 cce.this.decomp.recordDecompilationEvent(var32);
                                 cce.this.dex.getCommentManager().addMetaComment(var13, new MetaComment(var32.format(true, false), 1), false);
                                 this.Dw++;
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

   class ej implements IDEmulatorHooks {
      bye q;
      Set RF = new HashSet();

      ej(bye var2) {
         this.q = var2;
      }

      @Override
      public Wrapper getField(long var1, String var3, IDImm var4) {
         if (var4 == null) {
            IDexField var5 = cce.this.dex.getField(var3);
            if (var5 != null && var5.isInternal() && (var5.getData().getAccessFlags() & 8) == 8 && bwh.RF(var5.getFieldTypeSignature(false))) {
               this.RF.add(var3);
            }
         }

         return null;
      }
   }

   class eo {
      Map q;
      chs RF;
      int xK;

      eo(Map var2, chs var3) {
         this.q = var2;
         this.RF = var3;
      }

      int q() {
         String var1 = cce.this.ctx.getMethodSignature();
         JvmMethodSig var2 = JvmMethodSig.parse(var1);
         if (!var2.mname.equals("<clinit>")) {
            return 0;
         } else {
            String var3 = var2.csig;
            chs.eo var4 = this.RF.q(var3, true);
            if (var4.oW() > 0) {
               return 0;
            } else {
               boolean var5 = false;

               for (IDInstruction var7 : cce.this.ctx.getCfg().instructions()) {
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
                  bye var17 = (bye)cce.this.g.getEmulator();
                  if (!var17.canRun()) {
                     return 0;
                  } else {
                     int var18 = var17.LK();
                     Watchdog var19 = var17.setWatchdog(cce.this.ctx.getWatchdog());
                     var17.pushContext(cce.nf + "{" + var1 + "}");

                     label101: {
                        byte var10;
                        try {
                           Object[] var10000 = new Object[]{var3};
                           var17.loadClass(var3);
                           break label101;
                        } catch (Exception var15) {
                           cce.q(var15, var1, var17);
                           var4.q(DUtil.generateNativeCoordinates(cce.this.ctx, null), null, var15);
                           var10 = 0;
                        } finally {
                           var17.oQ();
                           var17.setWatchdog(var19);
                        }

                        return var10;
                     }

                     var4.q(DUtil.generateNativeCoordinates(cce.this.ctx, null), null);
                     boolean var9 = var17.LK() - var18 > 0;
                     if (var9 && !com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q()) {
                        String var21 = S.L("Method %s: Some code could be decrypted but the native code emulator is not enabled in this build");
                        String var22 = String.format(var21, cce.this.ctx.getMethodSignature());
                        cce.Uv.info(var22);
                        return 0;
                     } else {
                        bxy var20 = var17.Uv(var3);

                        for (IDInstruction var12 : cce.this.cfg.instructions()) {
                           var12.visitInstruction(new ccg(this, var20, var17, var9, var4), true);
                        }

                        byg.q(var17).q();
                        return this.xK;
                     }
                  }
               }
            }
         }
      }
   }

   class iZ extends cce.CU {
      private static final String gO = "[B";
      private static final String nf = "Ljava/lang/String;";

      iZ(IDInstruction var2, Map var3, chs var4) {
         super(var2, var3, var4);
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         this.RF(var1, var2, var3);
      }

      public boolean RF(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var2 instanceof IDInstruction var4 && var4.getOpcode() == DOpcodeType.IR_INVOKE && var4.getInvokeData() instanceof IDCallInfo var6) {
            IDexMethod var7 = cce.this.dex.getMethod(var6.getMethodSignature());
            if (var7 != null && var7.isInternal() && var7.getData().isSynthetic()) {
               return false;
            }
         }

         if (var1 instanceof IDCallInfo var42) {
            String var43 = var42.getMethodSignature();
            if (bun.q(var43, cce.this.g).q()) {
               return false;
            } else {
               Watchdog.verify(cce.this.ctx.getWatchdog());
               chs.eo var44 = this.xK.q(var43);
               if (var44 != null && !var44.gO()) {
                  return false;
               } else {
                  if (var44 == null) {
                     var44 = this.xK.q(var43, true);
                  }

                  IDexMethod var8 = cce.this.dex.getMethod(var43);
                  if (var8 != null && var8.isInternal() && var8.getData().getCodeItem() != null) {
                     IDexType var9 = var8.getPrototype().getReturnType();
                     int var10 = -1;
                     if (!var9.isPrimitive()) {
                        var10 = Arrays.asList("Ljava/lang/String;", "[B").indexOf(var9.getSignature());
                        if (var10 < 0) {
                           var44.q(false);
                           return false;
                        }
                     } else if (var42.hasSideEffects(cce.this.ctx, false)) {
                        return false;
                     }

                     boolean var11 = var44.Dw() >= 1;
                     var44.q(true);
                     String var12 = var8.getSignature(false);
                     boolean var13 = var8.getData().isStatic();
                     ArrayList var14 = new ArrayList(var42.getArguments());
                     if (!var13) {
                        var14.set(0, cce.this.g.createNull());
                     }

                     for (int var15 = 0; var15 < var14.size(); var15++) {
                        IDExpression var16 = (IDExpression)var14.get(var15);
                        if (!(var16 instanceof IDImm) && !var16.getVarIds().isEmpty()) {
                           IDExpression var17 = null;
                           if (var16 instanceof IDVar) {
                              var17 = this.q(var16, var11);
                           } else if (var11) {
                              var17 = this.q(var16, true);
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

                     bye var45 = (bye)cce.this.g.getEmulator();
                     if (!var45.canRun()) {
                        return false;
                     } else {
                        String var47 = null;
                        byte[] var18 = null;
                        String var19 = DUtil.generateNativeAddress(cce.this.ctx, var1);
                        ICodeCoordinates var20 = DUtil.generateNativeCoordinates(cce.this.ctx, var1);
                        Integer var21 = null;
                        Integer var22 = null;
                        IDEmuContext var23 = var45.pushContext(cce.nf + "{" + var8.getSignature() + "}");
                        var23.pushOriginInfo(cce.this.ctx.getMethodSignature());
                        int var24 = var45.LK();
                        int var25 = var45.IN();
                        Watchdog var26 = var45.setWatchdog(cce.this.ctx.getWatchdog());

                        Object var46;
                        try {
                           boolean var27 = false;
                           IDImm var50 = null;
                           cce.ej var29 = null;
                           if (cce.this.io) {
                              ArrayList var30 = new ArrayList();

                              for (IDExpression var32 : var14.subList(var13 ? 0 : 1, var14.size())) {
                                 if (var32 instanceof IDImm var33) {
                                    Object var34 = var33.getImmediateAsJavaObject(cce.this.g);
                                    if (var34 == null) {
                                       var30 = null;
                                       break;
                                    }

                                    var30.add(var34);
                                 }
                              }

                              if (var30 != null) {
                                 Object var63 = cce.this.qa.q(var12, var30);
                                 if (var63 instanceof String) {
                                    var50 = var45.q("Ljava/lang/String;", var63);
                                 }
                              }
                           }

                           if (var50 == null) {
                              if (cce.this.io) {
                                 cce.nI var55 = cce.this.new nI(var45, var8);
                                 var21 = var45.registerEmulatorHooks(var55);
                              }

                              var29 = cce.this.new ej(var45);
                              var22 = var45.registerEmulatorHooks(var29);
                              if (var10 >= 0 && !var8.getData().isStatic()) {
                                 IDexType var56 = var8.getClassType();
                                 String var64 = var56.getSignature(false) + "-><init>()V";
                                 IDexMethod var68 = cce.this.dex.getMethod(var64);
                                 if (var68 != null && var68.isInternal()) {
                                    try {
                                       IDImm var71 = var45.q(var68.getSignature(false), Arrays.asList(), DInvokeType.NEW, var56.getSignature(false));
                                       var14.set(0, var71);
                                    } catch (DexDecEvaluationException var39) {
                                    }
                                 }
                              }

                              Object[] var10000 = new Object[]{var1};
                              var27 = true;
                              cce.this.q(var45);
                              var50 = var45.RF(var8, var14);
                              if (cce.this.JY == 2 && var45.IN() - var25 > 0) {
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
                              ((IDExpression)var46).setType(cce.this.tf.createType(var9.getSignature()));
                           } else if (var10 == 0) {
                              var47 = var45.getStringObject(var50);
                              if (var47 == null) {
                                 return false;
                              }

                              var46 = cce.this.g.createString(var47);
                           } else {
                              if (var10 != 1) {
                                 throw new RuntimeException();
                              }

                              var18 = (byte[])var45.getArrayObject(var50);
                              if (var18 == null) {
                                 return false;
                              }

                              var46 = cce.this.g.createByteArray(var18);
                           }

                           if (var27) {
                              bto.q(cce.this.g, var43);
                              if (var29 != null) {
                                 bwh var59 = ((btx)cce.this.g).nf();

                                 for (String var69 : var29.RF) {
                                    var59.q(var69, (IDState)var45);
                                 }
                              }
                           }

                           boolean var60 = var45.LK() - var24 > 0;
                           if (var60 && !com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q()) {
                              String var67 = S.L(
                                 "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                              );
                              String var70 = String.format(var67, cce.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                              cce.Uv.info(var70);
                              return false;
                           }

                           String var66 = cce.oW + var1;
                           if (var60) {
                              var66 = var66 + cce.gO;
                           }

                           ((IDExpression)var46).setOrigin(var66);
                        } catch (Exception var40) {
                           cce.q(var40, var43, var45);
                           var44.q(var20, var1, var40);
                           return false;
                        } finally {
                           if (var21 != null) {
                              var45.unregisterEmulatorHooks(var21);
                           }

                           if (var22 != null) {
                              var45.unregisterEmulatorHooks(var22);
                           }

                           var45.oQ();
                           var45.setWatchdog(var26);
                        }

                        if (!var2.replaceSubExpression(var1, (IDExpression)var46)) {
                           if (!(var2 instanceof IDInstruction var48) || var48.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                              var44.RF(var20, var1);
                              return false;
                           }

                           if (var10 == 0) {
                              IDNewInfo var51 = cce.this.g.createNewInfo(cce.LK, (IDExpression)var46);
                              this.RF.put(this.q, var51);
                           } else if (var10 == 1) {
                              this.RF.put(this.q, var46);
                           } else {
                              this.RF.put(this.q, null);
                           }
                        }

                        byg.q(var45).q();
                        var44.q(var20, var1);
                        Object var49 = null;
                        String var52 = null;
                        if (var47 != null) {
                           var49 = new DexDecompilerEvent.DecryptedString(var47, var19);
                           String var53 = S.L("Method %s: Decrypted string: \"%s\"");
                           var52 = Strings.ff(var53, cce.this.ctx.getMethodSignature(), Formatter.escapeString(var47));
                        } else if (var18 != null) {
                           var49 = new DexDecompilerEvent.DecryptedBytes(var18, var19);
                           String var54 = S.L("Method %s: Decrypted bytes: \"%s\"");
                           var52 = Strings.ff(var54, cce.this.ctx.getMethodSignature(), Formatter.byteArrayToHexString(var18));
                        }

                        if (var49 != null) {
                           cce.this.decomp.recordDecompilationEvent((DexDecompilerEvent)var49);
                           cce.this.dex.getCommentManager().addMetaComment(var19, new MetaComment(((DexDecompilerEvent)var49).format(true, false), 1), false);
                           if (var47 != null) {
                              cce.this.dex.getReferenceManager().addStringReference(var47, var19, DexReferenceType.REFLECTED);
                           }
                        }

                        if (var52 != null) {
                           cce.Uv.info(var52);
                        }

                        this.Dw++;
                        return true;
                     }
                  } else {
                     var44.q(false);
                     return false;
                  }
               }
            }
         } else {
            return false;
         }
      }

      private IDExpression q(IDExpression var1, boolean var2) {
         if (var1 instanceof IDVar var6) {
            return this.q(var6, var2);
         } else {
            IDExpression var3 = null;
            AtomicBoolean var4 = new AtomicBoolean(true);

            for (IDExpression var5 = var1; var5.visitDepthPost(new cch(this, var2, var4)); var5 = var3) {
               if (!var4.get()) {
                  return var3;
               }

               var4.set(false);
               var3 = var1.duplicate();
            }

            return null;
         }
      }

      private IDExpression q(IDVar var1, boolean var2) {
         cce.this.analyzeChains();
         IDInstruction[] var3 = new IDInstruction[1];
         Set var4 = var1.getVarIds();
         Map var5 = cce.this.dfa.getUseDefChains(this.q.getOffset());
         if (!bto.q(cce.this.cfg, var5, var4, var3)) {
            IDImm[] var8 = new IDImm[1];
            return !bto.q(cce.this.cfg, var5, var4, var8) ? null : var8[0];
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

   class nI implements IDEmulatorHooks {
      bye q;
      boolean RF;
      String xK;
      Map Dw = new HashMap();

      nI(bye var2, IDexMethod var3) {
         this.q = var2;
         this.RF = var3.getData().isStatic();
         this.xK = var3.getSignature(false);
      }

      @Override
      public Wrapper invokeMethod(long var1, String var3, List var4) {
         try {
            if (var3.equals(this.xK)) {
               ArrayList var5 = new ArrayList();

               for (IDImm var7 : var4.subList(this.RF ? 0 : 1, var4.size())) {
                  Object var8 = this.q.getObject(var7);
                  var5.add(var8);
               }

               this.Dw.put(var1, var5);
            }

            return null;
         } catch (DexDecEvaluationException var9) {
            return null;
         }
      }

      @Override
      public Wrapper examineMethodResult(long var1, IDImm var3) {
         try {
            List var4 = (List)this.Dw.remove(var1);
            if (var4 != null) {
               Object var5 = this.q.getObject(var3);
               if (var5 != null) {
                  cce.this.qa.q(this.xK, var4, var5);
               }
            }

            return null;
         } catch (DexDecEvaluationException var6) {
            return null;
         }
      }
   }

   static class oM {
      Map q = new HashMap();

      void q(String var1, List var2, Object var3) {
         cce.oM.eo var4 = (cce.oM.eo)this.q.get(var1);
         if (var4 == null) {
            var4 = new cce.oM.eo();
            this.q.put(var1, var4);
         }

         var4.q.put(var2, var3);
      }

      Object q(String var1, List var2) {
         cce.oM.eo var3 = (cce.oM.eo)this.q.get(var1);
         return var3 == null ? null : var3.q.get(var2);
      }

      static class eo {
         Map q = new HashMap();
      }
   }

   class tw extends cce.CU {
      tw(IDInstruction var2, Map var3, chs var4) {
         super(var2, var3, var4);
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         this.RF(var1, var2, var3);
      }

      private boolean RF(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.isStringImm()) {
            return false;
         } else if (var2 != null && var1.getType() != null && var1.getType().isJavaLangString()) {
            if (var1 instanceof IDCallInfo var4) {
               String var5 = var4.getMethodSignature();
               if (var5.equals(cce.LK)) {
                  return false;
               }
            }

            IDExpression var46 = var1;
            Watchdog.verify(cce.this.ctx.getWatchdog());
            boolean[] var47 = new boolean[1];
            Long var6 = this.q(this.q.getOffset(), var1, var47);
            if (var6 == null) {
               return false;
            } else {
               boolean var7 = var47[0];
               IDInstruction var8 = (IDInstruction)cce.this.cfg.getInstruction(var6);
               if (var8 != null && var8.isStoreException()) {
                  return false;
               } else {
                  String var9 = "@" + var6;
                  String var10 = cvm.q(new byte[]{6, 33, 51, 42, 38, 59}, 2, 94) + "_" + var9;
                  chs.eo var11 = this.xK.q(var10);
                  if (var11 != null && !var11.gO()) {
                     return false;
                  } else {
                     if (var11 == null) {
                        var11 = this.xK.q(var10, true);
                     }

                     var11.q(true);
                     long var12 = var6;
                     long var14 = this.q.getOffset();
                     bye var16 = (bye)cce.this.g.getEmulator();
                     if (!var16.canRun()) {
                        return false;
                     } else {
                        String var19 = DUtil.generateNativeAddress(cce.this.ctx, var1);
                        ICodeCoordinates var20 = DUtil.generateNativeCoordinates(cce.this.ctx, var1);
                        IDEmuContext var21 = var16.pushContext(cce.nf + "{INLINED}");
                        var21.pushOriginInfo(cce.this.ctx.getMethodSignature());
                        int var22 = var16.LK();
                        int var23 = var16.IN();
                        DEmuExternalPolicy var24 = var16.setExternalPolicy(new DEmuExternalPolicy(true, true, true));
                        cce.ej var25 = cce.this.new ej(var16);
                        int var26 = var16.registerEmulatorHooks(var25);
                        Watchdog var27 = var16.setWatchdog(cce.this.ctx.getWatchdog());

                        String var17;
                        IDImm var18;
                        try {
                           Object[] var10000 = new Object[]{var46};
                           var16.pushFrame(cce.this.ctx.getMethodSignature());
                           TreeMap var28 = new TreeMap();
                           cce.this.cfg.getInstructions().forEach(var1x -> var28.put((int)var1x.getOffset(), var1x));
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

                           if (cce.this.JY == 2 && var16.IN() - var23 > 0) {
                              return false;
                           }

                           boolean var31 = var16.LK() - var22 > 0;
                           if (var31 && !com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q()) {
                              String var65 = S.L(
                                 "Method %s: Some code could be decrypted and replaced by a string but the native code emulator is not enabled in this build: \"%s\""
                              );
                              String var69 = String.format(var65, cce.this.ctx.getMethodSignature(), Formatter.escapeString(var1.toString()));
                              cce.Uv.info(var69);
                              return false;
                           }

                           var18 = cce.this.g.createString(var17);
                           String var32 = cce.oW + var1;
                           if (var31) {
                              var32 = var32 + cce.gO;
                           }

                           var18.setOrigin(var32);
                           if (var25 != null) {
                              bwh var33 = ((btx)cce.this.g).nf();

                              for (String var35 : var25.RF) {
                                 var33.q(var35, (IDState)var16);
                              }
                           }
                        } catch (Exception var44) {
                           cce.q(var44, cce.this.ctx.getMethodSignature(), var16);
                           var11.q(var20, var1, var44);
                           return false;
                        } finally {
                           var16.unregisterEmulatorHooks(var26);
                           var16.oQ();
                           var16.setExternalPolicy(var24);
                           var16.setWatchdog(var27);
                        }

                        boolean var48 = false;
                        if (!var2.replaceSubExpression(var1, var18)) {
                           if (!(var2 instanceof IDInstruction var50) || var50.getOpcode() != DOpcodeType.IR_INVOKE || !(var1 instanceof IDCallInfo)) {
                              var11.RF(var20, var1);
                              return false;
                           }

                           var48 = true;
                           IDNewInfo var54 = cce.this.g.createNewInfo(cce.LK, var18);
                           this.RF.put(this.q, var54);
                        }

                        long var51 = var12;

                        while (var51 < var14) {
                           IDInstruction var57 = (IDInstruction)cce.this.cfg.getInstruction(var51);
                           if (var57 == null) {
                              break;
                           }

                           if (var57.isInvoke() && var57.getInvokeData() instanceof IDCallInfo var63) {
                              String var67 = var63.getMethodSignature();
                              bto.q(cce.this.g, var67);
                           }

                           var51 += var57.getSize();
                        }

                        if (!var48 && var12 < var14 && !var7) {
                           int[] var52 = new int[1];
                           Collection var55 = this.q(var12, var14, var52);
                           if (var55 != null && !var55.isEmpty()) {
                              int var58 = var52[0];
                              if (var58 < 2 && (cce.this.HF == 1 && var58 == 0 || cce.this.HF == 2)) {
                                 Couple var64 = cce.this.cfg.getInstructionLocation(var12);
                                 Couple var68 = cce.this.cfg.getInstructionLocation(var14);
                                 BasicBlock var70 = (BasicBlock)var64.getFirst();
                                 int var72 = (Integer)var64.getSecond();
                                 BasicBlock var36 = (BasicBlock)var68.getFirst();
                                 int var37 = (Integer)var68.getSecond();
                                 if (var70.outsize() == 1) {
                                    IDInstruction var38 = (IDInstruction)var70.get(var72);
                                    BasicBlock var39;
                                    if (var37 > 0) {
                                       var39 = DUtil.splitBlock(cce.this.ctx, var36, var37);
                                    } else {
                                       var39 = var36;
                                    }

                                    BasicBlock var40;
                                    if (var72 < var70.size() - 1) {
                                       var40 = DUtil.splitBlock(cce.this.ctx, var70, var72 + 1);
                                    } else {
                                       var40 = var70.getOutputBlock(0);
                                    }

                                    IDInstruction var41 = cce.this.ctx.createJump((int)var14);
                                    var41.copyBaseFields(var38);
                                    var70.set(var72, var41);
                                    cce.this.cfg.reconnectEdge(var70, var40, var39);
                                    DUtil.cleanGraph(cce.this.ctx);
                                    this.Uv = true;
                                    var3.interrupt(true);
                                 }
                              }
                           }
                        }

                        byg.q(var16).q();
                        var11.q(var20, var1);
                        DexDecompilerEvent.DecryptedString var53 = new DexDecompilerEvent.DecryptedString(var17, var19);
                        cce.this.decomp.recordDecompilationEvent(var53);
                        cce.this.dex.getCommentManager().addMetaComment(var19, new MetaComment(var53.format(true, false), 1), false);
                        cce.this.dex.getReferenceManager().addStringReference(var17, var19, DexReferenceType.REFLECTED);
                        String var56 = S.L("Method %s: Decrypted string: \"%s\"");
                        String var59 = String.format(var56, cce.this.ctx.getMethodSignature(), Formatter.escapeString(var17));
                        cce.Uv.info(var59);
                        this.Dw++;
                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      }

      Long q(long var1, IDExpression var3, boolean[] var4) {
         cce.this.analyzeChains();
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
               for (long var17 : cce.this.dfa.getUseDefs((Long)var14.getFirst(), (Integer)var14.getSecond())) {
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
               IDInstruction var24 = (IDInstruction)cce.this.cfg.getInstruction(var21);
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
            IDInstruction var22 = (IDInstruction)cce.this.cfg.getInstruction(var20);
            var22.getDefUse(var9, var10);

            for (int var25 : var9) {
               for (long var18 : cce.this.dfa.getDefUses(var20, var25)) {
                  if (var18 > var1) {
                     var4[0] = true;
                     return !this.q(var7, var1) ? null : var7;
                  }
               }
            }

            var20 += var22.getSize();
         }

         return !this.q(var7, var1) ? null : var7;
      }

      boolean q(long var1, long var3) {
         BasicBlock var5 = cce.this.cfg.getBlockContaining(var1);
         BasicBlock var6 = cce.this.cfg.getBlockContaining(var3);
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
                  BasicBlock var12 = cce.this.cfg.getBlockAt(var10);
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

      Collection q(long var1, long var3, int[] var5) {
         HashSet var6 = new HashSet();
         BasicBlock var7 = cce.this.cfg.getBlockContaining(var1);
         BasicBlock var8 = cce.this.cfg.getBlockContaining(var3);
         if (var7 == var8) {
            var6.add(var7.getBase());
         } else {
            HashSet var9 = new HashSet();
            ArrayDeque var10 = new ArrayDeque();
            var10.add(var7.getBase());

            while (!var10.isEmpty()) {
               long var11 = (Long)var10.remove();
               if (var6.add(var11)) {
                  BasicBlock var13 = cce.this.cfg.getBlockAt(var11);
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

         IDFA var24 = cce.this.cfg.doDataFlowAnalysis(true);
         byte var25 = 0;
         ArrayList var26 = new ArrayList();

         for (long var27 : var6) {
            BasicBlock var30 = cce.this.cfg.getBlockAt(var27);
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

      int q(IDNewArrayInfo var1) {
         if (!var1.getInitialValues().isEmpty()) {
            return 0;
         } else {
            return var1.getSize() instanceof IDImm var3 ? (int)var3.getRawValue() : 0;
         }
      }
   }
}
