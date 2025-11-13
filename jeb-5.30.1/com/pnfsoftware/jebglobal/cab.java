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
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cab {
   private static final ILogger RF = GlobalLog.getLogger(cab.class);
   public static final List q = Collections.unmodifiableList(Arrays.asList(AndroidPlatformABI.ARM64));
   private static Object xK = new Object();
   private bye Dw;
   private List Uv;
   private IApkUnit oW;
   private cjo gO;
   private Map nf = new HashMap();
   private boolean gP = true;
   private cac za = new cac();
   private cac lm = new cac();

   public cab(bye var1, List var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
         if (var2 == null) {
            var2 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var1.getGlobalContext().getDecompiler()).rL();
            if (var2 == null) {
               var2 = q;
            }
         }

         this.Uv = var2;
         this.oW = var1.getApk();
      }
   }

   public void q() {
   }

   public void RF() throws DexDecNativeEvalFailedException {
      this.q(ProcessorType.ARM64);
   }

   private void q(ProcessorType var1) throws DexDecNativeEvalFailedException {
      if (var1 != ProcessorType.ARM64) {
         throw new RuntimeException("Only arm64 (aarch64) is supported by the native code emulator for Android at the moment");
      } else if (this.gO == null) {
         if (this.oW == null) {
            throw new DexDecNativeEvalFailedException("Needs an APK to emulate native code");
         } else {
            this.gO = new cjo(this.Dw);
            this.gO.q();
            EEmulator var2 = this.gO.za();

            for (IEEmulatorHooks var4 : this.za.RF()) {
               var2.registerHooks(var4, true);
            }

            for (IEStateHooks var6 : this.lm.RF()) {
               var2.getState().registerHooks(var6, true);
            }
         }
      }
   }

   private cab.CU RF(IELFUnit var1) throws DexDecEvaluationException {
      if (var1.getHeader().getMachine() != 183) {
         throw new RuntimeException("Only arm64 (aarch64) is supported by the native code emulator for Android at the moment");
      } else {
         String var2 = var1.getName();
         cab.CU var3 = (cab.CU)this.nf.get(var2);
         if (var3 == null) {
            this.q(ProcessorType.ARM64);
            long var4 = 0L;
            EvaluationException var6 = null;

            try {
               boolean var7 = this.gP;
               var4 = this.gO.q(var1, var7);
            } catch (EvaluationException var8) {
               var6 = var8;
            }

            var3 = new cab.CU(var1, var4);
            var3.xK = var6;
            this.nf.put(var2, var3);
            if (this.gP) {
               this.q(var3, this.gO.gP(), true);
            }
         }

         return var3;
      }
   }

   public cjo xK() {
      return this.gO;
   }

   public EEmulator Dw() {
      return this.gO == null ? null : this.gO.za();
   }

   public Map Uv() {
      return this.gO == null ? null : this.gO.zz();
   }

   public int q(IEEmulatorHooks var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = this.za.q(var1, true);
         if (this.gO != null) {
            this.gO.za().registerHooks(var1, true);
         }

         return var2;
      }
   }

   public boolean q(int var1) {
      IEEmulatorHooks var2 = (IEEmulatorHooks)this.za.q(var1);
      if (var2 == null) {
         return false;
      } else {
         if (this.gO != null) {
            this.gO.za().unregisterHooks(var2);
         }

         return true;
      }
   }

   public int q(IEStateHooks var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = this.lm.q(var1, true);
         if (this.gO != null) {
            this.gO.za().getState().registerHooks(var1, true);
         }

         return var2;
      }
   }

   public boolean RF(int var1) {
      IEStateHooks var2 = (IEStateHooks)this.lm.q(var1);
      if (var2 == null) {
         return false;
      } else {
         if (this.gO != null) {
            this.gO.za().getState().unregisterHooks(var2);
         }

         return true;
      }
   }

   public IDImm q(IDexMethod var1, List var2, bye.CU var3) throws DexDecEvaluationException {
      if (!var1.getData().isNative()) {
         throw new DexDecNativeEvalFailedException("Expected a native method");
      } else {
         cab.eo var4 = new cab.eo(var1, var2, var3);
         return var4.q();
      }
   }

   public Collection oW() throws DexDecEvaluationException {
      return Collections.unmodifiableCollection(this.nf.values());
   }

   public INativeLibrary q(bye.CU var1) throws DexDecEvaluationException {
      if (var1 == null) {
         throw new DexDecNativeEvalFailedException("Expected a library information object");
      } else {
         cab.eo var2 = new cab.eo(null, null, var1);
         var2.q();
         return var2.Uv;
      }
   }

   public INativeLibrary q(IELFUnit var1) throws DexDecEvaluationException {
      if (var1 == null) {
         throw new DexDecNativeEvalFailedException("Expected a library unit");
      } else {
         return this.RF(var1);
      }
   }

   private int q(cab.CU var1, MemoryChangesRecorder.Results var2, boolean var3) {
      IVirtualMemory var4 = this.gO.za().getVirtualMemory();
      int var5 = var4.getPageSize();
      byte[] var6 = new byte[var5];
      int var7 = 0;
      IDexDecompilerUnit var8 = this.Dw.getGlobalContext().getDecompiler();
      ArrayList var9 = new ArrayList();
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
               var22 = q(var13, var14, var22);
               if (var22 < 0) {
                  break;
               }

               long var16 = var11 + var22;
               StringEntry var18 = DataStringUtil.getStringAt(var4, var16, var16 + var5, 4, 200);
               if (var18 == null) {
                  var22++;
               } else {
                  String var19 = Strings.ff("@LIB:%s@0x%X", var1.q.getName(), var18.getAddress());
                  String var20 = var18.getString();
                  if (var3) {
                     var8.recordDecompilationEvent(new DexDecompilerEvent.DecryptedString(var20, var19));
                  }

                  String var21 = S.L("Found a decrypted string in native code: %s: %s");
                  RF.info(var21, var19, var20);
                  var22 = Math.max(var22 + var18.getMemorySize(), var22 + 1);
                  var7++;
               }
            }
         }
      }

      return var7;
   }

   private static int q(byte[] var0, byte[] var1, int var2) {
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

   static class CU implements INativeLibrary {
      IELFUnit q;
      long RF;
      Exception xK;

      CU(IELFUnit var1, long var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public IELFUnit getElfUnit() {
         return this.q;
      }

      @Override
      public boolean isBad() {
         return this.xK != null;
      }

      @Override
      public long getMappingAddress() {
         return this.RF;
      }

      @Override
      public String toString() {
         return Strings.ff("%s @ 0x%X [%s]", this.q.getName(), this.getMappingAddress(), this.isBad() ? "KO" : "OK");
      }
   }

   private class eo {
      IDexMethod q;
      List RF;
      bye.CU xK;
      IDImm Dw;
      cab.CU Uv;
      long oW;

      public eo(IDexMethod var2, List var3, bye.CU var4) {
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
      }

      public IDImm q() throws DexDecEvaluationException {
         this.RF();
         if (this.q == null) {
            return null;
         } else {
            this.xK();
            return this.Dw;
         }
      }

      private void RF() throws DexDecEvaluationException {
         IDexDecompilerUnit var1 = cab.this.Dw.getGlobalContext().getDecompiler();
         IDexUnit var2 = var1.getCodeUnit();
         if (!(var2.getParent() instanceof IApkUnit)) {
            throw new DexDecNativeEvalFailedException("Needs an APK to emulate native code");
         } else {
            IApkUnit var3 = (IApkUnit)var2.getParent();
            String var4 = null;
            String[] var5 = null;
            if (this.q != null) {
               var4 = this.q.getSignature(false);
               var5 = DexUtil.generateDefaultJniNames(var4);
            }

            ArrayList var6 = null;
            if (this.xK != null && this.xK.RF) {
               bld var19 = (bld)cab.this.Dw.getEmulatedEnvironment();
               if (var19 != null && var19.xK()) {
                  String var24 = (String)var19.gO().get(this.xK.q);
                  if (var24 != null) {
                     IUnit var28 = var19.xK(false);
                     if (var28 != null) {
                        IUnit var32 = UnitUtil.findFirstChildByName(var28, var24);
                        if (var32 instanceof cjm) {
                           var32 = ((cjm)var32).getOriginal();
                        }

                        if (var32 instanceof IELFUnit) {
                           var6 = new ArrayList();
                           var6.add((IELFUnit)var32);
                        }
                     }
                  }
               }
            } else {
               IUnit var7 = var3.getLibraries();
               if (var7 != null) {
                  label163:
                  for (AndroidPlatformABI var9 : cab.this.Uv) {
                     IUnit var10 = UnitUtil.findFirstChildByName(var7, var9.toString());
                     if (var10 != null) {
                        List var11 = UnitUtil.findDescendantsByType(var10, IELFUnit.class, false);
                        var6 = new ArrayList(var11);
                        if (this.xK != null) {
                           for (IELFUnit var13 : var11) {
                              String var14 = var13.getName();
                              if (var14 != null && var14.equals("lib" + this.xK.q + ".so")) {
                                 var6.clear();
                                 var6.add(0, var13);
                                 break label163;
                              }
                           }
                        }
                        break;
                     }
                  }
               }
            }

            if (var6 == null) {
               IDImm var23 = cab.this.Dw.registerObject(new UnsatisfiedLinkError("Library not found"));
               throw new DexDecEvalCodeThrownException(var23);
            } else {
               if (this.q == null) {
                  if (var6.size() == 1) {
                     IELFUnit var20 = (IELFUnit)var6.get(0);
                     if (!var20.isProcessed()) {
                        synchronized (cab.xK) {
                           if (!var20.isProcessed()) {
                              var20.process();
                           }
                        }
                     }

                     this.Uv = cab.this.RF(var20);
                  }
               } else {
                  IELFUnit var21 = null;
                  Long var26 = null;

                  for (IELFUnit var33 : var6) {
                     if (!var33.isProcessed()) {
                        synchronized (cab.xK) {
                           if (!var33.isProcessed()) {
                              var33.process();
                           }
                        }
                     }

                     ISymbolInformation var37 = CodeObjectUnitUtil.findExportedSymbolByName(var33, var5[0]);
                     if (var37 == null) {
                        var37 = CodeObjectUnitUtil.findExportedSymbolByName(var33, var5[1]);
                     }

                     if (var37 != null) {
                        var21 = var33;
                        var26 = var37.getSymbolRelativeAddress();
                        break;
                     }
                  }

                  if (var26 != null) {
                     if (!(var21.getImageUnit() instanceof INativeCodeUnit)) {
                        throw new DexDecNativeEvalFailedException("SO lib does not contain executable code");
                     }

                     this.Uv = cab.this.RF(var21);
                     this.oW = this.Uv.RF + var26;
                  } else {
                     for (IELFUnit var34 : var6) {
                        if (var34.getImageUnit() instanceof INativeCodeUnit) {
                           cab.CU var38 = cab.this.RF(var34);
                           Long var39 = cab.this.gO.Uv(var4);
                           if (var39 != null) {
                              this.Uv = var38;
                              this.oW = var39;
                              break;
                           }
                        }
                     }
                  }
               }

               if (this.Uv == null && this.q != null) {
                  for (cab.CU var27 : cab.this.nf.values()) {
                     ISymbolInformation var31 = CodeObjectUnitUtil.findExportedSymbolByName(var27.q, var5[0]);
                     if (var31 == null) {
                        var31 = CodeObjectUnitUtil.findExportedSymbolByName(var27.q, var5[1]);
                     }

                     if (var31 != null) {
                        long var35 = var31.getSymbolRelativeAddress();
                        this.Uv = var27;
                        this.oW = this.Uv.RF + var35;
                        break;
                     }
                  }

                  throw new DexDecNativeEvalFailedException("Cannot find target method: " + this.q.getSignature(false) + " in lib: " + this.xK);
               } else if (this.Uv == null) {
                  throw new DexDecNativeEvalFailedException("Cannot find lib: " + this.xK);
               } else if (this.Uv.isBad()) {
                  throw new DexDecNativeEvalFailedException("Cannot use a bad image: " + this.Uv);
               } else {
                  if (var4 != null) {
                     Object[] var10000 = new Object[]{var4, this.Uv, this.oW};
                  }
               }
            }
         }
      }

      private void xK() throws DexDecEvaluationException {
         List var1 = this.RF;
         String var2 = this.q.getSignature(false);
         boolean var3 = this.q.getData().isStatic();
         int var4;
         byte var5;
         if (var3) {
            var4 = cab.this.Dw.getClassReference(this.q.getClassTypeSignature(false)).evaluate(cab.this.Dw).getObjectReferenceId();
            var5 = 0;
         } else {
            var4 = ((IDExpression)var1.get(0)).evaluate(cab.this.Dw).getObjectReferenceId();
            var5 = 1;
         }

         ArrayList var6 = new ArrayList();
         List var7 = this.q.getParameterTypes();
         int var8 = 0;

         for (IDExpression var10 : var1.subList(var5, var1.size())) {
            IDImm var11 = var10.evaluate(cab.this.Dw);
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

         Long var21;
         try {
            var21 = cab.this.gO.q(var2, var3, this.oW, var4, var6);
         } catch (Exception var20) {
            throw new DexDecNativeEvalFailedException("JNI execution failed for: " + var2 + " in image: " + this.Uv, var20);
         }

         int var22 = cab.this.gO.q(true);
         if (var22 != 0) {
            IDImm var24 = cab.this.Dw.getGlobalContext().createRef(var22);
            throw new DexDecEvalCodeThrownException(var24);
         } else {
            if (var21 == null) {
               this.Dw = null;
            } else {
               long var23 = var21;
               IDexType var13 = this.q.getReturnType();
               String var25 = var13.getSignature(false);
               switch (var25) {
                  case "Z":
                     this.Dw = cab.this.Dw.getGlobalContext().createBoolean(var23 != 0L);
                     break;
                  case "B":
                     this.Dw = cab.this.Dw.getGlobalContext().createByte((byte)var23);
                     break;
                  case "C":
                     this.Dw = cab.this.Dw.getGlobalContext().createChar((char)var23);
                     break;
                  case "S":
                     this.Dw = cab.this.Dw.getGlobalContext().createShort((short)var23);
                     break;
                  case "I":
                     this.Dw = cab.this.Dw.getGlobalContext().createInt((int)var23);
                     break;
                  case "J":
                     this.Dw = cab.this.Dw.getGlobalContext().createLong(var23);
                     break;
                  case "F":
                     this.Dw = cab.this.Dw.getGlobalContext().createFloat(Float.intBitsToFloat((int)var23));
                     break;
                  case "D":
                     this.Dw = cab.this.Dw.getGlobalContext().createDouble(Double.longBitsToDouble(var23));
                     break;
                  default:
                     int var17 = (int)var23;
                     if (var17 == 0) {
                        this.Dw = cab.this.Dw.getGlobalContext().createNull();
                     } else {
                        IDGlobalContext var27 = cab.this.Dw.getGlobalContext();
                        IJavaType var19 = var27.getTypeFactory().createType(var25);
                        this.Dw = var27.createRef(var17, var19);
                     }
               }
            }
         }
      }
   }
}
