package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.INativeLibrary;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecNativeEvalFailedException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.StringEntry;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryChangesRecorder;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class bvm {
   private static final ILogger A = GlobalLog.getLogger(bvm.class);
   public static final List pC = Collections.unmodifiableList(Arrays.asList(AndroidPlatformABI.ARM64));
   private static Object kS = new Object();
   private btp wS;
   private List UT;
   private IApkUnit E;
   private cdz sY;
   private Map ys = new HashMap();
   private boolean ld = true;
   private bvn gp = new bvn();
   private bvn oT = new bvn();

   public bvm(btp var1, List var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
         if (var2 == null) {
            var2 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var1.getGlobalContext().getDecompiler()).vP();
            if (var2 == null) {
               var2 = pC;
            }
         }

         this.UT = var2;
         this.E = var1.getApk();
      }
   }

   public void pC() {
   }

   public void A() throws DexDecNativeEvalFailedException {
      this.pC(ProcessorType.ARM64);
   }

   private void pC(ProcessorType var1) throws DexDecNativeEvalFailedException {
      if (var1 != ProcessorType.ARM64) {
         throw new RuntimeException("Only arm64 (aarch64) is supported by the native code emulator for Android at the moment");
      } else if (this.sY == null) {
         if (this.E == null) {
            throw new DexDecNativeEvalFailedException("Needs an APK to emulate native code");
         } else {
            this.sY = new cdz(this.wS);
            this.sY.A();
            EEmulator var2 = this.sY.fI();

            for (IEEmulatorHooks var4 : this.gp.A()) {
               var2.registerHooks(var4, true);
            }

            for (IEStateHooks var6 : this.oT.A()) {
               var2.getState().registerHooks(var6, true);
            }
         }
      }
   }

   private bvm.Sv A(IELFUnit var1) throws DexDecEvaluationException {
      if (var1.getHeader().getMachine() != 183) {
         throw new RuntimeException(
            ckx.pC(
               new byte[]{
                  -34,
                  33,
                  2,
                  21,
                  89,
                  65,
                  19,
                  31,
                  91,
                  2,
                  20,
                  8,
                  73,
                  0,
                  19,
                  17,
                  11,
                  94,
                  2,
                  29,
                  9,
                  73,
                  26,
                  83,
                  83,
                  6,
                  5,
                  0,
                  31,
                  29,
                  6,
                  17,
                  1,
                  68,
                  66,
                  27,
                  89,
                  84,
                  28,
                  13,
                  69,
                  78,
                  15,
                  21,
                  29,
                  31,
                  19,
                  69,
                  67,
                  12,
                  11,
                  1,
                  69,
                  69,
                  8,
                  24,
                  25,
                  13,
                  21,
                  27,
                  29,
                  82,
                  70,
                  9,
                  29,
                  82,
                  97,
                  47,
                  10,
                  22,
                  29,
                  6,
                  13,
                  68,
                  65,
                  21,
                  84,
                  84,
                  28,
                  13,
                  69,
                  77,
                  2,
                  2,
                  8,
                  11,
                  26
               },
               1,
               145
            )
         );
      } else {
         String var2 = var1.getName();
         bvm.Sv var3 = (bvm.Sv)this.ys.get(var2);
         if (var3 == null) {
            this.pC(ProcessorType.ARM64);
            long var4 = 0L;
            EvaluationException var6 = null;

            try {
               boolean var7 = this.ld;
               var4 = this.sY.pC(var1, var7);
            } catch (EvaluationException var8) {
               var6 = var8;
            }

            var3 = new bvm.Sv(var1, var4);
            var3.kS = var6;
            this.ys.put(var2, var3);
            if (this.ld) {
               this.pC(var3, this.sY.oT(), true);
               this.A(var3, this.sY.oT(), true);
            }
         }

         return var3;
      }
   }

   public cdz kS() {
      return this.sY;
   }

   public EEmulator wS() {
      return this.sY == null ? null : this.sY.fI();
   }

   public Map UT() {
      return this.sY == null ? null : this.sY.NS();
   }

   public int pC(IEEmulatorHooks var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = this.gp.pC(var1, true);
         if (this.sY != null) {
            this.sY.fI().registerHooks(var1, true);
         }

         return var2;
      }
   }

   public boolean pC(int var1) {
      IEEmulatorHooks var2 = (IEEmulatorHooks)this.gp.pC(var1);
      if (var2 == null) {
         return false;
      } else {
         if (this.sY != null) {
            this.sY.fI().unregisterHooks(var2);
         }

         return true;
      }
   }

   public int pC(IEStateHooks var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = this.oT.pC(var1, true);
         if (this.sY != null) {
            this.sY.fI().getState().registerHooks(var1, true);
         }

         return var2;
      }
   }

   public boolean A(int var1) {
      IEStateHooks var2 = (IEStateHooks)this.oT.pC(var1);
      if (var2 == null) {
         return false;
      } else {
         if (this.sY != null) {
            this.sY.fI().getState().unregisterHooks(var2);
         }

         return true;
      }
   }

   public IDImm pC(IDexMethod var1, List var2, btp.Sv var3) throws DexDecEvaluationException {
      if (!var1.getData().isNative()) {
         throw new DexDecNativeEvalFailedException("Expected a native method");
      } else {
         bvm.Av var4 = new bvm.Av(var1, var2, var3);
         return var4.pC();
      }
   }

   public Collection E() throws DexDecEvaluationException {
      return Collections.unmodifiableCollection(this.ys.values());
   }

   public INativeLibrary pC(btp.Sv var1) throws DexDecEvaluationException {
      if (var1 == null) {
         throw new DexDecNativeEvalFailedException("Expected a library information object");
      } else {
         bvm.Av var2 = new bvm.Av(null, null, var1);
         var2.pC();
         return var2.UT;
      }
   }

   public INativeLibrary pC(IELFUnit var1) throws DexDecEvaluationException {
      if (var1 == null) {
         throw new DexDecNativeEvalFailedException("Expected a library unit");
      } else {
         return this.A(var1);
      }
   }

   private int pC(bvm.Sv var1, MemoryChangesRecorder.Results var2, boolean var3) {
      IVirtualMemory var4 = this.sY.fI().getVirtualMemory();
      int var5 = var4.getPageSize();
      byte[] var6 = new byte[var5];
      int var7 = 0;
      IDexDecompilerUnit var8 = this.wS.getGlobalContext().getDecompiler();
      LinkedHashSet var9 = new LinkedHashSet();
      var9.addAll(var2.getAllocatedPages());
      var9.addAll(var2.getWrittenPages());

      for (long var11 : var9) {
         byte[] var13 = var2.getPreWritePageData(var11);
         if (var13 == null) {
            var13 = var6;
         }

         byte[] var14 = new byte[var5];
         if (VirtualMemoryUtil.readBytes(var4, var11, var14, 0, var5)) {
            int var22 = 0;

            while (var22 < var5) {
               var22 = pC(var13, var14, var22);
               if (var22 < 0) {
                  break;
               }

               long var16 = var11 + var22;
               StringEntry var18 = DataStringUtil.getStringAt(var4, var16, var16 + var5, 4, 200);
               if (var18 == null) {
                  var22++;
               } else {
                  String var19 = Strings.ff("@LIB:%s@0x%X", var1.pC.getName(), var18.getAddress());
                  String var20 = var18.getString();
                  if (var3) {
                     var8.recordDecompilationEvent(new DexDecompilerEvent.DecryptedString(var20, var19));
                  }

                  String var21 = S.L("Found a decrypted string in native code: %s: %s");
                  A.info(var21, var19, var20);
                  var22 = Math.max(var22 + var18.getMemorySize(), var22 + 1);
                  var7++;
               }
            }
         }
      }

      return var7;
   }

   private static int pC(byte[] var0, byte[] var1, int var2) {
      int var3 = var0.length;
      if (var3 != var1.length) {
         return -1;
      } else {
         for (int var4 = var2; var4 < var3; var4++) {
            if (var0[var4] != var1[var4]) {
               return var4;
            }
         }

         return -1;
      }
   }

   private int A(bvm.Sv var1, MemoryChangesRecorder.Results var2, boolean var3) {
      IVirtualMemory var4 = this.sY.fI().getVirtualMemory();
      int var5 = var4.getPageSize();
      byte[] var10000 = new byte[var5];
      int var6 = 0;
      LinkedHashSet var7 = new LinkedHashSet();
      var7.addAll(var2.getWrittenPages());

      for (long var9 : var7) {
         var2.getPreWritePageData(var9);
         byte[] var11 = new byte[var5];
         if (VirtualMemoryUtil.readBytes(var4, var9, var11, 0, var5)) {
            for (int var12 = 0; var12 <= var5 - 36; var12++) {
               if (var11[var12] == 100
                  && var11[var12 + 1] == 101
                  && var11[var12 + 2] == 120
                  && var11[var12 + 3] == 10
                  && var11[var12 + 4] == 48
                  && var11[var12 + 7] == 0) {
                  int var13 = EndianUtil.littleEndianBytesToInt(var11, var12 + 32);
                  if (var13 < 10485760) {
                     long var14 = var9 + var12;
                     byte[] var16 = new byte[var13];
                     if (VirtualMemoryUtil.readBytes(var4, var14, var16, 0, var16.length)) {
                        String var17 = Strings.ff("@LIB:%s@0x%X", var1.pC.getName(), var14);
                        String var18 = S.L("Found a dex file in native memory: %s: size=%d");
                        A.info(var18, var17, var13);
                        var6++;
                        var12 += var13 - 1;
                     }
                  }
               }
            }
         }
      }

      return var6;
   }

   private class Av {
      IDexMethod pC;
      List A;
      btp.Sv kS;
      IDImm wS;
      bvm.Sv UT;
      long E;

      public Av(IDexMethod var2, List var3, btp.Sv var4) {
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
      }

      public IDImm pC() throws DexDecEvaluationException {
         this.A();
         if (this.pC == null) {
            return null;
         } else {
            this.kS();
            return this.wS;
         }
      }

      private void A() throws DexDecEvaluationException {
         IDexDecompilerUnit var1 = bvm.this.wS.getGlobalContext().getDecompiler();
         IDexUnit var2 = var1.getCodeUnit();
         if (var2.getParent() instanceof IApkUnit var3) {
            String var19 = null;
            String[] var5 = null;
            if (this.pC != null) {
               var19 = this.pC.getSignature(false);
               var5 = DexUtil.generateDefaultJniNames(var19);
            }

            ArrayList var6 = null;
            if (this.kS != null && this.kS.A) {
               bhg var20 = (bhg)bvm.this.wS.getEmulatedEnvironment();
               if (var20 != null && var20.A()) {
                  String var25 = (String)var20.E().get(this.kS.pC);
                  if (var25 != null) {
                     IUnit var29 = var20.kS(false);
                     if (var29 != null) {
                        IUnit var33 = UnitUtil.findFirstChildByName(var29, var25);
                        if (var33 instanceof cdx var37) {
                           var33 = var37.getOriginal();
                        }

                        if (var33 instanceof IELFUnit var38) {
                           var6 = new ArrayList();
                           var6.add(var38);
                        }
                     }
                  }
               }
            } else {
               IUnit var7 = var3.getLibraries();
               if (var7 != null) {
                  label160:
                  for (AndroidPlatformABI var9 : bvm.this.UT) {
                     IUnit var10 = UnitUtil.findFirstChildByName(var7, var9.toString());
                     if (var10 != null) {
                        List var11 = UnitUtil.findDescendantsByType(var10, IELFUnit.class, false);
                        var6 = new ArrayList(var11);
                        if (this.kS != null) {
                           for (IELFUnit var13 : var11) {
                              String var14 = var13.getName();
                              if (var14 != null && var14.equals("lib" + this.kS.pC + ".so")) {
                                 var6.clear();
                                 var6.add(0, var13);
                                 break label160;
                              }
                           }
                        }
                        break;
                     }
                  }
               }
            }

            if (var6 == null) {
               IDImm var24 = bvm.this.wS.registerObject(new UnsatisfiedLinkError("Library not found"));
               throw new DexDecEvalCodeThrownException(var24);
            } else {
               if (this.pC == null) {
                  if (var6.size() == 1) {
                     IELFUnit var21 = (IELFUnit)var6.get(0);
                     if (!var21.isProcessed()) {
                        synchronized (bvm.kS) {
                           if (!var21.isProcessed()) {
                              var21.process();
                           }
                        }
                     }

                     this.UT = bvm.this.A(var21);
                  }
               } else {
                  IELFUnit var22 = null;
                  Long var27 = null;

                  for (IELFUnit var34 : var6) {
                     if (!var34.isProcessed()) {
                        synchronized (bvm.kS) {
                           if (!var34.isProcessed()) {
                              var34.process();
                           }
                        }
                     }

                     ISymbolInformation var40 = CodeObjectUnitUtil.findExportedSymbolByName(var34, var5[0]);
                     if (var40 == null) {
                        var40 = CodeObjectUnitUtil.findExportedSymbolByName(var34, var5[1]);
                     }

                     if (var40 != null) {
                        var22 = var34;
                        var27 = var40.getSymbolRelativeAddress();
                        break;
                     }
                  }

                  if (var27 != null) {
                     if (!(var22.getImageUnit() instanceof INativeCodeUnit)) {
                        throw new DexDecNativeEvalFailedException("SO lib does not contain executable code");
                     }

                     this.UT = bvm.this.A(var22);
                     this.E = this.UT.A + var27;
                  } else {
                     for (IELFUnit var35 : var6) {
                        if (var35.getImageUnit() instanceof INativeCodeUnit) {
                           bvm.Sv var41 = bvm.this.A(var35);
                           Long var42 = bvm.this.sY.UT(var19);
                           if (var42 != null) {
                              this.UT = var41;
                              this.E = var42;
                              break;
                           }
                        }
                     }
                  }
               }

               if (this.UT == null && this.pC != null) {
                  for (bvm.Sv var28 : bvm.this.ys.values()) {
                     ISymbolInformation var32 = CodeObjectUnitUtil.findExportedSymbolByName(var28.pC, var5[0]);
                     if (var32 == null) {
                        var32 = CodeObjectUnitUtil.findExportedSymbolByName(var28.pC, var5[1]);
                     }

                     if (var32 != null) {
                        long var36 = var32.getSymbolRelativeAddress();
                        this.UT = var28;
                        this.E = this.UT.A + var36;
                        break;
                     }
                  }

                  throw new DexDecNativeEvalFailedException("Cannot find target method: " + this.pC.getSignature(false) + " in lib: " + this.kS);
               } else if (this.UT == null) {
                  throw new DexDecNativeEvalFailedException("Cannot find lib: " + this.kS);
               } else if (this.UT.isBad()) {
                  throw new DexDecNativeEvalFailedException("Cannot use a bad image: " + this.UT);
               }
            }
         } else {
            throw new DexDecNativeEvalFailedException("Needs an APK to emulate native code");
         }
      }

      private void kS() throws DexDecEvaluationException {
         List var1 = this.A;
         String var2 = this.pC.getSignature(false);
         boolean var3 = this.pC.getData().isStatic();
         int var4;
         byte var5;
         if (var3) {
            var4 = bvm.this.wS.getClassReference(this.pC.getClassTypeSignature(false)).evaluate(bvm.this.wS).getObjectReferenceId();
            var5 = 0;
         } else {
            var4 = ((IDExpression)var1.get(0)).evaluate(bvm.this.wS).getObjectReferenceId();
            var5 = 1;
         }

         ArrayList var6 = new ArrayList();
         List var7 = this.pC.getParameterTypes();
         int var8 = 0;

         for (IDExpression var10 : var1.subList(var5, var1.size())) {
            IDImm var11 = var10.evaluate(bvm.this.wS);
            long var12 = var11.getRawValue();
            String var16 = ((IDexType)var7.get(var8)).getSignature(false);

            var6.add(switch (var16) {
               case "Z" -> var12 & 1L;
               case "B" -> (byte)var12;
               case "C" -> (char)var12;
               case "S" -> (short)var12;
               case "I" -> (int)var12;
               case "J" -> var12;
               case "F" -> var12 & 4294967295L;
               case "D" -> var12;
               default -> (int)var12;
            });
            var8++;
         }

         Long var20;
         try {
            var20 = bvm.this.sY.pC(var2, var3, this.E, var4, var6);
         } catch (Exception var19) {
            throw new DexDecNativeEvalFailedException("JNI execution failed for: " + var2 + " in image: " + this.UT, var19);
         }

         int var21 = bvm.this.sY.pC(true);
         if (var21 != 0) {
            IDImm var23 = bvm.this.wS.getGlobalContext().createRef(var21);
            throw new DexDecEvalCodeThrownException(var23);
         } else {
            if (var20 == null) {
               this.wS = null;
            } else {
               long var22 = var20;
               IDexType var13 = this.pC.getReturnType();
               String var24 = var13.getSignature(false);
               switch (var24) {
                  case "Z":
                     this.wS = bvm.this.wS.getGlobalContext().createBoolean(var22 != 0L);
                     break;
                  case "B":
                     this.wS = bvm.this.wS.getGlobalContext().createByte((byte)var22);
                     break;
                  case "C":
                     this.wS = bvm.this.wS.getGlobalContext().createChar((char)var22);
                     break;
                  case "S":
                     this.wS = bvm.this.wS.getGlobalContext().createShort((short)var22);
                     break;
                  case "I":
                     this.wS = bvm.this.wS.getGlobalContext().createInt((int)var22);
                     break;
                  case "J":
                     this.wS = bvm.this.wS.getGlobalContext().createLong(var22);
                     break;
                  case "F":
                     this.wS = bvm.this.wS.getGlobalContext().createFloat(Float.intBitsToFloat((int)var22));
                     break;
                  case "D":
                     this.wS = bvm.this.wS.getGlobalContext().createDouble(Double.longBitsToDouble(var22));
                     break;
                  default:
                     int var17 = (int)var22;
                     if (var17 == 0) {
                        this.wS = bvm.this.wS.getGlobalContext().createNull();
                     } else {
                        IDGlobalContext var26 = bvm.this.wS.getGlobalContext();
                        this.wS = var26.createRef(var17, var26.getTypeFactory().createType(var24));
                     }
               }
            }
         }
      }
   }

   static class Sv implements INativeLibrary {
      IELFUnit pC;
      long A;
      Exception kS;

      Sv(IELFUnit var1, long var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public IELFUnit getElfUnit() {
         return this.pC;
      }

      @Override
      public boolean isBad() {
         return this.kS != null;
      }

      @Override
      public long getMappingAddress() {
         return this.A;
      }

      @Override
      public String toString() {
         return Strings.ff("%s @ 0x%X [%s]", this.pC.getName(), this.getMappingAddress(), this.isBad() ? "KO" : "OK");
      }
   }
}
